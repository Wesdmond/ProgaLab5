package Presenter.Exceptions;

/**
 * Ошибка пустой команды (например, пустая строка из пробелов была считана при вводе команды)
 */
public class NullCommandException extends Exception {
    public NullCommandException(String message) {
        super(message);
    }
}
