package com.group22.score;

public class Score {
    private int punishment = -5;
    private int regular = 3;
    private int bonus = 5;
    protected int currentScore;
    private int rewardNum = 0;

    public Score(){
        this.currentScore = 0;
    }

    public int getScore(){
        return currentScore;
    }

    public int punishment(){
        currentScore += punishment;
        return currentScore;
    }

    public int regular(){
        currentScore += regular;
        rewardNum++;
        return currentScore;
    }

    public int bonus(){
        currentScore += bonus;
        return currentScore;
    }

    public int setScore(int newScore){
        currentScore = newScore;
        return currentScore;
    }

    public boolean belowZero(){
        return currentScore < 0;
    }

    public boolean getAllReward(){
        return rewardNum >= 30;
    }

    public int getRewardNumber() {
        return rewardNum;
    }

    public void setRewardNumber(int x) {
        rewardNum = x;
    }
}
