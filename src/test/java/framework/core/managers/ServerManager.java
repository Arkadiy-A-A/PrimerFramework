package framework.core.managers;

import java.io.IOException;
import java.util.Scanner;

/**
 * Класс для работы с информацией о сервере на котором гоняются тесты
 *
 * @author Arkadiy
 */
public class ServerManager {

    /**
     * Переменная для хранения типа ОС
     */
    public static String OS;

    /**
     * Переменная для хранения адреса ANDROID_HOME
     */
    private static String ANDROID_HOME;

    /**
     * Инициализирует и возвращает переменную ANDROID_HOME
     *
     * @return ANDROID_HOME
     */
    public static String getAndroidHome() {
        if (ANDROID_HOME == null) {
            ANDROID_HOME = System.getenv("ANDROID_HOME");
            if (ANDROID_HOME == null) {
                throw new RuntimeException("Не удалось найти ANDROID_HOME, убедитесь, что задана переменная окружения");
            }
        }
        return ANDROID_HOME;
    }

    /**
     * Метод возврощает семейство ОС на котором проводится тестирование
     *
     * @return тип ОС
     */
    public static String getOS() {
        if (OS == null) OS = System.getenv("os.name");
        return OS;
    }

    /**
     * Проверка на то что ОС Windows
     *
     * @return true - значит Windows
     */
    public static boolean isWindows() {
        return getOS().startsWith("Windows");
    }

    /**
     * Проверка на то что ОС Mac
     *
     * @return true - значит Mac
     */
    public static boolean isMac() {
        return getOS().startsWith("Mac");
    }

    /**
     * Метод позволяет посылать запросы ADB серверу на исполнение
     *
     * @param command команда для исполнение в ADB
     * @return ответ от сервера
     */
    public static String runCommand(String command) {
        String output = null;
        try {
            Scanner scanner = new Scanner(Runtime.getRuntime().exec(command).getInputStream()).useDelimiter("\\A");
            if (scanner.hasNext()) output = scanner.next();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return output;
    }

}
