package Observer;

import States.Events;

public class InterfaceObserver {
    /*This class is use to comunicate the interface with the
    back end
     */
    private static Observer[] observers = new Observer[2];

    static void Notify(Events event, Object[] data){
        for (Observer observer: observers){
            observer.update(event, data);
        }
    }
}
