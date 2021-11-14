package com.example.lullaby;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.lullaby.videos.AsmrActivity;

public class ScreenSlidePageFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        ImageButton btn1 = (ImageButton)view.findViewById(R.id.video2);
        btn1.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {
        getActivity().startActivity(new Intent(getActivity(), AsmrActivity.class));
    }
}
