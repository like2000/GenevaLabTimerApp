package ch.li.k.genevalabtimerapp.cards;

import android.arch.persistence.room.Entity;

import java.time.LocalDateTime;

@Entity(tableName = "exercise_table")
public class CardsModel {

    private int set1, set2, set3, set4;
    private LocalDateTime timestamp;
    private String exercise;

    public CardsModel(LocalDateTime timestamp, String exercise,
                      int set1, int set2, int set3, int set4) {
        this.set1 = set1;
        this.set2 = set2;
        this.set3 = set3;
        this.set4 = set4;
        this.timestamp = timestamp;
        this.exercise = exercise;
    }

    public int getSet1() {
        return set1;
    }

    public void setSet1(int set1) {
        this.set1 = set1;
    }

    public int getSet2() {
        return set2;
    }

    public void setSet2(int set2) {
        this.set2 = set2;
    }

    public int getSet3() {
        return set3;
    }

    public void setSet3(int set3) {
        this.set3 = set3;
    }

    public int getSet4() {
        return set4;
    }

    public void setSet4(int set4) {
        this.set4 = set4;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }
}
