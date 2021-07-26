package ronit.subronit;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class OpenWindow extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	Button btn = new Button("Click Me");
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		btn.setMaxHeight(20);
		btn.setMaxWidth(70);
		primaryStage.setTitle("Main");
		
		btn.setOnAction(e->{
			Button btn = new Button("Click Me");
			btn.setMaxSize(70, 20);
			Stage s = new Stage();
			s.setScene(new Scene(btn, 400, 400));
			s.setTitle("Secondary");
			s.show();
		});
		
		primaryStage.setScene(new Scene(btn,300,300));
		primaryStage.show();
	}

}
