package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class TabBill 
{

	
	
	ComboBox<Integer> month,year;
	RadioButton fp,bal;
	Button bFtch,bFftchAll,bPP,bExcel;
	Label lblm,lbly,lblBills;
	Connection con;
	PreparedStatement pst;
	VBox vb;
	ObservableList<TCBill> ary2 = FXCollections.observableArrayList();
	
	TableView<TCBill> tbl;
	@SuppressWarnings("unchecked")
	
	TabBill()
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
		
		fp = new RadioButton("Fully Paid");
		bal = new RadioButton("Balance");
		
		ToggleGroup group = new ToggleGroup();
		fp.setToggleGroup(group);
		bal.setToggleGroup(group);
		
		bFtch = new Button("Fetch");
		bFftchAll = new Button("Fetch All");
		bPP = new Button("Pending Payments");
		bExcel = new Button("Export to Excel");
		bExcel.setDisable(true);
		
		Separator sep = new Separator();
		
		lblm = new Label("Month");
		lbly = new Label("Year");
		
		lblBills = new Label("Bills");
		lblBills.setUnderline(true);
		lblBills.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		
		HBox hb1 = new HBox();
		hb1.getChildren().addAll(lblm,month,lbly,year);
		hb1.setSpacing(20);
		hb1.setAlignment(Pos.CENTER);
		
		HBox hb2 = new HBox();
		hb2.getChildren().addAll(fp,bal,bFtch);
		hb2.setSpacing(20);
		hb2.setAlignment(Pos.CENTER);
		
		HBox hb3 = new HBox();
		hb3.getChildren().addAll(bFftchAll,bPP,bExcel);
		hb3.setSpacing(20);
		hb3.setAlignment(Pos.CENTER);
		
		tbl = new TableView<TCBill>();
		TableColumn<TCBill, String> col_name = new TableColumn<TCBill, String>("Name");
		col_name.setMinWidth(100);
		col_name.setCellValueFactory(new PropertyValueFactory<>("cname"));
		
		TableColumn<TCBill, String> col_sd = new TableColumn<TCBill, String>("Start Date");
		col_sd.setMinWidth(100);
		col_sd.setCellValueFactory(new PropertyValueFactory<>("sd"));
		
		TableColumn<TCBill, String> col_ed = new TableColumn<TCBill, String>("End Date");
		col_ed.setMinWidth(100);
		col_ed.setCellValueFactory(new PropertyValueFactory<>("ed"));
		
		TableColumn<TCBill, Float> col_tot = new TableColumn<TCBill, Float>("Total");
		col_tot.setMinWidth(100);
		col_tot.setCellValueFactory(new PropertyValueFactory<>("tot"));
		
		TableColumn<TCBill, String> col_status = new TableColumn<TCBill, String>("Status");
		col_status.setMinWidth(100);
		col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
		
		tbl.getColumns().addAll(col_name,col_sd,col_ed,col_tot,col_status);
		
		
		
		vb = new VBox();
		vb.getChildren().addAll(lblBills,hb1,hb2,sep,hb3,tbl); 
		vb.setSpacing(20);
		vb.setAlignment(Pos.CENTER);
		VBox.setMargin(hb1, new Insets(20,0,0,0));
		
		Stage stage = new Stage();
		stage.setScene(new Scene(vb,500,500));
		stage.show();
		
		//------------------EVENTS============================
		
bExcel.setOnAction(e->writeExcel());
		
		bFtch.setOnAction(e->{
			//System.out.println("Hello");
			if(fp.isSelected())
				tbl.setItems(doFtchPaid());
			else if(bal.isSelected()){
				tbl.setItems(doFtchBal());
			}
		});
		
		bFftchAll.setOnAction(e->{
				
			tbl.setItems(doFtchAll());
		});
		
		bPP.setOnAction(e->{
			tbl.setItems(doFtchPP());
		});


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

	ObservableList<TCBill> doFtchPaid(){
		
		ObservableList<TCBill> ary = FXCollections.observableArrayList();
		try {
			pst = con.prepareStatement("select * from bills where month(sdate) = ? and year(sdate) = ? and status = 1");
			pst.setInt(1, month.getSelectionModel().getSelectedItem());
			pst.setInt(2, year.getSelectionModel().getSelectedItem());
			ResultSet rs = pst.executeQuery();
			
			//For export to Excel button
			forBExcel(rs);
			
			while(rs.next()){
				ary.add(new TCBill(rs.getString("cname"), rs.getDate("sdate").toString(), rs.getDate("edate").toString(), rs.getFloat("tot"), 1));
				//System.out.println(rs.getString("cname"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ary.size());
		return ary;
	}
	
	ObservableList<TCBill> doFtchBal(){
		ObservableList<TCBill> ary3 = FXCollections.observableArrayList();
		try {
			pst = con.prepareStatement("select * from bills where month(sdate) = ? and year(sdate) = ? and status = 0");
			pst.setInt(1, month.getSelectionModel().getSelectedItem());
			pst.setInt(2, year.getSelectionModel().getSelectedItem());
			ResultSet rs = pst.executeQuery();
			
			//For export to Excel button
			forBExcel(rs);
			
			while(rs.next()){
				ary3.add(new TCBill(rs.getString("cname"), rs.getDate("sdate").toString(), rs.getDate("edate").toString(), rs.getFloat("tot"), 0));
				//System.out.println(rs.getString("cname"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ary3.size());
		return ary3;
	}
	
	ObservableList<TCBill> doFtchPP(){
		ObservableList<TCBill> ary2 = FXCollections.observableArrayList();
		try {
			pst = con.prepareStatement("select * from bills where status = 0");
			ResultSet rs = pst.executeQuery();
			
			//For export to Excel button
			forBExcel(rs);
			
			while(rs.next()){
				ary2.add(new TCBill(rs.getString("cname"), rs.getDate("sdate").toString(), rs.getDate("edate").toString(), rs.getFloat("tot"), 0));
				System.out.println(rs.getString("cname"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ary2.size());
		return ary2;
	}

	ObservableList<TCBill> doFtchAll(){
		//tbl.getColumns().clear();
		ObservableList<TCBill> ary = FXCollections.observableArrayList();
		
		try {
			pst = con.prepareStatement("select * from bills");
			ResultSet rs = pst.executeQuery();
			
			//For export to Excel button
			forBExcel(rs);
			
			while(rs.next()){
				ary.add(new TCBill(rs.getString("cname"), rs.getDate("sdate").toString(), rs.getDate("edate").toString(), rs.getFloat("tot"), rs.getInt("status")));
				System.out.println(rs.getString("cname"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ary.size());
		return ary;
	}
	
	void forBExcel(ResultSet rs){
		try {
			if(rs.next())
				bExcel.setDisable(false);
			else
				bExcel.setDisable(true);
			rs.previous();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void writeExcel(){
		Writer writer = null;
		File file = new File("D:\\Payments.csv");
		String text = "Customer Name, Bill Amount\n";
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(text);
			for(TCBill t : ary2){
				text = t.getCname() + "," + t.getTot() + "\n";
				writer.write(text);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

		
	


}
