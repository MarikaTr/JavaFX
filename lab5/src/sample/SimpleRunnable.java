package sample;

public class SimpleRunnable implements Runnable {
    private String s;
    private volatile boolean stopped=false;
    public SimpleRunnable (String str){
        s=str;
    }
    @Override
    public void run() { //основная функция потокового класса
        while (!stopped){
            System.out.println(s);
            try {//нужно для обработки вероятных ошибок при задержке потока
                Thread.sleep(500); //задерживаем работу потока на 0,5 секунды
            } catch (InterruptedException e) {
                System.out.println(s+" exit"); //печатаем сообщение
                return; //и выходим из потока
            }
        }
        Thread.currentThread().interrupt(); //прерываем поток объекта
    }
    public void stopThread() { //метод для остановки потока
        stopped=true;
    }
}
