package com.lamine.quiz.ui.welcome;



import android.graphics.Color;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.text.Editable;
import android.text.TextWatcher;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.lamine.quiz.R;
import com.lamine.quiz.databinding.FragmentWelcomeBinding;
import com.lamine.quiz.ui.quiz.QuizFragment;


public class WelcomeFragment extends Fragment {

    private FragmentWelcomeBinding binding;

    public static WelcomeFragment newInstance() {
        WelcomeFragment fragment = new WelcomeFragment();

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWelcomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.lestGo.setEnabled(false);
       // binding.lestGo.setTextColor(Color.GREEN);
        binding.lestGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("username", binding.username.getText().toString());
                QuizFragment quizFragment = new QuizFragment();
                quizFragment.setArguments(bundle);

                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_container_view, quizFragment).commit();


            }
        });

        binding.username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    binding.lestGo.setEnabled(true);
                    binding.lestGo.setTextColor(Color.WHITE);
                    binding.lestGo.setBackgroundColor(Color.parseColor("#596FB7"));
                } else {
                    binding.lestGo.setEnabled(false);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
               // Log.i("TAG", "afterTextChanged: "+s);

            };


        });
    }
}