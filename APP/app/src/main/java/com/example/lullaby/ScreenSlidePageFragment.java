package com.example.lullaby;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.lullaby.videos.Asmr1Activity;
import com.example.lullaby.videos.Asmr2Activity;
import com.example.lullaby.videos.Asmr3Activity;
import com.example.lullaby.videos.Asmr4Activity;

public class ScreenSlidePageFragment extends Fragment implements View.OnClickListener{

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
        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.video1:
                getActivity().startActivity(new Intent(getActivity(), Asmr1Activity.class));
                break;
            case R.id.video2:
                getActivity().startActivity(new Intent(getActivity(), Asmr2Activity.class));
                break;
            case R.id.video3:
                getActivity().startActivity(new Intent(getActivity(), Asmr3Activity.class));
                break;
            case R.id.video4:
                getActivity().startActivity(new Intent(getActivity(), Asmr4Activity.class));
                break;
        }
    }
}
