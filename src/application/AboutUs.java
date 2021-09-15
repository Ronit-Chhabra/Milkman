package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AboutUs 
{

	Label lblDev,lblName;
	AboutUs(){
		lblDev = new Label("Developed under guidance of :");
		lblDev.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		lblDev.setUnderline(true);
		
		Circle c1 = new Circle(65);
		Image img1 = new Image(MainWin.class.getResourceAsStream("sir.jpg")); 
		c1.setFill(new ImagePattern(img1));
		
		lblName = new Label("Mr. RAJESH K. BANSAL(MCA,SCJP,MCP)");
		lblName.setFont(Font.font("Arial", FontWeight.BOLD, 20));
	
		VBox vb = new VBox();
		vb.getChildren().addAll(lblDev,c1,lblName);
		vb.setSpacing(20);
		vb.setAlignment(Pos.CENTER);
		
		Stage stage = new Stage();
		stage.setScene(new Scene(vb,600,400));
		stage.show();
	}

}
