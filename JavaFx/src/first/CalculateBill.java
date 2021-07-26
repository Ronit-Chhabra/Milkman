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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CalculateBill extends Application {
	public static void main(String []args){
		launch(args);
	}
	
	GridPane grid,grid2;
	TextField txtUnit,txtLd;
	Button btnBill,btnNew;
	RadioButton dom,com;
	AudioClip note;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		grid = new GridPane();
		grid.setGridLinesVisible(false);
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setAlignment(Pos.CENTER);
		
		Text lblTitle,lblUnit,lblLd;
		lblTitle = new Text("Electric Bill");
		lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		lblTitle.setUnderline(true);
		GridPane.setConstraints(lblTitle, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		
		lblUnit = new Text("Units");
		GridPane.setConstraints(lblUnit, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		txtUnit = new TextField();
		
		lblLd = new Text("Load");
		GridPane.setConstraints(lblLd, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, null);
		txtLd = new TextField();
		
		//----------------adding radio buttons
		dom = new RadioButton("Domestic");
		com = new RadioButton("Commercial");
		
		HBox hboxRdBtn = new HBox();
		hboxRdBtn.setSpacing(20);
		hboxRdBtn.getChildren().addAll(dom,com);
		hboxRdBtn.setAlignment(Pos.CENTER);
		
		ToggleGroup group = new ToggleGroup();
		dom.setToggleGroup(group);
		com.setToggleGroup(group);
		
		//---------------adding buttons
		btnBill = new Button("Bill");
		btnBill.setPrefSize(70, 20);
		btnBill.setAlignment(Pos.CENTER);
		
		btnNew = new Button("New");
		btnNew.setPrefSize(70, 20);
		btnNew.setAlignment(Pos.CENTER);
		
		HBox hboxBtns = new HBox();
		hboxBtns.setSpacing(20);
		hboxBtns.getChildren().addAll(btnBill,btnNew);
		hboxBtns.setAlignment(Pos.CENTER);
		
		grid.getChildren().add(lblTitle);
		grid.getChildren().add(lblUnit);
		grid.getChildren().add(lblLd);
		grid.add(txtUnit, 1, 1);
		grid.add(txtLd, 1, 2);
		grid.add(hboxRdBtn, 0, 3, 2, 1);
		grid.add(hboxBtns, 0, 4, 2, 1);
		
		//===========================GRID 2========
		
		grid2 =new GridPane();
		grid2.setGridLinesVisible(true);
		grid2.setVgap(20);
		grid2.setAlignment(Pos.CENTER);
		
		Text unit,ld,dis,net,unitVal,ldVal,disVal,netVal;
		
		unit = new Text("Units");
		ld = new Text("Load");
		dis = new Text("Discount");
		net = new Text("Net");
		unitVal = new Text("0");
		ldVal = new Text("0");
		disVal = new Text("0");
		netVal = new Text("0");
		
		GridPane.setConstraints(unit, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5,5,5,5));
		GridPane.setConstraints(ld, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5,5,5,5));
		GridPane.setConstraints(dis, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5,5,5,5));
		GridPane.setConstraints(net, 0, 3, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5,5,5,5));
		GridPane.setConstraints(unitVal, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5,10,5,10));
		GridPane.setConstraints(ldVal, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5,10,5,10));
		GridPane.setConstraints(disVal, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5,10,5,10));
		GridPane.setConstraints(netVal, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5,10,5,10));
		grid2.getChildren().addAll(unit,ld,dis,net,unitVal,ldVal,disVal,netVal);
		
		//--------------SOUND-------------------
		note = new AudioClip(this.getClass().getResource("gunshot.wav").toString());
		
		//------------------EVENTS------------------------
		btnBill.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				unitVal.setText(txtUnit.getText());
				ldVal.setText(txtLd.getText());
				double u = Double.parseDouble(txtUnit.getText());
				double l = Double.parseDouble(txtLd.getText());
				double dis,net,b;
				b=u*l;
				if(dom.isSelected()){
					dis = (float)10*b/100;
					disVal.setText(String.valueOf(dis));
					net = b-dis;
					netVal.setText(String.valueOf(net));
				}
				else if(com.isSelected()){
					dis = (float)20*b/100;
					disVal.setText(String.valueOf(dis));
					net = b-dis;
					netVal.setText(String.valueOf(net));
				}
				else{
					Alert al = new Alert(AlertType.WARNING);
					al.setHeaderText("Error");
					al.setTitle("Alert");
					al.setContentText("Choose domestic or Commercial");
					al.show();
				}
			}
		});
		
		btnNew.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				note.play();
				txtUnit.clear();
				txtLd.clear();
				unitVal.setText("0");
				ldVal.setText("0");
				disVal.setText("0");
				netVal.setText("0");
			}
		});
		
		//==================OUTER VBOX=======================
		VBox vbox = new VBox();
		vbox.getChildren().addAll(grid,grid2);
		vbox.setSpacing(40);
		vbox.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(vbox,500,500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
