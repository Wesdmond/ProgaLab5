package Presenter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Интерфейс коллекции данных с некоторым необходимым функционалом
 */
public interface IDataCollection {

    /**
     *
     * @return Тип коллекции
     */
    String getTypeName();

    /**
     *
     * @return Дата инициализации
     */
    LocalDateTime getInitDate();

    /**
     *
     * @return Дата модификации
     */
    LocalDateTime getModificationDate();

    /**
     *
     * @return Количество элементов
     */
    int getSize();

    /**
     * Сгенерировать уникальный id
     *
     * @return id
     */
    int generateUniqueId();

    /**
     * Получить все занятые id
     *
     * @return Список из всех занятых id
     */
    List<Integer> getAllIds();

}
