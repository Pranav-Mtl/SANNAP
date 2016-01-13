package com.example.admin.sannap;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PainFragment extends Fragment {


    public PainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_pain, container, false);

        ImageView forwardBtn= (ImageView) view.findViewById(R.id.pain_frwd_btn);

       forwardBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Fragment fragment = new EmotionFragment();
               FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
               FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
               fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left);
               fragmentTransaction.replace(R.id.pain_fragment, fragment);
               fragmentTransaction.addToBackStack(null);
               fragmentTransaction.commit();

           }
       });


        return  view;
    }


}
