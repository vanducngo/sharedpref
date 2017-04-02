package game.challenge.mysquar.challengegame.view;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import game.challenge.mysquar.challengegame.R;
import game.challenge.mysquar.challengegame.model.QuestionDB;
import game.challenge.mysquar.challengegame.presenter.Presenter;

public class GameFragment extends Fragment implements View.OnClickListener {
    //Constant
    private static final int QUESTION_NUMBER = 15;
    private static final String TAG = GameFragment.class.getSimpleName();

    //Data holder
    private Queue<QuestionDB> mQuestionList;
    private QuestionDB mCurrentQuestion;

    //View holder
    private TextView mTvQuestionNumber;
    private TextView mTvQuestion;
    private TextView mTvAnswerA;
    private TextView mTvAnswerB;
    private TextView mTvAnswerC;
    private TextView mTvAnswerD;
    private ObjectAnimator backgroundAnimator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_game, null);
        initView(view);
        getQuestionAndStartGame();
        return view;
    }

    /**
     * get Question randomly in database and play game
     */
    private void getQuestionAndStartGame() {
        List<QuestionDB> result = Presenter.getInstance().getRandomQuestion(QUESTION_NUMBER);
        if (result != null && result.size() > 0) {
            Log.i(TAG, "Size " + result.size());
            mQuestionList = new ArrayDeque<>();
            mQuestionList.addAll(result);
            playGame();
        }
    }

    /**
     * Play game
     */
    private void playGame() {
        if (!mQuestionList.isEmpty()) {
            mCurrentQuestion = mQuestionList.poll();
            bindQuestionToView();
        } else {
            showDialogFinish();
        }
    }

    /**
     * Show dialog congratulation and ask user to continue or not
     */
    private void showDialogFinish() {
        showDialog(R.string.congrats_title, R.string.congrats_content);
    }

    /**
     * show dialog prompt wrong answer and ask for retry
     */
    private void showDialogRetry() {
        showDialog(R.string.retry_title, R.string.retry_content);
    }

    /**
     * show Dialog with two button. Retry to replay game, exit to quit the game.
     * @param title title of the dialog
     * @param content content of the dialog
     */
    private void showDialog(@StringRes int title, @StringRes int content) {
        new AlertDialog.Builder(getActivity()).setTitle(title)
                .setMessage(content).setPositiveButton(R.string.btn_retry, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getQuestionAndStartGame();
            }
        }).setNegativeButton(R.string.btn_exit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                exitGame();
            }
        }).show();
    }

    /**
     * Exit game/application.
     */
    private void exitGame() {
        getActivity().finish();
    }


    /**
     * check user answer and prompt result in 2 seconds.
     * @param answer
     */
    private void answer(final int answer) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean isAnswerCorrect = Presenter.getInstance().isAnswerCorrect(mCurrentQuestion, answer);
                if (isAnswerCorrect) {
                    animateCorrectAnswer(getCorrectAnswer());
                    goToNextQuestion();
                } else {
                    View v = getCorrectAnswer();
                    v.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.red));
                    animateWrongQuestion();
                }
            }
        }, 2000);

    }

    /**
     * Goto next question in three second
     */
    private void goToNextQuestion() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                playGame();
            }
        }, 3000);
    }

    /**
     * animate wrong answer and then show dialog retry
     */
    private void animateWrongQuestion() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showDialogRetry();
            }
        }, 3000);
    }

    /**
     * animate corrent answer
     * @param view corrent answer view
     */
    private void animateCorrectAnswer(View view) {
        if (view != null) {
            backgroundAnimator = ObjectAnimator.ofInt(view, "backgroundColor", Color.GREEN, Color.BLUE);
            backgroundAnimator.setDuration(250);
            backgroundAnimator.setEvaluator(new ArgbEvaluator());
            backgroundAnimator.setRepeatCount(20);
            backgroundAnimator.setRepeatMode(ValueAnimator.REVERSE);
            backgroundAnimator.start();
        }
    }

    /**
     * get Correct answer view
     * @return correct view
     */
    private View getCorrectAnswer() {
        View correctView = null;
        switch (mCurrentQuestion.getAnswer()) {
            case 0:
                correctView = mTvAnswerA;
                break;
            case 1:
                correctView = mTvAnswerB;

                break;
            case 2:
                correctView = mTvAnswerC;

                break;
            case 3:
                correctView = mTvAnswerD;
                break;
            default:
                break;
        }
        return correctView;
    }

    //update question in View
    private void bindQuestionToView() {
        //Clear animator
        if(backgroundAnimator != null){
            backgroundAnimator.cancel();
        }

        mTvAnswerA.setBackgroundColor(Color.TRANSPARENT);
        mTvAnswerB.setBackgroundColor(Color.TRANSPARENT);
        mTvAnswerC.setBackgroundColor(Color.TRANSPARENT);
        mTvAnswerD.setBackgroundColor(Color.TRANSPARENT);

        int questionNumber = QUESTION_NUMBER - mQuestionList.size();
        mTvQuestionNumber.setText(String.valueOf(questionNumber));
        mTvQuestion.setText(mCurrentQuestion.getQuestion());
        mTvAnswerA.setText(mCurrentQuestion.getCaseA());
        mTvAnswerB.setText(mCurrentQuestion.getCaseB());
        mTvAnswerC.setText(mCurrentQuestion.getCaseC());
        mTvAnswerD.setText(mCurrentQuestion.getCaseD());
    }

    /**
     * @param view view root.
     */
    private void initView(View view) {
        mTvQuestionNumber = (TextView) view.findViewById(R.id.game_tv_question_numer);
        mTvQuestion = (TextView) view.findViewById(R.id.game_tv_question);
        mTvAnswerA = (TextView) view.findViewById(R.id.game_tv_answer_a);
        mTvAnswerB = (TextView) view.findViewById(R.id.game_tv_answer_b);
        mTvAnswerC = (TextView) view.findViewById(R.id.game_tv_answer_c);
        mTvAnswerD = (TextView) view.findViewById(R.id.game_tv_answer_d);
        mTvAnswerA.setOnClickListener(this);
        mTvAnswerB.setOnClickListener(this);
        mTvAnswerC.setOnClickListener(this);
        mTvAnswerD.setOnClickListener(this);
    }

    public static GameFragment getInstance() {
        return new GameFragment();
    }

    @Override
    public void onClick(View v) {
        v.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.blue));
        switch (v.getId()) {
            case R.id.game_tv_answer_a:
                answer(0);
                break;
            case R.id.game_tv_answer_b:
                answer(1);
                break;
            case R.id.game_tv_answer_c:
                answer(2);
                break;
            case R.id.game_tv_answer_d:
                answer(3);
                break;
            default:
                break;
        }
    }
}
