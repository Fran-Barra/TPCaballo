package App;


import MovementAlgorithm.MovementAlgorithm;
import Observer.BackendObserver;
import Observer.InterfaceObserver;
import Observer.Observer;
import States.Events;
import UserInterface.UserInterface;

public class App {
    public static void run(){
        Observer userInterface = new UserInterface();
        Observer movementAlgorith = new MovementAlgorithm();
        InterfaceObserver.subscrive(movementAlgorith);
        BackendObserver.subscrive(userInterface);
        BackendObserver.notify(Events.Start, new Object[] {});
    }

}
