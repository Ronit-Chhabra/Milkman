package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Google 
{
   
	Text ar,go;
	Button fe,all,goo;
	TextField sea;
	ComboBox<String> area=new ComboBox<String>();
	TableView<TCCustomers> table;
	Connection con;
	PreparedStatement pst;
	
	
		
		Google()
		   {
			connection();
		GridPane grid=new GridPane();
		grid.setGridLinesVisible(false);
		grid.setHgap(20);
		grid.setVgap(30);
		grid.setAlignment(Pos.CENTER);
        table =new TableView();
        TableColumn<TCCustomers,String> col_name=new TableColumn<TCCustomers,String>("Name");
        col_name.setMinWidth(100);
        col_name.setCellValueFactory(new PropertyValueFactory<>("cname"));
       
        TableColumn<TCCustomers,String> col_mn=new TableColumn<TCCustomers,String>("Mobile Number");
        col_mn.setMinWidth(100);
        col_mn.setCellValueFactory(new PropertyValueFactory<>("mob"));
       
        TableColumn<TCCustomers,String> col_adr=new TableColumn<TCCustomers,String>("Address");
        col_adr.setMinWidth(100);
        col_adr.setCellValueFactory(new PropertyValueFactory<>("adr"));
       
        TableColumn<TCCustomers,String> col_loc=new TableColumn<TCCustomers,String>("Locality");
        col_loc.setMinWidth(100);
        col_loc.setCellValueFactory(new PropertyValueFactory<>("city"));
        TableColumn<TCCustomers,String> col_dos=new TableColumn<TCCustomers,String>("Date Of Start");
        col_dos.setMinWidth(100);
        col_dos.setCellValueFactory(new PropertyValueFactory<>("dos"));
       
        table.getColumns().addAll(col_name,col_mn,col_adr,col_loc,col_dos);
        
        
		ar=new Text("Area");
		go=new Text("Googler");
		fe=new Button("Fetch");
		all=new Button("All Customer");
		goo= new Button("Google");
		sea=new TextField();
		sea.setPromptText("Search here");
		//area=new ComboBox<String>();
		fillloc();
		
		fe.setOnAction(e->{
	        	table.setItems(getrows());
	        }
	        );
		all.setOnAction(e->{
			table.setItems(getallrows());
		});
		goo.setOnAction(e->{
			if(!sea.getText().equals(""))
				
			table.setItems(getgoorows());
			else
				table.setItems(null);
		});
		HBox one=new HBox();
		one.setSpacing(70);
		one.getChildren().addAll(ar,area,fe,all);
		HBox two=new HBox();
		two.setSpacing(50);
		two.getChildren().addAll(go,sea,goo);
		GridPane.setConstraints(one, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(two, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(table, 0, 3, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		
		grid.getChildren().addAll(one,two,table);
		Scene scene = new Scene(grid, 500,300);
		Stage stage =new Stage();
		stage.setScene(scene);
		
		stage.show();
	
		
	}
     ObservableList<TCCustomers> getrows()
     {
    	 ObservableList<TCCustomers> ary =FXCollections.observableArrayList();
			
    	 try {
			pst=con.prepareStatement("select * from customers where city = ?");
			pst.setString(1,area.getSelectionModel().getSelectedItem());
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				ary.add(new TCCustomers(rs.getString("cname"),rs.getString("Mobile"),rs.getString("address"),rs.getString("city"),rs.getDate("dos").toString()));
			}
		} 
    	 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
  		
		}
    	 return(ary);
    	 
     }
     ObservableList<TCCustomers> getallrows()
     {
    	 ObservableList<TCCustomers> ary =FXCollections.observableArrayList();
			
    	 try {
			pst=con.prepareStatement("select * from customers ");
			
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				ary.add(new TCCustomers(rs.getString("cname"),rs.getString("Mobile"),rs.getString("address"),rs.getString("city"),rs.getDate("dos").toString()));
			}
		} 
    	 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
  		
		}
    	 return(ary);
    	 
     }
     ObservableList<TCCustomers> getgoorows()
     {
    	 ObservableList<TCCustomers> ary =FXCollections.observableArrayList();
			
    	 try {
			pst=con.prepareStatement("select * from customers where cname like ?");
			pst.setString(1,"%"+sea.getText()+"%");
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				ary.add(new TCCustomers(rs.getString("cname"),rs.getString("Mobile"),rs.getString("address"),rs.getString("city"),rs.getDate("dos").toString()));
			}
		} 
    	 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
  		
		}
    	 return(ary);
    	 
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
	
	void fillloc()
	{
		try {
			pst=con.prepareStatement("select distinct city from customers");
			ResultSet rs = pst.executeQuery();
			
			ArrayList<String> ary = new ArrayList<String>();
			while(rs.next()){
				ary.add(rs.getString("city"));
			//System.out.println(rs.getString("city"));
			}
			area.getItems().addAll(ary);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
