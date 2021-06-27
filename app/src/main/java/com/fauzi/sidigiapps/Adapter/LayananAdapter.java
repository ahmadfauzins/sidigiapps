package com.fauzi.sidigiapps.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fauzi.sidigiapps.Model.DataLayanan;
import com.fauzi.sidigiapps.R;

import java.util.List;


public class LayananAdapter extends RecyclerView.Adapter<LayananAdapter.holdData> {

    List<DataLayanan> lLayanan;
    private Context context;
    private RecyclerViewClickListener mListener;
    private RelativeLayout mRowContainer;




    public LayananAdapter (Context context, List<DataLayanan> lLayanan, RecyclerViewClickListener listener){
        this.context = context;
        this.lLayanan = lLayanan;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public holdData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_datalayanan, parent, false);
        return new holdData(layout, mListener);

    }

    @Override
    public void onBindViewHolder(@NonNull holdData holder, int position) {
        DataLayanan dataLayanan = lLayanan.get(position);

        holder.tvId.setText(String.valueOf(dataLayanan.getId()));
        holder.tvDesc.setText(dataLayanan.getDeskripsi());
        holder.tvNama.setText(dataLayanan.getNama());



    }

    @Override
    public int getItemCount() {
        return lLayanan.size();
    }


    public class holdData extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvNama,tvDesc,tvId;

        public holdData(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            tvId = itemView.findViewById(R.id.id);
            tvNama = itemView.findViewById(R.id.name);
            tvDesc = itemView.findViewById(R.id.desc);

            mRowContainer = itemView.findViewById(R.id.row_data);

            mListener = listener;
            mRowContainer.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.row_data:
                    mListener.onRowClick(mRowContainer, getAdapterPosition());
                    break;

                default:
                    break;
            }
        }
    }
    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);

        void onLoveClick(View view, int position);
//        void onLoveClick(View view, int position);
    }
}
