package com.example.lullaby.videos;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.example.lullaby.R;

public class MeditFragment1 extends Fragment implements View.OnClickListener{
    public String[] id = new String[3];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(
                R.layout.fragment_medit1, container, false);

        ImageButton btn1 = (ImageButton)view.findViewById(R.id.video1);
        btn1.setOnClickListener(this);
        ImageButton btn2 = (ImageButton)view.findViewById(R.id.video2);
        btn2.setOnClickListener(this);
        ImageButton btn3 = (ImageButton)view.findViewById(R.id.video3);
        btn3.setOnClickListener(this);

        id[0] = "BR4WG1XlLZw";
        id[1] = "TsZ6K6RLb-4";
        id[2] = "Fkf9dpdoDUs";

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
            case R.id.video1:
                intent.putExtra("videoId", id[0]);
                break;
            case R.id.video2:
                intent.putExtra("videoId", id[1]);
                break;
            case R.id.video3:
                intent.putExtra("videoId", id[2]);
                break;
        }
        getActivity().startActivity(intent);
    }

}
