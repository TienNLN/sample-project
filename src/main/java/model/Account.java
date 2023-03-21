package model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class Account implements Serializable {
    private String email;
    private String password;
    private String cdhPlayerId;
    private String localId;
    private Wallet wallet;
    private Integer totalTowerTokensEarned = 0;
    private Date lastDaySignin;
    private HashMap<String, CalenderReward> calenderInfor;
    private String id_generator;
    private String privateKey;

    public Account() {
    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCdhPlayerId() {
        return cdhPlayerId;
    }

    public void setCdhPlayerId(String cdhPlayerId) {
        this.cdhPlayerId = cdhPlayerId;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public Integer getTotalTowerTokensEarned() {
        return totalTowerTokensEarned;
    }

    public void setTotalTowerTokensEarned(Integer totalTowerTokensEarned) {
        this.totalTowerTokensEarned = totalTowerTokensEarned;
    }

    public Date getLastDaySignin() {
        return lastDaySignin;
    }

    public void setLastDaySignin(Date lastDaySignin) {
        this.lastDaySignin = lastDaySignin;
    }

    public HashMap<String, CalenderReward> getCalenderInfor() {
        return calenderInfor;
    }

    public void setCalenderInfor(HashMap<String, CalenderReward> calenderInfor) {
        this.calenderInfor = calenderInfor;
    }

    public String getId_generator() {
        return id_generator;
    }

    public void setId_generator(String id_generator) {
        this.id_generator = id_generator;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String toString() {
        return "Email: " + email +
                " localId: " + localId +
                " chid: " + cdhPlayerId +
                " total reward: " + totalTowerTokensEarned +
                " id today " + id_generator;
    }
}
