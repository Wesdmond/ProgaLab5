package Presenter.Commands;

import Presenter.Exceptions.CommandArgNotFound;
import Presenter.IPresenter;
import View.IView;

import java.util.ArrayList;
import java.util.List;


/**
 * Команда вывода информации обо всех командах
 */
public class HelpCommand implements ICommand {

    @Override
    public void execute(IPresenter presenter) {
        ICommand[] commandsList = presenter.getCommands();
        IView view = presenter.getView();
        List<String> commandsDescriptions = new ArrayList<String>();
        for (ICommand command : commandsList) {
            String argsString;
            if (command.getArgsNames() == null)
                argsString = "";
            else
                argsString = String.join(" ", command.getArgsNames()) + " ";
                
            commandsDescriptions.add(String.format("%s %s: %s", command.getName(), argsString, command.getDescription()));
        }
        view.showResult(String.join("\n", commandsDescriptions));
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "вывести справку по доступным командам";
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
