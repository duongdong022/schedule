package com.dddong.servicesample.fragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dddong.servicesample.R;

public class DaysOptionsFragment extends DialogFragment implements View.OnClickListener {

    private TextView btnOK, btnCancel;

    public static DaysOptionsFragment newInstance() {
        DaysOptionsFragment fragment = new DaysOptionsFragment();
        return fragment;
    }

    public DaysOptionsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_days_options, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().setTitle("Repeat Days");

        btnOK = (TextView) view.findViewById(R.id.btnOK);
        btnCancel = (TextView) view.findViewById(R.id.btnCancel);
        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnOK:

                dismiss();
                break;
            case R.id.btnCancel:

                dismiss();
                break;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
