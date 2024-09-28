package model.observer;

import java.util.ArrayList;

public abstract class ObservablePanel implements ObservableInt {

    protected ArrayList<ObserverInt> observers;

    protected ObservablePanel(){
        observers  =new ArrayList<>();
    }

    public abstract void addObserver(ObserverInt observer);

    public abstract void removeObserver(ObserverInt observer);
}
