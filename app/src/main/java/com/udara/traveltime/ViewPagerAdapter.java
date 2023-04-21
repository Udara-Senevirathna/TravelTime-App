package com.udara.traveltime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;

    int sliderAllImages[] = {R.drawable.untitled_design_removebg_preview__1_, R.drawable.__removebg_preview, R.drawable._23};
    int sliderAllTitle[] = {R.string.screen1, R.string.screen2, R.string.screen3};
    int sliderAllDescription[] = {R.string.screen1Description, R.string.screen2Description, R.string.screen3Description};

    public ViewPagerAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount(){
        return sliderAllTitle.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position){

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_screen, container, false);

        ImageView sliderImage = (ImageView) view.findViewById(R.id.sliderImage);
        TextView sliderTitle = (TextView) view.findViewById(R.id.sliderTitle);
        TextView sliderDescription = (TextView) view.findViewById(R.id.sliderDescription);

        sliderImage.setImageResource(sliderAllImages[position]);
        sliderTitle.setText(this.sliderAllTitle[position]);
        sliderDescription.setText(this.sliderAllDescription[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object){
        container.removeView((LinearLayout)object);
    }
}
