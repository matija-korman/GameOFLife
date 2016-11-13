package application;
	


import java.net.URL;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class GameOfLIfe extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	
	@Override
	public void start(Stage primaryStage){
		   
		//List of Items that will be shown in ListView when loading and saving matrix configurations
		//The items will be the names of the saved matrices
		final ObservableList<String> savedItems = FXCollections.observableArrayList();  
		
		
		primaryStage.setTitle("Game of Life");
		
			
		//Setting up the Main Menu (main Scene)
		BorderPane mainLayout=new BorderPane();
		mainLayout.setStyle("-fx-background-color: rgb(255, 255, 255);");

		VBox mainVBox=new VBox(8);
		mainVBox.setAlignment(Pos.TOP_CENTER);
		mainVBox.setPadding(new Insets(60, 0, 0, 0));
		
		mainLayout.setCenter(mainVBox);
		
		Image logo=new Image("file:src/application/logo.png");
		ImageView image=new ImageView(logo);
		Button mainStart=new Button("START");
		Button rules=new Button("RULES");
		Button about=new Button("ABOUT");
		mainVBox.getChildren().addAll(image,mainStart, rules, about);
		
		
		Scene mainScene=new Scene(mainLayout,500,750);
		mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		
		//Setting up the ABOUT scene
		
		BorderPane aboutLayout=new BorderPane();
		aboutLayout.setStyle("-fx-background-color: rgb(255, 255, 255);");
		
		VBox aboutVBox=new VBox(8);
		aboutVBox.setAlignment(Pos.TOP_CENTER);
		aboutVBox.setPadding(new Insets(30, 0, 0, 0));
		
		aboutLayout.setCenter(aboutVBox);
		
		HBox aboutToolbar=new HBox();
		aboutToolbar.setPrefHeight(30);
		
		
		aboutLayout.setTop(aboutToolbar);
		
		Button aboutBackToMain=new Button("Back");
		aboutToolbar.getChildren().add(aboutBackToMain);
		aboutBackToMain.setOnAction(e -> primaryStage.setScene(mainScene));
		
		Image logo3=new Image("file:src/application/logo.png");
		ImageView image3=new ImageView(logo3);
		
		Text aboutTitle=new Text("CONWAYS GAME OF LIFE\n");
		aboutTitle.setStyle("-fx-font-size: 14");
		aboutTitle.setTextAlignment(TextAlignment.CENTER);

		Text aboutText=new Text("The Game of Life, also known simply as Life, is a cellular automaton devised"
		+ "by the British mathematician John Horton Conway in 1970. The game is a zero-player game, meaning that its evolution"
		+ "is determined by its initial state, requiring no further input. One interacts with the Game of Life by creating an initial"
		+ "configuration and observing how it evolves, or, for advanced players, by creating patterns with particular properties.");

		aboutText.setStyle("-fx-font-size: 14");
		aboutText.setTextAlignment(TextAlignment.CENTER);
		aboutText.setWrappingWidth(400);
	
		aboutVBox.getChildren().addAll(image3, aboutTitle, aboutText);
		
		Scene aboutScene=new Scene(aboutLayout,500,750);
		aboutScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


		
		//Setting up the RULES scene
		
		BorderPane rulesLayout=new BorderPane();
		rulesLayout.setStyle("-fx-background-color: rgb(255, 255, 255);");
		
		VBox rulesVBox=new VBox(8);
		rulesVBox.setAlignment(Pos.TOP_CENTER);
		rulesVBox.setPadding(new Insets(30, 0, 0, 0));
		
		rulesLayout.setCenter(rulesVBox);
		
		HBox rulesToolbar=new HBox();
		rulesToolbar.setPrefHeight(30);
		
		
		rulesLayout.setTop(rulesToolbar);
		
		Button rulesBackToMain=new Button("Back");
		rulesToolbar.getChildren().add(rulesBackToMain);
		rulesBackToMain.setOnAction(e -> primaryStage.setScene(mainScene));
		
		Image logo4=new Image("file:src/application/logo.png");
		ImageView image4=new ImageView(logo4);
		
		Text rulesTitle=new Text("THE RULES\n");
		rulesTitle.setStyle("-fx-font-size: 14");
		rulesTitle.setTextAlignment(TextAlignment.CENTER);

		Text rulesText=new Text("The universe of the Game of Life is an infinite two-dimensional orthogonal grid of square cells,"
		+ "each of which is in one of two possible states, alive or dead. Every cell interacts with its eight neighbours, which are the"
		+ "cells that are horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions occur:"
		+ "\n\n1. Any live cell with fewer than two live neighbours dies, as if caused by under-population."
		+ "\n2. Any live cell with two or three live neighbours lives on to the next generation."
		+ "\n3. Any live cell with more than three live neighbours dies, as if by over-population."
		+ "\n4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.");

		rulesText.setStyle("-fx-font-size: 14");
		rulesText.setTextAlignment(TextAlignment.CENTER);
		rulesText.setWrappingWidth(400);
	
		rulesVBox.getChildren().addAll(image4, rulesTitle, rulesText);
		
		Scene rulesScene=new Scene(rulesLayout,500,750);
		rulesScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


	
		
		//Setting up the Game Scene - this is were the game will be played
		BorderPane borderLayout=new BorderPane();
		borderLayout.setStyle("-fx-background-color: rgb(255, 255, 255);");
		
		Pane matrixLayout=new Pane();
		matrixLayout.setPrefSize(700,700);
		
		VBox controls=new VBox(8);
		controls.setPrefSize(500,700);
		controls.setAlignment(Pos.CENTER);
		
		HBox toolbar=new HBox();
		toolbar.setPrefHeight(30);
		
		borderLayout.setCenter(matrixLayout);
		borderLayout.setRight(controls);
		borderLayout.setTop(toolbar);
		
		Button backToMain=new Button("Back");
		backToMain.setOnAction(e -> primaryStage.setScene(mainScene));
		
		Region region1=new Region();
		HBox.setHgrow(region1, Priority.ALWAYS);
		
		Button music=new Button("Music");
		
		
		toolbar.getChildren().addAll(backToMain,region1,music);	
		
		Text labelLifeCycles=new Text(280,690, "Life cycles: 0");
		labelLifeCycles.setStyle("-fx-font-size: 14");
	
		matrixLayout.getChildren().add(labelLifeCycles);
		
		ImageView image2=new ImageView(logo);
		Button newGameButton=new Button("NEW GAME");
		Button startButton=new Button("START");
		Button randomButton=new Button("RANDOM GAME");
		Button pauseButton=new Button("PAUSE");
		Button saveButton=new Button("SAVE");
		Button loadButton=new Button("LOAD");
		Region instructionBuffer=new Region();
		instructionBuffer.setPrefHeight(100.0);
		Text instructions=new Text("Click on a cell to give it life or click RANDOM GAME for a new random game");
		instructions.setWrappingWidth(300);
		instructions.setTextAlignment(TextAlignment.CENTER);
		
		controls.getChildren().addAll(image2,newGameButton,startButton,pauseButton, randomButton,
				saveButton, loadButton, instructionBuffer, instructions);
		controls.setAlignment(Pos.TOP_CENTER);
		
		
		Scene gameScene=new Scene(borderLayout,1200,750);
		gameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		
	    final URL resource = getClass().getResource("conway_music.mp3");
	    final Media media = new Media(resource.toString());
	    final MediaPlayer mediaPlayer = new MediaPlayer(media);
	    
	      
		//When the START button in the main menu is clicked go to game Scene
		mainStart.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {

            	primaryStage.setScene(gameScene);

            }
        });
		
		//When the START button in the main menu is clicked start playing the music
		mainStart.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {

            	mediaPlayer.play();

            }
        });
		
		music.setOnAction(e -> {
			if(!mediaPlayer.isMute()){
				mediaPlayer.setMute(true);
			}
			else{
				mediaPlayer.setMute(false);
			}
		}
		);
		
		//When the ABOUT button in the main menu is clicked go to aboutScene
		about.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
		{
		    @Override
		    public void handle(MouseEvent t) {

		         primaryStage.setScene(aboutScene);

		   }
		});
		
		
		//When the RULES button in the main menu is clicked go to rulesScene
		rules.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
		{
		    @Override
		    public void handle(MouseEvent t) {

		         primaryStage.setScene(rulesScene);

		   }
		});
		
		
		//Setting up the screen for saving (save Scene)
		BorderPane saveLayout=new BorderPane();
		saveLayout.setStyle("-fx-background-color: rgb(255, 255, 255);");

		VBox saveVBox=new VBox(8);
		saveVBox.setAlignment(Pos.TOP_CENTER);
		saveVBox.setPadding(new Insets(60, 0, 0, 0));

				
		saveLayout.setCenter(saveVBox);

		HBox saveButtons=new HBox();
		saveButtons.setAlignment(Pos.CENTER);

		Button savingButton=new Button("SAVE");
		Button cancelSaveButton=new Button("CANCEL");

		saveButtons.getChildren().addAll(savingButton, cancelSaveButton);

		TextField savingName=new TextField();
		savingName.setAlignment(Pos.CENTER);


		Label savingLabel=new Label("Enter configuration name");
		savingLabel.setPadding(new Insets(30,0,0,0));

		ListView<String> savedList=new ListView<String>();

		Label listLabel=new Label("Saved configurations");

		saveVBox.getChildren().addAll(listLabel, savedList, savingLabel, savingName, saveButtons);



		Scene saveScene=new Scene(saveLayout,500,750);
		saveScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


		
		//Setting up the screen for loading (load Scene)
		BorderPane loadLayout=new BorderPane();
		loadLayout.setStyle("-fx-background-color: rgb(255, 255, 255);");

		VBox loadVBox=new VBox(8);
		loadVBox.setAlignment(Pos.TOP_CENTER);
		loadVBox.setPadding(new Insets(60, 0, 0, 0));
						
		loadLayout.setCenter(loadVBox);

		HBox loadButtons=new HBox();
		loadButtons.setAlignment(Pos.CENTER);

		Button loadingButton=new Button("LOAD");
		Button cancelLoadButton=new Button("CANCEL");

		loadButtons.getChildren().addAll(loadingButton, cancelLoadButton);

		ListView<String> loadList=new ListView<String>();

		Label loadLabel=new Label("Load configuration");

		loadVBox.getChildren().addAll(loadLabel,loadList,loadButtons);


		Scene loadScene=new Scene(loadLayout,500,750);
		loadScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		

		//Pulling in the names of the previously saved matrices into the save, and load ListView
		setNames(savedItems, savedList, loadList);
		
		
				
		
		//Setting up the grid for the cells
		final int ROWS=120;
		final int COLUMNS=120;
		final int matrixPositionX=50;
		final int matrixPositionY=50;
		
		CellMatrix matrix=new CellMatrix(matrixPositionX,matrixPositionY,ROWS,COLUMNS);
		
				
		//Making a border around the matrix
		Line leftBorder = new Line(matrixPositionX,matrixPositionY,matrixPositionX,matrixPositionY+(5*COLUMNS));
		Line rightBorder = new Line(matrixPositionX+(5*ROWS),matrixPositionY,matrixPositionX+(5*ROWS),matrixPositionY+(5*COLUMNS));
		Line topBorder = new Line(matrixPositionX,matrixPositionY,matrixPositionY+(5*COLUMNS),matrixPositionY);
		Line bottomBorder = new Line(matrixPositionX,matrixPositionY+(5*COLUMNS),matrixPositionX+(5*ROWS),matrixPositionY+(5*COLUMNS));
		
		matrixLayout.getChildren().addAll(leftBorder, rightBorder, topBorder, bottomBorder);
		
		
		//Add cells to the Scene (at the start they are all dead)
		//makes them respond to mouse clicks as well - if the cell is the it becomes alive when clicked, or if it alive it dies when clicked
		for(int i=0;i<ROWS;i++){
			for(int j=0;j<COLUMNS;j++){
				matrix.getCells()[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
		        {
		            @Override
		            public void handle(MouseEvent event) {
		            	
		            	if(((Cell) event.getSource()).getAlive()){
		            		((Cell) event.getSource()).killCell();
		    			}
		    			else{
		    				((Cell) event.getSource()).giveLife();
		    			}

		            
		        }
		        });
	
				matrixLayout.getChildren().add(matrix.getCells()[i][j]);
			}
			
		}
		
		
		//At each frame of the game evolve the cell matrix to a new state
		AnimationTimer timer=new AnimationTimer() {
            @Override
            public void handle(long now) {
            CellMatrix newCellMatrix=matrix;
            newCellMatrix=newCellMatrix.evolveMatrix();
            for(int i=0;i<ROWS;i++){
    			for(int j=0;j<COLUMNS;j++){
    				if(newCellMatrix.getCells()[i][j].getAlive()){
    					matrix.getCells()[i][j].giveLife();
    				}
    				if(!newCellMatrix.getCells()[i][j].getAlive()){
    					matrix.getCells()[i][j].killCell();
    				}
    			}
    		}
            
            matrix.lifeCycles++;
            labelLifeCycles.setText("Life cycles: "+matrix.lifeCycles);
                        
            }
		};
	       
		
		
		//When START in the Game scene is clicked the the game starts
		startButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t) {

            	timer.start();

            }
        });
		
		//When START button in the Game scene is clicked change the instructions
		startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {

            	instructions.setText("You can click on cells to make them dead or alive even while the game is playing.");

            }
        });
		
		//When PAUSE button is clicked the game pauses
		pauseButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t) {
            	//matrix.interrupt();
            	timer.stop();

            }
        });
		
		//When PAUSE button is clicked change the instructions
		pauseButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {

            	instructions.setText("The Game is paused. Click on cells to change their life status. Press START to start the Game again");

            }
        });
		
		//When NEW GAME button is clicked kill all matrix Cells and set life cycles counter back to 0
		newGameButton.setOnAction(new EventHandler<ActionEvent>()
        {
		       @Override
	            public void handle(ActionEvent t) {
		    	    timer.stop();
		    	    
		    	    matrix.killAllCells();
		    	    
	            	matrix.lifeCycles=0;
	            	labelLifeCycles.setText("Life cycles: 0");
	            }
	        });
		
		//When NEW GAME button is clicked change the instructions
		newGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {

            	instructions.setText("Click on a cell to give it life or click RANDOM GAME for a new random game.");

            }
        });
		
		//When RANDOM button is clicked random cells in the grid are brought to life
		//and then the game starts
		randomButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent t) {
            	timer.stop();
            	for(int i=0;i<ROWS;i++){
            		for(int j=0;j<COLUMNS;j++){
            			double number=Math.random();
    				
            			if(number<0.4){
            				matrix.getCells()[i][j].giveLife();
            			}
            		}
            	}
            matrix.lifeCycles=0;
            timer.start();
            }
        });
		
		//When RANDOM button is clicked change the instructions
		randomButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {

            	instructions.setText("Playing a random game. Yuu can click on cells to make them dead or alive even while the game is playing. "
            			+ "Press NEW GAME to start a new game.");

            }
        });
		
		//When the SAVE button is clicked the game pauses and the saving scene (menu) is shown
		saveButton.setOnAction(new EventHandler<ActionEvent>()
		{
		      @Override
		       public void handle(ActionEvent t) {
		    	  
		    	  timer.stop();
		    	  
		    	  primaryStage.setScene(saveScene);
		           
		            
		           

		      }
		});
		
		//When the LOAD button is clicked the game pauses and the loading scene (menu) is shown
		loadButton.setOnAction(new EventHandler<ActionEvent>()
		{
		      @Override
		       public void handle(ActionEvent t) {
		           timer.stop();
	
		           primaryStage.setScene(loadScene);
		      }
		});
		
		
		
		
		
		
		//Setting up the main SAVE Button that adds the name of the matrix to the ListView
		//and saves the matrix through Hibernate session
		savingButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {

               	saveMatrix(savedItems, savingName, savedList, loadList,matrix);
            	primaryStage.setScene(gameScene);

            }
        });
		

		//Setting up the main LOAD Button that loads the matrix that was selected from the ListView
		loadingButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
            	
            	loadMatrix(loadList, matrix, ROWS, COLUMNS);
            	matrix.lifeCycles=0;
            	labelLifeCycles.setText("Life cycles: 0");
            	primaryStage.setScene(gameScene);

 
            }
        });
		
		//Setting up the cancelSaveButton
		cancelSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {

            	primaryStage.setScene(gameScene);

            }
        });
		
		
		//Setting up the cancelLoadButton
		cancelLoadButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {

            	primaryStage.setScene(gameScene);

            }
        });
		
		//Set the Stage
		primaryStage.setScene(mainScene);
		primaryStage.show();
		
			
	}
	
	
private void saveMatrix(ObservableList<String> savedItems, TextField savingName, ListView<String> savedList, ListView<String> loadList, CellMatrix matrix){
	savedItems.add(savingName.getText());
	savedList.setItems(savedItems);
	loadList.setItems(savedItems);
	
	//Create new CellMatrix Object that will be saved to the database
	CellMatrix newMatrix=new CellMatrix();
	
	//Match the life status of the cells in the new CellMatrix object to the one of the original matrix
	newMatrix.setMatrixName(savingName.getText());
	for(int i=0; i<matrix.getCellList().size();i++){
		newMatrix.getCellList().add(new Cell());
		if(matrix.getCellList().get(i).getAlive()){
			newMatrix.getCellList().get(i).setAlive(true);
		}
		else {newMatrix.getCellList().get(i).setAlive(false);}
		newMatrix.getCellList().get(i).setMatrixColumn(matrix.getCellList().get(i).getMatrixColumn());
		newMatrix.getCellList().get(i).setMatrixRow(matrix.getCellList().get(i).getMatrixRow());
	}
			           
	Configuration cfg = new Configuration();
	cfg.configure("hibernate.cfg.xml");
			    
	SessionFactory factory = cfg.buildSessionFactory();
	Session session = factory.openSession();
			       	
	Transaction tx=session.beginTransaction();
			       	
	session.save(newMatrix);

	tx.commit();
	System.out.println("Object saved successfully.....!!");
	session.close();
	factory.close();
	
}
	
@SuppressWarnings({ "unchecked"})
private void loadMatrix(ListView<String> loadList, CellMatrix matrix, int ROWS, int COLUMNS){
	String selectedItem =  loadList.getSelectionModel().getSelectedItem();


	Configuration cfg = new Configuration();
	cfg.configure("hibernate.cfg.xml");
	            			    
	SessionFactory factory = cfg.buildSessionFactory();
	Session session = factory.openSession();
	            			       	
	Transaction tx=session.beginTransaction();
	   
	            	
	TypedQuery<CellMatrix> query=session.createQuery("from CellMatrix where matrixName='"+selectedItem+"'");
	CellMatrix loadedMatrix=query.getSingleResult();
	
    //MAtch the life status of the cells in the matrix to the ones of the loaded CellMatrix object		
    for(int i=0;i<ROWS;i++){
		for(int j=0;j<COLUMNS;j++){
			if(loadedMatrix.getCellList().get((i*ROWS)+j).getAlive()){
				matrix.getCells()[i][j].giveLife();
			}
			if(!loadedMatrix.getCellList().get((i*ROWS)+j).getAlive()){
				matrix.getCells()[i][j].killCell();
			}
		}
	}
    
    tx.commit();
	System.out.println("Object loaded successfully.....!!");
	session.close();
	factory.close();



}

//Gets the names of every CellMatrix object that was saved in the database and shows them in ListView
private void setNames(ObservableList<String> savedItems, ListView<String> savedList, ListView<String> loadList){
	Configuration cfg = new Configuration();
	cfg.configure("hibernate.cfg.xml");
			    
	SessionFactory factory = cfg.buildSessionFactory();
	Session session = factory.openSession();
			       	
	Transaction tx=session.beginTransaction();
				       	

	@SuppressWarnings("unchecked")
	TypedQuery<CellMatrix> query=session.createQuery("from CellMatrix");
	List<CellMatrix> matrices= query.getResultList();
	for(CellMatrix m:matrices){
		savedItems.add(m.getMatrixName());
	}
	
	savedList.setItems(savedItems);
	loadList.setItems(savedItems);
	
	tx.commit();
	System.out.println("Matrix names loaded successfully.....!!");
	session.close();
	factory.close();
}
			
}


