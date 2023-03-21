package model;

import java.io.Serializable;

public class Wallet implements Serializable {
    private String id;
    private String walletAddress;
    private String walletSignature;
    private Integer towerTokensTotal = 0;

    public Wallet() {
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public String getWalletSignature() {
        return walletSignature;
    }

    public void setWalletSignature(String walletSignature) {
        this.walletSignature = walletSignature;
    }

    public Integer getTowerTokensTotal() {
        return towerTokensTotal;
    }

    public void setTowerTokensTotal(Integer towerTokensTotal) {
        this.towerTokensTotal = towerTokensTotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Wallet(String id, String walletAddress, String walletSignature) {
        this.id = id;
        this.walletAddress = walletAddress;
        this.walletSignature = walletSignature;
    }
}
