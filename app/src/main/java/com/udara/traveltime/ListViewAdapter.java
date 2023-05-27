package com.udara.traveltime;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class ListViewAdapter extends RecyclerView.Adapter<MyListHolder> {

    Context context;
    List<list_items> list_items;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ListViewAdapter(Context context, List<list_items> list_items) {
        this.context = context;
        this.list_items = list_items;
    }

    @NonNull
    @Override
    public MyListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyListHolder(LayoutInflater.from(context).inflate(R.layout.result_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyListHolder holder, @SuppressLint("RecyclerView") int position) {
        int buttonId = holder.route_button_id.getId();
        holder.depatureLocation.setText(list_items.get(position).getDepature_location());
        holder.arrivalLocation.setText(list_items.get(position).getArrival_location());
        holder.time.setText(list_items.get(position).getTime());
        holder.price.setText(list_items.get(position).getPrice());
        holder.bus_no.setText(list_items.get(position).getBus_no());
        holder.route_no.setText(list_items.get(position).getRoute_no());
        holder.route_button_id.setTag(list_items.get(position).getRoute_button_id());

//        Log.d("TN", String.valueOf( list_items.get(position).getRoute_button_id()));
        // Set click listener on the item view or any specific view within it
        holder.route_button_id.setOnClickListener(new View.OnClickListener() {  // todo change the item view to the routebutton_id
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position);
                    Log.d("TN", "HI HI");
                    Log.d("TN", String.valueOf( list_items.get(position).getRoute_button_id()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_items.size();
    }
}
