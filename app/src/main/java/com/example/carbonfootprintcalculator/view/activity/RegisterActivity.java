package com.example.carbonfootprintcalculator.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.carbonfootprintcalculator.R;
import com.example.carbonfootprintcalculator.base.BaseActivity;
import com.example.carbonfootprintcalculator.databinding.ActivityRegisterBinding;
import com.example.carbonfootprintcalculator.viewmodel.RegisterViewModel;

public class RegisterActivity extends BaseActivity {

    private RegisterViewModel vm;
    private ActivityRegisterBinding binding;

    @Override
    protected void initActivity() {
        vm = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(RegisterViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.setEvent(new Event());
        binding.setVm(vm);
        binding.setLifecycleOwner(this);
    }

    @Override
    protected void observerDataStateUpdateAction() {
        vm.requestState.observe(this, s -> {
            Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
            if (s.equals("注册成功!"))
                finish();
        });
    }

    public class Event {
        public void register(View view) {
            vm.register();
        }
    }
}