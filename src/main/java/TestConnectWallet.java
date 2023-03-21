import model.Account;
import service.DataService;
import utils.FileUtil;

import java.io.IOException;
import java.util.List;

public class TestConnectWallet {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileUtil.DATA_FILE = "vuong1.bat";
        FileUtil.readData();

        List<String> priv = FileUtil.readFile(FileUtil.PRIVATE_FILE);

       /* for (int i=0;i<100;i++){
            String privateKey = priv.get(i);
            privateKey = privateKey.subSequence(2, privateKey.length()).toString();
            String wallet1 = Web3Util.getAddressByPrivateKey(privateKey);
            for (String email: DataService.data.keySet()) {
                Account account = DataService.data.get(email);
                String wallet2 = account.getWallet().getWalletAddress();
                if (wallet2.equals(wallet1))
                    account.setPrivateKey(privateKey);
                    DataService.data.put(email,account);

            }
        }*/
       /* Account account1 = DataService.data.get("vuongvt1@mentonit.net");
        account1.setPrivateKey("12231a5f66ea8e5b4a5e5fdcfc2fd98eaac13d8cbc1eb9c91d59268182e1d3ac");
        System.out.println(account1.getWallet().getWalletAddress());
        System.out.println(Web3Util.getAddressByPrivateKey("12231a5f66ea8e5b4a5e5fdcfc2fd98eaac13d8cbc1eb9c91d59268182e1d3ac"));
        DataService.data.put("vuongvt1@mentonit.net",account1);
        FileUtil.writeData();*/
        for (String email : DataService.data.keySet()) {

            Account account = DataService.data.get(email);
            String wallet2 = account.getWallet().getWalletAddress();
            String privateKey = account.getPrivateKey();
            System.out.println(email);
            System.out.println(account.getWallet() != null);
            System.out.println(account.getLocalId());
            System.out.println(account.getCdhPlayerId());
            System.out.println(account.getWallet().getWalletAddress());
            System.out.println(account.getWallet().getId());
            System.out.println("================");

        }


        System.out.println(DataService.data.size());


    }
}
