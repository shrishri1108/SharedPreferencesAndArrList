package com.example.sharedpreferencesandarrlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sharedpreferencesandarrlist.databinding.ActivityMainBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String et_user_name;
    private String et_age;
    private ArrayList<ModelClass> my_shared_preferences_lst;
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);
        mainBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_user_name = mainBinding.userName.getText().toString();
                et_age = mainBinding.userAge.getText().toString();
                save_Data(et_user_name, et_age);
            }
        });

        loadData();
    }

    private void save_Data(String et_users, String user_age) {
        my_shared_preferences_lst.add(new ModelClass(et_user_name, et_age));

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myDatas", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();

        String jsonarr = gson.toJson(my_shared_preferences_lst);
        editor.putString("student_data", jsonarr);
        editor.apply();
        loadData();
    }

    private void loadData() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myDatas", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("student_data", null);
        Type type = new TypeToken<ArrayList<ModelClass>>() {

        }.getType();
        my_shared_preferences_lst = gson.fromJson(json, type);
        if (my_shared_preferences_lst == null) {
            my_shared_preferences_lst = new ArrayList<>();
            mainBinding.showLstHeading.setText("");
        } else {
            String mycompletesStrinsss ="";
            for (int i = 0; i < my_shared_preferences_lst.size(); i++) {
                mycompletesStrinsss+=("" + my_shared_preferences_lst.get(i).getUsername() + "  ----->  " + my_shared_preferences_lst.get(i).getUserAge() + " . \n");
            }
            mainBinding.showPreferencesGenratedLst.setText(mycompletesStrinsss);
            if(my_shared_preferences_lst.size()>0){
                mainBinding.showLstHeading.setVisibility(View.VISIBLE);

            }
        }
    }
}