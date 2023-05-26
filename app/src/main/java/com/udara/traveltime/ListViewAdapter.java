package com.udara.traveltime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<MyListHolder> {

    Context context;
    List<list_items>  list_items;

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
    public void onBindViewHolder(@NonNull MyListHolder holder, int position) {
        holder.depatureLocation.setText(list_items.get(position).getDepature_location());
        holder.arrivalLocation.setText(list_items.get(position).getArrival_location());
        holder.time.setText(list_items.get(position).getTime());
        holder.price.setText(list_items.get(position).getPrice());
        holder.bus_no.setText(list_items.get(position).bus_no);
        holder.route_no.setText(list_items.get(position).route_no);
//        holder.route_button_id.setText(list_items.get(position).route_button_id);
//        holder.image.setImageResource(list_items.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return list_items.size();
    }
}
