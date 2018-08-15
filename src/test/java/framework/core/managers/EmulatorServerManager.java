package framework.core.managers;

import api.android.Android;
import framework.core.Timer;

import java.io.IOException;

/**
 * Класс отвечающий за запуск emulator
 *
 * @author Arkadiy
 */
public class EmulatorServerManager {

    /**
     * Метод запускает командную строку и в ней запускает emulator
     */
    public void startEmulator() {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("cmd.exe /c start cmd.exe /k \"" + ServerManager.getAndroidHome() +
                    "\\tools\\emulator.exe @Nexus-5x-Oreo-8.0\"");
            Timer.waitInMilliSeconds(30000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод останавливает emulator и закрывает консоль
     */
    public void stopEmulator() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Android.adb.killEmulator();
            Timer.waitInMilliSeconds(2000);
            runtime.exec("taskkill /F /IM cmd.exe");
            Timer.waitInMilliSeconds(2000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
