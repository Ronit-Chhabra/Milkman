package ronit.subronit;


import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Comobox extends Application
{

	public static void main(String[] args) 
	{
		launch(args);
	}

	void showmymsg(String msg)
	{
		Alert alert =new Alert(AlertType.INFORMATION);
		alert.setTitle("title msg");
		alert.setContentText(msg);
		alert.showAndWait();
	}
	ComboBox<String> combo;
	ImageView imgvw;
	Image img;
	Button btn =new Button("click");
	
	public void start(Stage primaryStage) throws Exception 
	{
	GridPane layout = new GridPane();
	layout.setHgap(10);
	layout.setVgap(20);
	layout.setGridLinesVisible(false);
	ArrayList<String>technologies=new ArrayList<String>();
	technologies.add("select");
	technologies.add("Java");
	technologies.add("Python");
	technologies.add("Android");
	technologies.add("C/C++");
	technologies.add("Angular");
	technologies.add("Spring");
	combo=new ComboBox<String>();
	combo.getItems().addAll(technologies);
	combo.setEditable(true);
	imgvw =new ImageView();
	imgvw.setFitWidth(100);
	imgvw.setFitHeight(200);
	//combo.setValue("select");
	combo.getSelectionModel().select(1);
    imgvw.setImage(new Image(new FileInputStream("java.png")));
    combo.setPrefWidth(200);
    combo.setVisibleRowCount(3);
    combo.getSelectionModel().selectedItemProperty().addListener((property,oldValue,newValue)->
    {
    	btn.setText(combo.getSelectionModel().getSelectedItem());
    	String item=combo.getSelectionModel().getSelectedItem();
    	showmymsg("Index:"+combo.getSelectionModel().getSelectedIndex()+"\nItem:"+item);
    	try
    	{
    		if(newValue.equals("Android"))
    		{
    			showmymsg("Fee:8K Duration:1.5 Months Index:"+combo.getSelectionModel().getSelectedIndex());
    			imgvw.setImage(new Image(new FileInputStream("android.jpg")));}
    		
    		else
    			if(newValue.equals("Java"))
    			imgvw.setImage(new Image(new FileInputStream("java.png")));
    			else if(newValue.equals("C/C++"))
    			imgvw.setImage(new Image(new FileInputStream("C.jpg")));
    			else
    			{
    			//set no pic
    			int indx=combo.getSelectionModel().getSelectedIndex();
    			imgvw.setImage(null);;
    			}
    			}
    			catch(Exception ex){}
    	
    });
    /*btn.setOnAction(e->
    {
   	btn.setText(combo.getSelectionModel().getSelectedItem());
    });*/
    GridPane.setConstraints(combo, 0, 0, 1,1,HPos.CENTER,VPos.CENTER, null,null, new Insets(30,0,0,0));
    GridPane.setConstraints(btn, 1, 0, 1, 1, HPos.CENTER , VPos.CENTER,null,null,new Insets(30,0,0,0));
    GridPane.setConstraints(imgvw, 0, 1, 1, 1, HPos.CENTER , VPos.CENTER,null,null,new Insets(20,20,20,20));
    /*img=new Image(new FileInputStream("android.gif"));
    imgView.setImage(img);*/
    layout.setAlignment(Pos.TOP_CENTER);
    layout.getChildren().addAll(combo,imgvw,btn);
    primaryStage.setScene(new Scene(layout,300,280));
    primaryStage.show();
	}

}
