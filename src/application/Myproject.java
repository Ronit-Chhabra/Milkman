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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Myproject 
{
	Connection con;
    PreparedStatement pst;
	GridPane grid;
	Text txt,txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10,txt11;
	TextField mob,add,cl,cm,bm;
	Button btnAdd,btnDel,btnNew,btnUpd;
	CheckBox ch3 = new CheckBox();
	CheckBox ch4 = new CheckBox();
	RadioButton ch1 = new RadioButton();
	RadioButton ch2 = new RadioButton();
	ToggleGroup tg=new ToggleGroup();
	DatePicker dp;
	String suc="Success";
	String er="Error";
	ComboBox<String> name;
	Myproject()
	{
		connection();
		grid = new GridPane();
		grid.setGridLinesVisible(false);
		grid.setHgap(20);
		grid.setVgap(30);
		grid.setAlignment(Pos.CENTER);
	  
		
	   Text txt1= new Text("CUSTOMER CONSOLE");
	   txt1.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		txt1.setUnderline(true);
	   txt = new Text("Name       :");
		txt2 = new Text("Mobile      :");
		txt3 = new Text("Address      :");
		txt4 = new Text("City/Location      :");
		txt5 = new Text("Milk Type");
		txt5.setUnderline(true);
		txt5.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		txt6 = new Text("Cow Milk");
		txt7 = new Text("Buffalo Milk");
		txt8 = new Text("Timing");
		txt8.setUnderline(true);
		txt8.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		txt9 = new Text("Morning");
		txt10 = new Text("Evening");
		txt11 = new Text("Start Date");
		
		
		name =new ComboBox();
		name.setEditable(true);
		dofilluids();
		
		name.setPromptText("Enter Customer Name");
		name.setMaxWidth(317);
	
		mob =new TextField();
		mob.setPadding(new Insets(5));
		mob.setPromptText("Enter Mobile No.");
		mob.setMaxWidth(317);
		add =new TextField();
		add.setPadding(new Insets(5));
		add.setPromptText("Enter Address");
		add.setMaxWidth(317);
		cl =new TextField();
		cl.setPadding(new Insets(5));
		cl.setPromptText("Enter City/Location");
		cl.setMaxWidth(317);
		cm =new TextField();
		cm.setPadding(new Insets(5));
		cm.setPromptText("quantity(in kgs)");
		cm.setText("0");
		cm.setMaxWidth(317);
		bm =new TextField();
		bm.setPadding(new Insets(5));
		bm.setPromptText("quantity(in kgs)");
		bm.setText("0");
		bm.setMaxWidth(317);
		
		btnNew = new Button("New");
		btnNew.setPrefSize(70, 20);
		btnNew.setAlignment(Pos.CENTER);
		
		btnAdd = new Button("Add");
		btnAdd.setPrefSize(70, 20);
		btnAdd.setAlignment(Pos.CENTER);
		
		btnDel = new Button("Delete");
		btnDel.setPrefSize(70, 20);
		btnDel.setAlignment(Pos.CENTER);
		
		btnUpd = new Button("Update");
		btnUpd.setPrefSize(70, 20);
		btnUpd.setAlignment(Pos.CENTER);
		HBox milkhb=new HBox();
		milkhb.getChildren().add(txt5);
		milkhb.setAlignment(Pos.CENTER);
		HBox timehb =new HBox();
		timehb.getChildren().add(txt8);
		timehb.setAlignment(Pos.CENTER);
		HBox two=new HBox();
		two.setSpacing(100);
		two.getChildren().addAll(ch1,txt9);

		HBox two2=new HBox();
		two2.setSpacing(100);
		two2.getChildren().addAll(ch2,txt10);
		
		HBox two3=new HBox();
		two3.setSpacing(50);
		two3.getChildren().addAll(ch3,txt6,cm);
		
		HBox two4=new HBox();
		two4.setSpacing(50);
		two4.getChildren().addAll(ch4,txt7,bm);
		
		//ch3.setOnAction(e->{
			//boolean b=ch3.isSelected();
			//if(b==true)
			//{
		//		cm.setEditable(true);
			//}
			//else 
			//{
		//		cm.setEditable(false);
			//}
	//	});
		
		ch1.setToggleGroup(tg);
		ch2.setToggleGroup(tg);
		//DATE=================
		Calendar cal=Calendar.getInstance();
		//show current date
		 dp=new DatePicker(LocalDate.of(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,cal.get(Calendar.DATE)));
		
		//dp.setStyle("-fx-font-size:25");
		dp.setMaxWidth(350);
		dp.setPromptText("Select Date");
		VBox vbox=new VBox();
		vbox.setStyle("-fx-background-color:#336699");
		vbox.getChildren().addAll(dp);

		GridPane.setConstraints(txt1, 2, 0, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		
		GridPane.setConstraints(txt, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(txt2, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(txt3, 0, 3, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
        GridPane.setConstraints(txt4, 0, 4, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(two3, 0, 6, 3, 1, HPos.CENTER, VPos.CENTER, null, null, null);
        GridPane.setConstraints(two4, 0, 7, 3, 1, HPos.CENTER, VPos.CENTER, null, null, null);
        GridPane.setConstraints(two, 0, 9, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
    	GridPane.setConstraints(two2, 0, 10, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(txt11, 0, 11, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(vbox, 1, 11, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		
		grid.getChildren().addAll(txt,txt1,txt2,txt3,txt4,two3,two4,two,two2,txt11);
		grid.add(milkhb, 0, 5,1,1);
		grid.add(timehb, 0,8 ,1,1);
		grid.add(name, 2,1);
		grid.add(mob, 2,2);
		grid.add(add, 2,3);
		grid.add(cl, 2,4);
		
		HBox hboxBtns = new HBox();
		hboxBtns.setSpacing(20);
		hboxBtns.getChildren().addAll(btnNew,btnAdd,btnDel,btnUpd);
		
		
		//---------------------EVENT------------------------
		
		btnAdd.setOnAction(e->{
			
				doSave();
		});
		
		
		btnDel.setOnAction(e->dodelete());
		
		name.getSelectionModel().selectedItemProperty().addListener((property,oldvalue,newvalue)->dosrch());
		
		
		btnNew.setOnAction(e->doclear());
		btnUpd.setOnAction(e->doupdate());
		
		grid.setStyle("-fx-background-color:skyblue");
        grid.add(hboxBtns, 2, 12);
		grid.add(vbox, 2, 11);
		Stage stage = new Stage();
		stage.setTitle("Customer" );
		Scene scene = new Scene(grid, 900,900);
		stage.setScene(scene);
		
		stage.show();
	
	}
	void connection()
    {
    	
    	
    	{ 
    	try {
    	Class.forName("com.mysql.jdbc.Driver");
    	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1","root","ronit");
    	
    	} 
    	catch (Exception e)
    	{
    	e.printStackTrace();
    	}
    	}
    	

    	}
	void doSave() 
	{
		
		
	//? is in parameter
	try {
		pst=con.prepareStatement("insert into customers values(?,?,?,?,?,?,?,?)");
		pst.setString(1,name.getSelectionModel().getSelectedItem());
		pst.setString(2, mob.getText());
		pst.setString(3, add.getText());
		pst.setString(4, cl.getText());
		
		
		pst.setFloat(5,Float.parseFloat(cm.getText()));
		pst.setFloat(6, Float.parseFloat(bm.getText()));
		if(ch1.isSelected())
		{
			pst.setString(7,"Morning");
				
		}
		else
		{
			pst.setString(7, "Evening");
		}
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date dosObj;
		try {
			dosObj=format.parse(dp.getEditor().getText());
			java.sql.Date sqlDate= new java.sql.Date(dosObj.getTime());
			pst.setDate(8, sqlDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = pst.executeUpdate();
		if(i==1)
		{
			showmsg("Successfully registered",suc);
			
		}
		else
		{
			showmsg("error occured",er);
		}
		pst.close(); 	
	
	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		showmsg("enter full data",er);
	}
	System.out.println(dp.getEditor().getText());
	
	}
	void dodelete()
	{
		try {
			pst = con.prepareStatement("delete from customers where cname=?");
			pst.setString(1, name.getSelectionModel().getSelectedItem());
			int i=pst.executeUpdate();
			if(i==1)
			{
				showmsg("record deleted",suc);
				dofilluids();
				name.getEditor().clear();
				mob.clear();
				add.clear();
				cl.clear();
				ch1.setSelected(false);
				ch2.setSelected(false);
				ch3.setSelected(false);
				ch4.setSelected(false);
				cm.setText("0");
				bm.setText("0");
				Calendar cal=Calendar.getInstance();
				dp.setValue(LocalDate.of(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1 , cal.get(Calendar.DATE)));
				
			}
			else
			{
				showmsg("invalid uid",er);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	void doclear()
	{
		name.getEditor().clear();;
		mob.clear();
		add.clear();
		cl.clear();
		ch1.setSelected(false);
		ch2.setSelected(false);
		ch3.setSelected(false);
		ch4.setSelected(false);
		cm.setText("0");
		bm.setText("0");
		Calendar cal=Calendar.getInstance();
		dp.setValue(LocalDate.of(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1 , cal.get(Calendar.DATE)));
		
	}
	void showmsg(String msg,String hdr)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("title");
		alert.setHeaderText(hdr);
		alert.setContentText(msg);
		alert.show();
	}

   void dofilluids()
   {
	   ArrayList<String> al=new ArrayList<String>();
	   try {
		pst=con.prepareStatement("select distinct cname from customers");
		ResultSet rs=pst.executeQuery();
		   while(rs.next())
		   {
			   al.add(rs.getString("cname"));
			   
		   }
		   name.getItems().clear();
		   name.getItems().addAll(al);
	   } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   }
    void dosrch()
    {
    	try {
			pst= con.prepareStatement("select * from customers where cname = ?");
			pst.setString(1, name.getSelectionModel().getSelectedItem());
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				btnUpd.setDisable(false);
				mob.setText(rs.getString("mobile"));
				add.setText(rs.getString("address"));
				cl.setText(rs.getString("city"));
				ch1.setSelected(false);
				ch2.setSelected(false);
				ch3.setSelected(false);
				ch4.setSelected(false);
				cm.setText("0");
				bm.setText("0");
                if(rs.getFloat("cowqty")>0)
                {
                	ch3.setSelected(true);
                    cm.setText(String.valueOf(rs.getFloat("cowqty")));               
                 }
                if(rs.getFloat("bufqty")>0)
                {
                	ch4.setSelected(true);
                	bm.setText(String.valueOf(rs.getFloat("bufqty")));
                }
                if(rs.getString("time").equals("Morning"))
                {
                	ch1.setSelected(true);
                }
                else
                	ch2.setSelected(true);
                
                LocalDate l= rs.getDate("dos").toLocalDate();
                dp.setValue(l);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    void doupdate()
    {
    	try {
			pst = con.prepareStatement("update customers set Mobile=?,address=?,city=?,cowqty=?,bufqty=?,time=?,dos=? where cname=?");
			pst.setString(1, mob.getText());
			pst.setString(2, add.getText());
			pst.setString(3, cl.getText());
			
			
			pst.setFloat(4,Float.parseFloat(cm.getText()));
			pst.setFloat(5, Float.parseFloat(bm.getText()));
			if(ch1.isSelected())
			{
				pst.setString(6,"Morning");
					
			}
			else
			{
				pst.setString(6, "Evening");
			}
			DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			Date dosObj;
			try {
				dosObj=format.parse(dp.getEditor().getText());
				java.sql.Date sqlDate= new java.sql.Date(dosObj.getTime());
				pst.setDate(7, sqlDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pst.setString(8,name.getSelectionModel().getSelectedItem());
			int i =pst.executeUpdate();
			if(i==-1)
			{
				showmsg("Error occured",er);
			}
			else
				showmsg("updated successfully",suc);
    	
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
