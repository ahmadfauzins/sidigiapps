package com.fauzi.sidigiapps.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fauzi.sidigiapps.Model.Buah;
import com.fauzi.sidigiapps.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BuahAdapter extends RecyclerView.Adapter<BuahAdapter.ListViewHolder> {
    ArrayList<Buah> listBuah;
    private OnItemClickCallback onItemClickCallback;

    public interface OnItemClickCallback {
        void onItemClicked(Buah data);
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public BuahAdapter(ArrayList<Buah> list) {
        this.listBuah = list;
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layanan, parent, false);
        return new ListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Buah buah = listBuah.get(position);
        Picasso.get()
                .load(buah.getPhoto())
                .placeholder(R.drawable.icons8goal)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imgPhoto);

        holder.tvName.setText(buah.getName());
        holder.tvDetail.setText(buah.getManfaat());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listBuah.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBuah.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvDetail;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.imgBuah);
            tvName = itemView.findViewById(R.id.name);
            tvDetail = itemView.findViewById(R.id.manfaat);
        }
    }
}
