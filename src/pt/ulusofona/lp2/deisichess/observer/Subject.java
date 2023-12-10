package pt.ulusofona.lp2.deisichess.observer;

public abstract class Subject {
    public abstract void registerObserver(Observer observer);
    public abstract void notifyObservers();
}
