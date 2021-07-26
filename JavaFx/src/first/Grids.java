package first;

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

public class Grids extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	GridPane grid;
	Text lblUid,lblPwd,lblTitle;
	TextField txtUid, txtPwd;
	Button btnClose, btnLogin;
	int x = 30;
	@Override
	public void start(Stage stage) throws Exception {
		grid = new GridPane();
		grid.setGridLinesVisible(false);
		grid.setHgap(20);
		grid.setVgap(20);
		//grid.setPadding(new Insets(100, 20, 20, 50));
		
		ImageView logo = new ImageView(new Image(Grids.class.getResourceAsStream("google.jpg")));
		logo.setFitHeight(30);
		logo.setFitWidth(30);
		
		lblTitle = new Text("Login Panel");
		lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		GridPane.setMargin(lblTitle, new Insets(10,0,10,90));
		
		lblUid = new Text("User ID");
		txtUid = new TextField();
		txtUid.setPadding(new Insets(5));
		txtUid.setPromptText("Enter User Id");
		
		//to make textBox empty
		txtUid.clear();
		
		lblPwd = new Text("Password");
		txtPwd = new TextField();
		txtPwd.setPadding(new Insets(5));
		txtPwd.setPromptText("Enter Password");
		
		btnLogin = new Button("Login");
		btnLogin.setPrefSize(100, 20);
		btnClose = new Button("Close");
		
		//Add components on grid layout
		grid.add(lblTitle, 0, 0, 2, 1);
		grid.add(logo, 2, 0);
		
		//grid.add(lblUid, 0, 1);
		//OR
		
		GridPane.setConstraints(lblUid, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		GridPane.setConstraints(lblPwd, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5, 35, 5,35));
		grid.getChildren().add(lblUid);
		grid.add(txtUid, 1, 1);
		grid.add(lblPwd, 0, 2);
		grid.add(txtPwd, 1, 2);
		
		//Add buttons
		HBox hbox = new HBox();
		HBox.setMargin(btnClose, new Insets(0, 0, 0, 10));//set left margin
		hbox.getChildren().addAll(btnLogin, btnClose);
		grid.add(hbox, 1, 3);
		
		btnLogin.setOnAction(e->doLogin());
		
		int y=3;
		btnClose.setOnAction(e->{
			System.out.println("y = " + y);
			//y=5; Error can't modify local variable in anonymous inner function
			System.out.println("x = " + x);
			x=50;
			System.out.println("x = " + x);
		});
		
		grid.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(grid, 400, 400);
		stage.setScene(scene);
		stage.show();
	}
	
	void doLogin(){
		System.out.println(txtUid.getText()); 
	}

}
