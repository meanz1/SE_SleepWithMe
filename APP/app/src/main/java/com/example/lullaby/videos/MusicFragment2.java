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

public class MusicFragment2 extends Fragment implements View.OnClickListener{
    public String[] id = new String[4];

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
        ImageButton btn4 = (ImageButton)view.findViewById(R.id.video8);
        btn4.setOnClickListener(this);

        id[0] = "Af_VpSwsEKo";
        id[1] = "rcfbOtMeNFI";
        id[2] = "DZEgZE7kOnc";
        id[3] = "XghIuS2zRYY";

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
            case R.id.video8:
                intent.putExtra("videoId", id[3]);
                break;
        }
        getActivity().startActivity(intent);
    }

}
