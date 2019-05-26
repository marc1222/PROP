package gui;

import java.util.List;

public class MyEventHanlder {

    private List<MyEventListener> eventListeners;

    public void addMyEventListener(MyEventListener evtListener)
    {
        this.eventListeners.add(evtListener);
    }

    public void EventMakemove() {
        System.out.println("Make move event triggered");
        // Notify everybody that may be interested.
        for (MyEventListener hl : eventListeners)
            hl.make_move();
    }
}
