import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

public class Signature {
    public static void main(String[] args) {
        String privateKey1 = "df4a8561b33a2018cfed063e0d85b81dbe7fe259a09172cd80b16aca29f09be3";
        Credentials credentials = Credentials.create(ECKeyPair.create(Numeric.hexStringToByteArray(privateKey1)));
        System.out.println("Address: " + credentials.getAddress());

        String data = Numeric.toHexString("Sign CrazyDefenseHeroes for Faucet".getBytes());
        System.out.println("Hash :" + data);


        Sign.SignatureData signature = Sign.signPrefixedMessage(Numeric.hexStringToByteArray(data), ECKeyPair.create(Numeric.hexStringToByteArray(privateKey1)));

        System.out.println(Numeric.toHexString(signature.getR()) + Numeric.toHexString(signature.getS()).replaceFirst("0x", "") + Numeric.toHexString(signature.getV()).replaceFirst("0x", ""));
    }
}
