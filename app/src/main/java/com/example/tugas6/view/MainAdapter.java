package com.example.tugas6.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas6.R;
import com.example.tugas6.entity.DataData;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder> {
    Context context;
    List<DataData> list;
    MainContact.view aView;

    public MainAdapter(Context context, List<DataData> list, MainContact.view aView) {
        this.context = context;
        this.list = list;
        this.aView = aView;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_data,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final DataData item = list.get(position);
        holder.tvTanggal.setText(item.getTanggal());
        holder.tvKotor.setText(item.getKotor());
        holder.tvPengeluaran.setText(item.getPengeluaran());
        holder.tvBersih.setText(item.getBersih());
        holder.id.setText(item.getId());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aView.editData(item);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                aView.deleteData(item);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvTanggal,tvKotor,tvPengeluaran,tvBersih,id;
        CardView cardView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.tv_item_tanggal);
            tvKotor = itemView.findViewById(R.id.tv_item_kotor);
            tvPengeluaran = itemView.findViewById(R.id.tv_item_pengeluaran);
            tvBersih = itemView.findViewById(R.id.tv_item_bersih);
            id = itemView.findViewById(R.id.tv_item_id);
            cardView = itemView.findViewById(R.id.cv_item);
        }
    }
}
