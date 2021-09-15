package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Daily  
{


	Button pe,p;
	Text cow,buf;
	TextField cq,bq;
	ListView<String> cust=new ListView<String>();
	ArrayList<String> cnames,list2;
	Connection con;
	PreparedStatement pst;
	Daily() 
	{
		connection();
		fillname();
		cust.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	    
	GridPane grid=new GridPane();
	grid.setGridLinesVisible(false);
	grid.setHgap(20);
	grid.setVgap(30);
	grid.setAlignment(Pos.CENTER);

		//cust=new ListView<String>();
		cust.setPrefSize(200, 300);
		pe =new Button("Post Entry");
		pe.setStyle("-fx-border-color : blue;-fx-border-width:1.5");
		
		
		p=new Button("Post");
		p.setStyle("-fx-border-color : blue;-fx-border-width:1.5");
		cow=new Text("Cow Milk");
		cow.setUnderline(true);
		buf=new Text("Buffalo Milk");
		buf.setUnderline(true);
		cq=new TextField();
		cq.setPromptText("Quantity(in kgs)");
		bq=new TextField();
		bq.setPromptText("Quantity(in kgs)");
		
		
		
		
		HBox two = new HBox();
		two.setSpacing(150);
		two.getChildren().addAll(cow,buf);
		
		HBox thr = new HBox();
		thr.setSpacing(20);
		thr.getChildren().addAll(cq,bq);

		VBox vbox =new VBox();
		vbox.setSpacing(30);
		vbox.getChildren().addAll(pe,two,thr,p);   
		vbox.setAlignment(Pos.CENTER);
		
		HBox one =new HBox();
		one.setSpacing(50);
		one.getChildren().addAll(cust,vbox);
		
		GridPane.setConstraints(one, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		grid.getChildren().addAll(one);
		
	//	----------------EVENTS------------
		
		pe.setOnAction(e->dopostentry());
		
		cust.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if(event.getButton().equals(MouseButton.PRIMARY)){
					if(event.getClickCount()==2){
						getqty();
					}
				}
			}
		});
		p.setOnAction(e->doupdatedentry());
		
		Scene scene = new Scene(grid, 800,600);
		Stage stage =new Stage();
		stage.setScene(scene);
		grid.setStyle("-fx-background-color:skyblue");
        
		stage.show();
	
		
	}
   void fillname()
   {
	   cnames= new ArrayList<String>();
	   try {
		pst = con.prepareStatement("select cname from customers");
		ResultSet rs= pst.executeQuery();
		while(rs.next())
		{
			cnames.add(rs.getString("cname"));
			System.out.println(rs.getString("cname"));
		}
		cust.getItems().addAll(cnames);
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
   }
   void connection()
   {
   	
   	
   	{ 
   	try {
   	Class.forName("com.mysql.jdbc.Driver");
   	System.out.println("done");
   	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdata","root","ronit");
   	System.out.println("okee");
   	} 
   	catch (Exception e)
   	{
   	e.printStackTrace();
   	}
   	}
   	

   	}

   void dopostentry()
   {
	   list2=new ArrayList<String>();
	   for(int i=0;i<cnames.size();i++)
	   {
		   if(cust.getSelectionModel().isSelected(i)==false)
		   {
			  list2.add(cust.getItems().get(i));
		   }
	   }
	   
		   try {
			   for(int j=0;j<list2.size();j++)
			   {
			pst=con.prepareStatement("select cname,cowqty,bufqty from customers where cname=?");
		    pst.setString(1,list2.get(j));
		    ResultSet rs = pst.executeQuery();
		    dosave(rs);
		    pst.close();
			   }} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
   void dosave(ResultSet rs)
   {
	 try {
		pst=con.prepareStatement("insert into dailyrecord values(?,?,?,curdate())");
		rs.next();
		pst.setString(1,rs.getString("cname"));
		pst.setFloat(2, rs.getFloat("cowqty"));
		pst.setFloat(3,rs.getFloat("bufqty"));
		pst.executeUpdate();
		pst.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
   }
   void getqty()
   {
	   String name= cust.getSelectionModel().getSelectedItem();
	   try {
		pst=con.prepareStatement("select cname,cowqty,bufqty from customers where cname=?");
		pst.setString(1,name);
		ResultSet rs =pst.executeQuery();
		rs.next();
		cq.setText(String.valueOf(rs.getFloat("cowqty")));
		bq.setText(String.valueOf(rs.getFloat("bufqty")));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
   void doupdatedentry()
   {
	   String name= cust.getSelectionModel().getSelectedItem();
	   try {
		pst=con.prepareStatement("insert into dailyrecord values(?,?,?,curdate())");
		pst.setString(1,name);
		pst.setFloat(2, Float.parseFloat(cq.getText()));
		pst.setFloat(3, Float.parseFloat(bq.getText()));
		pst.executeUpdate();
	   } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
   }
}
