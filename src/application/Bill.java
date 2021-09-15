package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Bill 
{
	
	

	Label lblBill,lblName,lblMob,lblpriceC,lblpriceB,lblqtyC,lblqtyB,lbltotC,lbltotB,lblTot,lblStDate,lblEndDate;
	TextField mob,fpriceC,fpriceB,fqtyC,fqtyB,ftotC,ftotB,ftot;
	ComboBox<String> name;
	ArrayList<String> srch = new ArrayList<String>();
	Button bTot,bBill,bSndSms;
	Float qtyC,qtyB,priceC=(float) 50,priceB=(float) 40;
	Float totC,totB,tot;
	DatePicker sd,ed;
	Calendar cal = Calendar.getInstance();
	
	Connection con;
	PreparedStatement pst;

	Bill()
	{
		estCon();
		
		GridPane grid = new GridPane();
		grid.setGridLinesVisible(false);
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setAlignment(Pos.CENTER);
		
		lblBill = new Label("Bill Generation");
		lblBill.setUnderline(true);
		lblBill.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		
		sd = new DatePicker(LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DATE)));
		ed = new DatePicker(LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DATE)));
		
		lblName = new Label("Name");
		lblMob = new Label("Mobile No.");
		lblpriceC = new Label("Cow Price");
		lblpriceB = new Label("Buffalo Price");
		lblqtyC = new Label("Cow Qty");
		lblqtyB = new Label("Buffalo Qty.");
		lbltotC = new Label("Total Cow");
		lbltotB = new Label("Total Buffalo");
		lblTot = new Label("Total");
		lblStDate = new Label("Start Date");
		lblEndDate = new Label("End Date");
		
		name = new ComboBox<String>();
		name.setEditable(true);
		//name.setPadding(new Insets(5));
		name.setVisibleRowCount(3);
		name.setStyle(null);
		doFillUids();
		
		mob = new TextField();
		mob.setPadding(new Insets(5));
		mob.setEditable(false);
		
		fpriceC = new TextField();
		fpriceC.setPadding(new Insets(5));
		fpriceC.setEditable(false);
		fpriceC.setMaxWidth(40);
		fpriceC.setText(String.valueOf(priceC));
		
		fpriceB = new TextField();
		fpriceB.setPadding(new Insets(5));
		fpriceB.setEditable(false);
		fpriceB.setMaxWidth(40);
		fpriceB.setText(String.valueOf(priceB));
		
		fqtyC = new TextField();
		fqtyC.setPadding(new Insets(5));
		fqtyC.setEditable(false);
		fqtyC.setMaxWidth(40);
		
		fqtyB = new TextField();
		fqtyB.setPadding(new Insets(5));
		fqtyB.setEditable(false);
		fqtyB.setMaxWidth(40);
		
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
		
		bTot = new Button("Total");
		bTot.setPrefSize(70, 30);
		bTot.setDisable(true);
		
		bBill = new Button("Bill");
		bBill.setPrefSize(70, 30);
		bBill.setDisable(true);
		
		bSndSms = new Button("Send SMS");
		bSndSms.setPrefSize(70, 30);
	bSndSms.setDisable(true);
		
//		GridPane gridPriceC = new GridPane();
//		GridPane.setConstraints(lblpriceC, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
//		GridPane.setConstraints(fpriceC, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
//		gridPriceC.getChildren().addAll(lblpriceC, fpriceC);
//		gridPriceC.setVgap(10);
//		
//		GridPane gridPriceB = new GridPane();
//		GridPane.setConstraints(lblpriceB, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
//		GridPane.setConstraints(fpriceB, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
//		gridPriceB.getChildren().addAll(lblpriceB, fpriceB);
//		gridPriceB.setHgap(10);
//		
		HBox Cprice = new HBox();
		Cprice.getChildren().add(lblpriceC);
		Cprice.setAlignment(Pos.CENTER);
		
		HBox Bprice = new HBox();
		Bprice.getChildren().add(lblpriceB);
		Bprice.setAlignment(Pos.CENTER);
		
		HBox price = new HBox();
		price.getChildren().addAll(Cprice,fpriceC,Bprice,fpriceB);
		price.setSpacing(10);
		price.setAlignment(Pos.CENTER);
		
		HBox CQty = new HBox();
		CQty.getChildren().add(lblqtyC);
		CQty.setAlignment(Pos.CENTER);
		
		HBox BQty = new HBox();
		BQty.getChildren().add(lblqtyB);
		BQty.setAlignment(Pos.CENTER);
		
		HBox Qty = new HBox();
		Qty.getChildren().addAll(CQty,fqtyC,BQty,fqtyB);
		Qty.setSpacing(10);
		Qty.setAlignment(Pos.CENTER);
		
		HBox CTot = new HBox();
		CTot.getChildren().add(lbltotC);
		CTot.setAlignment(Pos.CENTER);
		
		HBox BTot = new HBox();
		BTot.getChildren().add(lbltotB);
		BTot.setAlignment(Pos.CENTER);
		
		HBox Tot = new HBox();
		Tot.getChildren().addAll(CTot,ftotC,BTot,ftotB);
		Tot.setSpacing(10);
		Tot.setAlignment(Pos.CENTER);
		
		
		//----------------------EVENTS-=========================
		
		bTot.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				doTotal();
			}
		});
		
		bBill.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				doBill();
			}
		});
		
		name.getSelectionModel().selectedItemProperty().addListener((property,oldvalue,newvalue)->doSrch());
		
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
		
		
		
		//Add to grid
		Separator sp = new Separator();
		Separator sp2 = new Separator();
		GridPane.setConstraints(lblBill, 0, 0, 3, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(lblStDate, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(sd, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(lblEndDate, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(ed, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(lblName, 0, 3, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(name, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(lblMob, 0, 4, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(mob, 1, 4, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(price, 0, 5, 3, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(bTot, 0, 6, 3, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(sp, 0, 7, 3, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(Qty, 0, 8, 3, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(bBill, 0, 9, 3, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(sp2, 0, 10, 3, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(Tot, 0, 11, 3, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(lblTot, 0, 12, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(ftot, 1, 12, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(bSndSms, 2, 12, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		
		grid.getChildren().addAll(lblBill,lblStDate,sd,lblEndDate,ed,lblName,name,lblMob,mob,price,bTot,sp,Qty,bBill,sp2,Tot,lblTot,ftot,bSndSms);
		
		Stage stage = new Stage();
		stage.setScene(new Scene(grid,550,600));
		stage.show();
	}
	
	void estCon(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Right side bridge");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdata", "root", "ronit");
			System.out.println("Connection Established");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	void doSrch(){
		try {
			bBill.setDisable(true);
			bTot.setDisable(true);
			bSndSms.setDisable(true);
			pst = con.prepareStatement("select Mobile from customers where cname = ?");
			pst.setString(1, name.getSelectionModel().getSelectedItem());
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
			bBill.setDisable(false);
			bTot.setDisable(false);
			bSndSms.setDisable(false);
			mob.setText(rs.getString("Mobile"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	void doTotal(){
		try {
			pst = con.prepareStatement("select * from dailyrecord where cname = ? and date between ? and ?");
			pst.setString(1, name.getSelectionModel().getSelectedItem());
			
			//Convert date to sql format
			DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			Date sdObj,edObj;
			
			try {
				sdObj = format.parse(sd.getEditor().getText());
				edObj = format.parse(ed.getEditor().getText());
				java.sql.Date sdDate = new java.sql.Date(sdObj.getTime());
				java.sql.Date edDate = new java.sql.Date(edObj.getTime());
				
				pst.setDate(2, sdDate);
				pst.setDate(3, edDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ResultSet rs = pst.executeQuery();
			
			qtyC = (float) 0;
			qtyB = (float) 0;
			while(rs.next()){
				qtyC =qtyC + rs.getFloat("cowqty");
				qtyB =qtyB + rs.getFloat("bufqty");
			    //System.out.println(rs.getFloat("cowqty"));
			}
			fqtyC.setText(String.valueOf(qtyC));
			fqtyB.setText(String.valueOf(qtyB));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void doBill(){
		totC = (float) 0; totB = (float) 0; tot = (float) 0;
		totC = (float)priceC*Float.parseFloat(fqtyC.getText());
		totB = (float)priceB*Float.parseFloat(fqtyB.getText());
		ftotC.setText(String.valueOf(totC));
		ftotB.setText(String.valueOf(totB));
		tot =(float) totC + totB;
		ftot.setText(String.valueOf(tot));
		saveBill();
 	}

	void saveBill(){
		try {
			pst = con.prepareStatement("insert into bills values(?,?,?,?,?,?,?,?,?)");
			pst.setString(1, name.getSelectionModel().getSelectedItem());
			pst.setFloat(4, qtyC);
			pst.setFloat(5, qtyB);
			pst.setFloat(6, totC);
			pst.setFloat(7, totB);
			pst.setFloat(8, tot);
			pst.setInt(9, 0);
			
			DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			Date sdObj,edObj;
			
			try {
				sdObj = format.parse(sd.getEditor().getText());
				edObj = format.parse(ed.getEditor().getText());
				java.sql.Date sdDate = new java.sql.Date(sdObj.getTime());
				java.sql.Date edDate = new java.sql.Date(edObj.getTime());
				
				pst.setDate(2, sdDate);
				pst.setDate(3, edDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void doFillUids(){
		ArrayList<String> ary = new ArrayList<String>();
		try {
			pst = con.prepareStatement("select distinct cname from dailyrecord");
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				ary.add(rs.getString("cname"));
			}
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
			pst = con.prepareStatement("select cname from dailyrecord where cname like ? order by cname asc" );
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
	
	
	void showMsg(String msg, String hdr){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Title");
		alert.setHeaderText(hdr);
		alert.setContentText(msg);
		alert.show();
	}

	
	
		
	}


