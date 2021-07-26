package ronit.subronit;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Login extends Application{
	Connection con;
	PreparedStatement pst;
	/*Login()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("done");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/firstdata","root","ronit");
			System.out.println("okee");
			} 
			catch (Exception e)
			{
			e.printStackTrace();
			}
	}*/
	
	public static void main(String []args){
		launch(args);
	}
	
	GridPane grid;
	TextField txtUid,txtPwd,txtmob;
	Button btnLogin,btnClear;
	MediaPlayer mediaPlayer;
	AudioClip note;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		grid = new GridPane();
		grid.setGridLinesVisible(false);
		//grid.setHgap(50);
		//grid.setVgap(50);
		grid.setAlignment(Pos.CENTER);
		
		Circle cir = new Circle(60,60,30);
		Image img = new Image(AddUsers.class.getResourceAsStream("google.jpg"));
		cir.setFill(new ImagePattern(img));
		
		Text lblTitle,lblUid,lblPwd,lblmob;
		lblTitle = new Text("LOGIN");
		lblTitle.setUnderline(true);
		lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		
		HBox hboxTitle= new HBox();
		hboxTitle.setAlignment(Pos.CENTER);
		HBox.setMargin(lblTitle, new Insets(0,0,0,0));
		hboxTitle.getChildren().add(lblTitle);
		HBox hbox1 = new HBox();
		hbox1.getChildren().addAll(cir,lblTitle);
		hbox1.setAlignment(Pos.CENTER);
		
		lblUid = new Text("User Id");
		lblPwd = new Text("Password");
		lblmob=new Text("Mobile No.");
		GridPane.setConstraints(lblUid, 0, 1, 1, 1, null, VPos.CENTER, null, null, new Insets(30,0,5,0));
		GridPane.setConstraints(lblPwd, 0, 3, 1, 1, null, VPos.CENTER, null, null, new Insets(20,0,5,0));
		GridPane.setConstraints(lblmob, 0, 5, 1, 1, null, VPos.CENTER, null, null, new Insets(10,0,5,0));
		
		txtUid = new TextField();
		txtUid.setPadding(new Insets(5));
		txtUid.setPromptText("Enter User Id");
		
		txtPwd = new TextField();
		txtPwd.setPadding(new Insets(5));
		txtPwd.setPromptText("Enter Password");
		txtmob = new TextField();
		txtmob.setPadding(new Insets(5));
		txtmob.setPromptText("Enter Mobile no.");
		
		btnLogin = new Button("Login");
		btnLogin.setPrefSize(70, 20);
		btnLogin.setAlignment(Pos.CENTER);
		btnLogin.setStyle("-fx-background-color:cyan");
		
		btnClear = new Button("Clear");
		btnClear.setPrefSize(70, 20);
		btnClear.setAlignment(Pos.CENTER);
		btnClear.setStyle("-fx-background-color:seagreen");
		
		HBox hboxBtns = new HBox();
		hboxBtns.setSpacing(20);
		hboxBtns.getChildren().addAll(btnLogin,btnClear);
		hboxBtns.setPadding(new Insets(10,0,0,0));
		
		grid.add(hbox1,0,0,3,1);
		grid.getChildren().addAll(lblUid,lblPwd,lblmob);
		grid.add(txtUid, 0, 2);
		grid.add(txtPwd, 0, 4);
		grid.add(txtmob, 0, 6);
		
		grid.add(hboxBtns, 0, 10, 1, 1);
		
		String musicFile = "gunshot.wav";
		//Media sound = new Media(new File(musicFile).toURI().toString());
		//mediaPlayer = new MediaPlayer(sound);
		note = new AudioClip(this.getClass().getResource(musicFile).toString());
		
		
		//==================EVENTS========================
				btnLogin.setOnAction(new EventHandler<ActionEvent>() 
				{
					@Override
					public void handle(ActionEvent event) {
						//mediaPlayer.play();
						note.play();
						
						if(txtUid.getText().isEmpty())
						{
							txtUid.setStyle("-fx-border-color:red");
						}
						if(txtPwd.getText().isEmpty())
						{
							txtPwd.setStyle("-fx-border-color:red");
						}}
						
						
					
				});
				
				btnClear.setOnAction(e->doClear());
				
				Scene scene = new Scene(grid, 400,300);
				primaryStage.setScene(scene);
				primaryStage.show();
				


	}
	
	void doClear(){
		//mediaPlayer.play();
		note.play();
		txtUid.clear();
		txtPwd.clear();
	}
	

}
