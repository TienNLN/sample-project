package utils;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

public class CredentialsUtils implements Serializable {
    public static Credentials getCredentialFromPrivateKey(String privateKey) {
        try {

            BigInteger key = new BigInteger(privateKey, 16);
            ECKeyPair ecKeyPair = ECKeyPair.create(key.toByteArray());
            Credentials credentials = Credentials.create(ecKeyPair);
            return credentials;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Invalid privateKey: " + privateKey);
        }
        return null;
    }

    public static String getWalletAddressFromPrivateKey(String privateKey) {
        Credentials credentials = getCredentialFromPrivateKey(privateKey);
        return credentials.getAddress();
    }

    public static HashMap<String, String> getWalletAddressFromPrivateKey(List<String> privates) {
        HashMap<String, String> result = new HashMap<>();
        for (String privateKey : privates) {
            String address = getWalletAddressFromPrivateKey(privateKey);
            result.put(address, privateKey);
        }
        return result;
    }
}
