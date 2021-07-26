package ronit.subronit;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class IndLect extends Application 
{

public static void main(String[] args)
{
launch(args);
}
ImageView logoo;
GridPane grid;
@Override
public void start(Stage primaryStage) throws Exception 
{
 grid=new GridPane();
grid.setGridLinesVisible(false);
grid.setHgap(20);
grid.setVgap(10);
grid.setPadding(new Insets(100,20,20,50));
 logoo=new ImageView(new Image(IndLect.class.getResourceAsStream("ron.jpg")));
logoo.setFitWidth(30);
logoo.setFitHeight(30);

Text lblUid,lblPwd,lblTitle;
TextField txtUid,txtPwd;
Button btnLog,btnClose;
lblTitle=new Text("Login Panel");
lblTitle.setFont(Font.font("Arial" ,FontWeight.BOLD,20));
GridPane.setMargin(lblTitle, new Insets(10,0,10,50));
lblUid=new Text("User Id");
txtUid=new TextField();
//txtUid.setPadding(new Insets(5));
txtUid.setPromptText("Enter User id plz");
//to make textbox empty
//txtUid.clear();
lblPwd=new Text("Password");
txtPwd=new TextField();
txtPwd.setPromptText("Enter Password");
btnLog=new Button("Login");
btnLog.setPrefSize(100, 20);
btnClose=new Button("Close");
//add components on grid layout
grid.add(lblTitle, 0, 0,2,1);
grid.add(logoo, 2, 0);
//grid.add(lblUid, 0, 1);
//OR
GridPane.setConstraints(lblUid, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null,new Insets(5,35,5,35));
grid.getChildren().add(lblUid);
grid.add(txtUid, 1, 1);
grid.add(lblPwd, 0, 2);
grid.add(txtPwd, 1, 2);
//add buttons
HBox hbox=new HBox();
HBox.setMargin(btnClose, new Insets(0,0,0,10)); //set left margin
hbox.getChildren().addAll(btnLog,btnClose);
grid.add(hbox, 1, 3);

Scene scene=new Scene(grid,400,400);
primaryStage.setScene(scene);
primaryStage.show();
}
}
