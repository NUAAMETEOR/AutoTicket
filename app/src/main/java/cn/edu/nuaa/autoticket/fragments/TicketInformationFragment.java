package cn.edu.nuaa.autoticket.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;

import cn.edu.nuaa.autoticket.model.TicketInformation;

public class TicketInformationFragment extends ListFragment {
    private TicketInformation ticketInformation;
    public static final String TICKET_INFORMATION_KEY = "ticket";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        ticketInformation = (TicketInformation) bundle.get(TICKET_INFORMATION_KEY);
    }
}
