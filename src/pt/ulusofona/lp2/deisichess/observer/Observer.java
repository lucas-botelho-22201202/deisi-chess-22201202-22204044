package pt.ulusofona.lp2.deisichess.observer;

public abstract class Observer {
    public abstract void update(int roundNumber);
    public abstract boolean wantsToSubscribe();
}
