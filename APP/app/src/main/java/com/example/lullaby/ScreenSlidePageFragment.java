package com.example.lullaby;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.example.lullaby.videos.Asmr1Activity;

public class ScreenSlidePageFragment extends Fragment implements View.OnClickListener{
    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        ImageButton btn1 = (ImageButton)view.findViewById(R.id.video1);
        btn1.setOnClickListener(this);
        ImageButton btn2 = (ImageButton)view.findViewById(R.id.video2);
        btn2.setOnClickListener(this);
        ImageButton btn3 = (ImageButton)view.findViewById(R.id.video3);
        btn3.setOnClickListener(this);
        ImageButton btn4 = (ImageButton)view.findViewById(R.id.video4);
        btn4.setOnClickListener(this);

        id = "-3hxDkxuyD4";
        //for
        Glide.with(this)
                .load("https://img.youtube.com/vi/" + id + "default.jpg")
                .into(btn1);

        return view;

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), Asmr1Activity.class);
        switch (v.getId()){
            case R.id.video1:
                intent.putExtra("videoId", id);
                break;
            case R.id.video2:
                intent.putExtra("videoId", "p2fxv3PAtLU");
                break;
            case R.id.video3:
                intent.putExtra("videoId", "p2fxv3PAtLU");
                break;
            case R.id.video4:
                intent.putExtra("videoId", "p2fxv3PAtLU");
                break;
        }
        getActivity().startActivity(intent);
    }

}
