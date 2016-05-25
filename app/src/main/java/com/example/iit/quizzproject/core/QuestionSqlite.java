package com.example.iit.quizzproject.core;

import java.io.Serializable;

/**
 * Created by SAMSUNG on 24/05/2016.
 */
public class QuestionSqlite  {

    private String textQuestionFr;
    private String textQuestionAn;
    private String proposition1Fr;
    private String proposition1An;
    private String proposition2Fr;
    private String proposition2An;
    private String proposition3Fr;
    private String proposition3An;
    private String answerFr;
    private String answerAn;

    public QuestionSqlite() {
    }

    public String getTextQuestionFr() {
        return textQuestionFr;
    }

    public void setTextQuestionFr(String textQuestionFr) {
        this.textQuestionFr = textQuestionFr;
    }

    public String getTextQuestionAn() {
        return textQuestionAn;
    }

    public void setTextQuestionAn(String textQuestionAn) {
        this.textQuestionAn = textQuestionAn;
    }

    public String getProposition1Fr() {
        return proposition1Fr;
    }

    public void setProposition1Fr(String proposition1Fr) {
        this.proposition1Fr = proposition1Fr;
    }

    public String getProposition1An() {
        return proposition1An;
    }

    public void setProposition1An(String proposition1An) {
        this.proposition1An = proposition1An;
    }

    public String getProposition2Fr() {
        return proposition2Fr;
    }

    public void setProposition2Fr(String proposition2Fr) {
        this.proposition2Fr = proposition2Fr;
    }

    public String getProposition2An() {
        return proposition2An;
    }

    public void setProposition2An(String proposition2An) {
        this.proposition2An = proposition2An;
    }

    public String getProposition3Fr() {
        return proposition3Fr;
    }

    public void setProposition3Fr(String proposition3Fr) {
        this.proposition3Fr = proposition3Fr;
    }

    public String getProposition3An() {
        return proposition3An;
    }

    public void setProposition3An(String proposition3An) {
        this.proposition3An = proposition3An;
    }

    public String getAnswerFr() {
        return answerFr;
    }

    public void setAnswerFr(String answerFr) {
        this.answerFr = answerFr;
    }

    public String getAnswerAn() {
        return answerAn;
    }

    public void setAnswerAn(String answerAn) {
        this.answerAn = answerAn;
    }
}

