package application;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainWin
{
	
	
	
	Label lblCustomer,lblDR,lblBillGen,lblBillCol,lblTabC,lblTabBill,lblSel,lblRecord,lblabtus;
	
MainWin()
	
	 {
			
			GridPane grid = new GridPane();
			grid.setGridLinesVisible(false);
			grid.setHgap(20);
			grid.setVgap(20);
			grid.setAlignment(Pos.CENTER);
			
			lblSel = new Label("Milk Man Assistant");
			lblSel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
			lblSel.setUnderline(true);
			
			Circle c1 = new Circle(65);
			c1.setStroke(Color.SEAGREEN); 
			c1.setFill(Color.SNOW); 
			c1.setEffect(new DropShadow(+25d, 0d, +2d, Color.BROWN));
			Image img1 = new Image(MainWin.class.getResourceAsStream("custom.jpg")); 
			c1.setFill(new ImagePattern(img1));
			
			lblCustomer = new Label("Customer Console");
			lblCustomer.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
			
			Circle c2 = new Circle(65);
			c2.setStroke(Color.SEAGREEN); 
			c2.setFill(Color.SNOW); 
			c2.setEffect(new DropShadow(+25d, 0d, +2d, Color.BROWN));
			
			Image img2 = new Image(MainWin.class.getResourceAsStream("du.jpg")); 
			c2.setFill(new ImagePattern(img2));
			
			lblDR = new Label("Update Daily Entry");
			lblDR.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
			
			Circle c3 = new Circle(65);
			c3.setStroke(Color.SEAGREEN); 
			c3.setFill(Color.SNOW); 
			c3.setEffect(new DropShadow(+25d, 0d, +2d, Color.BROWN));
			
			Image img3 = new Image(MainWin.class.getResourceAsStream("billg.jpg")); 
			c3.setFill(new ImagePattern(img3));
			
			lblBillGen = new Label("Generate Bill");
			lblBillGen.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
			
			Circle c4 = new Circle(65);
			c4.setStroke(Color.SEAGREEN); 
			c4.setFill(Color.SNOW); 
			c4.setEffect(new DropShadow(+25d, 0d, +2d, Color.BROWN));
			
			Image img4 = new Image(MainWin.class.getResourceAsStream("bill.jpg")); 
			c4.setFill(new ImagePattern(img4));
			
			lblBillCol = new Label("Pay Bill");
			lblBillCol.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
			
			Circle c5 = new Circle(65);
			c5.setStroke(Color.SEAGREEN); 
			c5.setFill(Color.SNOW); 
			c5.setEffect(new DropShadow(+25d, 0d, +2d, Color.BROWN));
			
			Image img5 = new Image(MainWin.class.getResourceAsStream("customer.jpg")); 
			c5.setFill(new ImagePattern(img5));
			
			lblTabC = new Label("Customer Table");
			lblTabC.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
			
			Circle c6 = new Circle(65);
			c6.setStroke(Color.SEAGREEN); 
			c6.setFill(Color.SNOW); 
			c6.setEffect(new DropShadow(+25d, 0d, +2d, Color.BROWN));
			
			Image img6 = new Image(MainWin.class.getResourceAsStream("bt.jpg")); 
			c6.setFill(new ImagePattern(img6));
			
			lblTabBill = new Label("Bill Table");
			lblTabBill.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
			
			
			Circle c7 = new Circle(65);
			
			c7.setStroke(Color.SEAGREEN); 
			c7.setFill(Color.SNOW); 
			c7.setEffect(new DropShadow(+25d, 0d, +2d, Color.BROWN));
			Image img7 = new Image(MainWin.class.getResourceAsStream("deve.jpg")); 
			c7.setFill(new ImagePattern(img7));
		
			lblabtus = new Label("About us");
			lblabtus.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
			
			
			
			GridPane.setConstraints(lblSel, 0, 0, 3, 1, HPos.CENTER, VPos.CENTER, null, null, null);
			GridPane.setConstraints(c1, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
			GridPane.setConstraints(c2, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
			GridPane.setConstraints(c3, 2, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
			GridPane.setConstraints(lblCustomer, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
			GridPane.setConstraints(lblDR, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
			GridPane.setConstraints(lblBillGen, 2, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
			GridPane.setConstraints(c4, 0, 3, 3, 1, HPos.CENTER, VPos.CENTER, null, null, null);
			GridPane.setConstraints(lblBillCol, 0, 4, 3, 1, HPos.CENTER, VPos.CENTER, null, null, null);
			GridPane.setConstraints(c5, 0, 5, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
			GridPane.setConstraints(c6, 1, 5, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
			GridPane.setConstraints(lblTabC, 0, 6, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
			GridPane.setConstraints(lblTabBill, 1, 6, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
			GridPane.setConstraints(c7, 2, 5, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
			GridPane.setConstraints(lblabtus, 2, 6, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
			
			grid.getChildren().addAll(lblSel,c1,lblCustomer,c2,lblDR,c3,lblBillGen,c4,lblBillCol,c5,lblTabC,c6,lblTabBill,c7,lblabtus);
			
			//String musicFile = "click.wav";
			//note = new AudioClip(this.getClass().getResource(musicFile).toString());
			
			Stage stage = new Stage();
			stage.setScene(new Scene(grid,700,700));
			stage.show();
			
			c1.setOnMouseEntered(e->{
				c1.setStyle("-fx-cursor: hand");
			});
			
			c2.setOnMouseEntered(e->{
				c2.setStyle("-fx-cursor: hand");
			});
			
			c3.setOnMouseEntered(e->{
				c3.setStyle("-fx-cursor: hand");
			});
			
			c4.setOnMouseEntered(e->{
				c4.setStyle("-fx-cursor: hand");
			});
			
			c5.setOnMouseEntered(e->{
				c5.setStyle("-fx-cursor: hand");
			});
			
			c6.setOnMouseEntered(e->{
				c6.setStyle("-fx-cursor: hand");
			});
		
			c1.setOnMouseClicked(e->{

				new Myproject();
			});
				c2.setOnMouseClicked(e->{
				//note.play();
				new Daily();
			});
			
			c3.setOnMouseClicked(e->{
				//note.play();
				new Bill();
			});
			
			c4.setOnMouseClicked(e->{
				//note.play();
				new BillCollector();
			});
		
			c5.setOnMouseClicked(e->{
				//note.play();
		new Google();
				
			});
			
			c6.setOnMouseClicked(e->{
				//note.play();
				new TabBill();
			});
			
			c7.setOnMouseClicked(e->{
				//note.play();
				new AboutUs();
			});
					}

	}
	

