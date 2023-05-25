import Model.IModel;
import Model.XmlModel;
import Presenter.CommandsPresenter;
import Presenter.IPresenter;
import View.IView;
import View.View;

public class Main {

    public static void main(String[] args) {
        IModel model = new XmlModel();
        IView view = new View();
        IPresenter presenter = new CommandsPresenter(view, model);
        presenter.start();
    }
}
