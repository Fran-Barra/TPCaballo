package Observer;

import States.Events;

public interface Observer {
    public void update(Events event);
}
