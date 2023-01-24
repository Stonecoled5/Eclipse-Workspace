import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import javafx.geometry.Pos;
import java.util.Random;

public class DessertGame extends Application {

	private int score;
	
    @Override
    public void start(final Stage stage) {
        // Step 3 & 4
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 640, 480);
        stage.setTitle("Dessert in the Desert JavaFX Game");
        score = 0;
        // Step 5
        Label scoreLabel = new Label("Score: " + score);
        borderPane.setTop(scoreLabel);
        BorderPane.setAlignment(scoreLabel, Pos.TOP_LEFT);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> {
            Platform.exit();
        });
        exitButton.requestFocus();
        borderPane.setBottom(exitButton);
        BorderPane.setAlignment(exitButton, Pos.BOTTOM_RIGHT);
        
        // Step 6
        Pane pane = new Pane();
        borderPane.setCenter(pane);
        BorderPane.setAlignment(pane, Pos.CENTER);

        // Step 7
        Random random = new Random();
        Button[] buttons = new Button[8];
        for(int i = 0; i < 8; i++) {
        	if(i == 0) {
        		Button button = new Button("Dessert");
        		buttons[i] = button;
        		pane.getChildren().add(button);
        		button.setOnAction(event ->{
                	randomizeButtonPositions(random, buttons);
                	score++;
                	scoreLabel.setText("Score: " + score);
                	exitButton.requestFocus();
                });
        	}
        	else {
        	Button button = new Button("Desert");
        	buttons[i] = button;
        	pane.getChildren().add(button);
        	button.setOnAction(event ->{
            	randomizeButtonPositions(random, buttons);
            	score--;
            	scoreLabel.setText("Score: " + score);
            	exitButton.requestFocus();
            });}
        }
        randomizeButtonPositions(random, buttons);
        
        stage.setScene(scene);
        stage.show();
    }
    
    private void randomizeButtonPositions(java.util.Random random, Button[] buttons) {
    	for(int i =0; i < buttons.length; i++) {
    		buttons[i].setLayoutX(random.nextInt(600));
    		buttons[i].setLayoutY(random.nextInt(400));
    	}
    }
    
    public static void main(String[] args) {
        Application.launch();
    }
    
    
}
