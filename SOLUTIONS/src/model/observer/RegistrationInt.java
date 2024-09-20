package model.observer;

public interface RegistrationInt extends ObserverInt {

    void registerObserver(ObservablePanel observer);

    void removeObserver(ObservablePanel observer);
}
