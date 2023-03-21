package thread;

import model.Account;
import parameter.RequestBody;
import service.DataService;
import service.RequestBodyCreate;
import utils.CommonUtils;
import utils.RequestUtil;

import java.io.IOException;
import java.util.HashMap;

public class FetchCHIDThread extends BaseThread {
    private String email;

    public FetchCHIDThread(RequestBody requestBody, String email) {
        super(requestBody);
        this.email = email;
    }

    public void run() {

        String chId = "";
        try {
            System.out.println(this.getName() + " login fetch chid");
            Thread.sleep(3500);
            String response = RequestUtil.postRequest(getRequestBody());
            System.out.println(response);
            HashMap<String, Object> data = CommonUtils.convertResponseToHashMap(response);
            chId = (String) data.get("CdhPlayerId");
            synchronized (this) {
                Account account = DataService.data.get(email);
                account.setCdhPlayerId(chId);
                DataService.data.put(email, account);
            }
            if (chId != "") {
                Account account = DataService.data.get(email);
                RequestBody requestBody = RequestBodyCreate.createBindWallet(account);
                BindWalletThread bindWalletThread = new BindWalletThread(requestBody, email);
                bindWalletThread.start();
            }
            System.out.println(this.getName() + " login fetch chid finish");

        } catch (IOException | InterruptedException e) {

            System.out.println(e.getMessage());
        }

    }
}
