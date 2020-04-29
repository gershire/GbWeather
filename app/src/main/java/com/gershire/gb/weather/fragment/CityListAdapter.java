package com.gershire.gb.weather.fragment;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gershire.gb.weather.R;
import com.gershire.gb.weather.model.CityWeather;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder> {
    private CityListDataSource dataSource;
    private OnItemClickListener onItemClickListener;

    public CityListAdapter(CityListDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.populate(dataSource.getItem(i));
    }

    @Override
    public int getItemCount() {
        return dataSource.getSize();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView cityImage;
        private ImageView condImage;
        private TextView label;
        private View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cityImage = itemView.findViewById(R.id.city_card_image);
            condImage = itemView.findViewById(R.id.cond_card_image);
            label = itemView.findViewById(R.id.item_label);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null)
                        onItemClickListener.onItemClick(v, getAdapterPosition());
                }
            });
            this.itemView = itemView;
        }

        public void populate(CityWeather data) {
            label.setText(data.getName());
            Context context = itemView.getContext();
            cityImage.setImageDrawable(context.getDrawable(data.getBgId()));
            condImage.setImageDrawable(context.getDrawable(data.getConditions()));
        }
    }
}
