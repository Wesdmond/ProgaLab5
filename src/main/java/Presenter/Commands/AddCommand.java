package Presenter.Commands;

import Model.DataClasses.*;
import Model.Exceptions.BadIdException;
import Model.Exceptions.UserInputException;
import Presenter.Exceptions.CommandArgNotFound;
import Presenter.Exceptions.InputEndedException;
import Presenter.IPresenter;
import View.IView;


/**
 * Команда добавления объекта в коллекцию
 */
public class AddCommand implements ICommand {
    
    @Override
    public void execute(IPresenter presenter) {
        IView view = presenter.getView();
        Dragon dragon = null;

        try {
            int id = presenter.getCollection().generateUniqueId();
            dragon = readDragon(id, view);
        } catch (NoSuchMethodException | UserInputException | BadIdException e) {
            view.showError(e.getMessage());
        }

        presenter.getCollection().push(dragon);
    }

    static Dragon readDragon(Integer id, IView view) throws NoSuchMethodException, UserInputException, BadIdException {
        String name;
        Coordinates coordinates;
        Long age;
        Color color;
        DragonType type;
        DragonCharacter character;
        DragonHead head;
        try {
            name = view.readSimpleField("имя дракона", Dragon.class.getMethod("checkName", String.class), String.class, -1);
            int x = view.readSimpleField("координату X", Coordinates.class.getMethod("checkX", int.class), Integer.class, 0);
            Long y = view.readSimpleField("координату Y", Coordinates.class.getMethod("checkY", Long.class), Long.class, 0);
            age = view.readSimpleField("возраст дракона", Dragon.class.getMethod("checkAge", Long.class), Long.class, 0);
            coordinates = new Coordinates(x, y);
            color = view.readEnumConstant("цвет дракона", Dragon.class.getMethod("checkColor", Color.class), Color.class, 0);
            type = view.readEnumConstant("тип дракона", Dragon.class.getMethod("checkType", DragonType.class), DragonType.class, 0);
            character = view.readEnumConstant("характер дракона", Dragon.class.getMethod("checkCharacter", DragonCharacter.class), DragonCharacter.class, 0);
            int eyes = view.readSimpleField("количество глаз на голове", DragonHead.class.getMethod("checkEyes", int.class), Integer.class, 0);
            head = new DragonHead(eyes);
        } catch (InputEndedException e) {
            return null;
        }

        return new Dragon(id, name, coordinates, age, color, type, character, head);
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию";
    }

    @Override
    public String[] getArgsNames() {
        return null;
    }

    @Override
    public void setArg(String argName, String valueString) {
        return;
    }

    @Override
    public Object getArg(String argName) throws CommandArgNotFound {
        throw new CommandArgNotFound(getName(), argName);
    }

}
