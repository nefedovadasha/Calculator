package Apps;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class JavaFXCalculator extends Application {

    int buffer, value = 0;
    char operator = ' ';
    boolean isBuffedInitilized = false;
    String resStr = "";

    TextField tf = new TextField();

    @Override
    public void start(Stage myStage) throws Exception {
        myStage.setTitle("Calculator");

        GridPane rootNode = createPane();

        myStage.setScene(new Scene(rootNode,235,235));

        //tf.setPromptText("Enter number");
        GridPane.setColumnSpan(tf, 5);
        tf.setPrefSize(220, 30);
        rootNode.add(tf,0,0);

        for (int i = 1; i < 10; i++) {
            rootNode.add(createDigitButton(i+""),(i-1)%3,(i+2)/3);
        }

        Button btnZero = createDigitButton("0");
        btnZero.setPrefSize(85.0,40.0);
        GridPane.setColumnSpan(btnZero,2);
        rootNode.add(btnZero, 0,4);

        Button btnPlus = createOperatorButton("+");
        Button btnMulti = createOperatorButton("*");
        Button btnMinus = createOperatorButton("-");
        Button btnDiv = createOperatorButton("/");
        Button btnPoint = createOperatorButton(".");

        Button btnClear = createOperatorButton("C");
        Button btnDelete = createOperatorButton("←");

        Button btnResult = new Button("=");
        btnResult.setOnAction(ae->handleOperator(ae));
        btnResult.setPrefSize(40.0,85.0);
        GridPane.setRowSpan(btnResult,2);

        rootNode.add(btnPoint, 2,4);
        rootNode.add(btnPlus, 3,1);
        rootNode.add(btnMinus, 3,2);
        rootNode.add(btnMulti, 3,3);
        rootNode.add(btnDiv, 3,4);
        rootNode.add(btnDelete, 4,1);
        rootNode.add(btnClear,4,2);
        rootNode.add(btnResult,4,3);

        myStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Button createDigitButton(String n){
        Button newButton = new Button(n);
        newButton.setOnAction(ae->handleDigit(ae));
        newButton.setPrefSize(40.0,40.0);
        return newButton;
    }

    private Button createOperatorButton(String n){
        Button newButton = new Button(n);
        newButton.setOnAction(ae->handleOperator(ae));
        newButton.setPrefSize(40.0,40.0);
        return newButton;
    }

    private void handleOperator(ActionEvent ae){
        Button tempButton = (Button)ae.getSource();
        char tempOperator = tempButton.getText().charAt(0);

        if (tempOperator == '←') {
            resStr = resStr.substring(0, resStr.lastIndexOf(" ", resStr.length() - 2) + 1);
            tf.setText(resStr);
            value = 0;
            return;
        }
        if (!isBuffedInitilized){
            buffer = value;
            isBuffedInitilized = true;
        }
        else{
            switch (operator) {
                case ' ':
                    break;
                case '+':
                    buffer += value;
                    break;
                case '*':
                    buffer *= value;
                    break;
                case '-':
                    buffer -= value;
                    break;
                case '/':
                    buffer /= value;
                    break;
            }
            operator = ' ';
        }

        value = 0;

        if (tempOperator == '='){
            resStr += " = " + buffer + " ";
            tf.setText(resStr);
            resStr = buffer + " ";
            return;
        }
        else if (tempOperator == 'C') {
            buffer = 0;
            operator = ' ';
            isBuffedInitilized = false;
            resStr = "";
        }
        else {
            operator = tempOperator;
            resStr += " " + operator + " ";
        }

        tf.setText(resStr);
    }

    private void handleDigit(ActionEvent ae){
        Button tempButton = (Button)ae.getSource();
        int enterNum = Integer.parseInt(tempButton.getText());

        value = value * 10 + enterNum;

        resStr += enterNum;
        tf.setText(resStr);
    }

    private GridPane createPane(){
        GridPane newNode = new GridPane();
        newNode.setHgap(5);
        newNode.setVgap(5);
        newNode.setPadding(new Insets(25, 25, 25, 25));
        return newNode;
    }
}
