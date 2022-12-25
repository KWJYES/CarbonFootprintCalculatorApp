package com.example.carbonfootprintcalculator.view.activity;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.carbonfootprintcalculator.R;
import com.example.carbonfootprintcalculator.adapter.MainRVItemAdapter;
import com.example.carbonfootprintcalculator.base.BaseActivity;
import com.example.carbonfootprintcalculator.callback.MainRVItemNumChangeListener;
import com.example.carbonfootprintcalculator.databinding.ActivityMainBinding;
import com.example.carbonfootprintcalculator.entity.MainRVItem;
import com.example.carbonfootprintcalculator.utils.ActivityCollector;
import com.example.carbonfootprintcalculator.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private MainViewModel vm;

    @Override
    protected void initActivity() {
        setTransparentStatusBar(true);
        vm = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setEvent(new Event());
        binding.setVm(vm);
        binding.setLifecycleOwner(this);
    }

    @Override
    protected void observerDataStateUpdateAction() {
        vm.selectPos.observe(this, this::updateRecyclerView);
        vm.requestSate.observe(this, s -> Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show());
    }

    private void updateRecyclerView(Integer integer) {
        List<MainRVItem> mainRvItemList = new ArrayList<>();
        switch (integer) {
            case 0:
                mainRvItemList.addAll(vm.calculatorUtil.getRVItemList("commercialOffice"));
                break;
            case 1:
                mainRvItemList.addAll(vm.calculatorUtil.getRVItemList("DailyLiving"));
                break;
            case 2:
                mainRvItemList.addAll(vm.calculatorUtil.getRVItemList("Electronics"));
                break;
            case 3:
                mainRvItemList.addAll(vm.calculatorUtil.getRVItemList("MealsADay"));
                break;
            case 4:
                mainRvItemList.addAll(vm.calculatorUtil.getRVItemList("tourism"));
                break;
            case 5:
                mainRvItemList.addAll(vm.calculatorUtil.getRVItemList("transformation"));
                break;
        }
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        MainRVItemAdapter adapter=new MainRVItemAdapter(mainRvItemList);
        adapter.setMainRVItemNumChangeListener(item -> vm.calculatorUtil.updateNum(item.name,item.num));
        binding.recyclerview.setAdapter(adapter);
    }

    @Override
    protected void initView() {
        vm.selectPos.setValue(0);
        vm.carbonNum.setValue("0.0");
    }

    public class Event {
        public void select0(View view) {
            vm.selectPos.setValue(0);
        }

        public void select1(View view) {
            vm.selectPos.setValue(1);
        }

        public void select2(View view) {
            vm.selectPos.setValue(2);
        }

        public void select3(View view) {
            vm.selectPos.setValue(3);
        }

        public void select4(View view) {
            vm.selectPos.setValue(4);
        }

        public void select5(View view) {
            vm.selectPos.setValue(5);
        }

        public void menu(View view) {
            showPopupMenu(view);
        }

        public void clear(View view){
            vm.clear();
            updateRecyclerView(vm.selectPos.getValue());
        }

        public void calculator(View view){
            vm.calculator();
        }
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()){
                case R.id.history:
                    startActivity(new Intent(MainActivity.this,HistoryRecordActivity.class));
                    break;
                case R.id.login_out:
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    ActivityCollector.loginOutFinish();
                    break;
            }
            return false;
        });
        popupMenu.show();
    }
}