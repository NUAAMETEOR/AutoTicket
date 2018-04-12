package cn.edu.nuaa.autoticket.component;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import cn.edu.nuaa.autoticket.R;
import cn.edu.nuaa.autoticket.activities.MainActivity;
import cn.edu.nuaa.autoticket.common.Utils;

/**
 * @author Meteor
 * @version V1.0
 * @ClassName: TrainTypeClickListener.java
 * @Description:
 * @Date 2018/4/12 21:30
 */
public class TrainTypeClickListener implements View.OnClickListener {
    private static Drawable               bkPic      = ContextCompat.getDrawable(AutoTaskApplication.getAppContext(), R.drawable.text_select);
    private static Map<Integer, Drawable> clickState = new HashMap<Integer, Drawable>() {{
        put(R.id.all_train, bkPic);
        put(R.id.high_speed_train, null);
        put(R.id.normal_train, null);
    }};


    @Override

    public void onClick(View v) {
        MainActivity mainActivity = (MainActivity) Utils.getParentActivity(v);
        Fragment     fragment     = mainActivity.getSupportFragmentManager().findFragmentById(R.id.base_fragment_container);
        View         view         = fragment.getView();
        int          id           = v.getId();
        setBkPic(id, view);
    }

    private void setBkPic(int id, View v) {
        TextView textView;
        for (Map.Entry<Integer, Drawable> entry : clickState.entrySet()) {
            textView = v.findViewById(entry.getKey());
            if (id == textView.getId()) {
                entry.setValue(bkPic);
                textView.setBackground(bkPic);
            } else {
                entry.setValue(null);
                textView.setBackground(null);
            }
            textView.invalidate();//一定要加，不然看不出效果
        }
    }
}
