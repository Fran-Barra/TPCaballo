package Observer;

import States.Events;

public class InterfaceObserver {
    /*This class is use to comunicate the interface with the
    back end (its "looking" at the interface)
     */
    private static Observer[] observers = new Observer[1];

    public static void Notify(Events event, Object[] data){
        for (Observer observer: observers){
            observer.update(event, data);
        }
    }
}
