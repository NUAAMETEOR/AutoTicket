package cn.edu.nuaa.autoticket.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.LinkedList;

import cn.edu.nuaa.autoticket.model.TicketInformation;

public class TicketInformationActivity extends AppCompatActivity {
    ViewPager                     viewPager = new ViewPager(this);
    LinkedList<TicketInformation> list      = new LinkedList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(viewPager);
    }

    private void initViewPage() {
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            /**
             * Return the number of views available.
             */
            @Override
            public int getCount() {
                return list.size();
            }

            /**
             * Return the Fragment associated with a specified position.
             *
             * @param position
             */
            @Override
            public Fragment getItem(int position) {
                TicketInformation ticketInformation = list.get(position);
                return null;
            }
        });
    }
}
