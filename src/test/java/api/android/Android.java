package api.android;

import api.apps.Apps;
import framework.core.Adb;
import io.appium.java_client.android.AndroidDriver;

/**
 * Класс позволяющий получить доступ к основным элементам Framework
 *
 * @author Arkadiy
 */
public class Android {

    /**
     * Переменная позволяющая работать с приложениями. Инициализируется при создании AndroidDriver
     */
    public static Apps apps;

    /**
     * Переменная позволяющая работать с драйвером. Инициализируется при создании AndroidDriver
     */
    public static AndroidDriver driver;

    /**
     * Переменная позволяющая работать с adb. Инициализируется при создании AndroidDriver
     */
    public static Adb adb;

}
