package first;

import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Combox extends Application {
	public static void main(String args[]){
		launch(args);
	}
	
	void showMyMsg(String msg){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setContentText(msg);
		alert.show();
	}
	
	ComboBox<String> combo;
	ImageView imgView;
	Image img;
	Button btn = new Button("Click");

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane layout = new GridPane();
		layout.setHgap(20);
		layout.setHgap(20);
		layout.setGridLinesVisible(false);
		layout.setAlignment(Pos.CENTER);
		
		ArrayList<String> technologies = new ArrayList<String>();
		technologies.add("Select");
		technologies.add("Java");
		technologies.add("C/C++");
		technologies.add("Android");
		technologies.add("Python");
		technologies.add("Spring");
		technologies.add("Angular");
		
		combo = new ComboBox<String>();
		combo.getItems().addAll(technologies);
		combo.setEditable(true);
		
		imgView = new ImageView();
		imgView.setFitHeight(200);
		imgView.setFitWidth(200);
		
		combo.setValue("Select");
		combo.getSelectionModel().select(1);
		imgView.setImage(new Image(new FileInputStream("java.png")));
		
		combo.setPrefWidth(200);
		combo.setVisibleRowCount(3);
		
		combo.getSelectionModel().selectedItemProperty().addListener((property,oldvalue,newvalue)->{
			String item = combo.getSelectionModel().getSelectedItem();
			showMyMsg("Index : " + combo.getSelectionModel().getSelectedIndex() + "\nItem : " + item);
			
			System.out.println(oldvalue + " " + newvalue);
			
			try{
			if(newvalue.equals("Android")){
				showMyMsg("Fee : 8k Duration : 1.5 months Index : " +
						combo.getSelectionModel().getSelectedIndex());
				imgView.setImage(new Image(new FileInputStream("android.png")));
			}
			else if(newvalue.equals("Java")){
				showMyMsg("Java " +
						combo.getSelectionModel().getSelectedIndex());
				imgView.setImage(new Image(new FileInputStream("java.png")));
			}
			else if(newvalue.equals("C/C++")){
				showMyMsg("C/C++ " +
						combo.getSelectionModel().getSelectedIndex());
				imgView.setImage(new Image(new FileInputStream("cpp.png")));
			}
			else{
				//set no pic
				imgView.setImage(null);
			}
			
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		});
		
		btn.setOnAction(e->{
			btn.setText(combo.getSelectionModel().getSelectedItem());
		});
		
		GridPane.setConstraints(combo, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(30,0,0,0));
		GridPane.setConstraints(btn, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(30,0,0,0));
		GridPane.setConstraints(imgView, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(30,0,0,0));
		
		layout.setAlignment(Pos.TOP_CENTER);
		layout.getChildren().addAll(combo,imgView,btn);
		primaryStage.setScene(new Scene(layout, 300, 300));
		 primaryStage.show();
	}
}
