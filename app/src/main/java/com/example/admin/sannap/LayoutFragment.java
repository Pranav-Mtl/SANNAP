package com.example.admin.sannap;

import android.content.Intent;
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
public class LayoutFragment extends Fragment {


    private  final int REQUEST_CODE=1192;

    public LayoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_layout, container, false);

        ImageView firstBtn= (ImageView) view.findViewById(R.id.emotion_image);
//        ImageView secondBtn= (ImageView) view.findViewById(R.id.pain_image);
//        ImageView emotionBtn= (ImageView) view.findViewById(R.id.emotion_image);
//        ImageView sleepBtn= (ImageView) view.findViewById(R.id.sleep_image);
//        ImageView fifth= (ImageView) view.findViewById(R.id.fifth_image);
//        ImageView energyBtn= (ImageView) view.findViewById(R.id.energy_image);





        firstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), TrackingScreen.class);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });

//
//        secondBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Fragment fragment = new PainFragment();
////                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
////                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//////                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left);
////                fragmentTransaction.replace(R.id.layout_fragment, fragment);
////                fragmentTransaction.addToBackStack(null);
////                fragmentTransaction.commit();
//
//
//                Intent intent=new Intent(getActivity(),TodayFirstActivity.class);
//                startActivity(intent);
//
//
//            }
//        });
//
//
//
//        emotionBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = new EmotionFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left);
//                fragmentTransaction.replace(R.id.layout_fragment, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//
//            }
//        });
//
//
//        sleepBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = new SleepFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left);
//                fragmentTransaction.replace(R.id.layout_fragment, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//
//            }
//        });
//
//
//        fifth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = new FifthFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left);
//                fragmentTransaction.replace(R.id.layout_fragment, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//
//            }
//        });
//
//
//        energyBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = new EnergyFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left);
//                fragmentTransaction.replace(R.id.layout_fragment, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//
//            }
//        });
//
//
//


                return view;
            }

        }