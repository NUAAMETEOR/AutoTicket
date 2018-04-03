package cn.edu.nuaa.autoticket.activities;

import android.support.v4.app.Fragment;

import cn.edu.nuaa.autoticket.fragments.BuyTicketFragment;

public class MainActivity extends BaseFragmentActivity {
    public MainActivity() {
//        fullScreen = true;
    }

    @Override
    protected Fragment prepareFragment() {
        return new BuyTicketFragment();
    }
}
