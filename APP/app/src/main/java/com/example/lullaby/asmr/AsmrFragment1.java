package com.example.lullaby.asmr;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.example.lullaby.R;

public class AsmrFragment1 extends Fragment implements View.OnClickListener{
    public String[] id = new String[4];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(
                R.layout.fragment_asmr_1, container, false);

        ImageButton btn1 = (ImageButton)view.findViewById(R.id.video1);
        btn1.setOnClickListener(this);
        ImageButton btn2 = (ImageButton)view.findViewById(R.id.video2);
        btn2.setOnClickListener(this);
        ImageButton btn3 = (ImageButton)view.findViewById(R.id.video3);
        btn3.setOnClickListener(this);
        ImageButton btn4 = (ImageButton)view.findViewById(R.id.video4);
        btn4.setOnClickListener(this);

        id[0] = "Fbz4GTDLLnQ";
        id[1] = "-3hxDkxuyD4";
        id[2] = "5QGxUg7MkHo";
        id[3] = "vqE7WKq2S0k";

        Glide.with(this)
                .load("https://img.youtube.com/vi/" + id[0] + "/default.jpg")
                .into(btn1);
        Glide.with(this)
                .load("https://img.youtube.com/vi/" + id[1] + "/default.jpg")
                .into(btn2);
        Glide.with(this)
                .load("https://img.youtube.com/vi/" + id[2] + "/default.jpg")
                .into(btn3);
        Glide.with(this)
                .load("https://img.youtube.com/vi/" + id[3] + "/default.jpg")
                .into(btn4);

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), AsmrVideoActivity.class);
        switch (v.getId()){
            case R.id.video1:
                intent.putExtra("videoId", id[0]);
                break;
            case R.id.video2:
                intent.putExtra("videoId", id[1]);
                break;
            case R.id.video3:
                intent.putExtra("videoId", id[2]);
                break;
            case R.id.video4:
                intent.putExtra("videoId", id[3]);
                break;
        }
        getActivity().startActivity(intent);
    }

}
