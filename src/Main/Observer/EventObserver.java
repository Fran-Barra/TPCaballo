package Observer;

import States.Events;

public class EventObserver {
    /*This class is use to comunicate the backend with the
    User interface
     */
    private static Observer[] observers = new Observer[2];

    static void Notify(Events event, Object[] data){
        for (Observer observer: observers){
            observer.update(event, data);
        }
    }
}
