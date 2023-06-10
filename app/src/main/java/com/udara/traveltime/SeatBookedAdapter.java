package com.udara.traveltime;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;
public class SeatBookedAdapter extends BaseAdapter {
    private List<Integer> seatIds;
    private Context context;
    private OnItemClickCallback onItemClickCallback;

    public SeatBookedAdapter(List<Integer> seatIds, Context context, OnItemClickCallback onItemClickCallback) {
        this.seatIds = seatIds;
        this.context = context;
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public int getCount() {
        return seatIds.size();
    }

    @Override
    public Object getItem(int position) {
        return seatIds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_seat_selection, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.button = convertView.findViewById(R.id.my_button1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        int seatId = seatIds.get(position);
        viewHolder.button.setText(String.valueOf(seatId));
        viewHolder.button.setTag(seatId);

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int seatId = (int) viewHolder.button.getTag();
                viewHolder.button.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
                onItemClickCallback.onItemClick(seatId);
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        Button button;
    }

    public interface OnItemClickCallback {
        void onItemClick(int seatId);
    }
}
