import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Ryan on 7/8/2015.
 */
public class MainBoard extends Application {
    private static final int BOARD_WIDTH = 600;
    private static final int BOARD_HEIGHT = 600;
    private int score;
    private List<Circle> circleArray;
    private String moveStatus = "";
    private Scene menu, game;
    private Stage window;
    private GraphicsContext gc;
    private Dots dots;
    private Canvas canvas;
    private ColorText colorText = new ColorText();
    private ColorMatch colorMatch = new ColorMatch();
    private Color currentTextColor = colorText.randTextColor();
    private String descriptionColor = colorText.randDescColor();
    private Label label;

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

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.window = primaryStage;

        //Console errors resulting from circleArray
        circleArray = new ArrayList<>();
        dots = new Dots();
        score = 0;
        window.setOnCloseRequest(e -> closeProgram());

        //Create window + canvas
        window.setTitle("Color Blitz!");
        final Group root = new Group();
        canvas = new Canvas(BOARD_WIDTH,BOARD_HEIGHT);

        initMainMenu();

        this.gc = canvas.getGraphicsContext2D();

        StackPane gameLayout = new StackPane();
        game = new Scene(root);
        label = new Label(descriptionColor);

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                gc.setFill(Color.DIMGREY);
                gc.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

                //Fade/transition http://stackoverflow.com/questions/23190049/how-to-make-a-text-content-disappear-after-some-time-in-javafx
                //set text
                gc.setTextAlign(TextAlignment.CENTER);
                gc.setTextBaseline(VPos.CENTER);

                label.setFont(Font.font("Tahoma", 60));
                label.setTextFill(currentTextColor);
                label.setTranslateX((BOARD_WIDTH / 2) - 75);
                label.setTranslateY(20);

                Circle goodDot;
                if (dots.getDotArray().size() != 8) {
                    for (int i = 0; i <= 1000; i++) {
                        Random randX = new Random();
                        Random randY = new Random();

                        //Bound the screen so that the dots don't hide behind the edge of the left border
                        int rangeX = randX.nextInt((550 - 20) + 1) + 20;
                        //Bound the screen so that dots do not appear in the upper margin for rangeY
                        int rangeY = randY.nextInt((550 - 100) + 1) + 100;

                        goodDot = dots.drawDots(rangeX, rangeY);
                        if (goodDot != null) {
                            root.getChildren().add(goodDot);
                            circleArray.add(goodDot);
                        }
                    }
                }
                gc.setFill(Color.BLUE);
                gc.setFont(Font.font("Tahoma", 30));
                String scoreText = "Score: " + score;
                gc.fillText(scoreText, 520, 50);
                gc.strokeText(scoreText, 520, 50);

                //display moveStatus ie. correct or incorrect
                gc.setFill(Color.FIREBRICK);
                gc.fillText(moveStatus, BOARD_WIDTH / 2, 560);
                gc.strokeText(moveStatus, BOARD_WIDTH / 2, 560);
            }
        }.start();

        game.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int i;
                for (i = 0; i <= 7; i++) {
                    if (!circleArray.isEmpty() && circleArray.get(i).contains(mouseEvent.getX(), mouseEvent.getY())) {
                        if (colorMatch.getDescription(descriptionColor).equals(circleArray.get(i).getFill())) {
                            score++;
                            moveStatus = "Correct!";
                        } else if (!colorMatch.getDescription(descriptionColor).equals(circleArray.get(i).getFill())) {
                            score = 0;
                            moveStatus = "Incorrect!";
                        }
                        for (Circle c : circleArray)
                            root.getChildren().remove(c);
                        circleArray.clear();
                        dots.getDotArray().clear();
                        label.setText(descriptionColor = colorText.randDescColor());
                        label.setTextFill(currentTextColor = colorText.randTextColor());
                        FadeTransition fader = textFade(label);
                        SequentialTransition fade = new SequentialTransition(label,fader);
                        fade.play();
                    }
                }
            }
        });
        gameLayout.getChildren().addAll(canvas);

        root.getChildren().add(canvas);
        root.getChildren().add(label);
        window.setScene(menu);
        window.show();
    }

    public FadeTransition textFade(Node node) {
        FadeTransition fade = new FadeTransition(Duration.millis(50), node);
        fade.setFromValue(1);
        fade.setToValue(0);
        return fade;
    }

    public void closeProgram() {
        window.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
