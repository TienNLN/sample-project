package model;

import java.io.Serializable;

public class CalenderReward implements Serializable {
    private String id;
    private Integer noofstarchest;
    private Integer towerTokenEarned;
    private String date;
    private boolean userSignedTransaction;

    public CalenderReward() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNoofstarchest() {
        return noofstarchest;
    }

    public void setNoofstarchest(Integer noofstarchest) {
        this.noofstarchest = noofstarchest;
    }

    public Integer getTowerTokenEarned() {
        return towerTokenEarned;
    }

    public void setTowerTokenEarned(Integer towerTokenEarned) {
        this.towerTokenEarned = towerTokenEarned;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isUserSignedTransaction() {
        return userSignedTransaction;
    }

    public void setUserSignedTransaction(boolean userSignedTransaction) {
        this.userSignedTransaction = userSignedTransaction;
    }
}
