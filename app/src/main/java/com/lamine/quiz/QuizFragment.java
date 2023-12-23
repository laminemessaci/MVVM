package com.lamine.quiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lamine.quiz.databinding.FragmentQuizBinding;


public class QuizFragment extends Fragment {

    FragmentQuizBinding binding ;

  public static QuizFragment newInstance() {
      QuizFragment fragment = new QuizFragment();

      return fragment;

  }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      binding = FragmentQuizBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}