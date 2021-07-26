package first;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Radioabuttons extends Application {
	public static void main(String []args){
		launch(args);
	}

	RadioButton job,bus;
	Button btn = new Button("Click Me");
	@Override
	public void start(Stage stage) throws Exception {
		HBox hbox = new HBox();
		
		job = new RadioButton("JOB");
		bus = new RadioButton("Business");
		
		hbox.getChildren().addAll(job,bus,btn);
		hbox.setAlignment(Pos.CENTER);
		
		job.setPadding(new Insets(20,20,20,20));
		bus.setPadding(new Insets(20,20,20,20));
		
		job.setStyle("-fx-background-color:yellow");
		bus.setStyle("-fx-background-color:green");
		
		ToggleGroup group = new ToggleGroup();
		job.setToggleGroup(group);
		bus.setToggleGroup(group);
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Hello");
				alert.setHeaderText("Occupation");
				
				if(job.isSelected()){
					alert.setContentText("U Selected JOB");
					alert.show();
				}
				else
				{
					alert.setContentText("U Selected Business");
					alert.show();
				}
				
			}
		});
		
		Scene scene = new Scene(hbox,400,300);
		stage.setScene(scene);
		stage.show();
	}

}
