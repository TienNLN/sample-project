package utils;

import model.ContractClaim;
import model.Tower;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Web3Util {
    /*public final static String RPC_POLYGON = "https://polygon-rpc.com";*/
    public final static String RPC_POLYGON = "https://rpc-mainnet.matic.quiknode.pro";
    /*public final static String RPC_POLYGON = "https://polygon-mainnet.g.alchemy.com/v2/TmzbTUjxJsxN9jVjnCXqYUzvk8culqq1";*/
    public final static Web3j web3 = Web3j.build(new HttpService(RPC_POLYGON));
    public final static String CONTRACT_DAILYLOG = "0xe57dad9c809c5ff0162b17d220917089d4cc7075";
    public final static String CONTRACT_TOWER = "0x2bc07124d8dac638e290f401046ad584546bc47b";
    public static BigInteger GAS_PRICE = BigInteger.valueOf(300000000000L);
    public static final BigInteger GAS_LIMIT = BigInteger.valueOf(73318);
    public static Integer totalSuccess = 0;

    public static void sendTower(String privateKey, String address) {
        try {
            Tower contract = getContractTower(privateKey);
            Credentials credentials = CredentialsUtils.getCredentialFromPrivateKey(privateKey);
            String owner = credentials.getAddress();
            BigInteger balance = contract.balanceOf(credentials.getAddress()).send();
            System.out.println(owner + " Balance: " + convertBalanceToInt(balance) + " Tower");

            if (balance.compareTo(BigInteger.valueOf(0l)) > 0) {
                System.out.println(owner + " Balance: " + convertBalanceToInt(balance) + " Tower");
                CompletableFuture<TransactionReceipt> transactionReceiptCompletableFuture = contract.transfer(address, balance).sendAsync();

                transactionReceiptCompletableFuture.thenAccept(transactionReceipt -> {
                    System.out.println(address + " Received " + convertBalanceToInt(balance) + " Tower");
                    System.out.println("Hash: " + transactionReceipt.getTransactionHash());

                }).exceptionally(transactionReceipt -> {
                    System.out.println(transactionReceipt.getCause().getMessage());

                    return null;
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String generateSignature(String localId, String privateKey) {
        String body = "Link account: " + localId;


        String data = Numeric.toHexString(body.getBytes());
        Sign.SignatureData signature = Sign.signPrefixedMessage(Numeric.hexStringToByteArray(data), ECKeyPair.create(Numeric.hexStringToByteArray(privateKey)));
        String signatureGenerate = Numeric.toHexString(signature.getR())
                + Numeric.toHexString(signature.getS()).replaceFirst("0x", "")
                + Numeric.toHexString(signature.getV()).replaceFirst("0x", "");
        return signatureGenerate;
    }

    public static void signContractWithPrivateKey(String privateKey, String idGenerated) {

        ContractClaim contract = getContractClaim(privateKey);

        CompletableFuture<TransactionReceipt> transactionReceiptCompletableFuture = contract.dailyLog(idGenerated).sendAsync();

        transactionReceiptCompletableFuture.thenAccept(transactionReceipt -> {
            System.out.println(transactionReceipt.getGasUsedRaw() + "Hash: " + transactionReceipt.getTransactionHash() + "- Status: " + ("0x1".equals(transactionReceipt.getStatus()) ? "success" : "fail"));

        }).exceptionally(transactionReceipt -> {

            System.out.println(transactionReceipt.getCause().getMessage());

            return null;
        });


    }

    public static ContractClaim getContractClaim(String privateKey) {
        FastRawTransactionManager txManeger = getFastRawTransactionManagerByPrivateKey(privateKey);
        ContractClaim contract = ContractClaim.load(CONTRACT_DAILYLOG, web3, txManeger, new StaticGasProvider(GAS_PRICE, GAS_LIMIT));

        return contract;
    }

    public static Tower getContractTower(String privateKey) {
        FastRawTransactionManager txManeger = getFastRawTransactionManagerByPrivateKey(privateKey);
        Tower contract = Tower.load(CONTRACT_TOWER, web3, txManeger, new StaticGasProvider(GAS_PRICE, GAS_LIMIT));
        return contract;
    }

    public static FastRawTransactionManager getFastRawTransactionManagerByPrivateKey(String privateKey) {
        Credentials credentials = CredentialsUtils.getCredentialFromPrivateKey(privateKey);

        FastRawTransactionManager txManeger = new FastRawTransactionManager(web3, credentials, 137);

        return txManeger;
    }

    public static void getGasEstimate() {
        try {
            GAS_PRICE = Web3Util.web3.ethGasPrice().sendAsync().get().getGasPrice().multiply(BigInteger.valueOf(3));

        } catch (ExecutionException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public static BigInteger convertBalanceToInt(BigInteger balance) {
        return balance.divide(BigInteger.valueOf(10).pow(18));
    }

    public static String getAddressByPrivateKey(String privateKey) {
        if (privateKey != null) {
            Credentials credentials = Credentials.create(ECKeyPair.create(Numeric.hexStringToByteArray(privateKey)));
            return credentials.getAddress();
        }
        return null;
    }
}
