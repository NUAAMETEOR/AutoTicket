/**
 * @ClassName: TicketQueryClickListener.java
 * @Description:
 * @author Meteor
 * @version V1.0
 * @Date 2018/4/8 22:19
 */
package cn.edu.nuaa.autoticket.component;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import cn.edu.nuaa.autoticket.R;
import cn.edu.nuaa.autoticket.activities.MainActivity;
import cn.edu.nuaa.autoticket.common.Utils;
import cn.edu.nuaa.autoticket.model.AsynTicketFetcher;
import cn.edu.nuaa.autoticket.model.QueryParameters;


public class TicketQueryClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        MainActivity    mainActivity = (MainActivity) Utils.getParentActivity(v);
        Fragment        fragment     = mainActivity.getSupportFragmentManager().findFragmentById(R.id.base_fragment_container);
        View            view         = fragment.getView();
        String          startCity    = ((TextView) (view.findViewById(R.id.start_city))).getText().toString();
        String          destCity     = ((TextView) (view.findViewById(R.id.dest_city))).getText().toString();
        String          year         = ((TextView) (view.findViewById(R.id.year))).getText().toString();
        String          month        = ((TextView) (view.findViewById(R.id.month))).getText().toString();
        String          day          = ((TextView) (view.findViewById(R.id.day))).getText().toString();
        String          time         = ((TextView) (view.findViewById(R.id.time))).getText().toString();
        Boolean         student      = ((CheckBox) (view.findViewById(R.id.student))).isChecked();
        QueryParameters parameters   = new QueryParameters();
        parameters.setStartCity(startCity);
        parameters.setDestCity(destCity);
        parameters.setDeliverDay(year + "-" + month + "-" + day);
        new AsynTicketFetcher(mainActivity).execute(parameters);
    }
}
