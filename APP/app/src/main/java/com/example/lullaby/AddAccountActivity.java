package com.example.lullaby;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.lullaby.data.AccountData;
import com.example.lullaby.data.GlobalVariable;
import com.example.lullaby.data.Profile;
import com.example.lullaby.login.LoginActivity;
import com.example.lullaby.login.SignUpActivity;
import com.example.lullaby.network.ProfileNetworkTask;
import com.example.lullaby.network.SignUpNetworkTask;
import com.example.lullaby.videos.SelectActivity;

import java.util.ArrayList;

public class AddAccountActivity extends AppCompatActivity {
    private ToggleButton toggleMan;
    private ToggleButton toggleWoman;
    private AlertDialog dialog;
    private ToggleButton toggleAnimal;
    private ToggleButton toggleEdu;
    private ToggleButton toggleClassic;
    private ToggleButton toggleJazz;
    private ToggleButton toggleNature;
    private ToggleButton toggleDaily;
    public ArrayList<String> category = new ArrayList<String>();

    String gender = "남"; // 초기값 남자
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        EditText p_name=(EditText)findViewById(R.id.edittext);
        Spinner brSpinner = (Spinner)findViewById(R.id.birth);
        ArrayAdapter brAdapter = ArrayAdapter.createFromResource(this,
                R.array.birth, android.R.layout.simple_spinner_item);
        brAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brSpinner.setAdapter(brAdapter);

        Button saveButton = findViewById(R.id.add_save);
        saveButton.setOnClickListener(new View.OnClickListener(){ // 계정 추가 완료버튼 눌렀을때
            @Override
            public void onClick(View v) {
                if (p_name.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddAccountActivity.this);
                    dialog = builder.setMessage("이름을 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }
                if (category.size() != 2) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddAccountActivity.this);
                    dialog = builder.setMessage("2개의 카테고리를 선택해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }
                ContentValues values = new ContentValues();
                values.put("id", AccountData.getInstance().profiles.get(AccountData.getInstance().getUserSelected()).getUserId());
                values.put("name", p_name.getText().toString());
                values.put("gender", gender);
                values.put("age", Integer.parseInt(brSpinner.getSelectedItem().toString()));
                values.put("category1", category.get(0));
                values.put("category2", category.get(1));
                String url = "http://35.213.59.137/profile/add";
                category.clear();
                ProfileNetworkTask ProfileNetworkTask = new ProfileNetworkTask(url, values);
                ProfileNetworkTask.execute();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() { if(ProfileNetworkTask.success) {
                        finish();
                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddAccountActivity.this);
                        dialog = builder.setMessage("프로필 생성에 실패했습니다.").setNegativeButton("확인", null).create();
                        dialog.show();}}
                }, 1000);
            }
        });

        Button backButton = findViewById(R.id.add_cancel);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toggleMan = (ToggleButton) this.findViewById(R.id.gender_man);
        toggleMan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (toggleMan.isChecked()) {
                    toggleWoman.setChecked(false);
                    toggleWoman.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.ok_buttonshape)
                    );
                    toggleMan.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.ok_select_buttonshape)
                    );
                    gender = "남";
                } else {
                    toggleMan.setBackgroundDrawable(getResources().getDrawable(R.drawable.ok_buttonshape));
                }
            }
        });
        toggleWoman = (ToggleButton) this.findViewById(R.id.gender_woman);
        toggleWoman.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (toggleWoman.isChecked()) {
                    toggleMan.setChecked(false);
                    toggleMan.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.ok_buttonshape)
                    );
                    toggleWoman.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.ok_select_buttonshape)
                    );
                    gender = "여";
                } else {
                    toggleWoman.setBackgroundDrawable(getResources().getDrawable(R.drawable.ok_buttonshape));
                }
            }
        });
        toggleAnimal = (ToggleButton) this.findViewById(R.id.animal);
        toggleEdu = (ToggleButton) this.findViewById(R.id.edu);
        toggleClassic = (ToggleButton) this.findViewById(R.id.classic);
        toggleJazz = (ToggleButton) this.findViewById(R.id.jazz);
        toggleNature = (ToggleButton) this.findViewById(R.id.nature);
        toggleDaily= (ToggleButton) this.findViewById(R.id.daily);
        toggleAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleAnimal.isChecked()){
                    toggleAnimal.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_animal));
                    category.add("동물");
                }else{
                    toggleAnimal.setBackgroundDrawable(getResources().getDrawable(R.drawable.animal));
                    category.remove("동물");
                }
            }
        });
        toggleEdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleEdu.isChecked()){
                    toggleEdu.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_edu));
                    category.add("교육");
                }else{
                    toggleEdu.setBackgroundDrawable(getResources().getDrawable(R.drawable.education));
                    category.remove("교육");
                }
            }
        });
        toggleClassic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleClassic.isChecked()){
                    toggleClassic.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_classic));
                    category.add("클래식");
                }else{
                    toggleClassic.setBackgroundDrawable(getResources().getDrawable(R.drawable.classic));
                    category.remove("클래식");
                }
            }
        });
        toggleJazz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleJazz.isChecked()){
                    toggleJazz.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_jazz));
                    category.add("재즈");
                }else{
                    toggleJazz.setBackgroundDrawable(getResources().getDrawable(R.drawable.jazz));
                    category.remove("재즈");
                }
            }
        });
        toggleNature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleNature.isChecked()){
                    toggleNature.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_nature));
                    category.add("자연");
                }else{
                    toggleNature.setBackgroundDrawable(getResources().getDrawable(R.drawable.nature));
                    category.remove("자연");
                }
            }
        });
        toggleDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleDaily.isChecked()){
                    toggleDaily.setBackgroundDrawable(getResources().getDrawable(R.drawable.select_daily));
                    category.add("일상");
                }else{
                    toggleDaily.setBackgroundDrawable(getResources().getDrawable(R.drawable.daily));
                    category.remove("일상");
                }
            }
        });
    }
}