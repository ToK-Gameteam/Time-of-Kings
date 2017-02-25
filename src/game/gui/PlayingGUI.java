package game.gui;

import config.LanguageController;
import game.player.Player;
import game.sql.Db;
import game.util.Location;
import game.village.Building;
import game.village.Village;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PlayingGUI {
	private Stage primaryStage;
	private Canvas buildings, options, bg, buildingOptions, info;
	private GraphicsContext buildingsC, optionsC, bgc, buildingC, infoC;
	private Scene playingScene;
	private Group root;
	private HBox rooter;
	private Button save;
	private Db game;
	private LanguageController langController;

	private Player player;
	private Village village;
	
	private int screenWidth, screenHeight;

	public PlayingGUI(Stage primaryStage, int screenWidth, int screenHeight, Db game,
			LanguageController langController){
		this.primaryStage = primaryStage;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.game = game;
		this.langController = langController;
		save = new Button("Speichern");
		
		buildings = new Canvas(screenWidth, screenHeight);
		buildingsC = buildings.getGraphicsContext2D();
		
		options = new Canvas(screenWidth, screenHeight);
		optionsC = options.getGraphicsContext2D();
		
		bg = new Canvas(screenWidth, screenHeight);
		bgc = bg.getGraphicsContext2D();
		
		buildingOptions = new Canvas(screenWidth, screenHeight);
		buildingC = buildingOptions.getGraphicsContext2D();
		
		info = new Canvas(screenWidth, screenHeight);
		infoC = info.getGraphicsContext2D();
				
		root = new Group();
		rooter = new HBox();
		
		playingScene = new Scene(rooter);
		initialize();
	}
	
	private void initialize(){
		bgc.setFill(Color.FORESTGREEN);
		bgc.fillRect(0, 0, screenWidth, screenHeight);

		root.getChildren().addAll(buildings, options, bg, buildingOptions, info);
		rooter.getChildren().addAll(root, save);
		
		infoC.setStroke(Color.BLACK);
		infoC.setFill(Color.gray(0.5, 0.5));
		infoC.setLineWidth(2);
		
		bg.toBack();
		options.toBack();
		info.toFront();
		buildings.toFront();
		
		buildings.setOnMouseClicked(e->{
			showOptions((int)e.getX(), (int)e.getY());
		});
		save.setOnAction(e->{
			game.updatePlayer(player);
		});
	}
	
	public void play(Player player){
		this.player = player;
		village = player.getVillage();
		village.setLimit();
		draw();
		primaryStage.setScene(playingScene);
		primaryStage.setFullScreen(true);
		primaryStage.show();
	}
	
	private void draw(){
		buildingsC.clearRect(0, 0, screenWidth, screenHeight);
		infoC.clearRect(0, 0, screenWidth, screenHeight);
		Building[] buildings = village.getBuildings();
		for(Building building : buildings){
			if(building != null){
				buildingsC.drawImage(new Image("game/resources/0_" + building.getLevel() + "building.png"),
						building.getLocation().getLocationX()*50, building.getLocation().getLocationY()*50,
						50, 50);
			}
		}
		this.buildings.toFront();
		infoC.strokeRect(screenWidth-120, 20, 100, 10);
		double xWood = screenWidth-120+100-(player.getResources()[0]*1.0/player.getResources()[1]*1.0)*100.0;
		infoC.fillRect(xWood, 21, (player.getResources()[0]*1.0/player.getResources()[1]*1.0)*100.0, 8);
		infoC.strokeText(player.getResources()[0]+ "", screenWidth-150, 30);
		
		infoC.strokeRect(screenWidth-120, 40, 100, 10);
		double xStone = screenWidth-120+100-(player.getResources()[2]*1.0/player.getResources()[3]*1.0)*100.0;
		infoC.fillRect(xStone, 41, (player.getResources()[2]*1.0/player.getResources()[3]*1.0)*100.0, 8);
		infoC.strokeText(player.getResources()[2] + "", screenWidth-150, 50);

		infoC.strokeRect(screenWidth-120, 60, 100, 10);
		double xIron = screenWidth-120+100-(player.getResources()[4]*1.0/player.getResources()[5]*1.0)*100.0;
		infoC.fillRect(xIron, 61, (player.getResources()[4]*1.0/player.getResources()[5]*1.0)*100.0, 8);
		infoC.strokeText(player.getResources()[4] + "", screenWidth-150, 70);
	}
	
	private void showOptions(int helpX, int helpY){
		optionsC.clearRect(0, 0, screenWidth, screenHeight);
		Building[] buildings = village.getBuildings();
		options.toFront();
		int x = (int)helpX/50;
		int y = (int)helpY/50;
		int index = 0;
		for(Building building : buildings){
			if(building != null){
				if(building.getLocation().equals(new Location(x, y))){
					optionsC.drawImage(new Image("game/resources/loginPane.png"),
							screenWidth/2-20, screenHeight-50, 80, 40);
					String buildingDetails = "";
					switch(building.getType()){
					case(Village.COMMUNITY_HALL):
						buildingDetails += langController.getString("community_hall");break;
					case(Village.SAWMILL):
						buildingDetails += langController.getString("sawmill");break;
					case(Village.QUARRY):
						buildingDetails += langController.getString("quarry");break;
					case(Village.MINE):
						buildingDetails += langController.getString("mine");break;
					case(Village.APARTMENT):
						buildingDetails += langController.getString("apartment");break;
					case(Village.STORAGE):
						buildingDetails += langController.getString("storage");break;
					case(Village.WALL):
						buildingDetails += langController.getString("wall");break;
					}
					buildingDetails += (", " + langController.getString("level") + " " + building.getLevel());
					optionsC.strokeText(buildingDetails, screenWidth/2-10, screenHeight-60);
					optionsC.strokeText("Level-Up", screenWidth/2-10, screenHeight-30);
					options.toFront();
					final int i = index;
					options.setOnMouseClicked(e->{
						if(e.getX() >= screenWidth/2 && e.getX()<= screenWidth/2+40 
								&& e.getY() >= screenHeight-50 && e.getY() <= screenHeight-10){
							player.levelUp(building.getType(), i-building.getType());
							draw();
							
						}
						options.toBack();
					});
					if(building.getType() == Village.SAWMILL || building.getType() == Village.QUARRY || 
							building.getType() == Village.MINE){

						optionsC.drawImage(new Image("game/resources/loginPane.png"),
								 screenWidth/2+70, screenHeight-50, 80, 40);
						optionsC.strokeText(langController.getString("collect"), screenWidth/2+80, screenHeight-30);
						options.setOnMouseClicked(e->{
							if(e.getX() >= screenWidth/2 && e.getX()<= screenWidth/2+40 
									&& e.getY() >= screenHeight-50 && e.getY() <= screenHeight-10){
								player.levelUp(building.getType(), i-building.getType());
								draw();
							}else if(e.getX() >= screenWidth/2+70 && e.getX()<= screenWidth/2+150 
									&& e.getY() >= screenHeight-50 && e.getY() <= screenHeight-10){
								player.collect(building);
								draw();
							}
							options.toBack();
						});
					}
					return;
				}
			}
			++index;
		}
		optionsC.drawImage(new Image("game/resources/loginPane.png"),
				screenWidth/2, screenHeight-50, 80, 40);
		optionsC.strokeText(langController.getString("building_menu"), screenWidth/2+10,  screenHeight-30);
		options.setOnMouseClicked(e->{
			if(e.getX() >= screenWidth/2 && e.getX()<= screenWidth/2+40 
					&& e.getY() >= screenHeight-50 && e.getY() <= screenHeight-10){
				showBuildingOptions(x, y);
			}
			options.toBack();
		});
	}
	
	private void showBuildingOptions(int x, int y){
		buildingC.clearRect(0, 0, screenWidth, screenHeight);
		buildingC.drawImage(new Image("game/resources/loginPane.png"),
				50, 50, 200, 200);
		buildingC.strokeText(langController.getString("community_hall"), 100, 100);
		buildingC.drawImage(new Image("game/resources/loginPane.png"),
				300, 50, 200, 200);
		buildingC.strokeText(langController.getString("sawmill"), 350, 100);
		buildingC.drawImage(new Image("game/resources/loginPane.png"),
				550, 50, 200, 200);
		buildingC.strokeText(langController.getString("quarry"), 600, 100);
		buildingC.drawImage(new Image("game/resources/loginPane.png"),
				50, 300, 200, 200);
		buildingC.strokeText(langController.getString("mine"), 100, 350);
		buildingC.drawImage(new Image("game/resources/loginPane.png"),
				300, 300, 200, 200);
		buildingC.strokeText(langController.getString("apartment"), 350, 350);
		buildingC.drawImage(new Image("game/resources/loginPane.png"),
				550, 300, 200, 200);
		buildingC.strokeText(langController.getString("storage"), 600, 350);
		buildingC.drawImage(new Image("game/resources/loginPane.png"),
				50, 550, 200, 200);
		buildingC.strokeText(langController.getString("wall"), 100, 600);
		
		buildingOptions.toFront();
		buildingOptions.setOnMouseClicked(e->{
			if(e.getX() >= 50 && e.getX() <= 250 && e.getY() >= 50 && e.getY() <= 250){
				player.build(Village.COMMUNITY_HALL, new Location(x, y));
			}else if(e.getX() >= 300 && e.getX() <= 500 && e.getY() >= 50 && e.getY() <= 250){
				player.build(Village.SAWMILL, new Location(x, y));
			}else if(e.getX() >= 550 && e.getX() <= 750 && e.getY() >= 50 && e.getY() <= 250){
				player.build(Village.QUARRY, new Location(x, y));
			}else if(e.getX() >= 50 && e.getX() <= 250 && e.getY() >= 250 && e.getY() <= 450){
				player.build(Village.MINE, new Location(x, y));
			}else if(e.getX() >= 300 && e.getX() <= 500 && e.getY() >= 250 && e.getY() <= 450){
				player.build(Village.APARTMENT, new Location(x, y));
			}else if(e.getX() >= 550 && e.getX() <= 750 && e.getY() >= 250 && e.getY() <= 450){
				player.build(Village.STORAGE, new Location(x, y));
			}else if(e.getX() >= 50 && e.getX() <= 250 && e.getY() >= 500 && e.getY() <= 700){
				player.build(Village.WALL, new Location(x, y));
			}
			draw();
			buildingOptions.toBack();
			options.toBack();
		});
	}
	
/**	private Building getBuildingAt(int x, int y){
		for(Building building : village.getBuildings()){
			if(building != null && building.getLocation().equals(new Location((int)x/50, (int)y/50))){
				System.out.println(building.toString());
				return building;
			}
		}
		return null;
 	}**/	
}

