package Observer;

import States.Events;

public class InterfaceObserver {
    /*This class is use to comunicate the interface with the
    back end (its "looking" at the interface)
     */
    private static Observer[] observers = new Observer[1];

    public static void notify(Events event, Object[] data){
        for (Observer observer: observers){
            if (observer!= null)
                observer.update(event, data);
        }
    }

    public static  void subscrive(Observer object){
        int i = 0;
        while (i<observers.length){
            if (observers[i] == null){
                observers[i] = object;
                i++;
            }
        }
    }
}
