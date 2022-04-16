package Observer;

import States.Events;

public class BackendObserver {
    /*This class is use to comunicate the backend with the
    User interface(its "looking" at the bacend)
    */
    private static Observer[] observers = new Observer[2];

    static void Notify(Events event, Object[] data){
        for (Observer observer: observers){
            observer.update(event, data);
        }
    }
}
