package first;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class MyListbox extends Application
{
	public static void main(String[] args) 
	{
		launch(args);
	}
	
	ListView<String> technologies;
	Button btn,delBtn;
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		GridPane grid = new GridPane();
		grid.setGridLinesVisible(false);
		grid.setVgap(20);
		grid.setHgap(20);
		technologies = new ListView<String>();
		ArrayList<String> ary=new ArrayList<String>();
		ary.add("HTML");ary.add("Java Script");ary.add("JQuery");
		ary.add("JSON");ary.add("Spring");ary.add("Hibernate");
		for(String item:ary)
		{
			technologies.getItems().add(item);
		}
		
		//set to multiple selection
		technologies.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		//setting width &....
		technologies.setPrefSize(100,200);
		btn = new Button("Selected");
		btn.setPrefSize(100, 40);
		delBtn = new Button("Delete Selected");
		delBtn.setPrefSize(100, 40);

		btn.setOnAction(e->{
			String all="";
			ObservableList<String> selected;
			selected = technologies.getSelectionModel().getSelectedItems();
			for(String one:selected)
				all=all+one+",";
			showMyMsg(all);
			ObservableList<Integer>indxs= technologies.getSelectionModel().getSelectedIndices();
			all="";
			for(Integer indx:indxs)
			{
				all=all+indx+",";
			}
			showMyMsg(all);
		});
		
		delBtn.setOnAction(e->{
			ObservableList<String> selected;
			selected = technologies.getSelectionModel().getSelectedItems();
			technologies.getItems().removeAll(selected);
		});
		grid.setConstraints(technologies, 0, 0, 1, 1, HPos.CENTER, VPos.TOP, null, null, new Insets(20,0,0,20));
		grid.setConstraints(btn, 0, 1, 1, 1, HPos.LEFT, VPos.TOP, null, null, new Insets(20,0,0,20));
		grid.setConstraints(delBtn, 1, 1, 1, 1, HPos.LEFT, VPos.TOP, null, null, new Insets(20,0,0,20));
		
		//adding list to gridpane
		grid.getChildren().addAll(technologies,btn,delBtn);
		Scene scene=new Scene(grid, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	void showMyMsg(String msg)
	{
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setContentText(msg);
		alert.showAndWait();
	}
}