package parameter;

import java.io.Serializable;

public class ChdLogin implements Serializable {
    private String Firebaseuuid;
    private String SiwaId = "";
    private String CdhPlayerId = "";

    public ChdLogin(String firebaseuuid) {
        Firebaseuuid = firebaseuuid;
    }

    public String getFirebaseuuid() {
        return Firebaseuuid;
    }

    public void setFirebaseuuid(String firebaseuuid) {
        Firebaseuuid = firebaseuuid;
    }

    public String getSiwaId() {
        return SiwaId;
    }

    public void setSiwaId(String siwaId) {
        SiwaId = siwaId;
    }

    public String getCdhPlayerId() {
        return CdhPlayerId;
    }

    public void setCdhPlayerId(String cdhPlayerId) {
        CdhPlayerId = cdhPlayerId;
    }
}
