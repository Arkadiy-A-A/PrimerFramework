package framework.core;

import java.util.Date;

/**
 * Класс для работы со временем и задержками
 *
 * @author Arkadiy
 */
public class Timer {

    /**
     * Метка времени старта
     */
    public long startStamp;

    /**
     * Метод получения времени старта
     */
    public void start(){
        startStamp = getTimeStamp();
    }

    /**
     * Возвращает время в миллесекундах
     *
     * @return миллисекунды
     */
    public static long getTimeStamp() {
        return new Date().getTime();
    }

    /**
     * Метод проверяет превысили ли мы интервал отведённого времени
     *
     * @param seconds секунды которые задают интервал ожидания
     * @return true - значит время ожидание было превышено
     */
    public boolean expired(int seconds) {
        return getDifference(startStamp, getTimeStamp()) > seconds;
    }

    /**
     * Возвращает разницу в секундах между концом и началом
     *
     * @param start время начала
     * @param end   время конца
     * @return секунды
     */
    public static int getDifference(long start, long end) {
        return (int) ((end - start) / 1000);
    }

    /**
     * Функция для некоторых задержек
     *
     * @param milliseconds количество милисекунд
     */
    public static void waitInMilliSeconds(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
