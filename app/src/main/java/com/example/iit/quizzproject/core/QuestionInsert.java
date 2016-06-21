package com.example.iit.quizzproject.core;

/**
 * Created by dmk on 20/06/16.
 */
public class QuestionInsert {

    private int id;
    private int idQ;
    private int idP1;
    private int idP2;
    private int idP3;
    private int idRep;

    public QuestionInsert(int id, int idQ, int idP1, int idP2, int idP3, int idRep) {
        this.id = id;
        this.idQ = idQ;
        this.idP1 = idP1;
        this.idP2 = idP2;
        this.idP3 = idP3;
        this.idRep = idRep;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdQ() {
        return idQ;
    }

    public void setIdQ(int idQ) {
        this.idQ = idQ;
    }

    public int getIdP1() {
        return idP1;
    }

    public void setIdP1(int idP1) {
        this.idP1 = idP1;
    }

    public int getIdP2() {
        return idP2;
    }

    public void setIdP2(int idP2) {
        this.idP2 = idP2;
    }

    public int getIdP3() {
        return idP3;
    }

    public void setIdP3(int idP3) {
        this.idP3 = idP3;
    }

    public int getIdRep() {
        return idRep;
    }

    public void setIdRep(int idRep) {
        this.idRep = idRep;
    }

    public QuestionInsert() {

    }
}
