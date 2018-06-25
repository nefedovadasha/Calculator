package Apps.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculatorOverviewController {
    private int buffer, value = 0;
    private char operator = ' ';
    private boolean isBuffedInitilized = false;
    private String resStr = "";

    @FXML
    private Label resultLabel = new Label();

    @FXML
    private void handleOperator(ActionEvent ae){
        Button tempButton = (Button)ae.getSource();
        char tempOperator = tempButton.getText().charAt(0);

        if (tempOperator == '←') {
            resStr = resStr.substring(0, resStr.lastIndexOf(" ", resStr.length() - 2) + 1);
            resultLabel.setText(resStr);
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
            resultLabel.setText(resStr);
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

        resultLabel.setText(resStr);
    }

    @FXML
    private void handleDigit(ActionEvent ae){
        Button tempButton = (Button)ae.getSource();
        int enterNum = Integer.parseInt(tempButton.getText());

        value = value * 10 + enterNum;

        resStr += enterNum;
        resultLabel.setText(resStr);
    }

}
