package com.lamine.quiz.ui.quiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lamine.quiz.R;
import com.lamine.quiz.data.Question;
import com.lamine.quiz.databinding.FragmentQuizBinding;
import com.lamine.quiz.injection.ViewModelFactory;
import com.lamine.quiz.ui.welcome.WelcomeFragment;

import java.util.Arrays;
import java.util.List;


public class QuizFragment extends Fragment {

    FragmentQuizBinding binding ;

    private QuizViewModel viewModel;

  public static QuizFragment newInstance() {
      QuizFragment fragment = new QuizFragment();

      return fragment;

  }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(QuizViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      binding = FragmentQuizBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         viewModel.startQuiz();
        viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(QuizViewModel.class);

        // Observer for the currentQuestion
        viewModel.currentQuestion.observe(getViewLifecycleOwner(), new Observer<Question>() {
            @Override
            public void onChanged(Question question) {
                binding.question.setText(question.getQuestion());
                binding.answer1.setText(question.getAnswers().get(0));
                binding.answer2.setText(question.getAnswers().get(1));
                binding.answer3.setText(question.getAnswers().get(2));
                binding.answer4.setText(question.getAnswers().get(3));
            }

        });

        viewModel.isLastQuestion.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLastQuestion) {
                if (isLastQuestion){
                    binding.next.setText("Finish");
                } else {
                    binding.next.setText("Next");
                }
            }
        });

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isLastQuestion = viewModel.isLastQuestion.getValue();
                if(isLastQuestion != null && isLastQuestion){
                    displayResultDialog();
                } else {
                    viewModel.nextQuestion();
                    resetQuestion();
                }
            }
        });

        binding.answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAnswer(binding.answer1, 0);
            }
        });

        binding.answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAnswer(binding.answer2, 1);
            }
        });

        binding.answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAnswer(binding.answer3, 2);
            }
        });

        binding.answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAnswer(binding.answer4, 3);
            }
        });

    }

    private void updateAnswer(Button button, Integer index){
        showAnswerValidity(button, index);
        enableAllAnswers(false);
        binding.next.setVisibility(View.VISIBLE);
    }

    private void showAnswerValidity(Button button, Integer index){
        Boolean isValid = viewModel.isAnswerValid(index);
        if (isValid) {
            button.setBackgroundColor(Color.parseColor("#388e3c")); // dark green
            binding.validityText.setText("Good Answer ! 💪");
        } else {
            button.setBackgroundColor(Color.RED);
            binding.validityText.setText("Bad answer 😢");
        }
        binding.validityText.setVisibility(View.VISIBLE);
    }

    private void enableAllAnswers(Boolean enable){
        List<Button> allAnswers = Arrays.asList(binding.answer1, binding.answer2, binding.answer3, binding.answer4);
        allAnswers.forEach( answer -> {
            answer.setEnabled(enable);
        });
    }

    private void resetQuestion(){
        List<Button> allAnswers = Arrays.asList(binding.answer1, binding.answer2, binding.answer3, binding.answer4);
        allAnswers.forEach( answer -> {
            answer.setBackgroundColor(Color.parseColor("#6200EE"));
        });
        binding.validityText.setVisibility(View.INVISIBLE);
        enableAllAnswers(true);
    }

    private void displayResultDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Finished !");
        Integer score = viewModel.score.getValue();
        builder.setMessage("Your final score is "+ score);
        builder.setPositiveButton("Quit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                goToWelcomeFragment();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void goToWelcomeFragment(){
        WelcomeFragment welcomeFragment = WelcomeFragment.newInstance();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view, welcomeFragment).commit();
    }

}