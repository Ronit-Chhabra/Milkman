package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	TextField txtUid;
	PasswordField txtPwd;
	Button btnLogin,btnClear,bFP;
	AudioClip note;
	
	Connection con;
	PreparedStatement pst;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		estCon();
		
		GridPane grid = new GridPane();
		grid.setGridLinesVisible(false);
		//grid.setHgap(20);
		//grid.setVgap(20);
		grid.setAlignment(Pos.CENTER);
		
		Circle cir = new Circle(60,60,30);
		Image img = new Image(Login.class.getResourceAsStream("mm2.jpg"));
		cir.setFill(new ImagePattern(img));
		
		Text lblTitle,lblUid,lblPwd;
		lblTitle = new Text("LOGIN");
		lblTitle.setUnderline(true);
		lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		
		HBox hbox1 = new HBox();
		hbox1.getChildren().addAll(cir,lblTitle);
		hbox1.setSpacing(10);
		hbox1.setAlignment(Pos.CENTER);
		
		lblUid = new Text("User Id");
		lblPwd = new Text("Password");
		
		GridPane.setConstraints(lblUid, 0, 1, 1, 1, null, VPos.CENTER, null, null, new Insets(15,0,5,0));
		GridPane.setConstraints(lblPwd, 0, 3, 1, 1, null, VPos.CENTER, null, null, new Insets(10,0,5,0));
		
		txtUid = new TextField();
		txtUid.setPadding(new Insets(5));
		txtUid.setPromptText("Enter User Id");
		
		txtPwd = new PasswordField();
		txtPwd.setPadding(new Insets(5));
		txtPwd.setPromptText("Enter Password");
		
		btnLogin = new Button("Login");
		btnLogin.setPrefSize(70, 20);
		btnLogin.setAlignment(Pos.CENTER);
		
		btnClear = new Button("Clear");
		btnClear.setPrefSize(70, 20);
		btnClear.setAlignment(Pos.CENTER);
		
		bFP = new Button("Forgot Password");
		bFP.setAlignment(Pos.CENTER);
		
		HBox hboxBtns = new HBox();
		hboxBtns.setSpacing(20);
		hboxBtns.getChildren().addAll(btnLogin,btnClear);
		hboxBtns.setPadding(new Insets(10,0,0,0));
		
		grid.add(hbox1,0,0,1,1);
		grid.getChildren().addAll(lblUid,lblPwd);
		grid.add(txtUid, 0, 2);
		grid.add(txtPwd, 0, 4);
		grid.add(hboxBtns, 0, 5, 1, 1);
		GridPane.setConstraints(bFP, 0, 6, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10,0,0,0));
		grid.getChildren().add(bFP);
		
		//String musicFile = "click.wav";
		//note = new AudioClip(this.getClass().getResource(musicFile).toString());
		
		primaryStage.setScene(new Scene(grid, 400, 300));
		primaryStage.show();
		
		//-------------EVENTS=================
		
		btnClear.setOnAction(e->doClear());
		
		btnLogin.setOnAction(e->doLogin());
		
		//bFP.setOnAction(e->forgotPwd());
	}

	void estCon(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1","root","ronit");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void showMsg(String msg, String hdr){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Title");
		alert.setHeaderText(hdr);
		alert.setContentText(msg);
		alert.show();
	}
	
	void doClear(){

		txtUid.clear();
		txtPwd.clear();
	}
	
	void doLogin(){
//		note.play();
		try {
			if(txtUid.getText().isEmpty())
			{
				txtUid.setStyle("-fx-border-color:red");
			}
			if(txtPwd.getText().isEmpty())
			{
				txtPwd.setStyle("-fx-border-color:red");
			}
			if(!txtUid.getText().isEmpty() && !txtPwd.getText().isEmpty()){
				txtUid.setStyle(null);
				txtPwd.setStyle(null);
			
			pst = con.prepareStatement("select * from login where userid = ? and password = ?");
			pst.setString(1, txtUid.getText());
			pst.setString(2, txtPwd.getText());
		//System.out.println(pst.toString());
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()){
				//System.out.println("hello");
				new MainWin();
			}
			else
				showMsg("Invalid Username/Password", "Error");
			pst.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
/*	void forgotPwd(){
		note.play();
		if(txtUid.getText().isEmpty())
		{
			txtUid.setStyle("-fx-border-color:red");
		}
		
		if(!txtUid.getText().isEmpty()){
			txtUid.setStyle(null);
			try {
				pst = con.prepareStatement("select * from owner where uid = ?");
				pst.setString(1, txtUid.getText());
				
				ResultSet rs = pst.executeQuery();
				
				if(rs.next()){
					String msg = "Your Password is " + rs.getString("pwd");
					String resp=SMS_API.bceSunSoftSend(rs.getString("mno"), msg);
					if(resp.indexOf("Exception")!=-1)
						showMsg("Check Internet Connection", "Error");
						//System.out.println("Check Internet Connection");
						else
						if(resp.indexOf("successfully")!=-1)
							showMsg("Your Password is sent to your mobile no. " + rs.getString("mno"), "Success");
							//System.out.println("Sent");
						else
							showMsg("Invalid Number", "Error");
							//System.out.println( "Invalid Number");
				}
				else
				{
					showMsg("Invalid Uid", "Error");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
*/
}
