import model.Account;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import service.DataService;
import utils.FileUtil;
import utils.Web3Util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.concurrent.CompletableFuture;

import static utils.Web3Util.*;


public class Demo {
    public static void main(String[] args) throws Exception {
        FileUtil.DATA_FILE = "tam.bat";
        FileUtil.readData();
        getGasEstimate();
        String privaty = "a811b1a4a96a7e4694b9cbad58e47306a1a498eef918a9bdf4bafd127b690fdc";
        /*Web3Util.signContractWithPrivateKey(privaty,"98342d4e54d2df3bf8f9656585b5fcf6edf79b96844cce05817d7657598a00de");
         */
        FastRawTransactionManager tx = getFastRawTransactionManagerByPrivateKey(privaty);
        Transfer transfer = new Transfer(web3, tx);

        getGasEstimate();
        System.out.println(GAS_PRICE);
        BigDecimal bigInteger = BigDecimal.valueOf(0);

        //List<String> wallets = FileUtil.readAccount("faucet.txt");
        for (String email : DataService.data.keySet()) {
            Account account = DataService.data.get(email);
            String address = account.getWallet().getWalletAddress();
            BigInteger balance = Web3Util.web3.ethGetBalance(address, DefaultBlockParameterName.LATEST).send().getBalance();


            BigDecimal bigDecimal1 = BigDecimal.valueOf(10).pow(18);
            BigDecimal bigDecimal = new BigDecimal(balance).divide(bigDecimal1, MathContext.DECIMAL32);

            bigInteger = bigInteger.add(bigDecimal, MathContext.DECIMAL32);

            if (bigDecimal.compareTo(new BigDecimal(0.054)) == -1) {
                CompletableFuture<TransactionReceipt> transactionReceiptCompletableFuture = transfer.sendFunds(address, BigDecimal.valueOf(0.055), Convert.Unit.ETHER, BigInteger.valueOf(100142227019l), GAS_LIMIT).sendAsync();
                transactionReceiptCompletableFuture.thenAccept(transactionReceipt -> System.out.println(transactionReceipt.getTransactionHash()))
                        .exceptionally(transactionReceipt -> {
                            System.out.println(transactionReceipt.getCause().getMessage());
                            return null;
                        });
                Thread.sleep(300);
            }

            System.out.println(address + "," + bigDecimal);


        }

        System.out.println("Total matic: " + bigInteger);
    }
}
