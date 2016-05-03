package com.example.dmk.quizzproject.core;

/**
 * Created by SAMSUNG on 02/05/2016.
 */
public class MathQuestion {

    private String text_question;
    private String proposition1;
    private String proposition2;
    private String proposition3;
    private String answer;

    public MathQuestion(String text_question, String proposition1, String proposition2, String proposition3, String answer) {
        this.text_question = text_question;
        this.proposition1 = proposition1;
        this.proposition2 = proposition2;
        this.proposition3 = proposition3;
        this.answer = answer;
    }

    public String getText_question() {
        return text_question;
    }

    public String getProposition1() {
        return proposition1;
    }

    public String getProposition2() {
        return proposition2;
    }

    public String getProposition3() {
        return proposition3;
    }

    public String getAnswer() {
        return answer;
    }

    public void setText_question(String text_question) {
        this.text_question = text_question;
    }

    public void setProposition1(String proposition1) {
        this.proposition1 = proposition1;
    }

    public void setProposition2(String proposition2) {
        this.proposition2 = proposition2;
    }

    public void setProposition3(String proposition3) {
        this.proposition3 = proposition3;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
