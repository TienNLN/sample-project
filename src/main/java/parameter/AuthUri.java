package parameter;

import java.io.Serializable;

public class AuthUri implements Serializable {
    private String identifier;
    private String continueUri;

    public AuthUri(String identifier, String continueUri) {
        this.identifier = identifier;
        this.continueUri = continueUri;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getContinueUri() {
        return continueUri;
    }

    public void setContinueUri(String continueUri) {
        this.continueUri = continueUri;
    }
}
