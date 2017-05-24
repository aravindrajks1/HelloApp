package com.example.aravind.dialerapp;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GoProFragment extends DialogFragment {

    ImageView close;

    public GoProFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_go, container, false);
        //  getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // getDialog().setTitle("Premium");
        close = (ImageView) view.findViewById(R.id.img_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closefragment();
            }
        });
        return view;
    }

    private void closefragment() {
        // getActivity().getSupportFragmentManager().beginTransaction().remove(GoProFragment.this).commit();
        // getActivity().getFragmentManager().popBackStack();
        //getActivity().onBackPressed();
        GoProFragment.this.dismiss();
    }
}
