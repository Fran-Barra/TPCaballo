package App;

import Observer.Observer;
import States.Events;

public class App implements Observer {
    boolean running;

    public App() {
        this.running = true;
    }

    public void run(){
        while (running){

        }
    }

    @Override
    public void update(Events event) {
        if (Events.Close == event){
            running = false;
        }
    }
}
