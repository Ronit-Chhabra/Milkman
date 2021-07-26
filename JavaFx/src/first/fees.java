package first;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class fees extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	ListView<String> tech,selTech;
	ListView<Integer> fee,selFee;
	ComboBox<String> combo;
	Button btn = new Button(">>");
	Text txt1,txt2;
	int total=0;

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setAlignment(Pos.CENTER);
		btn.setPrefSize(40, 20);
		
		combo = new ComboBox<String>();
		combo.setPrefWidth(150);
		combo.getItems().add("Select");
		combo.getItems().add("Java");
		combo.getItems().add("WDP");
		combo.setValue("Select");
		
		ArrayList<String> java = new ArrayList<String>();
		java.add("J2SE");
		java.add("J2EE");
		java.add("Android");
		
		ArrayList<Integer> javaFee = new ArrayList<Integer>();
		javaFee.add(8000);
		javaFee.add(9000);
		javaFee.add(10000);
		
		ArrayList<String> WDP = new ArrayList<String>();
		WDP.add("HTML");
		WDP.add("CSS");
		WDP.add("PHP");
		
		ArrayList<Integer> WDPFee = new ArrayList<Integer>();
		WDPFee.add(1000);
		WDPFee.add(2000);
		WDPFee.add(3000);
		
		tech = new ListView<String>();
		fee = new ListView<Integer>();
		selTech = new ListView<String>();
		selFee = new ListView<Integer>();
		tech.setMaxSize(100, 150);
		fee.setPrefSize(100, 150);
		selTech.setPrefSize(100, 150);
		selFee.setPrefSize(100, 150);
		
		tech.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		fee.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		selTech.getSelectionModel().setSelectionMode(null);
		selFee.getSelectionModel().setSelectionMode(null);
		
		txt1 = new Text("Total");
		txt2 = new Text("0");
		
		combo.getSelectionModel().selectedItemProperty().addListener((property,oldvalue,newvalue)->{
			selTech.getItems().clear();
			selFee.getItems().clear();
			txt2.setText("0");
			if(newvalue.equals("Java")){
				tech.getItems().clear();
				tech.getItems().addAll(java);
				fee.getItems().clear();
				fee.getItems().addAll(javaFee);
			}
			else if(newvalue.equals("WDP")){
				tech.getItems().clear();
				tech.getItems().addAll(WDP);
				fee.getItems().clear();
				fee.getItems().addAll(WDPFee);
			}
			else{
				tech.getItems().clear();
				fee.getItems().clear();
			}
		});
		
		txt1 = new Text("Total");
		txt2 = new Text("0");
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				//Clear lists
				total = 0;
				selTech.getItems().clear();
				selFee.getItems().clear();
				//fee.getSelectionModel().clearSelection();
				
				ObservableList<String> Olist;
				ObservableList<Integer> Olist2;
				
				Olist = tech.getSelectionModel().getSelectedItems();
				selTech.getItems().addAll(Olist);
				
				Olist2 = tech.getSelectionModel().getSelectedIndices();
				
				for(Integer l : Olist2){
					int a = l;
					fee.getSelectionModel().select(a);;
					//fee.getFocusModel().focus(l);
				}
				
				
				
				Olist2 = fee.getSelectionModel().getSelectedItems();
				selFee.getItems().addAll(Olist2);
				
				
				for(Integer f : Olist2){
					total = total + f;
				}
				
				txt2.setText(String.valueOf(total));
			
				fee.getSelectionModel().clearSelection();
			}
		});
		
		HBox hbox = new HBox();
		hbox.getChildren().addAll(txt1,txt2);
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
		
		grid.add(combo, 0, 0);
		grid.add(tech, 0, 1);
		grid.add(fee, 1, 1);
		grid.add(btn, 2, 1);
		grid.add(selTech, 3, 1);
		grid.add(selFee, 4, 1);
		grid.add(hbox, 3, 2, 2, 1);
		
		
		primaryStage.setScene(new Scene(grid, 700, 500));
		primaryStage.show();
	}

}
