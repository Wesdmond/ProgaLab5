package Presenter.Commands;

import Model.DataClasses.Dragon;
import Presenter.Exceptions.BadCommandArgException;
import Presenter.Exceptions.CommandArgNotFound;
import Presenter.IPresenter;

import java.util.*;


/**
 * Команда вывода значений всех уникальных значений head в коллекции
 */
public class PrintUniqueHeadCommand implements ICommand {
    
    @Override
    public void execute(IPresenter presenter) {
        Iterator<Dragon> it = presenter.getCollection().iterator();
        HashSet<Integer> uniqueHeadValues = new HashSet<Integer>();
        while (it.hasNext()) {
            Dragon element = it.next();
            uniqueHeadValues.add(element.getHead().getEyesCount());
        }
        String result = "";
        for (int value : uniqueHeadValues) {
            result += String.valueOf(value) + ' ';
        }
        presenter.getView().showResult(result);
    }

    @Override
    public String getName() {
        return "print_unique_head";
    }

    @Override
    public String getDescription() {
        return "вывести уникальные значения head в коллекции";
    }

    @Override
    public String[] getArgsNames() {
        return null;
    }

    @Override
    public void setArg(String argName, String valueString) throws BadCommandArgException {
        return;
    }

    @Override
    public Object getArg(String argName) throws CommandArgNotFound {
        throw new CommandArgNotFound(getName(), argName);
    }

}
