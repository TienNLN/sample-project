package thread;

import model.Account;
import model.Wallet;
import parameter.RequestBody;
import service.DataService;
import utils.CommonUtils;
import utils.RequestUtil;

import java.io.IOException;
import java.util.HashMap;

public class FetchWalletThread extends BaseThread {
    private String email;

    public FetchWalletThread(RequestBody requestBody, String email) {
        super(requestBody);
        this.email = email;
    }

    public void run() {
        try {
            System.out.println(this.getName() + " fetch wallet google");
            String response = RequestUtil.postRequest(getRequestBody());
            HashMap<String, Object> data = CommonUtils.convertResponseToHashMap(response);
            String id = (String) data.get("Id");
            String walletAddress = (String) data.get("WalletId");
            String walletSignature = (String) data.get("WalletSignature");
            Wallet wallet = new Wallet(id, walletAddress, walletSignature);
            synchronized (DataService.data) {
                Account account = DataService.data.get(email);
                account.setWallet(wallet);
                DataService.data.put(email, account);
            }
            System.out.println(this.getName() + " fetch wallet finish");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
