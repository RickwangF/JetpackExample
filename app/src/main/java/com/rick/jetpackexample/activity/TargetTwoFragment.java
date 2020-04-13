package com.rick.jetpackexample.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rick.jetpackexample.R;
import com.rick.jetpackexample.databinding.FragmentTargetTwoBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TargetTwoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TargetTwoFragment extends Fragment {

    FragmentTargetTwoBinding mBinding;

    public interface TargetTwoFragmentClickLisnter{
        public void fragmentOneBtnClicked();
    }

    public TargetTwoFragmentClickLisnter lisnter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TargetTwoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TargetTwoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TargetTwoFragment newInstance(String param1, String param2) {
        TargetTwoFragment fragment = new TargetTwoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("Life Circle", "FragmentTwo ----------------------------- onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Life Circle", "FragmentTwo ----------------------------- onCreate");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e("Life Circle", "FragmentTwo ----------------------------- onCreateView");
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_target_two, container, false);
        mBinding.tvOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lisnter != null) {
                    lisnter.fragmentOneBtnClicked();
                }
            }
        });
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("Life Circle", "FragmentTwo ----------------------------- onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("Life Circle", "FragmentTwo ----------------------------- onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("Life Circle", "FragmentTwo ----------------------------- onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("Life Circle", "FragmentTwo ----------------------------- onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("Life Circle", "FragmentTwo ----------------------------- onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("Life Circle", "FragmentTwo ----------------------------- onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Life Circle", "FragmentTwo ----------------------------- onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("Life Circle", "FragmentTwo ----------------------------- onDetach");
    }
}
