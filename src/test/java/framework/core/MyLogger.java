package framework.core;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Класс отвечающий за логи при отслеживание ошибок
 *
 * @author Arkadiy
 */
public class MyLogger {

    /**
     * Переменная для логирования
     */
    public static Logger log = Logger.getLogger(MyLogger.class);

    static {
        log.setLevel(Level.DEBUG);
    }
}
