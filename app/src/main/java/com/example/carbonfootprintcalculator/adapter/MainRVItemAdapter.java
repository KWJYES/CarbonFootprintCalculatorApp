package com.example.carbonfootprintcalculator.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbonfootprintcalculator.R;
import com.example.carbonfootprintcalculator.callback.MainRVItemNumChangeListener;
import com.example.carbonfootprintcalculator.databinding.ItemMainRvBinding;
import com.example.carbonfootprintcalculator.entity.MainRVItem;

import java.util.List;

public class MainRVItemAdapter extends RecyclerView.Adapter<MainRVItemAdapter.ViewHolder> {

    private List<MainRVItem> mainRVItemList;

    public MainRVItemAdapter(List<MainRVItem> mainRVItemList) {
        this.mainRVItemList = mainRVItemList;
    }

    private MainRVItemNumChangeListener mainRVItemNumChangeListener;

    public void setMainRVItemNumChangeListener(MainRVItemNumChangeListener mainRVItemNumChangeListener) {
        this.mainRVItemNumChangeListener = mainRVItemNumChangeListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMainRvBinding binding = DataBindingUtil.inflate((LayoutInflater.from(parent.getContext())),
                R.layout.item_main_rv,
                parent,
                false);
        ViewHolder holder = new ViewHolder(binding);
        binding.setClick(new Click(holder));
        binding.etNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int pos = holder.getAdapterPosition();
                if(pos==-1)return;
                String text=holder.binding.etNum.getText().toString();
                if(text.equals("")) return;
                double num= Double.parseDouble(text);
                num=Math.max(num,0) ;
                MainRVItem item = mainRVItemList.get(pos);
                item.num=num;
                mainRVItemNumChangeListener.onNumChange(item);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setItem(mainRVItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mainRVItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMainRvBinding binding;

        public ViewHolder(@NonNull ItemMainRvBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class Click {
        private ViewHolder holder;

        public Click(ViewHolder holder) {
            this.holder = holder;
        }

        public void add(View view) {
            int pos = holder.getAdapterPosition();
            if(pos==-1)return;
            String text=holder.binding.etNum.getText().toString();
            if(text.equals("")) return;
            double num= Double.parseDouble(text);
            num=num+1;
            MainRVItem item = mainRVItemList.get(pos);
            item.num=num;
            mainRVItemNumChangeListener.onNumChange(item);
            holder.binding.etNum.setText((num+""));
        }

        public void sub(View view) {
            int pos = holder.getAdapterPosition();
            if(pos==-1)return;
            String text=holder.binding.etNum.getText().toString();
            if(text.equals("")) return;
            double num= Double.parseDouble(text);
            num=Math.max(num-1,0) ;
            MainRVItem item = mainRVItemList.get(pos);
            item.num=num;
            mainRVItemNumChangeListener.onNumChange(item);
            holder.binding.etNum.setText((num+""));
        }
    }
}
