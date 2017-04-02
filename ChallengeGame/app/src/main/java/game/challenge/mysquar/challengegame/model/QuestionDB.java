package game.challenge.mysquar.challengegame.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Table;
import com.activeandroid.annotation.Column;

@Table(name = "questions", id = "_id")
public class QuestionDB extends Model {

    @Column(name="question")
    public String question;

    @Column(name="caseA")
    public String caseA;

    @Column(name="caseB")
    public String caseB;

    @Column(name="caseC")
    public String caseC;

    @Column(name="caseD")
    public String caseD;

    @Column(name="answer")
    public int answer;

    public String getQuestion() {
        return question;
    }

    public String getCaseA() {
        return caseA;
    }

    public String getCaseB() {
        return caseB;
    }

    public String getCaseC() {
        return caseC;
    }

    public String getCaseD() {
        return caseD;
    }

    public int getAnswer() {
        return answer;
    }
}
