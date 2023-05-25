package Presenter.Commands;

import Model.DataClasses.Dragon;
import Model.Exceptions.BadIdException;
import Model.Exceptions.UserInputException;
import Presenter.Exceptions.BadCommandArgException;
import Presenter.Exceptions.CommandArgNotFound;
import Presenter.IPresenter;

import java.util.Iterator;

import static Presenter.Commands.AddMinCommand.readDragon;


/**
 * Команда удаления всех элементом меньше указанного
 */
public class RemoveLowerCommand implements ICommand {

    @Override
    public void execute(IPresenter presenter) {
        Iterator<Dragon> it = presenter.getCollection().iterator();
        Dragon dragon = null;
        try {
            int id = presenter.getCollection().generateUniqueId();
            dragon = readDragon(id, presenter.getView());
            while (it.hasNext()) {
                Dragon element = it.next();
                if (dragon.compareTo(element) < 0)
                {
                    it.remove();
                }
            }
        } catch (NullPointerException e) {
            presenter.getView().showError(e.getMessage());
        } catch (UserInputException | BadIdException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getName() {
        return "remove_lower";
    }

    @Override
    public String getDescription() {
        return "удаляет все элементы коллекции меньше указанного";
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
