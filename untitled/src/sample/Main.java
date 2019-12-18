package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Button btn = new Button(); //создаем кнопку
        btn.setText("Say 'Hello World'"); //задаем ей текст
        btn.setOnAction(new EventHandler<ActionEvent>() { //обработчик нажатия
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 300, 250); //создаем сцену
        primaryStage.setTitle("Hello World"); //создаем окно с заголовком
        primaryStage.setScene(scene); //вставляем в окно сцену
        primaryStage.show(); //показываем окно
    }


    public static void main(String[] args) {
        launch(args);
    }
}
