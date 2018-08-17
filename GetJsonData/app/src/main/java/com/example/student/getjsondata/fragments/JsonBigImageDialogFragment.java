package com.example.student.getjsondata.fragments;

import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.student.getjsondata.R;
import com.example.student.getjsondata.activtyis.MainActivity;
import com.squareup.picasso.Picasso;

public class JsonBigImageDialogFragment extends DialogFragment {
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_style, container, false);
        ImageView imageView = view.findViewById(R.id.big_pictures);
        Picasso.get().load(((MainActivity) getActivity()).getJsonModel().getUrl()).into(imageView);
        return view;
    }
}
