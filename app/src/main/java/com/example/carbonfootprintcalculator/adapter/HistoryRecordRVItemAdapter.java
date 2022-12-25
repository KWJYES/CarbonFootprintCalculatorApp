package com.example.carbonfootprintcalculator.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbonfootprintcalculator.R;
import com.example.carbonfootprintcalculator.callback.ItemCallback;
import com.example.carbonfootprintcalculator.databinding.ItemHistoryRecoverRvBinding;
import com.example.carbonfootprintcalculator.databinding.ItemMainRvBinding;
import com.example.carbonfootprintcalculator.entity.body.Item;
import com.example.carbonfootprintcalculator.utils.ItemTouchCallBack;

import java.util.ArrayList;
import java.util.List;

public class HistoryRecordRVItemAdapter extends RecyclerView.Adapter<HistoryRecordRVItemAdapter.ViewHolder> implements ItemTouchCallBack.OnItemTouchListener{
    private List<Item> itemList;
    private ItemCallback itemCallback;

    public void setItemCallback(ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
    }

    public HistoryRecordRVItemAdapter(List<Item> itemList) {
        this.itemList = new ArrayList<>(itemList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHistoryRecoverRvBinding binding = DataBindingUtil.inflate((LayoutInflater.from(parent.getContext())),
                R.layout.item_history_recover_rv,
                parent,
                false);
        ViewHolder holder = new ViewHolder(binding);
        binding.setClick(new Click(holder));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setItem(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public void onMove(int fromPosition, int toPosition) {

    }

    @Override
    public void onSwiped(int position) {
        if(position==-1)return;
        Item item= itemList.get(position);
        itemCallback.delete(item);
        itemList.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemHistoryRecoverRvBinding binding;
        public ViewHolder(@NonNull ItemHistoryRecoverRvBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }

    public class Click{
        private ViewHolder holder;

        public Click(ViewHolder holder) {
            this.holder = holder;
        }

        public void delete(View view){
            int pos = holder.getAdapterPosition();
            if(pos==-1)return;
            Item item= itemList.get(pos);
            itemCallback.delete(item);
            itemList.remove(pos);
            notifyItemRemoved(pos);
        }
        public void onItemClick(View view){

        }
    }


}
