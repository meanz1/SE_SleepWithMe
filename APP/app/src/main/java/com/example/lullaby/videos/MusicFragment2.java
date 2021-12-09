package com.example.lullaby.videos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.lullaby.R;
import com.example.lullaby.data.AccountData;

public class MusicFragment2 extends Fragment implements View.OnClickListener{
    public String[] id = new String[3];
    public String[] cla_id = new String[3];
    public String[] jaz_id = new String[3];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(
                R.layout.fragment_music2, container, false);

        ImageButton btn1 = (ImageButton)view.findViewById(R.id.video5);
        btn1.setOnClickListener(this);
        ImageButton btn2 = (ImageButton)view.findViewById(R.id.video6);
        btn2.setOnClickListener(this);
        ImageButton btn3 = (ImageButton)view.findViewById(R.id.video7);
        btn3.setOnClickListener(this);

        id[0] = "JMjQP66TXzg"; // 기본
        id[1] = "X901on9pDEo";
        id[2] = "DZEgZE7kOnc";

        cla_id[0] = "KD2EiaIwTH4";
        cla_id[1] = "rcfbOtMeNFI";
        cla_id[2] = "iuVPO3164XE";

        jaz_id[0] = "ZokKDy-cQSs";
        jaz_id[1] = "9GaRkarLl_I";
        jaz_id[2] = "BjuNUDV7PO4";

        switch (AccountData.getInstance().profiles.get(AccountData.getInstance().getUserSelected()).getCategory2()){
            case "클래식":
                id = cla_id;
                break;
            case "재즈":
                id = jaz_id;
                break;
        }

        Glide.with(this)
                .load("https://img.youtube.com/vi/" + id[0] + "/maxresdefault.jpg")
                .into(btn1);
        Glide.with(this)
                .load("https://img.youtube.com/vi/" + id[1] + "/maxresdefault.jpg")
                .into(btn2);
        Glide.with(this)
                .load("https://img.youtube.com/vi/" + id[2] + "/maxresdefault.jpg")
                .into(btn3);

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), VideoActivity.class);
        switch (v.getId()){
            case R.id.video5:
                intent.putExtra("videoId", id[0]);
                break;
            case R.id.video6:
                intent.putExtra("videoId", id[1]);
                break;
            case R.id.video7:
                intent.putExtra("videoId", id[2]);
                break;
        }
        getActivity().startActivity(intent);
    }

}
