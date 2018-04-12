package cn.edu.nuaa.autoticket.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Calendar;

import cn.edu.nuaa.autoticket.R;
import cn.edu.nuaa.autoticket.activities.BaseFragmentActivity;
import cn.edu.nuaa.autoticket.component.TicketQueryClickListener;
import cn.edu.nuaa.autoticket.component.TrainTypeClickListener;

/**
 * Created by Meteor on 2018/3/28.
 */

public class MainFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View     v        = inflater.inflate(R.layout.buy_ticket_fragment, container, false);
        Activity activity = getActivity();
        int      rotation = BaseFragmentActivity.getScreenOrientation(activity);
        Drawable drawable = null;
        if (rotation == Surface.ROTATION_180 || rotation == Surface.ROTATION_0) {
            drawable = ContextCompat.getDrawable(getActivity(), R.drawable.main_v);
        } else {
            drawable = ContextCompat.getDrawable(getActivity(), R.drawable.main_h);
        }
        v.setBackground(drawable);
        TextView yearView = v.findViewById(R.id.year);
        Calendar calendar=Calendar.getInstance();
        yearView.setText(String.valueOf(calendar.get(Calendar.YEAR)));
        initAuxiliary(v);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.buy_ticket_fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        initControlListener();
    }

    private void initAuxiliary(View v) {
        TableLayout tableLayout = v.findViewById(R.id.auxiliary);
        TableRow    tableRow    = (TableRow) tableLayout.getChildAt(0);
        Context     context     = getActivity();
        int         cellCount   = tableRow.getChildCount();
        for (int i = 0; i < cellCount; i++) {
            LinearLayout cell      = (LinearLayout) tableRow.getChildAt(i);
            ImageView    imageView = (ImageView) cell.getChildAt(0);
            TextView     textView  = (TextView) cell.getChildAt(1);
            switch (i) {
                case 0:
                    textView.setText(R.string.current_location);
                    imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.luobing));
                    break;
                case 1:
                    textView.setText(R.string.travel_record);
                    imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.luff));
                    break;
                case 2:
                    textView.setText(R.string.entertainment);
                    imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.exchange));
                    break;
                case 3:
                    textView.setText(R.string.wiki);
                    imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.shanzhi));
                    break;
                default:
                    break;
            }
        }
    }

    private void initControlListener() {
        setQueryListener();
        setTrainTypeListener();
        setExchangeListner();
    }

    private void setExchangeListner() {

    }

    private void setTrainTypeListener() {
        View v=getView();
        TextView               allTrain              = v.findViewById(R.id.all_train);
        TextView               highTrain              = v.findViewById(R.id.high_speed_train);
        TextView               normalTrain              = v.findViewById(R.id.normal_train);
        TrainTypeClickListener trainTypeClickListener = new TrainTypeClickListener();
        highTrain.setOnClickListener(trainTypeClickListener);
        allTrain.setOnClickListener(trainTypeClickListener);
        normalTrain.setOnClickListener(trainTypeClickListener);
    }

    private void setQueryListener() {
        View   v           = getView();
        Button queryButton = v.findViewById(R.id.query);
        queryButton.setOnClickListener(new TicketQueryClickListener());
    }
}
