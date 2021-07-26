package ronit.subronit;

import java.util.ArrayList;

import javax.print.DocFlavor.STRING;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Fees extends Application 
{

	public static void main(String[] args) 
	{
		launch(args);
	}
	ListView<String> tech;
	ListView<String> seltech;
	ListView<Integer> fee,selfee;
	Button btn= new Button(">>");
	Text txt,txt2;
	int total=0;

	ComboBox <String>combo=new ComboBox<String>();
	public void start(Stage primaryStage) throws Exception 
	{
	GridPane grid=new GridPane();	
	grid.setVgap(20);
	grid.setHgap(20);
	grid.setGridLinesVisible(false);
	combo.getItems().add("Select");
	combo.getItems().add("Java");
	combo.getItems().add("WDP");
	
	ArrayList<String> java=new ArrayList<String>();
	java.add("J2SE");
	java.add("J2EE");
	java.add("Android");
	ArrayList<Integer> price1 = new ArrayList<Integer>();
	price1.add(8000);
	price1.add(9000);
	price1.add(10000);
	ArrayList<String> wdp=new ArrayList<String>();
	wdp.add("HTML");
	wdp.add("CSS");
	wdp.add("PHP");
	ArrayList<Integer> price2 = new ArrayList<Integer>();
	price2.add(5000);
	price2.add(6000);
	price2.add(7000);
	tech=new ListView<String>();
	fee=new ListView<Integer>();
	seltech=new ListView<String>();
	selfee=new ListView<Integer>();
	tech.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	fee.getSelectionModel().setSelectionMode(null);
	
	
	combo.getSelectionModel().selectedItemProperty().addListener((property,oldValue,newValue)->
	{
		if(newValue.equals("Java"))
		{
			tech.getItems().clear();
			tech.getItems().addAll(java);
			fee.getItems().clear();
			fee.getItems().addAll(price1);
		}
		else if(newValue.equals("WDP"))
		{
            tech.getItems().clear();
			tech.getItems().addAll(wdp);
			fee.getItems().clear();
			fee.getItems().addAll(price2);
		}
		else
		{
			tech.getItems().clear();
			fee.getItems().clear();
			}
	});
	txt=new Text("total");
	txt2=new Text("0");
	btn.setOnAction(e->
	{
	ObservableList<String> olist;
	ObservableList<Integer> olist2;
	olist=tech.getSelectionModel().getSelectedItems();
	seltech.getItems().clear();
	seltech.getItems().addAll(olist);
	olist2=tech.getSelectionModel().getSelectedIndices();
	
	selfee.getItems().clear();
	for (Integer integer : olist2) 
	{
		fee.getSelectionModel().selectIndices(integer);
	}
	olist2=fee.getSelectionModel().getSelectedItems();
	selfee.getItems().addAll(olist2);
	
	for (Integer int1 : olist2) 
	{
	total=total+int1;	
	}
	txt2.setText(String.valueOf(total));
	total=0;
	fee.getSelectionModel().clearSelection();
	
	});
	
	
	grid.add(combo, 0, 0);
	grid.add(txt, 4,2);
	grid.add(txt2, 5,2);
	grid.add(tech, 0,1);
	grid.add(fee, 1,1);
	grid.add(btn, 2,1);
	grid.add(seltech,3,1);
	grid.add(selfee, 4,1);
	primaryStage.setScene(new Scene(grid,500,400));
	primaryStage.show();
	}

}
