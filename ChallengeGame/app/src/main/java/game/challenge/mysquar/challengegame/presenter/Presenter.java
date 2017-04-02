package game.challenge.mysquar.challengegame.presenter;

import java.util.List;

import game.challenge.mysquar.challengegame.model.DatabaseHelper;
import game.challenge.mysquar.challengegame.model.QuestionDB;

public class Presenter {
    public static Presenter getInstance() {
        return new Presenter();
    }

    public List<QuestionDB> getRandomQuestion(int questionNumber) {
        return DatabaseHelper.getRandomQuestion(questionNumber);
    }

    public boolean isAnswerCorrect(QuestionDB questionDB, int answer) {
        return questionDB.getAnswer() == answer;
    }
}
