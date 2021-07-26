package first;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class Upload extends Application{
	
	public static void main(String args[]){
		launch(args);
	}

	AudioClip note;
	Button btn1,btn2;
	TextField t;
	@Override
	public void start(Stage primaryStage) throws Exception {
		note = new AudioClip(this.getClass().getResource("gunshot.wav").toString());
		btn1 = new Button("Play");
		btn1.setPrefSize(70, 20);
		btn2 = new Button("Change border Color");
		t = new TextField();
		t.setMaxWidth(100);
		
		btn1.setOnAction(e->{
			note.play();
		});
		
		btn2.setOnAction(e->{
			t.setStyle("-fx-border-color : red");
		});
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(btn1,t,btn2);
		vbox.setSpacing(20);
		vbox.setAlignment(Pos.CENTER);
		
		primaryStage.setScene(new Scene(vbox, 400, 400));
		primaryStage.show();
	}

}
