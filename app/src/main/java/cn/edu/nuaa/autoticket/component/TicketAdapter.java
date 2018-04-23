package cn.edu.nuaa.autoticket.component;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import cn.edu.nuaa.autoticket.model.TicketInformation;

/**
 * @author Meteor
 * @version V1.0
 * @ClassName: TicketAdapter.java
 * @Description:
 * @Date 2018/4/22 21:07
 */
public class TicketAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    HashMap<String, ArrayList<TicketInformation>> data;
    TreeSet<ArrayList<TicketInformation>> set = new TreeSet<>();

    public TicketAdapter(HashMap<String, ArrayList<TicketInformation>> data) {
        this.data = data;
        updateInformation();
    }

    public void updateInformation() {
        set.clear();
        set.addAll(data.values());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
