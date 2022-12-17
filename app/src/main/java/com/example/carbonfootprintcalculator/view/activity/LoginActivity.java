package com.example.carbonfootprintcalculator.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.carbonfootprintcalculator.R;
import com.example.carbonfootprintcalculator.base.BaseActivity;
import com.example.carbonfootprintcalculator.databinding.ActivityLoginBinding;
import com.example.carbonfootprintcalculator.viewmodel.LoginViewModel;

public class LoginActivity extends BaseActivity {

    private LoginViewModel vm;
    private ActivityLoginBinding binding;

    @Override
    protected void initActivity() {
        setTransparentStatusBar(true);
        vm = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(LoginViewModel.class);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.setEvent(new Event());
        binding.setVm(vm);
        binding.setLifecycleOwner(this);
    }

    @Override
    protected void observerDataStateUpdateAction() {
        vm.loginSate.observe(this, s -> {
            Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
            if(s.equals("登陆成功!")){
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }
        });
    }

    public class Event{
        public void login(View view){
            vm.login();
        }
        public void register(View view){
            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        }
    }
}