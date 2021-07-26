package ronit.subronit;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Fx extends Application
{

	public static void main(String[] args)
	{
	      launch(args);	

	}
	Button btnE=new Button("Exit");
	Button btnH=new Button("Hello");
	Button btnHi=new Button("hi");
    HBox hbox;
	@Override
	public void start(Stage stage) throws Exception 
	{
	stage.setTitle("JSG window");
	btnH.setMaxHeight(30);
	btnH.setMaxWidth(100);
	btnE.setMaxHeight(30);
	btnE.setMaxWidth(100);
	btnHi.setMaxHeight(30);
	btnHi.setMaxWidth(100);
	hbox= new HBox();
	hbox.setSpacing(20);
	hbox.setPadding(new Insets(15,10,10,100));
	hbox.getChildren().add(btnH);
	hbox.getChildren().addAll(btnE,btnHi);
	btnE.setOnAction(e->{System.exit(0);});
	btnH.setOnAction(new EventHandler<ActionEvent>(){

		@Override
		public void handle(ActionEvent arg0) {
			System.out.println("hello");
			
		}
	
	});
	Scene scene=new Scene(hbox,300,300);
	stage.setScene(scene);
	stage.show();	
	}
    
    
}