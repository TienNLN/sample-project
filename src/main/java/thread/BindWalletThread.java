package thread;

import model.Account;
import model.Wallet;
import parameter.RequestBody;
import service.DataService;
import utils.CommonUtils;
import utils.RequestUtil;

import java.io.IOException;
import java.util.HashMap;

public class BindWalletThread extends BaseThread {
    private String email;

    public BindWalletThread(RequestBody requestBody, String email) {
        super(requestBody);
        this.email = email;
    }

    public void run() {
        try {
            System.out.println(this.getName() + " bind Wallet");
            Thread.sleep(3500);
            Account account = DataService.data.get(email);
            if (account.getWallet().getId() == null) {
                String response = RequestUtil.postRequest(getRequestBody());
                HashMap<String, Object> data = CommonUtils.convertResponseToHashMap(response);
                System.out.println(data);
                if (!data.isEmpty()) {
                    String id = (String) data.get("Id");
                    synchronized (DataService.data) {
                        account = DataService.data.get(email);
                        Wallet wallet = account.getWallet();
                        wallet.setId(id);
                        account.setWallet(wallet);
                        DataService.data.put(email, account);
                    }
                }
            }

            System.out.println(this.getName() + " bind wallet finish");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
