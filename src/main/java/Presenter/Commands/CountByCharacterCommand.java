package Presenter.Commands;


import Model.DataClasses.Dragon;
import Model.DataClasses.DragonCharacter;
import Presenter.Exceptions.BadCommandArgException;
import Presenter.Exceptions.CommandArgNotFound;
import Presenter.IPresenter;

import java.util.Iterator;


/**
 * подсчитать и вывести количество всех элементов в коллекции, значение поля character которых эквивалентно заданному
 */
public class CountByCharacterCommand implements ICommand {
    
    private DragonCharacter character;
    private int count = 0;
    
    @Override
    public void execute(IPresenter presenter) {
        Iterator<Dragon> it = presenter.getCollection().iterator();
        while (it.hasNext()) {
            Dragon element = it.next();
            if (element.getCharacter() == this.character)
            {
                count += 1;
            }
        }
        presenter.getView().showResult(String.format("Количество типа \"%s\" равно %s ", character.toString(), ((Integer)count).toString()));
    }

    @Override
    public String getName() {
        return "count_by_character";
    }

    @Override
    public String getDescription() {
        return "подсчитать и вывести количество всех элементов в коллекции, значение поля character которых эквивалентно заданному";
    }

    @Override
    public String[] getArgsNames() {
        return new String[] {"character"};
    }

    @Override
    public void setArg(String argName, String valueString) throws BadCommandArgException {
        try {
            switch (argName) {
                case "character":
                    this.character = DragonCharacter.valueOf(valueString);
                    break;
            }
        } catch (IllegalArgumentException e) {
            throw new BadCommandArgException(getName(), argName, Long.class.getSimpleName());
        }
    }

    @Override
    public Object getArg(String argName) throws CommandArgNotFound {
        switch (argName) {
            case "character":
                return this.character;
            default:
                throw new CommandArgNotFound(getName(), argName);
        }
    }

}
