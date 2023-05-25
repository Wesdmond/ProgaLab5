package Model.DataClasses;

import Model.Exceptions.BadIdException;
import Model.Exceptions.UserInputException;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Дракон, основной объект данных
 *
 */
public class Dragon implements Comparable<Dragon> {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long age; //Значение поля должно быть больше 0, Поле не может быть null
    private Color color; //Поле не может быть null
    private DragonType type; //Поле может быть null
    private DragonCharacter character; //Поле может быть null
    private DragonHead head;

    /**
     *
     * @param id id
     * @param name Имя Дракона
     * @param coordinates Координаты
     * @param age Возраст Дракона
     * @param color Цвет Дракона
     * @param type Тип Дракона
     * @param character Характер Дракона
     * @param head Голова Дракона
     * @throws UserInputException Если параметры не соответствуют огрничениям
     * @throws BadIdException Если id не соответствует огрничениям
     */
    public Dragon(Integer id, String name, Coordinates coordinates, Long age, Color color, DragonType type, DragonCharacter character, DragonHead head) throws BadIdException, UserInputException {
        setId(id);
        setName(name);
        setCoordinates(coordinates);
        setAge(age);
        setColor(color);
        setType(type);
        setCharacter(character);
        setHead(head);
        generateCreationDate();
    }

    /**
     * Проверит поле id на соответствие ограничениям
     *
     * @param id id
     * @throws BadIdException Если не соответствует ограничениям
     */
    public static void checkId(Integer id) throws BadIdException {
        if (id <= 0)
            throw new BadIdException("id должно быть больше 0");
    }

    /**
     * Проверит поле name на соответствие ограничениям
     *
     * @param name Имя Дракона
     * @throws UserInputException Если не соответствует ограничениям
     */
    public static void checkName(String name) throws UserInputException {
        if (name == null || name.isBlank())
            throw new UserInputException("Имя не может быть пустым");
    }

    /**
     * Проверит поле coordinates на соответствие ограничениям
     *
     * @param coordinates Координаты
     * @throws UserInputException Если не соответствует ограничениям
     */
    public static void checkCoordinates(Coordinates coordinates) throws UserInputException {
        if (coordinates == null) {
            throw new UserInputException("Значение коондинат не может быть пустым");
        }
    }

    /**
     * Проверит поле age на соответствие ограничениям
     *
     * @param age Возраст Дракона
     * @throws UserInputException Если не соответствует ограничениям
     */
    public static void checkAge(Long age) throws UserInputException {
        if (age == null)
            throw  new UserInputException("Возраст не может быть пустым");
        if (age <= 0)
            throw new UserInputException("Возраст должен быть больше 0");
    }

    /**
     * Проверит поле color на соответствие ограничениям
     *
     * @param color Цвет Дракона
     * @throws UserInputException Если не соответствует ограничениям
     */
    public static void checkColor(Color color) throws UserInputException {
        if (color == null)
            throw new UserInputException("Цвет не может быть пустым");
    }

    public static void checkCreationDate(java.util.Date creationDate) throws UserInputException {
        if (creationDate == null)
            throw new UserInputException("Дата создания не может быть пустой");
    }

    /**
     * Проверит поле type на соответствие ограничениям
     *
     * @param type Тип Дракона
     * @throws UserInputException Если не соответствует ограничениям
     */
    public static void checkType(DragonType type) throws UserInputException {

    }

    /**
     * Проверит поле character на соответствие ограничениям
     *
     * @param character Характер Дракона
     * @throws UserInputException Если не соответствует ограничениям
     */
    public static void checkCharacter(DragonCharacter character) throws UserInputException {

    }

    /**
     * Проверит поле head на соответствие ограничениям
     *
     * @param head Голова Дракона
     * @throws UserInputException Если не соответствует ограничениям
     */
    public static void checkHead(DragonHead head) throws UserInputException {

    }

    /**
     * Устанавливает параметр id в поле id, поле проверяется перед установкой
     * @param id id
     * @throws BadIdException Если id не удовлетворяет ограничениям
     */
    public void setId(Integer id) throws BadIdException {
        checkId(id);
        this.id = id;
    }

    /**
     * Возвращает значение поля id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Устанавливает параметр name в поле name, поле проверяется перед установкой
     * @param name Имя Дракона
     * @throws UserInputException Если не соответствует ограничениям
     */
    public void setName(String name) throws UserInputException {
        checkName(name);
        this.name = name;
    }

    /**
     * Возвращает значение поля name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Устанавливает значение параметра coordinates в значение поля coordinates, значение проверяется перед установкой на соответствие ограничениям
     * @param coordinates Новые координаты
     * @throws UserInputException Если не соответствует ограничениям
     */
    public void setCoordinates(Coordinates coordinates) throws UserInputException {
        checkCoordinates(coordinates);
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public void setAge(Long age) throws UserInputException {
        checkAge(age);
        this.age = age;
    }

    public Long getAge() {
        return this.age;
    }

    public void setColor(Color color) throws UserInputException {
        checkColor(color);
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public void setCreationDate(java.util.Date creationDate) throws UserInputException {
        checkCreationDate(creationDate);
        this.creationDate = creationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void generateCreationDate() {
        this.creationDate = new java.util.Date();
    }

    public void setType(DragonType type) throws UserInputException {
        checkType(type);
        this.type = type;
    }

    public DragonType getType() {
        return type;
    }

    public void setCharacter(DragonCharacter character) throws UserInputException {
        checkCharacter(character);
        this.character = character;
    }

    public DragonCharacter getCharacter() {
        return character;
    }

    public void setHead(DragonHead head) throws UserInputException {
        checkHead(head);
        this.head = head;
    }

    public DragonHead getHead() {
        return head;
    }

    @Override
    public String toString() {
        String creationDateString = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT, new Locale("ru")).format(this.creationDate);
        return String.format("""
                        ID: %d
                        Имя дракона: %s
                        Текущие координаты: %s
                        Дата создания: %s
                        Возраст: %d
                        Цвет дракона: %s
                        Тип дракона: %s
                        Характер дракона: %s
                        Количество глаз: %d
                        """,
                this.id, this.name, this.coordinates, creationDateString, this.age, this.color, this.type, this.character, this.head.getEyesCount()
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == getClass()) {
            Dragon convObj = (Dragon) obj;
            return convObj.getId().equals(getId());
        } else {
            return false;
        }
    }

    /**
     * Сравнивает двух Dragon сначала по возрасту, если равны, то по координатам, если опять равны, то по имени, если снова равны, то по id
     *
     * @param o Объект класса Dragon, с которым будет идти сравнение
     */
    @Override
    public int compareTo(Dragon o) {
        if (o.getAge().equals(this.age)) {
            if (o.getCoordinates().equals(this.coordinates)) {
                if (o.getName().equals(this.name)) {
                    return this.id - o.getId();
                } else {
                    return this.name.compareTo(o.getName());
                }
            } else {
                return this.coordinates.compareTo(o.getCoordinates());
            }
        } else {
            return this.age.compareTo(o.age);
        }
    }
}