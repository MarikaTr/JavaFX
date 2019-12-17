package sample;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
    final static int CANVAS_WIDTH = 400; //задаем начальные значения для канваса
    final static int CANVAS_HEIGHT = 400;
    ColorPicker colorPicker; // объект для выбора цвета рисования
    ComboBox combo;
    String shape;
    private void initDraw(GraphicsContext gc){ //метод для инициализации графики
        colorPicker = new ColorPicker(); //создаем объект для выбора цвета рисования
        colorPicker.setValue(Color.BLACK); //задаем ему начальное значение цвета
        combo = new ComboBox();
        combo.getItems().addAll("Rectangle","Circle", "Arc");
        combo.setValue("Rectangle");
        double canvasWidth = gc.getCanvas().getWidth(); //локальные значения размеров канваса
        double canvasHeight = gc.getCanvas().getHeight();

        gc.setStroke(Color.BLACK); //задаем цвет рисования
        gc.strokeRect(0,0,canvasWidth,canvasHeight);// рисуем прямоугольник по границам канваса
        gc.setFill(colorPicker.getValue()); //задаем цвет заполнения
        gc.setStroke(colorPicker.getValue()); //и рисования
        gc.setLineWidth(1); //задаем толщину линий
        shape = combo.getValue().toString();
        System.out.println(shape);
    }

    @Override
    public void start(final Stage primaryStage) {
        final Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT); //создаем канвас
//получаем его графический контекст
        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        initDraw(graphicsContext); //вызываем созданный метод для инициализации графики
//вешаем лиснеры на канвас для обработки событий мыши
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>(){
            //обрабатываем нажатие мыши
            @Override
            public void handle(MouseEvent event) {
                graphicsContext.beginPath(); //начинаем кривую
//передвигаем курсор на позицию, где была нажата клавиша мыши
                graphicsContext.moveTo(event.getX(), event.getY());
//устанавливаем цвет рисования из объекта colorPicker
                graphicsContext.setStroke(colorPicker.getValue());
                shape = combo.getValue().toString();
                graphicsContext.stroke(); //рисуем созданную кривую
                if (shape=="Rectangle"){
                    graphicsContext.strokeRect(event.getX(), event.getY(),
                    50.0, 50.0);
                }
                else if (shape=="Circle"){
                    graphicsContext.strokeOval(event.getX(), event.getY(),
                            50.0, 50.0);
                }
                else if (shape=="Arc"){
                    graphicsContext.strokeArc(event.getX(), event.getY(),
                            50.0, 50.0,
                    0,
                    45,
                    ArcType.OPENo);

                }
            }
        });

        BorderPane root = new BorderPane(); //создаем панель

        Button buttonSave = new Button("Save"); //кнопка для сохранения рисунка
        buttonSave.setOnAction(new EventHandler<ActionEvent>() { //обработчик нажатия
            @Override
            public void handle(ActionEvent t) {
                FileChooser fileChooser = new FileChooser(); //создаем диалог для работы с файлами
                //создаем фильтр расширений
                FileChooser.ExtensionFilter extFilter =
                        new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
                fileChooser.getExtensionFilters().add(extFilter); //применяем его к диалогу

                //показываем диалог сохранения файла
                File file = fileChooser.showSaveDialog(primaryStage);
                if(file != null){ //если был выбран файл для сохранения
//если в имени файла нет расширения .png, то добавляем это расширение к имени файла
                    if (!file.getPath().contains(".png")) file=new File(file.getPath()+".png");
                    try {
//создаем объект класса WritableImage, в который будем сохранять содержимое канваса
                        WritableImage writableImage =
                                new WritableImage(CANVAS_WIDTH, CANVAS_HEIGHT);
//записываем содержимое канваса в writableImage
                        canvas.snapshot(null, writableImage);
//создаем объект класса RenderedImage из объекта writableImage
                        RenderedImage renderedImage =
                                SwingFXUtils.fromFXImage(writableImage, null);
//и записываем его в созданный ранее файл
                        ImageIO.write(renderedImage, "png", file);
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
//горизонтальный бокс для выбора цвета рисования и кнопки сохранения
        HBox hBox = new HBox();
        Region spacer = new Region(); //спецобъект для пружины между кнопками
        HBox.setHgrow(spacer, Priority.ALWAYS); //задаем ему возможность расширения
//добавляем все созданные ранее объекты в бокс
        hBox.getChildren().addAll(colorPicker, combo, spacer, buttonSave);
        root.setTop(hBox); //вставляем бокс в верхнюю часть окна
        root.setCenter(canvas); //вставляем канвас в центр окна
        Scene scene = new Scene(root, 400, 425);
        primaryStage.setTitle("SuperPaint");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
