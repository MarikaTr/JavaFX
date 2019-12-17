package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Controller {
    SimpleRunnable threadA=new SimpleRunnable("A"); //первый объект – реализует Runnable
    SimpleRunnable threadB=new SimpleRunnable("B"); //второй объект – реализует Runnable
    Thread tempThread=new Thread(threadB); //специальный поток для второго объекта
/*    @FXML //обработчик нажатия на кнопку запуска первого потока
    private void handleOnStartA(ActionEvent e){
//если поток только создан (т.е. еще не запускался), то запускаем его
        if (threadA.  getState()==Thread.State.NEW) threadA.run();
    }
    @FXML //обработчик нажатия на кнопку остановки первого потока
    private void handleOnStopA(ActionEvent e){
        threadA.stopThread(); //вызываем метод нашего класса, приводящий к остановке потока
        threadA=new SimpleThread("A"); //и создаем объект заново
    }*/
    @FXML //обработчик нажатия на кнопку запуска второго потока
    private void handleOnStart(ActionEvent e){
//если поток только создан (т.е. еще не запускался), то запускаем его
        if (tempThread.getState()==Thread.State.NEW) tempThread.start();
    }
    @FXML //обработчик нажатия на кнопку остановки второго потока
    private void handleOnStop(ActionEvent e){
        threadB.stopThread(); //вызываем метод нашего класса, приводящий к остановке потока
        threadB=new SimpleRunnable("B"); //и создаем объект заново
        tempThread=new Thread(threadB); //вместе с дополнительным потоком
    }
}
