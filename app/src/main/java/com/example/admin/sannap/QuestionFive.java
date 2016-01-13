package com.example.admin.sannap;
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
public class QuestionFive extends Fragment {

    SignupBE objSignupBE;

    public QuestionFive() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_question_five, container, false);

        objSignupBE = (SignupBE) getArguments().getSerializable("SignupBE");
        Button noBtn= (Button) view.findViewById(R.id.fifth_fragment_noBtn);

        Button yesBtn= (Button) view.findViewById(R.id.fifth_fragment_yesBtn);


        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objSignupBE.setResearch("No");
                nextFragment();

            }
        });

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objSignupBE.setResearch("Yes");
                nextFragment();
            }
        });




        return  view;
    }

    public void   nextFragment()
    {
        Fragment fragment = new QuestionSeven();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left);
        Bundle args = new Bundle();
        args.putSerializable("SignupBE", objSignupBE);
        fragment.setArguments(args);
        fragmentTransaction.replace(R.id.forth_fragment, fragment);

        fragmentTransaction.commit();

    }

    public  void previousFragment()
    {

        Fragment fragment = new QuestionFour();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left,R.anim.exit_from_right);
        fragmentTransaction.replace(R.id.forth_fragment, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }




}
