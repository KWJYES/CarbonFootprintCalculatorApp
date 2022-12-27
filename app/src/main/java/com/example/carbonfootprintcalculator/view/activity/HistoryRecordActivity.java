package com.example.carbonfootprintcalculator.view.activity;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.carbonfootprintcalculator.R;
import com.example.carbonfootprintcalculator.adapter.HistoryRecordRVItemAdapter;
import com.example.carbonfootprintcalculator.base.BaseActivity;
import com.example.carbonfootprintcalculator.databinding.ActivityHistoryRecordBinding;
import com.example.carbonfootprintcalculator.entity.body.Item;
import com.example.carbonfootprintcalculator.utils.ItemTouchCallBack;
import com.example.carbonfootprintcalculator.utils.MyApplication;
import com.example.carbonfootprintcalculator.viewmodel.HistoryRecordViewModel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryRecordActivity extends BaseActivity {

    ActivityHistoryRecordBinding binding;
    HistoryRecordViewModel vm;


    @Override
    protected void initActivity() {
        setTransparentStatusBar(true);
        vm = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(HistoryRecordViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_history_record);
        binding.setEvent(new Event());
        binding.setLifecycleOwner(this);
    }

    @Override
    protected void observerDataStateUpdateAction() {
        vm.deleteSate.observe(this, s -> Toast.makeText(HistoryRecordActivity.this, s, Toast.LENGTH_SHORT).show());
        vm.findSate.observe(this, s -> {
            if (s.equals(MyApplication.SUSSED))
                binding.llEmptyData.setVisibility(View.GONE);
            else {
                binding.llEmptyData.setVisibility(View.VISIBLE);
                Toast.makeText(HistoryRecordActivity.this, s, Toast.LENGTH_SHORT).show();
            }
            binding.smartRefreshLayout.finishRefresh();
        });
        vm.itemList.observe(this, this::updateRv);
    }

    @Override
    protected void initView() {
        binding.llEmptyData.setVisibility(View.GONE);
        vm.findAll();
        binding.smartRefreshLayout.setOnRefreshListener(refreshLayout -> vm.findAll());
        binding.llEmptyData.setVisibility(View.GONE);
    }

    private void updateRv(List<Item> itemList) {
        List<Item> rItemList=new ArrayList<>();
        for (int i= itemList.size()-1;i>=0;i--)
            rItemList.add(itemList.get(i));
        ItemTouchCallBack touchCallBack = new ItemTouchCallBack();
        binding.rvHistoryItem.setLayoutManager(new LinearLayoutManager(this));
        HistoryRecordRVItemAdapter adapter = new HistoryRecordRVItemAdapter(rItemList);
        adapter.setItemCallback(item -> vm.deleteRecord(item));
        touchCallBack.setOnItemTouchListener(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(touchCallBack);
        binding.rvHistoryItem.setAdapter(adapter);
        itemTouchHelper.attachToRecyclerView(binding.rvHistoryItem);
    }

    public class Event {
        public void back(View view) {
            finish();
        }
    }
}