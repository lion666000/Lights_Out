package com.example.lights_out;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class HelloController {
    int size = 3;



    private Button[][] buttons;

    private boolean[][] state;


    @FXML
    private GridPane gridPane;


    @FXML
    private Label endLabel;

    @FXML
    private Label clicksLabel;

    @FXML
    protected void endScreen(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Konec");
        alert.setHeaderText("Konec"); // Removes the header space
        alert.setContentText("Konec Hry");

        alert.showAndWait();
    }

    int clicks = 0;


    @FXML
    protected void size3(){
        size = 3;
        initialize();
    }

    @FXML
    protected void size5(){
        size = 5;
        initialize();
    }

    @FXML
    protected void size6(){
        size = 6;
        initialize();
    }

    @FXML
    protected void size9(){
        size = 9;
        initialize();
    }

    public void initialize() {
        gridPane.getChildren().clear();

        buttons =  new Button[size][size];
        state = new boolean[size][size];


        endLabel.setText("");
        clicks = 0;
        clicksLabel.setText("Current clicks : " + clicks);


        for (Node node : gridPane.getChildren()) {
            node.setDisable(false);
        }

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Button button = new Button();
                button.setPrefSize(100, 100);

                buttons[row][col] = button;

                Random random = new Random();

               if  (random.nextInt(2) == 1) {
                   state[row][col] = true;
               }
               else {
                   state[row][col] = false;
               }



                setColor(row, col);

                int r = row;
                int c = col;

                button.setOnAction(e -> {
                    clicks++;

                    clicksLabel.setText("Current clicks : " + clicks);

                    state[r][c] = !state[r][c];
                    setColor(r, c);

                    int rew = r;
                    int cel = c;

                    for (int i = 0; i < 4; i++) {
                        switch(i){
                            case 0:
                                rew++;//dole
                                break;
                            case 1:
                                //nahore
                                rew = r -1;
                                break;
                            case 2:
                                //vpravo
                                rew = r;
                                cel = c +1;
                                break;
                            case 3:
                                //vlevo
                                cel = c -1;
                                break;
                        }

                        try {
                            state[rew][cel] = !state[rew][cel];
                            setColor(rew, cel);
                        }
                        catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }

                    }

                    boolean end = false;



                    for (int rowan = 0; rowan < size; rowan++) {
                        for (int colan = 0; colan < size; colan++) {
                            if (!state[rowan][colan]) {
                                end = true;
                                System.out.println(rowan + " " + colan + " off");
                            }
                            else {
                                end = false;
                                System.out.println(rowan + " " + colan + " on");
                                break;
                            }
                            if (!end) {
                                break;
                            }
                        }
                        if (!end) {
                            break;
                        }
                    }

                    if (end){

                        for (Node node : gridPane.getChildren()) {
                            node.setDisable(true);
                        }

                        endLabel.setText("End");

                        endScreen();
                    }
                });

                gridPane.add(button, col, row);
            }
        }
    }

    @FXML
    private void setColor(int row, int col) {
        if  (state[row][col]) {
            buttons[row][col].setStyle("-fx-background-color: #00f6f0");
        }
        else {
            buttons[row][col].setStyle("-fx-background-color: #a6a6a6");
        }
    }

}
