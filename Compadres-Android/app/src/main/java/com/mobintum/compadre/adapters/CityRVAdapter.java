package com.mobintum.compadre.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobintum.compadre.R;
import com.mobintum.compadre.application.AppController;
import com.mobintum.compadre.models.City;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Rick on 16/05/16.
 * email: ricardo.centeno@mobintum.com
 */
public class CityRVAdapter extends RecyclerView.Adapter<CityRVAdapter.ViewHolder> {

    private List<City> objects;
    private Context context;
    private OnItemClickListener mListener;

    public CityRVAdapter(List<City> objects, Context context, OnItemClickListener mListener) {
        this.objects = objects;
        this.context = context;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_city, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final City city = objects.get(position);
        holder.txtTownName.setText(city.getName());
        int id = context.getResources().getIdentifier(city.getPathMainPhoto(), "drawable", context.getPackageName());

        holder.imgTown.setImageResource(id);
        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(city);
            }
        });

    }


    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTown;
        TextView txtTownName;
        View parentView;
        public ViewHolder(View itemView) {
            super(itemView);
            imgTown = (ImageView) itemView.findViewById(R.id.imgTown);
            txtTownName = (TextView) itemView.findViewById(R.id.txtTownName);
            parentView = itemView;
        }
    }

    public interface OnItemClickListener{
        public void onClick(City city);
    }
}
