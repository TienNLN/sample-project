package parameter;

public class BindWallet {
    private String FirebaseUuid;
    private String SiwaId;
    private String Signature;
    private String WalletId;
    private String WalletType = "metamask";
    private String CdhPlayerId;

    public BindWallet(String firebaseUuid, String signature, String walletId, String cdhPlayerId) {
        FirebaseUuid = firebaseUuid;
        Signature = signature;
        WalletId = walletId;
        CdhPlayerId = cdhPlayerId;
    }

    public BindWallet() {
    }

    public String getFirebaseUuid() {
        return FirebaseUuid;
    }

    public void setFirebaseUuid(String firebaseUuid) {
        FirebaseUuid = firebaseUuid;
    }

    public String getSiwaId() {
        return SiwaId;
    }

    public void setSiwaId(String siwaId) {
        SiwaId = siwaId;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

    public String getWalletId() {
        return WalletId;
    }

    public void setWalletId(String walletId) {
        WalletId = walletId;
    }

    public String getWalletType() {
        return WalletType;
    }

    public void setWalletType(String walletType) {
        WalletType = walletType;
    }

    public String getCdhPlayerId() {
        return CdhPlayerId;
    }

    public void setCdhPlayerId(String cdhPlayerId) {
        CdhPlayerId = cdhPlayerId;
    }
}
