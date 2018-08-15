package api.interfaces;

/**
 * Интерфейс позволяющий работать с приложением
 *
 * @author Arkadiy
 */
public interface Application {

    /**
     * Метод останавливает работу приложения и скрывает его
     * В зависимости от настроек приложения может произойти выход из аккаунта если банковский софт
     */
    void forceStop();

    /**
     * Метод закрывает приложения и скидывает все сохранённые данные (настроичные в том числе)
     */
    void closeAndClearData();

    /**
     * Метод открывает приложение
     *
     * @return возвращает нужный объект (т.е. приложение)
     */
    Object open();

    /**
     * Метод возвращает пакет приложения
     *
     * @return строка с пакетом
     */
    String getPackageId();

    /**
     * Метод возвращает активити приложения
     *
     * @return строка с активити
     */
    String getActivityId();

}
