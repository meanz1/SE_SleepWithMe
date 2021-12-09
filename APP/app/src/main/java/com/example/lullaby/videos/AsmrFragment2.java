package com.example.lullaby.videos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.lullaby.R;
import com.example.lullaby.data.AccountData;

public class AsmrFragment2 extends Fragment implements View.OnClickListener{
    public String[] id = new String[3];
    public String[] edu_id = new String[3];
    public String[] ani_id = new String[3];
    public String[] nat_id = new String[3];
    public String[] dai_id = new String[3];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(
                R.layout.fragment_asmr_2, container, false);

        ImageButton btn1 = (ImageButton)view.findViewById(R.id.video5);
        btn1.setOnClickListener(this);
        ImageButton btn2 = (ImageButton)view.findViewById(R.id.video6);
        btn2.setOnClickListener(this);
        ImageButton btn3 = (ImageButton)view.findViewById(R.id.video7);
        btn3.setOnClickListener(this);


        id[0] = "Av3I8QQa80U";
        id[1] = "RJCodiQW9vA";
        id[2] = "hYxojSUMaU4";

        ani_id[0] = "mS849gxNW4o";
        ani_id[1] = "AyGYQh_6s6g";
        ani_id[2] = "Z-KnyrxjeAA";

        edu_id[0] = "28PANniKWXA";
        edu_id[1] = "PkbVKZroeto";
        edu_id[2] = "0IpOWAAkKaI";

        nat_id[0] = "200Evg4ndJk";
        nat_id[1] = "a9DS0uPVu5c";
        nat_id[2] = "gZzuQDRSl9w";

        dai_id[0] = "-3hxDkxuyD4";
        dai_id[1] = "q7tb1OciN_Y";
        dai_id[2] = "71hFJDmpzm4";

        Log.d("asdf", "hihih: " + AccountData.getInstance().profiles.get(AccountData.getInstance().getUserSelected()).getCategory2());
        switch(AccountData.getInstance().profiles.get(AccountData.getInstance().getUserSelected()).getCategory2()){
            case "동물":
                id = ani_id;
                break;
            case "교육":
                id = edu_id;
                Log.d("자연 선택됨",AccountData.getInstance().profiles.get(AccountData.getInstance().getUserSelected()).getCategory2());
                break;
            case "자연":
                id = nat_id;
                break;
            case "일상":
                id = dai_id;
                break;
        }

        Glide.with(this)
                .load("https://i.ytimg.com/vi/" + id[0] + "/maxresdefault.jpg")
                .into(btn1);
        Glide.with(this)
                .load("https://i.ytimg.com/vi/" + id[1] + "/maxresdefault.jpg")
                .into(btn2);
        Glide.with(this)
                .load("https://i.ytimg.com/vi/" + id[2] + "/maxresdefault.jpg")
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
