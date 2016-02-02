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


public class QuestionOne extends Fragment {


    SignupBE objSignupBE;


    public QuestionOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_question_one, container, false);


        objSignupBE=new SignupBE();

        final ImageButton noBtn= (ImageButton) view.findViewById(R.id.qfirst_nobtn);
        ImageButton yesBtn= (ImageButton) view.findViewById(R.id.qfirst_yesbtn);

        ImageButton back= (ImageButton) view.findViewById(R.id.back_btn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });



//        View.OnClickListener clickListener=new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(v.getId()==R.id.qfirst_nobtn)
//                {
//                    Fragment fragment = new QuestionTwo();
//                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left);
//                    fragmentTransaction.replace(R.id.first_fragment, fragment);
//                    fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.commit();
//                }
//
//
//            }
//        };
//
//        noBtn.setOnClickListener(clickListener);




        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objSignupBE.setPeriodLength("");
               nextFragment();

            }
        });

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivityForResult(new Intent(getActivity(), PeriodLenghtPicker.class).putExtra("SignupBE", objSignupBE), 1);
            }
        });
        return view;
    }





    public void   nextFragment()
    {
        Fragment fragment = new QuestionTwo();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Bundle args = new Bundle();
        args.putSerializable("SignupBE", objSignupBE);
        fragment.setArguments(args);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left);
        fragmentTransaction.replace(R.id.first_fragment, fragment);
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
