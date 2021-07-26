package ronit.subronit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Browsse extends Application
{

	public static void main(String[] args) {
		
         launch(args);
	}
    HBox hbox;
	Button btn;
	public void start(Stage primarystage) throws Exception {
		
		FileChooser filechooser = new FileChooser();
		
		btn=new Button("click");
		btn.setMaxHeight(30);
		btn.setMaxWidth(100);
		hbox= new HBox();
		
		hbox.setPadding(new Insets(15,10,10,100));
		hbox.getChildren().add(btn);
		btn.setOnAction(e->filechooser.showOpenDialog(null));
		
		
		primarystage.setScene(new Scene(hbox,200,200));
		primarystage.show();
	}

}
