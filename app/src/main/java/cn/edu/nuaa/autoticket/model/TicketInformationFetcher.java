package cn.edu.nuaa.autoticket.model;

import android.os.HandlerThread;
import android.view.View;

public class TicketInformationFetcher extends HandlerThread{

    public TicketInformationFetcher(String name) {
        super(name);
    }

    public TicketInformationFetcher(String name, int priority) {
        super(name, priority);
    }
}
