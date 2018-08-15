package api.interfaces;

/**
 * Интерфейс позволяющий определить общие методы для активити приложения
 *
 * @author Arkadiy
 */
public interface Activity {

    /**
     * Ждем появление активити приложения
     */
    Object waitToLoad();
}
