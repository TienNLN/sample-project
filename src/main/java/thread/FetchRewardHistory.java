package thread;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.Account;
import parameter.RequestBody;
import service.DataService;
import utils.CommonUtils;
import utils.RequestUtil;

import java.io.IOException;
import java.util.HashMap;

public class FetchRewardHistory extends BaseThread {
    private String email;

    public FetchRewardHistory(RequestBody requestBody, String email) {
        super(requestBody);
        this.email = email;
    }

    public void run() {

        try {
            int count = 0;
            String response = "";
            do {
                response = RequestUtil.postRequest(getRequestBody());

                if (response.length() == 0) {
                    Thread.sleep(4000);
                    count++;
                    if (count == 3) {
                        System.out.println(email + " fetch fail");
                        break;
                    }
                } else {
                    HashMap<String, Object> data = CommonUtils.convertResponseToHashMap(response);
                    synchronized (DataService.data) {
                        Account account = DataService.data.get(email);

                        Integer earn = (Integer) data.get("totalTowerTokenEarned");

                        Integer curentBalance = account.getTotalTowerTokensEarned();
                        System.out.println(earn);
                        System.out.println(curentBalance);
                        System.out.println(earn + curentBalance);
                        account.setTotalTowerTokensEarned(earn);
                        DataService.data.put(email, account);
                    }
                    Thread.sleep(4000);
                }
            }
            while (response.length() == 0);

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
