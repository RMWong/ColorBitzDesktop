import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Ryan on 7/8/2015.
 */
public class MainBoard extends Application {
    private static final int BOARD_WIDTH = 600;
    private static final int BOARD_HEIGHT = 600;
    private int points;
    List<Circle> circleArray;
    private String moveStatus = "placeHolder";
    private Scene menu, game;
    private Stage window;
    private Group root;
    private GraphicsContext gc;
    private Dots dots;
    private Canvas canvas;

    /**
     * Initialize the main menu
     * @return Scene
     */
    public Scene initMainMenu() {
        //Menu button to play
        Button startBtn = new Button("Play!");
        startBtn.setOnAction(e -> window.setScene(game));

        //Menu layout
        StackPane menuLayout = new StackPane();
        menuLayout.getChildren().addAll(startBtn);
        menu = new Scene(menuLayout, BOARD_WIDTH, BOARD_HEIGHT);
        menuLayout.getChildren().addAll(canvas);
        return menu;
    }

    public Scene gotoGame() {
        //Game layout

        return game;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.window = primaryStage;
        points = 0;
        dots = new Dots();
        circleArray = new ArrayList<>();
        window.setOnCloseRequest(e -> closeProgram());

        //create window + canvas
        window.setTitle("Color Blitz!");
        final Group root = new Group();
        canvas = new Canvas(BOARD_WIDTH,BOARD_HEIGHT);

        initMainMenu();

        this.gc = canvas.getGraphicsContext2D();

        StackPane gameLayout = new StackPane();
        game = new Scene(root);

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                gc.setFill(Color.DIMGREY);
                gc.fillRect(0,0, BOARD_WIDTH, BOARD_HEIGHT);

                //set text
                gc.setTextAlign(TextAlignment.CENTER);
                gc.setTextBaseline(VPos.CENTER);
                gc.setFont(Font.font("Tahoma", 50));
                gc.setFill(Color.RED);
                gc.fillText("BLUE", BOARD_WIDTH / 2, 50);

                if (dots.getDotArray().size() != 8) {
                    for (int i = 0; i <= 1000; i++) {
                        Random randX = new Random();
                        Random randY = new Random();

                        //Bound the screen so that the dots don't hide behind the edge of the left bourder
                        int rangeX = randX.nextInt((550 - 20) + 1) + 20;
                        //Bound the screen so that dots do not appear in the upper margin for rangeY
                        int rangeY = randY.nextInt((550 - 100) + 1) + 100;

                        Circle goodDot = dots.drawDots(rangeX, rangeY);
                        if (goodDot != null) {
                            root.getChildren().add(goodDot);
                            circleArray.add(goodDot);
                        }
                    }
                }
                gc.setFill(Color.BLUE);
                String pointsText = "Points: " + points;
                gc.fillText(pointsText, 470, 50);
                gc.strokeText(pointsText, 470, 50);
                //for some reason this work...
                if (moveStatus.equals("Correct!")) {
                    gc.setFill(Color.FIREBRICK);
                    gc.fillText(moveStatus, BOARD_WIDTH/2, 560);
                    gc.strokeText(moveStatus, BOARD_WIDTH/2, 560);
                }
                else if (moveStatus.equals("Incorrect!")){
                    gc.setFill(Color.FIREBRICK);
                    gc.fillText(moveStatus, BOARD_WIDTH / 2, 560);
                    gc.strokeText(moveStatus, BOARD_WIDTH / 2, 560);
                }
            }
        }.start();

        game.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                for (int i = 0; i <= 7; i++) {
                    if (circleArray.get(i).getFill().equals(Color.BLUE) && circleArray.get(i).contains(mouseEvent.getX(), mouseEvent.getY())) {
                        System.out.println("YES!");
                        points++;
                        moveStatus = "Correct!";
                    } else if (!circleArray.get(i).getFill().equals(Color.BLUE) && circleArray.get(i).contains(mouseEvent.getX(), mouseEvent.getY())) {
                        System.out.println("NO!");
                        points = 0;
                        moveStatus = "Incorrect!";
                    }
                }
            }
        });

        gameLayout.getChildren().addAll(canvas);


        root.getChildren().add(canvas);
        window.setScene(menu);
        window.show();
    }

    public void closeProgram() {
        System.out.println("Program is saved!");
        window.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
