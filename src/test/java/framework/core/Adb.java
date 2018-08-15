package framework.core;

import framework.core.managers.ServerManager;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Класс для работы напрямую с adb запросыми
 *
 * @author Arkadiy
 */
public class Adb {

    /**
     * id (deviceName) устройства с которым мы работаем
     */
    private String ID;

    /**
     * Конструктор с параметром инициализирующий перемеенную отвечающая за id устройва или эмулятора
     *
     * @param deviceId
     */
    public Adb(String deviceId) {
        this.ID = deviceId;
    }

    /**
     * Метод проверяет правильность команды а также принимает ответ от сервера и возвращает его
     *
     * @param command команда которую исполняем в adb
     * @return
     */
    public static String command(String command) {
        MyLogger.log.info("Выполнение ADB команды: " + command);
        if (command.startsWith("adb")) {
            MyLogger.log.debug("Форматирование ADB команды: " + command);
            command = command.replace("adb ", ServerManager.getAndroidHome() + "/platform-tools/adb ");
        } else throw new RuntimeException("Этот метод предназначен для запуска только команд ADB!");
        MyLogger.log.debug("Отформатированая ADB команды: " + command);
        String output = ServerManager.runCommand(command);
        MyLogger.log.debug("Ответ от ADB: \n" + output);
        if (output == null) {
            return "";
        } else {
            return output.trim();
        }
    }

    /**
     * Завершить работу adb
     */
    public static void killServer() {
        command("adb kill-server");
    }

    /**
     * Запустить adb
     */
    public static void startServer() {
        command("adb start-server");
    }

    /**
     * Вернуть список доступных к подключению устройств
     *
     * @return Список устройств
     */
    public static ArrayList getConnectedDevices() {
        ArrayList devices = new ArrayList();
        String output = command("adb devices");
        for (String line : output.split("\n")) {
            line = line.trim();
            if (line.endsWith("device")) {
                devices.add(line.replace("device", "").trim());
            }
        }
        return devices;
    }

    /**
     * Возвращаем значение пакета и активити открытого на данный момент приложения, т.е. узнаем где мы находимся
     *
     * @return Строка с параметрами mCurrentFocus=Window{* * пакет/активити}
     */
    public String getForegroundActivity() {
        return command("adb -s " + ID + " shell dumpsys window windows | grep mCurrentFocus");
    }

    /**
     * Возвращаем версию Андроида на устройстве
     *
     * @return Строка с указание версии андроида с тремя цифрами через точку
     */
    public String getAndroidVersionAsString() {
        String output = command("adb -s " + ID + " shell getprop ro.build.version.release");
        if (output.length() == 3)
            output += ".0";
        return output;
    }

    /**
     * Возвращаем версию Андроида на устройстве
     *
     * @return Целое число с указание версии андроида
     */
    public int getAndroidVersion() {
        return Integer.parseInt(getAndroidVersionAsString().replaceAll("\\.", ""));
    }

    /**
     * Возвращаем список всех возможных установленных пакетов на устройстве
     *
     * @return Список пакетов
     */
    public ArrayList getInstalledPackages() {
        ArrayList packages = new ArrayList();
        String[] output = command("adb -s " + ID + " shell pm list packages").split("\n");
        for (String packageID : output) {
            packages.add(packageID.replace("package:", "").trim());
        }
        return packages;
    }

    /**
     * Открытие приложения
     *
     * @param packageID  пакет приложения
     * @param activityID активити приложения
     */
    public void openAppsActivity(String packageID, String activityID) {
        command("adb -s " + ID + " shell am start -c api.android.intent.category.LAUNCHER -a api.android.intent.action.MAIN -n " + packageID + "/" + activityID);
    }

    /**
     * Закрывает приложение и скидывает все сохранённые данные
     *
     * @param packageID пакет приложения
     */
    public void closeAndClearAppsData(String packageID) {
        command("adb -s " + ID + " shell pm clear " + packageID);
    }

    /**
     * Сворачивает приложение при этом может сброситься сессия соединения в зависимости от самого приложения
     * Calculator при этой команде сворачивается и выходит из аккаунта пользователя,
     * но настройки и данные не скидываются
     *
     * @param packageID пакет приложения
     */
    public void forceStopApp(String packageID) {
        command("adb -s " + ID + " shell am force-stop " + packageID);
    }

    /**
     * Установка приложения apk
     *
     * @param apkPath путь к apk файлу
     */
    public void installApp(String apkPath) {
        command("adb -s " + ID + " install " + apkPath);
    }

    /**
     * Удаление приложения из устройства
     *
     * @param packageID пакет приложения
     */
    public void uninstallApp(String packageID) {
        command("adb -s " + ID + " uninstall " + packageID);
    }

    /**
     * Закрыть эмулятор
     */
    public void killEmulator() {
        command("adb emu kill");
    }

    /**
     * Очистка логов в буфере файла
     */
    public void clearLogBuffer(){
        command("adb -s " + ID + " shell -c");
    }

    /**
     * Скопировать файл на устройство
     *
     * @param source путь к файлу
     * @param target путь (именно в устройстве) куда хотим скопировать
     */
    public void pushFile(String source, String target){
        command("adb -s " + ID + " push "+source+" "+target);
    }

    /**
     * Скопировать файл из устройства
     *
     * @param source путь к файлу на устройстве
     * @param target путь куда хотим скопировать себе
     */
    public void pullFile(String source, String target){
        command("adb -s " + ID + " pull "+source+" "+target);
    }

    /**
     * Удалить файл с устройства
     *
     * @param target путь до файла который нужно удалить
     */
    public void deleteFile(String target){
        command("adb -s " + ID + " shell rm "+target);
    }

    /**
     * Перемещение файла внутри устройства
     *
     * @param source путь откуда переносим
     * @param target путь куда переносим
     */
    public void moveFile(String source, String target){
        command("adb -s " + ID + " shell mv "+source+" "+target);
    }

    /**
     * Сделать скриншот устройства и сохранить на устройстве
     *
     * @param target путь сохранения скриншота на устройстве
     */
    public void takeScreenshot(String target){
        command("adb -s " + ID + " shell screencap "+target);
    }

    /**
     * Перезагрузка устройства
     */
    public void rebootDevice(){
        command("adb -s " + ID + " reboot");
    }

    /**
     * Вернуть название модели
     *
     * @return Строка с названием модели
     */
    public String getDeviceModel(){
        return command("adb -s "+ ID + " shell getprop ro.product.model");
    }

    /**
     * Вернуть серию устройства
     *
     * @return Строка с серией устройства
     */
    public String getDeviceSerialNumber(){
        return command("adb -s " + ID + " shell getprop ro.serialno");
    }

    /**
     * Вернуть носителя
     *
     * @return Строка с название носителя
     */
    public String getDeviceCarrier(){
        return command("adb -s " + ID + " shell getprop gsm.operator.alpha");
    }

    /**
     * Возвращает запущенные процессы логирования
     *
     * @return Возвращаем список процессов pid
     */
    public ArrayList getLogcatProcesses(){
        String[] output = command("adb -s " + ID + " shell top -n 1 | grep -i 'logcat'").split("\n");
        ArrayList processes = new ArrayList();
        for(String line : output){
            processes.add(line.split(" ")[0]);
            processes.removeAll(Arrays.asList("", null));
        }
        return processes;
    }

    /**
     * Создания начало процесса логирования
     *
     * @param logID id процесса, любое значение
     * @param grep  маска того что нужно считать, можно передать null
     * @return Object - pid - id процесса
     */
    public Object startLogcat(final String logID, final String grep){
        ArrayList pidBefore = getLogcatProcesses();

        Thread logcat = new Thread(() -> {
            if(grep == null) command("adb -s " + ID + " shell logcat -v threadtime > /sdcard/" + logID + ".txt");
            else command("adb -s " + ID + " shell logcat -v threadtime | grep -i '" + grep + "'> /sdcard/" + logID + ".txt");
        });
        logcat.setName(logID);
        logcat.start();
        logcat.interrupt();

        ArrayList pidAfter = getLogcatProcesses();
        Timer timer = new Timer();
        timer.start();
        while(!timer.expired(5)){
            if(pidBefore.size() > 0)
                pidAfter.removeAll(pidBefore);
            if(pidAfter.size() > 0)
                break;
            pidAfter = getLogcatProcesses();
        }

        if(pidAfter.size() == 1) return pidAfter.get(0);
        else if(pidAfter.size() > 1) throw new RuntimeException("Несколько процессов logcat были запущены, ожидался только один!");
        else throw new RuntimeException("Не удалось запустить процесс logcat!");
    }

    /**
     * Остоновка логирования конкретного процесса
     *
     * @param PID id процесса
     */
    public void stopLocat(Object PID){
        command("adb -s " + ID + " shell kill " + PID);
    }


}
