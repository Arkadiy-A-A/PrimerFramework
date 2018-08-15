package framework.core.managers;

import framework.core.Timer;

import java.io.IOException;

/**
 * Класс отвечающий за запуск appium
 *
 * @author Arkadiy
 */
public class AppiumServerManager {

    /**
     * Метод запускает командную строку и в ней запускает appium
     */
    public void startServer() {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723 " +
                    "--session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\"");
            Timer.waitInMilliSeconds(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Метод останавливает appium и закрывает консоль
     */
    public void stopServer() {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("taskkill /F /IM node.exe");
            runtime.exec("taskkill /F /IM cmd.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
