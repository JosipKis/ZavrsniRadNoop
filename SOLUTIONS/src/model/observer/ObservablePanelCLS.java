package model.observer;

public class ObservablePanelCLS extends ObservablePanel{

    public static ObservablePanelCLS instance;

    private ObservablePanelCLS(){

    }

    @Override
    public void notifyObservers(String text) {
        for (ObserverInt observer : observers) {
            observer.update(text);
        }
    }

    @Override
    public void addObserver(ObserverInt observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ObserverInt observer) {
        observers.remove(observer);
    }

    public void printObservers(){
        for (ObserverInt observer : observers) {
            System.out.println(observer + " is observing");
        }
    }

    public static ObservablePanelCLS getInstance() {
        if (instance == null) {
            instance = new ObservablePanelCLS();
        }

        return instance;
    }
}
