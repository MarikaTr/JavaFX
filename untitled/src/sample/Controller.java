package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class Controller {
    @FXML //обязательное указание
    private void handleButtonOnPress(   ActionEvent event){ // import javafx.event.ActionEvent;
        System.out.println("Hello");
    }
    @FXML
    private TextField textField;
    @FXML
    private void handleMouseEnter(MouseEvent event){ // import javafx.scene.input.MouseEvent;
        textField.setText(event.getSource().toString());
    }
    @FXML
    private TextArea textArea;
    @FXML
    private ListView listView1;
    @FXML
    private void handle(MouseEvent event){
        listView1.getItems().add("Hello!!\n");
    }
    @FXML
    private void handleRadioButton(ActionEvent event){
        RadioButton selRadio=(RadioButton)event.getSource();
//очищаем установленные стили (на сцене и на главной панели)
        selRadio.getScene().getStylesheets().clear();
        selRadio.getScene().getRoot().getStylesheets().clear();
//делаем switch по тексту на радиокнопке (в java 1.7 это стало возможно)
        switch (selRadio.getText()) {
            case "Коричневый":
//берем родительскую сцену (основу окна в JavaFX) и добавляем в ее список стилей новый из файла
                selRadio.getScene().getStylesheets().add(getClass().getResource("Gray.css").
                        toExternalForm());
                break;
            case "Синий":
                selRadio.getScene().getStylesheets().add(getClass().getResource("Blue.css").
                        toExternalForm());
                break;
            case "Зеленый":
                System.out.println("Ya tut");
                selRadio.getScene().getStylesheets().add(getClass().getResource("Green.css").
                        toExternalForm());
                break;
        }
    }
}
