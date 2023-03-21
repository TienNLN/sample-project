package parameter;

public class ChdIdParameter {
    private String playerId;
    private boolean cachedData = true;

    public ChdIdParameter(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public boolean isCachedData() {
        return cachedData;
    }

    public void setCachedData(boolean cachedData) {
        this.cachedData = cachedData;
    }
}
