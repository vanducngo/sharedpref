package game.challenge.mysquar.challengegame.model;

import com.activeandroid.query.Select;

import java.util.List;

public class DatabaseHelper {
    public static List<QuestionDB>  getRandomQuestion(int questionNumber){
        return new Select().from(QuestionDB.class).orderBy("RANDOM()").limit(questionNumber).execute();
    }
}
