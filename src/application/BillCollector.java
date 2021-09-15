package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class BillCollector 
{

	
	
	
	ComboBox<Integer> month,year;
	ComboBox<String> name;
	ArrayList<String> srch = new ArrayList<String>();
	Button bFtch,bUpd;
	Label lblBC,lbltotC,lbltotB,lblTot,lblName,lblm,lbly;
	TextField ftotC,ftotB,ftot;
	String er = "Error", suc = "Success";
	
	Connection con;
	PreparedStatement pst;

	BillCollector()
	{
		estCon();
		
		ArrayList<Integer> m = new ArrayList<>();
		for(int i=1;i<13;i++)
			m.add(i);
		month = new ComboBox<Integer>();
		month.getItems().addAll(m);
		month.getSelectionModel().select(0);
		
		year = new ComboBox<Integer>();
		year.getItems().addAll(2016,2017);
		year.getSelectionModel().select(1);
		
		name = new ComboBox<String>();
		name.setVisibleRowCount(3);
		name.setEditable(true);
		name.setDisable(true);
		
		lblBC = new Label("Bill Collector");
		lblBC.setUnderline(true);
		lblBC.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		
		lbltotC = new Label("Total Cow");
		lbltotB = new Label("Total Buffalo");
		lblTot = new Label("Total");
		lblm = new Label("Month");
		lbly = new Label("Year");
		lblName = new Label("Name");
		
		ftotC = new TextField();
		ftotC.setPadding(new Insets(5));
		ftotC.setEditable(false);
		ftotC.setMaxWidth(40);    
		
		ftotB = new TextField();
		ftotB.setPadding(new Insets(5));
		ftotB.setEditable(false);
		ftotB.setMaxWidth(40);
		
		ftot = new TextField();
		ftot.setPadding(new Insets(5));
		ftot.setEditable(false);
		
		bFtch = new Button("Fetch");
		bUpd = new Button("Pay Bill");
		bUpd.setDisable(true);
		
		HBox hb = new HBox();
		hb.getChildren().add(lblBC);
		hb.setSpacing(20);
		hb.setAlignment(Pos.CENTER);
		
		HBox hb1 = new HBox();
		hb1.getChildren().addAll(lblm,month,lbly,year,bFtch);
		hb1.setSpacing(20);
		hb1.setAlignment(Pos.CENTER);
		
		HBox hb2 = new HBox();
		hb2.getChildren().addAll(lblName,name);
		hb2.setSpacing(20);
		hb2.setAlignment(Pos.CENTER);
		
		HBox hb3 = new HBox();
		hb3.getChildren().addAll(lbltotC,ftotC,lbltotB,ftotB);
		hb3.setSpacing(20);
		hb3.setAlignment(Pos.CENTER);
		
		HBox hb4 = new HBox();
		hb4.getChildren().addAll(lblTot,ftot);
		hb4.setSpacing(20);
		hb4.setAlignment(Pos.CENTER);
		
		VBox vb = new VBox();
		vb.getChildren().addAll(hb,hb1,hb2,hb3,hb4,bUpd);
		vb.setSpacing(20);
		vb.setAlignment(Pos.CENTER);
		
		Stage stage = new Stage();
		stage.setScene(new Scene(vb,500,500));
		stage.show();

		
		//-----------------EVENTS==================================
		
		name.getSelectionModel().selectedItemProperty().addListener((property,oldvalue,newvalue)->doGetBill());
		
		name.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				name.show();
				if(name.getEditor().getText().isEmpty())
					{
						doFillUids();
					}
				else
					GoogleSrch();
			}
		});
		
		bFtch.setOnAction(e->doFillUids());

		bUpd.setOnAction(e->doUpd());
	}

	
	void estCon(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdata","root","ronit");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void showMsg(String msg, String hdr){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Title");
		alert.setHeaderText(hdr);
		alert.setContentText(msg);
		alert.show();
	}
	
	void doGetBill(){
		try {
			pst = con.prepareStatement("select cTot,bTot,tot from bills where cname = ? and month(sdate) = ? and year(sdate) = ? and status = 0");
			pst.setString(1, name.getSelectionModel().getSelectedItem());
			pst.setInt(2, month.getSelectionModel().getSelectedItem());
			pst.setInt(3, year.getSelectionModel().getSelectedItem());
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()){
				bUpd.setDisable(false);
				ftotC.setText(String.valueOf(rs.getFloat("cTot")));
				ftotB.setText(String.valueOf(rs.getFloat("bTot")));
				ftot.setText(String.valueOf(rs.getFloat("tot")));
				
		}
			else
				bUpd.setDisable(true);
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void doFillUids(){
		ArrayList<String> ary = new ArrayList<String>();
		try {
			pst = con.prepareStatement("select distinct cname from bills where month(sdate) = ? and year(sdate) = ? and status = 0");
			pst.setInt(1, month.getSelectionModel().getSelectedItem());
			pst.setInt(2, year.getSelectionModel().getSelectedItem());
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
				name.setDisable(false);
			rs.previous();
			while(rs.next()){
				ary.add(rs.getString("cname"));
			}
			//name.setDisable(false);
			name.getItems().clear();
			name.getItems().addAll(ary);
			//txtUid.getItems().add(0, "Select");
			//txtUid.getSelectionModel().select(0);
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void GoogleSrch(){
		try {
			pst = con.prepareStatement("select cname from bills where cname like ? and status = 0 order by cname asc" );
			pst.setString(1, name.getEditor().getText()+"%");
			ResultSet rs = pst.executeQuery();
			srch.clear();
			if(rs.next()){
				rs.previous();
			while(rs.next()){
				srch.add(rs.getString("cname"));
			}
			name.getItems().clear();
			name.show();
			name.getItems().addAll(srch);
			//name.setVisibleRowCount(srch.size());
			}
			else{
				name.getItems().clear();
				name.hide();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Hello");
		//System.out.println(txtUid.getValue());
		//System.out.println(txtUid.getEditor().getText());
	}

	void doUpd(){
		try {
			pst = con.prepareStatement("update bills set status = 1 where cname =? and month(sdate) = ? and year(sdate) = ? and status = 0");
			pst.setString(1, name.getSelectionModel().getSelectedItem());
			pst.setInt(2, month.getSelectionModel().getSelectedItem());
			pst.setInt(3, year.getSelectionModel().getSelectedItem());
			
			int i = pst.executeUpdate();
			if(i==-1){
				showMsg("Error occurred....", er);
			}
			else if(i==1){
				showMsg("Updated Successfully",suc);
			    name.getEditor().clear();
			    doFillUids();
			    ftot.clear();
			    ftotB.clear();
			    ftotC.clear();
			}
			else
				showMsg("Problem", er);
			
			//close
			pst.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
		
	}


