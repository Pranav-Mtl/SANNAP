package com.example.admin.sannap;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.admin.sannap.BE.SignupBE;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionThree extends Fragment {

    SignupBE objSignupBE;

    public QuestionThree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_question_three, container, false);

        objSignupBE = (SignupBE) getArguments().getSerializable("SignupBE");

        Button noBtn= (Button) view.findViewById(R.id.third_fragment_noBtn);

        Button yesBtn= (Button) view.findViewById(R.id.third_fragment_yesBtn);



        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objSignupBE.setPeriodDate("");
                nextFragment();

            }
        });

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivityForResult(new Intent(getActivity(), PeriodDatePicker.class).putExtra("SignupBE",objSignupBE),1);
            }
        });



        return  view;
    }

    public void   nextFragment()
    {
        Fragment fragment = new QuestionFour();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle args = new Bundle();
        args.putSerializable("SignupBE", objSignupBE);
        fragment.setArguments(args);
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left);
        fragmentTransaction.replace(R.id.third_fragment, fragment);
        fragmentTransaction.commit();

    }

    public  void previousFragment()
    {

        Fragment fragment = new QuestionTwo();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_from_right);
        fragmentTransaction.replace(R.id.third_fragment, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //Write your code if there's no result
            objSignupBE= (SignupBE) data.getSerializableExtra("SignupBE");

            nextFragment();
        }
    }



}
