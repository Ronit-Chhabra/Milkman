package first;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
public class Copied extends Application
{
public static void main(String[] args) 
{
launch(args);
}
Button btnE=new Button("Exit");
Button btnH=new Button("Hello");
Button btnHi=new Button("Hi");
HBox hbox;
@Override
public void start(Stage stage) throws Exception 
{
stage.setTitle("JSG Window");
btnH.setMaxHeight(30);
btnH.setMaxWidth(100);
btnE.setMaxHeight(30);
btnE.setMaxWidth(100);
btnHi.setMaxHeight(30);
btnHi.setMaxWidth(100);
hbox=new HBox();
hbox.setSpacing(20);
hbox.setPadding(new Insets(15,10,10,100));
hbox.getChildren().add(btnH);
hbox.getChildren().addAll(btnE,btnHi);
//events
btnE.setOnAction(e->{
System.exit(0);
}
);
//----------------------------------------------------
btnH.setOnAction(new EventHandler<ActionEvent>() {
@Override
public void handle(ActionEvent arg0) {
// TODO Auto-generated method stub
System.out.println("Hellllloooooooo");
}
});
//===================================
btnHi.setOnAction(e->doHi());
Scene scene=new Scene(hbox,300,300);
stage.setScene(scene);
stage.show();
}
void doHi()
{
Alert alert=new Alert(AlertType.WARNING);
alert.setHeaderText("header");
alert.setTitle("title");
alert.setContentText("Hi Dear How R U???");
alert.show();

}
}