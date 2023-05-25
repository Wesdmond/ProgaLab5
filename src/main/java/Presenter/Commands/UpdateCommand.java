package Presenter.Commands;

import Model.DataClasses.Dragon;
import Model.Exceptions.BadIdException;
import Model.Exceptions.UserInputException;
import Presenter.Exceptions.BadCommandArgException;
import Presenter.Exceptions.CommandArgNotFound;
import Presenter.IPresenter;


/**
 * Команда для обновления объекта коллекции с заданным полем id
 */
public class UpdateCommand implements ICommand {

    private int id;

    @Override
    public void execute(IPresenter presenter) {
        boolean found = false;
        
        for (Dragon element : presenter.getCollection()) {
            if (element.getId() == this.id) {
                try {
                    Dragon replaceDragon = AddCommand.readDragon(this.id, presenter.getView());
                    if (replaceDragon == null)
                        return;
                    element.setName(replaceDragon.getName());
                    element.setCoordinates(replaceDragon.getCoordinates());
                    element.setAge(replaceDragon.getAge());
                    element.setColor(replaceDragon.getColor());
                    element.setType(replaceDragon.getType());
                    element.setCharacter(replaceDragon.getCharacter());
                    element.setHead(replaceDragon.getHead());
                    found = true;
                    break;
                } catch (NoSuchMethodException | UserInputException | BadIdException e) {
                    presenter.getView().showError(e.getMessage());
                }
            }
        }

        if (!found)
            presenter.getView().showError(String.format("Не удалось найти объект с id = %d", this.id));
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescription() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public String[] getArgsNames() {
        return new String[] {"id"};
    }

    @Override
    public void setArg(String argName, String valueString) throws BadCommandArgException {
        try {
            switch (argName) {
                case "id":
                    this.id = Integer.parseInt(valueString);
                    break;
            }
        } catch (NumberFormatException e) {
            throw new BadCommandArgException(getName(), argName, Integer.class.getSimpleName());
        }
    }

    @Override
    public Object getArg(String argName) throws CommandArgNotFound {
        switch (argName) {
            case "id":
                return this.id;
            default:
                throw new CommandArgNotFound(getName(), argName);
        }
    }

}
