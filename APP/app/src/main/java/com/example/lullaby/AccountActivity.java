package com.example.lullaby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ToggleButton eunseoButton =
                (ToggleButton) this.findViewById(R.id.eunseoButton);
        eunseoButton.setChecked(true);
        ToggleButton minjiButton =
                (ToggleButton) this.findViewById(R.id.minjiButton);
        ToggleButton myeongkyunButton =
                (ToggleButton) this.findViewById(R.id.myeongkyunButton);
        ToggleButton haeunButton =
                (ToggleButton) this.findViewById(R.id.haeunButton);
        ImageButton addButton =
                (ImageButton) this.findViewById(R.id.add);

        eunseoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (eunseoButton.isChecked()) {
                eunseoButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.account_eunseo_s));
                minjiButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.account_minji));
                minjiButton.setChecked(false);
                myeongkyunButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.account_myeong));
                myeongkyunButton.setChecked(false);
                haeunButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.account_haeun));
                haeunButton.setChecked(false);
                } else {
                }
            }
        });
        minjiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (minjiButton.isChecked()) {
                    eunseoButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.account_eunseo));
                    eunseoButton.setChecked(false);
                    minjiButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.account_minji_s));
                    myeongkyunButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.account_myeong));
                    myeongkyunButton.setChecked(false);
                    haeunButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.account_haeun));
                    haeunButton.setChecked(false);
                } else {
                }
            }
        });
        myeongkyunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myeongkyunButton.isChecked()) {
                    eunseoButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.account_eunseo));
                    eunseoButton.setChecked(false);
                    minjiButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.account_minji));
                    minjiButton.setChecked(false);
                    myeongkyunButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.account_myeong_s));
                    haeunButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.account_haeun));
                    haeunButton.setChecked(false);
                } else {
                }
            }
        });
        haeunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (haeunButton.isChecked()) {
                    eunseoButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.account_eunseo));
                    eunseoButton.setChecked(false);
                    minjiButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.account_minji));
                    minjiButton.setChecked(false);
                    myeongkyunButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.account_myeong));
                    myeongkyunButton.setChecked(false);
                    haeunButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.account_haeun_s));
                } else {
                }
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddAccountActivity.class);
                startActivity(intent);
            }
        });
    }
}