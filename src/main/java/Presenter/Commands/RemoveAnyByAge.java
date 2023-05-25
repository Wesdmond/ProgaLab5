package Presenter.Commands;

import Model.DataClasses.Dragon;
import Presenter.Exceptions.BadCommandArgException;
import Presenter.Exceptions.CommandArgNotFound;
import Presenter.IPresenter;

import java.util.Iterator;


/**
 * Команда удаления одного любого дракона с заданным значением поля age
 */
public class RemoveAnyByAge implements ICommand {
    
    private Long age;
    
    @Override
    public void execute(IPresenter presenter) {
        Iterator<Dragon> it = presenter.getCollection().iterator();
        while (it.hasNext()) {
            Dragon element = it.next();
            if (element.getAge() == this.age)
            {
                it.remove();
                break;
            }
        }
    }

    @Override
    public String getName() {
        return "remove_any_by_age";
    }

    @Override
    public String getDescription() {
        return "удалить из коллекции один любой элемент, значение поля age которого эквивалентно заданному";
    }

    @Override
    public String[] getArgsNames() {
        return new String[] {"age"};
    }

    @Override
    public void setArg(String argName, String valueString) throws BadCommandArgException {
        try {
            switch (argName) {
                case "age":
                    this.age = Long.valueOf(valueString);
                    break;
            }
        } catch (NumberFormatException e) {
            throw new BadCommandArgException(getName(), argName, Long.class.getSimpleName());
        }
    }

    @Override
    public Object getArg(String argName) throws CommandArgNotFound {
        switch (argName) {
            case "age":
                return this.age;
            default:
                throw new CommandArgNotFound(getName(), argName);
        }
    }

}
