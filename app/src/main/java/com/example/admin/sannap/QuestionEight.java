package com.example.admin.sannap;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionEight extends Fragment {


    public QuestionEight() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_question_eight, container, false);

        Button noBtn= (Button) view.findViewById(R.id.eight_fragmnet_noBtn);

        Button yesBtn= (Button) view.findViewById(R.id.eight_fragmnet_yesBtn);

        ImageButton back= (ImageButton) view .findViewById(R.id.eight_backBtn);

       back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                previousFragment();

            }
        });

      noBtn.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               startActivity(new Intent(getActivity(), SignUpScreen.class));

         }
       });

     yesBtn.setOnClickListener(new View.OnClickListener() {
          @Override
           public void onClick(View v) {
               startActivity(new Intent(getActivity(),SignUpScreen.class));
          }
       });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousFragment();
            }
        });


        return  view;
    }

   // public void   nextFragment()
//    {
//        Fragment fragment = new QuestionEight();
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left);
//        fragmentTransaction.replace(R.id.seventh_fragment, fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//
//    }

    public  void previousFragment()
    {

        Fragment fragment = new QuestionSeven();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left,R.anim.exit_from_right);
        fragmentTransaction.replace(R.id.eigth_fragment, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }





}
