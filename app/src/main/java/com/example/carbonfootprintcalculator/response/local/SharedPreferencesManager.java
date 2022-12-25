package com.example.carbonfootprintcalculator.response.local;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;

import com.example.carbonfootprintcalculator.utils.MyApplication;

import java.util.HashSet;
import java.util.Set;

public class SharedPreferencesManager implements ISharedPreferencesRequest {

    /**
     * 使用单例模式
     */
    private SharedPreferencesManager(){}
    private static SharedPreferencesManager sharedPreferencesManager;
    public static SharedPreferencesManager getInstance(){
        if(sharedPreferencesManager==null){
            synchronized (SharedPreferencesManager.class){
                if(null==sharedPreferencesManager){
                    sharedPreferencesManager=new SharedPreferencesManager();
                }
            }
        }
        return sharedPreferencesManager;
    }

    private SharedPreferences.Editor editor;
    private SharedPreferences.Editor getEditor(Context context){
        if(editor==null){
            synchronized (SharedPreferences.Editor.class){
                if(null==editor){
                    editor=context.getSharedPreferences("data",MODE_PRIVATE).edit();
                }
            }
        }
        return editor;
    }

    private SharedPreferences pref;
    private SharedPreferences getPref(Context context){
        if(pref==null){
            synchronized (SharedPreferences.class){
                if(null==pref){
                    pref=context.getSharedPreferences("data",MODE_PRIVATE);
                }
            }
        }
        return pref;
    }


    /**
     * 当前用户账号
     * @param context
     * @return
     */
    @Override
    public String getAccount(Context context){
        return getPref(context).getString("account","");
    }

    @Override
    public String getPassword(Context context) {
        return getPref(context).getString("password","");
    }

    @Override
    public void applyAccount(Context context,String account){
        getEditor(context).putString("account",account);
        getEditor(context).apply();
    }

    @Override
    public void applyPassword(Context context, String password) {
        getEditor(context).putString("password",password);
        getEditor(context).apply();
    }

}
