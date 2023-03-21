package thread;

import common.Constant;
import model.Account;
import parameter.ChdLogin;
import parameter.RequestBody;
import parameter.UserVerify;
import service.DataService;
import utils.CommonUtils;
import utils.RequestUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import static service.RequestBodyCreate.*;

public class LoginThread extends BaseThread {

    public LoginThread(RequestBody requestBody) {
        super(requestBody);
    }

    public void run() {

        String email = ((UserVerify) this.getRequestBody().getData()).getEmail();
        String localId = "";
        try {
            System.out.println(this.getName() + " login into google");
            Account account;
            String response = RequestUtil.postRequest(getRequestBody());
            HashMap<String, Object> data = CommonUtils.convertResponseToHashMap(response);
            localId = (String) data.get("localId");
            synchronized (DataService.data) {
                account = DataService.data.get(((UserVerify) this.getRequestBody().getData()).getEmail());
                account.setLocalId(localId);
                DataService.data.put(email, account);
            }
            RequestBody chdFetchBody = createChdFetchBody(localId);
            FetchCHIDThread fetchCHIDThread = new FetchCHIDThread(chdFetchBody, email);
            System.out.println(this.getName() + " login into google success");
            fetchCHIDThread.start();
            fetchCHIDThread.join();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
}
