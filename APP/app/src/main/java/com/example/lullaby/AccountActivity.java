package com.example.lullaby;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.lullaby.data.AccountData;
import com.example.lullaby.videos.SelectActivity;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {
    public static Context aContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aContext = this;
        setContentView(R.layout.activity_account);
        // 리사이클러뷰에 표시할 데이터 리스트 생성.
        ArrayList<String> list = new ArrayList<>();
        for (int i=0; i<AccountData.getInstance().profiles.size()+1; i++) {
            if(i==AccountData.getInstance().profiles.size()){
                list.add("+");
            }else {
                AccountData.getInstance().setUserSelected(i);
                list.add(AccountData.getInstance().profiles.get(AccountData.getInstance().getUserSelected()).getName());
            }
        }

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = findViewById(R.id.recycler1) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        SimpleTextAdapter adapter = new SimpleTextAdapter(list) ;
        recyclerView.setAdapter(adapter) ;
    }
    void addPage(){
        Intent intent = new Intent(this, AddAccountActivity.class);
        startActivity(intent);
    }
}