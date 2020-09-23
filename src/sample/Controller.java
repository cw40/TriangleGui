package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.canvas.Canvas;
import javafx.scene.text.Text;


public class Controller implements Initializable {

    @FXML private Circle bigCircle;
    @FXML private TextField legOneLength;
    @FXML private TextField legTwoLength;
    @FXML private Text errorTextBox;
    @FXML private Text numTooBig;
    @FXML private Button SubmitButton;
    @FXML private Button circleColor;
    @FXML private Button ClearButton;
    @FXML private Canvas canvas;


    String legTwoLengthVar;
    String legOneLengthVar;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        legOneLength.textProperty().addListener(event -> {
            System.out.println("entering text now...");
            legOneLengthVar = legOneLength.getText(); // grab from the input
            System.out.println("Leg two length: " + legOneLengthVar);
        });

        legTwoLength.textProperty().addListener(event -> {
            System.out.println("entering text now...");
            legTwoLengthVar = legTwoLength.getText(); // grab from the input
            System.out.println("Leg two length: " + legTwoLengthVar);
        });
        circleColor.setOnAction(event -> {

            bigCircle.setFill(Color.color(Math.random(), Math.random(), Math.random()));

        });


        SubmitButton.setOnAction(event -> {

            System.out.println("Submit button has been pressed :)");

        if (!legOneLengthVar.matches("\\d*")|| !legTwoLengthVar.matches("\\d*") || legOneLengthVar == null || legTwoLengthVar == null)  {
            errorTextBox.setText("Please make sure you input whole numbers only");// text that prints when other thing pressed
        }
        else {


            int legOneLengthVarInt = Integer.parseInt(legOneLengthVar);
            int legTwoLengthVarInt = Integer.parseInt(legTwoLengthVar);

            if (legOneLengthVarInt > 175 || legTwoLengthVarInt>175){
                numTooBig.setFill(Color.RED);

            }
            else {
                numTooBig.setFill(Color.BLACK );
                numTooBig.setStrokeWidth(1);

                int startX = (200 / 2 - (legOneLengthVarInt / 2));
                int endX = 200 / 2 + (legOneLengthVarInt / 2);
                int endY = 200 / 2 - (legTwoLengthVarInt / 2);
                int startY = 200 / 2 + (legTwoLengthVarInt / 2);
                int legHyptLengthVarInt = (int) Math.sqrt((endX - startX) * (endX - startX) + (endY - startY) * (endY - startY));


                //Calculates the angles and adds to array
                double anglesArray[] = TriangleMath.triangleMath(legOneLengthVarInt, legTwoLengthVarInt, legHyptLengthVarInt);

                //finds the angles from the angle array
                double angleA = anglesArray[0];
                double angleB = anglesArray[1];

                System.out.println("angle A: " + angleA + " Angle B: " + angleB + "Hypt length:" + legHyptLengthVarInt);


                canvas.getGraphicsContext2D().strokeLine(startX, startY, endX, startY);
                canvas.getGraphicsContext2D().strokeLine(endX, startY, endX, endY);
                canvas.getGraphicsContext2D().strokeLine(endX - 1, startY, endX - 1, endY);
                canvas.getGraphicsContext2D().strokeLine(startX, startY, endX, endY);

                if (legOneLengthVarInt > legTwoLengthVarInt) {
                    canvas.getGraphicsContext2D().strokeText(String.valueOf(Math.round(angleA)) + "°", endX - 20, endY);
                    canvas.getGraphicsContext2D().strokeText(String.valueOf(Math.round(angleB)) + "°", startX, startY - 20);

                } else {
                    canvas.getGraphicsContext2D().strokeText(String.valueOf(Math.round(angleB)) + "°", endX - 20, endY);
                    canvas.getGraphicsContext2D().strokeText(String.valueOf(Math.round(angleA)) + "°", startX, startY - 20);

                }

                canvas.getGraphicsContext2D().strokeText(String.valueOf(90) + "°", endX - 25, startY - 10);

            }



        }


        });

        ClearButton.setOnAction(event -> {

            canvas.getGraphicsContext2D().clearRect(0, 0, 200, 200);

        });
        //End of class
    }



}




        // discrete clickable things use .setOnAction to specify what they should do
       /*toggle.setOnAction(event -> {
            System.out.println("toggling the switch"); // just to test

            if (toggle.isSelected()) { // whether or not it's on
                bigCircle.setFill(Color.DARKRED);
            } else {
                bigCircle.setFill(Color.DODGERBLUE);
            }
        });

        // sliders and other non-discrete actions need listeners attached to a specific property
        slider.valueProperty().addListener((ov, old_val, new_val) -> { // "listen" to the value property of the slider
            System.out.println("Changing the slider to..." + new_val);
            progBar.setProgress((double)new_val/100); // update the progress bar, it's range is 0.0 - 1.0
        });

        // a text input is a discrete event that happens when you hit the RETURN key
        textInput.setOnAction(event -> {
            System.out.println("entering text now...");
            String text = textInput.getText(); // grab from the input
            textArea.setText(textArea.getText() + text + "\n"); // and add it to the output area

            textArea.selectPositionCaret(textArea.getLength()); // scroll the window down
            textArea.deselect();

            textInput.setText(""); // reset the input box
        });

        // set the background when this loads up, we could also do this in the fxml itself or in css
        background.setStyle("-fx-background-color: " + colors[currentColor]);

        // when the button is clicked
        button.setOnAction(event -> {
            System.out.println("clicking the button");

            currentColor = (currentColor + 1) % colors.length; // update the current color, but don't get array out of bounds
            background.setStyle("-fx-background-color: " + colors[currentColor]); // set the style
        });


        // "listen" to the selected property of the checkbox
        checkbox.selectedProperty().addListener((ov, old_val, new_val) -> {
            if (!checkbox.isSelected()) { // if it's not currently selected
                textArea.setStyle("-fx-background-color: #FFFFFF");
            } else {
                textArea.setStyle("-fx-control-inner-background: " + colors[colors.length - 1 - currentColor]);
            }
        });


        // set default fonts when this loads, this could also be in fxml or css
        textInput.setFont(Font.font("Arial"));
        textArea.setFont(Font.font("Arial"));
        font1.setSelected(true); // preselect the first radio button, to match the font

        // add a listener to the group of radio buttons
        myButtons.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            RadioButton selected = (RadioButton) myButtons.getSelectedToggle(); // get the selected one
            if (selected != null) { // if one is selected
                if (selected.getText().equals("Font 1")) { // check the text to see which is selected
                    textInput.setFont(Font.font("Arial"));
                    textArea.setFont(Font.font("Arial"));
                } else if (selected.getText().equals("Font 2")) {
                    textInput.setFont(Font.font("Monaco"));
                    textArea.setFont(Font.font("Monaco"));
                } else {
                    textInput.setFont(Font.font("Comic Sans MS"));
                    textArea.setFont(Font.font("Comic Sans MS"));
                }
            }
        });

*/

