package ronit.subronit;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
public class RadioButtion extends Application
{

public static void main(String[] args) 
{
launch(args);
}
RadioButton job,bus;
Button btn=new Button("Click Me");
@Override
public void start(Stage stage) throws Exception 
{
HBox hbox=new HBox();
job=new RadioButton("Job");
bus=new RadioButton("Business");
hbox.getChildren().addAll(job,bus,btn);
hbox.setAlignment(Pos.CENTER);
job.setPadding(new Insets(20,20,20,20));
bus.setPadding(new Insets(20,20,20,20));
job.setStyle("-fx-background-color:yellow");
bus.setStyle("-fx-background-color:green");

ToggleGroup group=new ToggleGroup();
job.setToggleGroup(group);
bus.setToggleGroup(group);

btn.setOnAction(new EventHandler<ActionEvent>() {
@Override
public void handle(ActionEvent arg0) {
// TODO Auto-generated method stub
Alert alert=new Alert(AlertType.INFORMATION);
alert.setTitle("Occupation");
if(job.isSelected())
{
alert.setContentText("U selected JOB");
alert.showAndWait();
}
else
{
alert.setContentText("U selected Business");
alert.showAndWait();
}
}
});
bus.setOnAction(new EventHandler<ActionEvent>() {
@Override
public void handle(ActionEvent arg0) {
// TODO Auto-generated method stub
Alert alert=new Alert(AlertType.INFORMATION);
alert.setTitle("Occupation");
if(bus.isSelected())
{
alert.setContentText("U selected Business");
alert.showAndWait();
}
}
});
Scene scene=new Scene(hbox,400,300);
stage.setScene(scene);
stage.show();
}
}