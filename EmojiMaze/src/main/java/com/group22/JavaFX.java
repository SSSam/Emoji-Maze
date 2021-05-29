package com.group22;

import com.group22.characters.CharacterEntity;
import com.group22.characters.MainCharacter;
import com.group22.score.Score;
import com.group22.world.world;
import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class JavaFX extends Application {

    final int TILE_SIZE = 25;
    final int maxTime = 1_000_000_000;

    // initialize world
    Group parent = new Group();
    Scene pending = new Scene(parent);
    Scene menu, help1, help2, help3, help4;

    String world =
                    "25 25" + "\n" +
                    "1 10" + "\n" +
                    "19 10" + "\n" + "\n" +

                    "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1" + "\n" +
                    "1 2 0 0 0 4 1 3 0 0 0 0 2 1 1 1 1 1 2 0 1 0 0 2 1" + "\n" +
                    "1 0 4 0 0 0 1 0 0 4 0 0 0 0 0 1 0 0 0 0 0 0 0 0 1" + "\n" +
                    "1 0 0 0 0 0 1 2 0 0 0 0 0 1 0 0 0 1 0 0 1 0 0 0 1" + "\n" +
                    "1 1 0 1 1 0 1 1 1 1 1 1 1 1 0 0 0 1 1 0 1 2 0 5 1" + "\n" +
                    "1 0 0 0 1 0 0 0 0 0 0 0 0 1 0 0 0 1 1 0 1 1 0 1 1" + "\n" +
                    "1 5 0 2 1 0 0 0 0 0 0 0 2 1 0 0 0 1 0 0 0 1 0 1 1" + "\n" +
                    "1 1 1 1 1 1 1 2 1 1 1 0 1 1 2 0 3 1 2 0 0 1 0 1 1" + "\n" +
                    "1 1 1 1 0 0 0 0 0 0 0 2 1 1 1 1 1 1 0 0 0 1 0 0 1" + "\n" +
                    "1 6 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 2 1" + "\n" +
                    "1 6 0 0 0 0 0 4 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 1 1" + "\n" +
                    "1 6 0 0 0 0 0 0 0 0 0 0 2 0 0 0 0 4 0 0 2 1 0 1 1" + "\n" +
                    "1 1 1 1 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 0 1 1" + "\n" +
                    "1 1 1 1 1 1 1 0 1 1 1 0 1 2 0 0 0 0 1 4 0 0 0 3 1" + "\n" +
                    "1 2 0 0 0 0 0 2 0 0 0 0 1 0 0 0 1 0 1 2 0 0 0 0 1" + "\n" +
                    "1 0 1 1 1 1 1 0 1 0 1 0 1 0 0 3 1 0 0 0 0 0 0 0 1" + "\n" +
                    "1 0 1 2 0 0 0 0 1 0 1 0 1 1 0 1 1 1 1 1 1 1 1 4 1" + "\n" +
                    "1 0 1 0 4 0 0 0 1 0 1 0 1 1 0 1 2 0 0 0 0 0 0 0 1" + "\n" +
                    "1 0 1 0 0 3 0 0 1 0 1 0 0 1 0 1 0 0 0 0 1 1 0 1 1" + "\n" +
                    "1 0 1 0 1 1 1 0 1 0 1 0 0 0 2 0 0 0 0 0 1 0 0 0 1" + "\n" +
                    "1 2 0 0 0 0 0 2 1 0 1 0 0 1 0 1 0 0 0 0 1 0 0 2 1" + "\n" +
                    "1 1 1 1 1 1 0 1 1 0 1 2 1 1 0 1 4 0 0 2 1 1 1 1 1" + "\n" +
                    "1 0 0 2 0 0 0 0 0 0 1 0 1 1 0 1 1 0 1 1 1 5 0 0 1" + "\n" +
                    "1 5 0 0 0 0 4 0 0 2 0 0 0 0 0 0 0 0 0 0 0 0 0 2 1" + "\n" +
                    "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1" + "\n";

    world mainWorld = new world(world);
    Image bg = new Image(String.valueOf(getClass().getResource("/textures/BG.png")));
    ImageView bgGame = new ImageView(new Image(String.valueOf(getClass().getResource("/textures/gameBG.png"))));
    // instruction images
    ImageView hBG1 = new ImageView(String.valueOf(getClass().getResource("/textures/instruct1.png")));
    ImageView hBG2 = new ImageView(String.valueOf(getClass().getResource("/textures/instruct2.png")));
    ImageView hBG3 = new ImageView(String.valueOf(getClass().getResource("/textures/instruct3.png")));
    ImageView hBG4 = new ImageView(String.valueOf(getClass().getResource("/textures/instruct4.png")));
    //game result display
    static Image GameOver = new Image(String.valueOf(JavaFX.class.getResource("/textures/GameOver.png")));
    Image Congrats = new Image(String.valueOf(getClass().getResource("/textures/Congrats.png")));
    

    MainCharacter mainCharacter = new MainCharacter(TILE_SIZE*mainWorld.getSpawnX(), TILE_SIZE*mainWorld.getSpawnY(),TILE_SIZE,TILE_SIZE);
    CharacterEntity enemy = new CharacterEntity(TILE_SIZE*mainWorld.getEspawnX(), TILE_SIZE*mainWorld.getEspawnY(), TILE_SIZE, TILE_SIZE);
    Image mainCharImage = null;
    Image enemyImage = null;

    // initialize score and score label
    static Score score = new Score();
    Label scoreLabel = new Label();

    // initialize enemy and bonus timer
    static Timer enemyTimer = new Timer();
    static Timer bonusTimer = new Timer();

    // initialize timer and timer label
    IntegerProperty timeSeconds = new SimpleIntegerProperty(300);
    Label timerText = new Label("Timer: ");
    Label timerLabel = new Label();
    static Timeline timeline;

    Timer gameTimer = new Timer();
    static int secondsPassed = 0;
    static Label finishedTimer = new Label();

    TimerTask endscreenTime = new TimerTask() {
        @Override
        public void run() {
            secondsPassed++;
        }
    };

    public JavaFX() throws IOException {
    }

    // Function that is called by main
    @Override
    public void start(final Stage stage) throws Exception {
        double randomNumMain = Math.random() * 5;
        //Randomly pick the character's emoji
        switch ((int) randomNumMain) {
            case 0:
                mainCharImage = new Image(String.valueOf(getClass().getResource("/textures/Sunglasses_Emoji.png")));
                break;
            case 1:
                mainCharImage = new Image(String.valueOf(getClass().getResource("/textures/Kissing_Face_with_Smiling_Eyes_Emoji.png")));
                break;
            case 2:
                mainCharImage = new Image(String.valueOf(getClass().getResource("/textures/kissing_Emoji.png")));
                break;
            case 3:
                mainCharImage = new Image(String.valueOf(getClass().getResource("/textures/Shyly_Smiling_Face_Emoji.png")));
                break;
            case 4:
                mainCharImage = new Image(String.valueOf(getClass().getResource("/textures/Nerd_with_Glasses_Emoji.png")));
                break;
        }
        //Randomly pick the enemy's emoji
        double randomNumEnemy = Math.random() * 5;
        switch ((int) randomNumEnemy) {
            case 0:
                enemyImage = new Image(String.valueOf(getClass().getResource("/textures/alien_head.png")));
                break;
            case 1:
                enemyImage = new Image(String.valueOf(getClass().getResource("/textures/alien.png")));
                break;
            case 2:
                enemyImage = new Image(String.valueOf(getClass().getResource("/textures/devil.png")));
                break;
            case 3:
                enemyImage = new Image(String.valueOf(getClass().getResource("/textures/ghost.png")));
                break;
            case 4:
                enemyImage = new Image(String.valueOf(getClass().getResource("/textures/goblin.png")));
                break;
        }

        // set window name and size
        stage.setTitle("Emoji Maze");
        stage.setWidth(bg.getWidth());
        //stage.setWidth(mainWorld.getWidth() * TILE_SIZE * (16.0 / 9.0));
        stage.setHeight(mainWorld.getHeight() * TILE_SIZE + 37);
        stage.setResizable(false);

        // root node of scene graph
        AnchorPane root = new AnchorPane();
        Group instructions1 = new Group();
        Group instructions2 = new Group();
        Group instructions3 = new Group();
        Group instructions4 = new Group();


        // import logo, enter button, and background images
        Label credit = new Label("CMPT 276 Group 22 Project");
        ImageView logo = new ImageView(String.valueOf(getClass().getResource("/textures/logo.png")));
        ImageView enterImage = new ImageView(String.valueOf(getClass().getResource("/textures/enter_button.png")));
        ImageView nextImage = new ImageView(String.valueOf(getClass().getResource("/textures/next_button.png")));
        ImageView helpImage = new ImageView(String.valueOf(getClass().getResource("/textures/help_button.png")));
        BackgroundImage windowBackground = new BackgroundImage((bg), null, null, null, null);

        // set background of parent to background image
        root.setBackground(new Background(windowBackground));

        // create enter button add image to it
        Button enter = new Button();
        enter.setGraphic(enterImage);
        Button enter4 = new Button();
        enter4.setGraphic(enterImage);
        enter4.setLayoutX((stage.getWidth()/2.0) - 160.0);
        enter4.setLayoutY(stage.getHeight()-225.0);

        // create help button and next buttons (for next page)
        // also set image and position buttons
        Button help = new Button();
        help.setGraphic(helpImage);

        Button next1 = new Button();
        next1.setGraphic(nextImage);
        next1.setLayoutX((stage.getWidth()/2.0) - 115.0);
        next1.setLayoutY(stage.getHeight()-200.0);

        Button next2 = new Button();
        next2.setGraphic(nextImage);
        next2.setLayoutX((stage.getWidth()/2.0) - 115.0);
        next2.setLayoutY(stage.getHeight()-200.0);

        Button next3 = new Button();
        next3.setGraphic(nextImage);
        next3.setLayoutX((stage.getWidth()/2.0) - 115.0);
        next3.setLayoutY(stage.getHeight()-200.0);

        // add corresponding background and buttons to scene graph roots
        instructions1.getChildren().addAll(hBG1, next1);
        instructions2.getChildren().addAll(hBG2, next2);
        instructions3.getChildren().addAll(hBG3, next3);
        instructions4.getChildren().addAll(hBG4, enter4);

        // create one scene per help page
        help1 = new Scene(instructions1);
        help2 = new Scene(instructions2);
        help3 = new Scene(instructions3);
        help4 = new Scene(instructions4);

        // switch scenes for each next button to the next page

        next1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(help2);
                stage.show();
            }
        });

        next2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(help3);
                stage.show();
            }
        });

        next3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(help4);
                stage.show();
            }
        });


        // bind timer to label (makes it change)
        timerLabel.textProperty().bind(timeSeconds.asString());

        // enter button for last instruction page
        enter4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scoreLabel.setFont(new Font("Comic Sans MS", 60));
                timerText.setFont(new Font("Comic Sans MS", 60));
                timerLabel.setFont(new Font("Comic Sans MS", 60));
                parent.getChildren().addAll(scoreLabel, bgGame);

                updateWorld(mainWorld);
                updateCharacter();

                stage.setScene(pending);
                stage.show();
                startGame();
            }
        });

        // help button from menu to first instruction page
        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(help1);
                stage.show();
            }
        });


        // define that enter switches to the game when pressed
        enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scoreLabel.setFont(new Font("Comic Sans MS", 60));
                timerText.setFont(new Font("Comic Sans MS", 60));
                timerLabel.setFont(new Font("Comic Sans MS", 60));
                parent.getChildren().addAll(scoreLabel, bgGame);

                updateWorld(mainWorld);
                updateCharacter();

                stage.setScene(pending);
                stage.show();
                startGame();
            }
        });

        // set position of logo
        root.setLeftAnchor(logo,10.0);
        root.setTopAnchor(logo,(stage.getHeight()/15.0));

        // set position of enter button
        root.setRightAnchor(enter,30.0);
        root.setTopAnchor(enter,(stage.getHeight()/3.75));

        // set position of help button
        root.setRightAnchor(help,30.0);
        root.setTopAnchor(help,(stage.getHeight()/2.0));


        // set style and position of credit
        credit.setFont(new Font("Comic Sans MS", 28));
        root.setRightAnchor(credit,0.0);
        root.setBottomAnchor(credit,0.0);

        // add logo and enter nodes as children to parent
         root.getChildren().addAll(logo, enter, credit, help);

        // set menu to root node and set stage to show the menu first
        menu = new Scene(root);
        stage.setScene(menu);
        stage.show();
    }

    // Calling this function re-output the map, run if map is changed

    // load images from file into Images
    Image background = new Image(String.valueOf(getClass().getResource("/textures/Assets_background.png")));
    Image wall = new Image(String.valueOf(getClass().getResource("/textures/Assets_wall.png")));
    Image reward = new Image(String.valueOf(getClass().getResource("/textures/money-bag.png")));
    Image punishment = new Image(String.valueOf(getClass().getResource("/textures/poop.png")));
    Image bonus = new Image(String.valueOf(getClass().getResource("/textures/Diamond.png")));
    Image closeGate = new Image(String.valueOf(getClass().getResource("/textures/closeGate.png")));
    Image openGate = new Image(String.valueOf(getClass().getResource("/textures/openGate.png")));

    // Load images for background
    public void updateWorld(world mainWorld) {
        // for every tile check if it is background or wall, then add to scene
        for (int i = 0; i < mainWorld.getHeight(); i++) {
            for (int j = 0; j < mainWorld.getWidth(); j++) {
                ImageView currentTile = new ImageView(background);
                // 0 background
                // 1 wall
                // 2 reward
                // 3 bonus reward showing
                // 4 punishment
                // 5 bonus reward disappearing

                switch(mainWorld.getTile(i,j)) {
                    case 0:
                        currentTile = new ImageView(background);
                        break;
                    case 1:
                        currentTile = new ImageView(wall);
                        break;
                    case 2:
                        currentTile = new ImageView(reward);
                        break;
                    case 3:
                        currentTile = new ImageView(bonus);
                        break;
                    case 4:
                        currentTile = new ImageView(punishment);
                        break;
                    case 5:
                        currentTile = new ImageView(background);
                        break;
                    case 6:
                        currentTile = new ImageView(closeGate);
                        break;
                    case 7:
                        currentTile = new ImageView(openGate);
                        break;
                }
                currentTile.setX(TILE_SIZE * i);
                currentTile.setY(TILE_SIZE * j);

                currentTile.setFitHeight(TILE_SIZE);
                currentTile.setFitWidth(TILE_SIZE);

                parent.getChildren().add(currentTile);
            }
        }
    }

    public void updateCharacter() {
        ImageView mainChar = new ImageView(mainCharImage);
        mainChar.setX(mainCharacter.getX());
        mainChar.setY(mainCharacter.getY());

        mainChar.setFitHeight(mainCharacter.getHeight());
        mainChar.setFitWidth(mainCharacter.getWidth());

        parent.getChildren().add(mainChar);
    }

    public void updateAroundCharacter() {
        for (int i = mainCharacter.getX()/TILE_SIZE - 1; i < mainCharacter.getX()/TILE_SIZE + 2; i++) {
            for (int j = mainCharacter.getY()/TILE_SIZE - 1; j < mainCharacter.getY()/TILE_SIZE + 2; j++) {
                ImageView currentTile = new ImageView(background);
                // 0 background
                // 1 wall
                // 2 reward
                // 3 bonus reward showing
                // 4 punishment
                // 5 bonus reward disappearing

                switch(mainWorld.getTile(i,j)) {
                    case 0:
                        currentTile = new ImageView(background);
                        break;
                    case 1:
                        currentTile = new ImageView(wall);
                        break;
                    case 2:
                        currentTile = new ImageView(reward);
                        break;
                    case 3:
                        currentTile = new ImageView(bonus);
                        break;
                    case 4:
                        currentTile = new ImageView(punishment);
                        break;
                    case 5:
                        currentTile = new ImageView(background);
                        break;
                    case 6:
                        currentTile = new ImageView(closeGate);
                        break;
                    case 7:
                        currentTile = new ImageView(openGate);
                        break;
                        
                }
                currentTile.setX(TILE_SIZE * i);
                currentTile.setY(TILE_SIZE * j);

                currentTile.setFitHeight(TILE_SIZE);
                currentTile.setFitWidth(TILE_SIZE);

                parent.getChildren().add(currentTile);
            }
        }
    }

    public void updateEnemy() {
        ImageView enemyChar = new ImageView(enemyImage);
        enemyChar.setX(enemy.getX());
        enemyChar.setY(enemy.getY());

        enemyChar.setFitHeight(enemy.getHeight());
        enemyChar.setFitWidth(enemy.getWidth());

        parent.getChildren().add(enemyChar);
    }

    public void updateAroundEnemy() {
        for (int i = enemy.getX()/TILE_SIZE - 1; i < enemy.getX()/TILE_SIZE + 2; i++) {
            for (int j = enemy.getY()/TILE_SIZE - 1; j < enemy.getY()/TILE_SIZE + 2; j++) {
                ImageView currentTile = new ImageView(background);
                // 0 background
                // 1 wall
                // 2 reward
                // 3 bonus reward, doesn't start on map
                // 4 punishment
                // 5 bonus reward disappear

                switch(mainWorld.getTile(i,j)) {
                    case 0:
                        currentTile = new ImageView(background);
                        break;
                    case 1:
                        currentTile = new ImageView(wall);
                        break;
                    case 2:
                        currentTile = new ImageView(reward);
                        break;
                    case 3:
                        currentTile = new ImageView(bonus);
                        break;
                    case 4:
                        currentTile = new ImageView(punishment);
                        break;
                    case 5:
                        currentTile = new ImageView(background);
                        break;
                }

                currentTile.setX(TILE_SIZE * i);
                currentTile.setY(TILE_SIZE * j);

                currentTile.setFitHeight(TILE_SIZE);
                currentTile.setFitWidth(TILE_SIZE);

                parent.getChildren().add(currentTile);
            }
        }
    }

    // create the random appearing and disappearing Bonus
    public void bonusSpawn() {
        TimerTask bonusAppear = new TimerTask(){
            public void run() { 
                for (int i = 0; i <TILE_SIZE ; i++) {
                    for (int j = 0; j < TILE_SIZE; j++) {
                        double random_double = (int) Math.random() *3;
                        if(random_double < 2){
                            switch(mainWorld.getTile(i,j)) {
                                case 3:
                                    mainWorld.setTile(i, j, 5);
                                    break;
                                case 5:
                                    mainWorld.setTile(i, j, 3);
                                    break;
                            }
                        }
                    }
                } 
            }
        };
        int max = 8000;
        int min = 4000;
        double randomNum = Math.random() * (max - min + 1) + min;;
        long random = (long)randomNum; 
        bonusTimer.schedule(bonusAppear, random, random);
    }

    public void updateBonus() {
        // check two specific type of bonus reward 
        for (int i = 0; i < mainWorld.getHeight(); i++) {
            for (int j = 0; j < mainWorld.getWidth(); j++) {
                ImageView currentTile = new ImageView(background);
                // 0 background
                // 1 wall
                // 2 reward
                // 3 bonus reward showing
                // 4 punishment
                // 5 bonus reward disappearing
                if(mainWorld.getTile(i, j)==3 || mainWorld.getTile(i, j)==5){
                    switch(mainWorld.getTile(i,j)) {
                        case 3:
                            currentTile = new ImageView(bonus);
                            break;
                        case 5:
                            currentTile = new ImageView(background);
                            break;
                    }
                    currentTile.setX(TILE_SIZE * i);
                    currentTile.setY(TILE_SIZE * j);

                    currentTile.setFitHeight(TILE_SIZE);
                    currentTile.setFitWidth(TILE_SIZE);

                    parent.getChildren().add(currentTile);
                }
            }
        }
    }

    public void updateGate(){
        // for every tile check if it is background or wall, then add to scene
        for (int i = 0; i < mainWorld.getHeight(); i++) {
            for (int j = 0; j < mainWorld.getWidth(); j++) {
                ImageView currentTile = new ImageView(background);
                // 0 background
                // 1 wall
                // 2 reward
                // 3 bonus reward showing
                // 4 punishment
                // 5 bonus reward disappearing
                if(mainWorld.getTile(i, j)==6){
                    switch(mainWorld.getTile(i,j)) {
                        case 6:
                            currentTile = new ImageView(openGate);
                            mainWorld.setTile(i, j, 7);
                            break;
                        }
                    currentTile.setX(TILE_SIZE * i);
                    currentTile.setY(TILE_SIZE * j);

                    currentTile.setFitHeight(TILE_SIZE);
                    currentTile.setFitWidth(TILE_SIZE);

                    parent.getChildren().add(currentTile);
                    }
                }
            }
        }

    //pop out the ending screen/result of the game
    public static void gameResult(ImageView imageView, String str){
        if (str == "Congrats!!")
            finishedTimer.setText("Completed game in " + secondsPassed + " seconds with a score of " + score.getScore() + "!");
        else if (str == "Game Over")
            finishedTimer.setText("Failed after " + secondsPassed + " seconds with a score of " + score.getScore() + ", try again.");
        finishedTimer.setFont(new Font("Comic Sans MS", 32));
        imageView.setX(0);
        imageView.setY(0);
        imageView.setFitHeight(600);
        imageView.setFitWidth(900);
        imageView.setPreserveRatio(true);
        Group root = new Group();
        root.getChildren().addAll(imageView, finishedTimer);
        Scene scene = new Scene(root, 900, 500);
        Stage stage = new Stage();
        stage.setTitle(str);
        stage.setScene(scene);
        stage.showAndWait();
        System.exit(0);
    }

    // draws the score label and updates whenever a reward or punishment is picked up
    public void drawScore() {
        parent.getChildren().remove(scoreLabel);
        scoreLabel.setText("Score: " + score.getScore());
        scoreLabel.setLayoutX(657);
        scoreLabel.setLayoutY(150);
        parent.getChildren().add(scoreLabel);
    }

    // draws timer and counts down from maxTime
    public void drawTimer() {
        parent.getChildren().addAll(timerText, timerLabel);
        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds.set(0);
        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(maxTime-1), new KeyValue(timeSeconds, maxTime)));
        timeline.playFromStart();

        timerText.setLayoutX(657);
        timerText.setLayoutY(350);
        timerLabel.setLayoutX(850);
        timerLabel.setLayoutY(350);
    }

    public void startGame(){
        // start enemy movement
        enemyTimer.schedule(new moveEnemy(mainCharacter,mainWorld,enemy,TILE_SIZE), 1000, 750);
        gameTimer.scheduleAtFixedRate(endscreenTime, 1000, 1000);

        drawScore();
        drawTimer();
        bonusSpawn();
        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                pending.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        // checked if tile is walkable and then move
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // Move the main character using the arrow buttons
                        switch (event.getCode()) {
                            case UP:
                                if (mainWorld.getTile(mainCharacter.getX()/TILE_SIZE,mainCharacter.getY()/TILE_SIZE - 1) != 1) {
                                    mainCharacter.setY(mainCharacter.getY() - TILE_SIZE);
                                }
                            break;
                            case DOWN:
                                if (mainWorld.getTile(mainCharacter.getX()/TILE_SIZE,mainCharacter.getY()/TILE_SIZE + 1) != 1) {
                                    mainCharacter.setY(mainCharacter.getY() + TILE_SIZE);
                                }
                            break;
                            case LEFT:
                                if (mainWorld.getTile(mainCharacter.getX()/TILE_SIZE - 1,mainCharacter.getY()/TILE_SIZE) != 1) {
                                    mainCharacter.setX(mainCharacter.getX() - TILE_SIZE);
                                }
                            break;
                            case RIGHT:
                                if (mainWorld.getTile(mainCharacter.getX()/TILE_SIZE + 1,mainCharacter.getY()/TILE_SIZE) != 1) {
                                    mainCharacter.setX(mainCharacter.getX() + TILE_SIZE);
                                }
                            break;
                        }

                        // Check what tile you walked into and add/subtract score accordingly

                        // walked into reward
                        if (mainWorld.getTile(mainCharacter.getX()/TILE_SIZE,mainCharacter.getY()/TILE_SIZE) == 2) {
                            score.regular();
                            drawScore();
                            mainWorld.setTile(mainCharacter.getX()/TILE_SIZE, mainCharacter.getY()/TILE_SIZE, 0);
                        }

                        // walked into bonus reward
                        if (mainWorld.getTile(mainCharacter.getX()/TILE_SIZE,mainCharacter.getY()/TILE_SIZE) == 3) {
                            score.bonus();
                            drawScore();
                            mainWorld.setTile(mainCharacter.getX()/TILE_SIZE, mainCharacter.getY()/TILE_SIZE, 0);
                        }

                        // walked into punishment
                        if (mainWorld.getTile(mainCharacter.getX()/TILE_SIZE,mainCharacter.getY()/TILE_SIZE) == 4) {
                            mainCharacter.setX(mainCharacter.getX());
                            score.punishment();
                            drawScore();
                            mainWorld.setTile(mainCharacter.getX()/TILE_SIZE, mainCharacter.getY()/TILE_SIZE, 0);
                        }

                        if(score.belowZero()){
                            gameOver();
                        }

                        if (mainCharacter.getX() == enemy.getX() && mainCharacter.getY() == enemy.getY()) {
                            gameOver();
                        }

                        if(score.getAllReward()){
                            updateGate();
                            if(mainWorld.getTile(mainCharacter.getX()/TILE_SIZE,mainCharacter.getY()/TILE_SIZE) == 7){
                                enemyTimer.cancel();
                                bonusTimer.cancel();
                                timeline.stop();
                                ImageView imageView = new ImageView(Congrats);
                                gameResult(imageView, "Congrats!!");
                            }
                        }
                    }
                });
                updateBonus();
                updateAroundCharacter();
                updateAroundEnemy();

                updateCharacter();
                updateEnemy();
                
            }
            
        };
        timer.start();
    }

    public static void gameOver() {
        System.out.println("gameOver function");
        enemyTimer.cancel();
        bonusTimer.cancel();
        timeline.stop();
        ImageView imageView = new ImageView(GameOver);
        gameResult(imageView,  "Game Over");
    }

    // Stops the enemy timer when game is closed
    public void stop() throws Exception {
        enemyTimer.cancel();
        bonusTimer.cancel();
        gameTimer.cancel();

    }
}
