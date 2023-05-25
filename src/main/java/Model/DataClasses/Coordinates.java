package Model.DataClasses;

import Model.Exceptions.UserInputException;

/**
 * Координаты
 */
public class Coordinates implements Comparable<Coordinates>{
    private int x;
    private Long y; //Поле не может быть null

    /**
     *
     * @param x Координата X
     * @param y Координата Y
     * @throws UserInputException Если параметры не соответствуют ограничениям
     */
    public Coordinates(int x, Long y) throws UserInputException {
        setX(x);
        setY(y);
    }

    /**
     * Проверит поле x на соответствие ограничениям
     *
     * @param x Координата X
     * @throws UserInputException Если не соответствует ограничениям
     */
    public static void checkX(int x) throws UserInputException {

    }

    /**
     * Проверит поле y на соответствие ограничениям
     *
     * @param y Координата Y
     * @throws UserInputException Если не соответствует ограничениям
     */
    public static void checkY(Long y) throws UserInputException {
        if (y == null)
            throw new UserInputException("Значение Y не может быть пустым");
    }

    /**
     * Устанавливает новое значение поля X
     * @param x Координата X
     * @throws UserInputException Если не соответствует ограничениям
     */
    public void setX(int x) throws UserInputException {
        checkX(x);
        this.x = x;
    }

    /**
     * Возвращает поле X
     * @return Координата X
     */
    public int getX() {
        return x;
    }

    /**
     * Устанваливает новое значение поля Y
     * @param y Координата Y
     * @throws UserInputException Если не соответствует ограничениям
     */
    public void setY(Long y) throws UserInputException {
        checkY(y);
        this.y = y;
    }

    /**
     * Возвращает поле Y
     * @return Координата Y
     */
    public Long getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("(%d; %d)", this.x, this.y);
    }

    /**
     * Сранивает координаты сначала по X. Если они равны, то по Y.
     *
     * @param o Объекет класса Coordinates, с которым будет идти сравнение
     * @return значение 0, если объекты равны; значение меньше нуля если 1) если поле Y меньше поля Y параметра o
     * при равных полях X, или 2) если поле X меньше поля X параметра o
     */
    @Override
    public int compareTo(Coordinates o) {
        if (o.getX() == this.x) {
            return this.y.compareTo(o.getY());
        } else {
            return this.x - o.getX();
        }
    }
}