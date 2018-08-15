package framework.core.managers;

import api.android.Android;
import api.apps.Apps;
import framework.core.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс является менеджером над драйвером
 *
 * @author Arkadiy
 */
public class DriverManager {

    /**
     * Наши хосты подклчения
     */
    private static HashMap<String, URL> hosts;

    /**
     * Разблокирующий пакет
     */
    private static String unlockPackage = "io.appium.unlock";

    /**
     * Переменная позволяющая работать с appiumServer.
     */
    private static AppiumServerManager appiumServer = new AppiumServerManager();

    /**
     * Переменная позволяющая работать с emulator.
     */
    private static EmulatorServerManager emulatorServer = new EmulatorServerManager();

    /**
     * Получения нужных параметры для инициализации драйвера
     *
     * @param deviceID id устройства или имя эмулятора
     * @return DesiredCapabilities нужные параметры для инициализации драйвера
     */
    private static DesiredCapabilities getCaps(String deviceID){
        MyLogger.log.info("Создания нужного набора параметров для инициализации драйвера AndroidDriver уст-ва: " + deviceID);
        DesiredCapabilities caps = new DesiredCapabilities();
        // Простой после которой происходит разрыв сессии
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "300");
        // Устройство с портом
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceID);
        // Тестируемая платформа
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        // Пакет запуска
        caps.setCapability("appPackage", "io.crash.air");
        // Путь до самой apk калькулятора
        File app = new File(".\\src\\test\\resources\\Calculator.apk");
        caps.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        MyLogger.log.info("Набор параметров для инициализации драйвера AndroidDriver уст-ва: " + deviceID + " созданы.");
        return caps;
    }

    /**
     * Метод для инициализации устройства на котором проходит тестирование
     *
     * @param deviceID id устройства или имя эмулятора
     * @return URL устройства
     * @throws MalformedURLException
     */
    private static URL host(String deviceID) throws MalformedURLException {
        if (hosts == null) {
            hosts = new HashMap<>();
            hosts.put(deviceID, new URL("http://127.0.0.1:4723/wd/hub"));
        }
        return hosts.get(deviceID);
    }

    /**
     * Метод возвращает все устройства доступные для тестирования
     *
     * @return ArrayList<String> все доступные устройства
     */
    private static ArrayList<String> getAvailableDevices() {
        MyLogger.log.info("Проверка доступных устройств");
        ArrayList<String> avaiableDevices = new ArrayList<String>();
        ArrayList connectedDevices = Adb.getConnectedDevices();
        for (Object connectedDevice : connectedDevices) {
            String device = connectedDevice.toString();
            ArrayList apps = new Adb(device).getInstalledPackages();
            if (!apps.contains(unlockPackage)) {
                avaiableDevices.add(device);
            } else {
                MyLogger.log.info("Данное устройство: " + device + " уже находится в тестировании " +
                        "так как на устройстве установлен пакет: " + unlockPackage);
            }
        }
        if (avaiableDevices.size() == 0) {
            throw new RuntimeException("В настоящее время ни одно устройство не доступно для тестирования");
        }
        return avaiableDevices;
    }

    /**
     * Создание и инициализация AndroidDriver
     *
     * @throws MalformedURLException
     */
    public static void createDriver() throws MalformedURLException {
        appiumServer.startServer();
        emulatorServer.startEmulator();
        ArrayList<String> devices = getAvailableDevices();
        for(String device : devices){
            try{
                MyLogger.log.info("Попытка создать новый драйвер для устройства: " + device);
                Android.driver = new AndroidDriver(host(device), getCaps(device));
                Android.adb = new Adb(device);
                Android.apps = new Apps();
                MyLogger.log.info("Драйвер для устройства: " + device + " успешно создан");
                return;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Завершение работы AndroidDriver
     */
    public static void killDriver() {
        if (Android.driver != null) {
            MyLogger.log.info("Звершение работы AndroidDriver!");
            Android.driver.quit();
            Android.adb.uninstallApp(unlockPackage);
            appiumServer.stopServer();
            emulatorServer.stopEmulator();
        }
        else {
            MyLogger.log.info("AndroidDriver не проинициализирован, нечего завершать!");
        }
    }

}
