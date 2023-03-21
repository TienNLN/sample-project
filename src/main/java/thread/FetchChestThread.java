package thread;

import model.Account;
import model.CalenderReward;
import parameter.RequestBody;
import service.DataService;
import utils.CommonUtils;
import utils.RequestUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static utils.Web3Util.signContractWithPrivateKey;

public class FetchChestThread extends BaseThread {
    private String email;

    public FetchChestThread(RequestBody requestBody, String email) {
        super(requestBody);
        this.email = email;
    }

    public void run() {

        try {
            Account account = DataService.data.get(email);
            HashMap<String, CalenderReward> calenderRewards = account.getCalenderInfor() != null ? account.getCalenderInfor() : new HashMap<>();
            List<CalenderReward> calenderRewardsCheck = new ArrayList<>();
            if (calenderRewards.size() > 0) {
                calenderRewardsCheck = calenderRewards.entrySet().stream()
                        .filter(x -> CommonUtils.isRewardToday(x.getValue().getDate()) == true && x.getValue().isUserSignedTransaction() == true)
                        .map(Map.Entry::getValue)
                        .collect(Collectors.toList());
                if (calenderRewardsCheck.size() > 0) {
                    System.out.println(email + " Already claim");
                } else {
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

                            synchronized (DataService.data) {
                                HashMap<String, Object> data = CommonUtils.convertResponseToHashMap(response);
                                List<Object> calenderInfos = CommonUtils.convertObjectToList(data.get("calender_info"));
                                calenderRewards = new HashMap<>();
                                for (Object obj : calenderInfos) {
                                    CalenderReward calenderReward = CommonUtils.calenderObjectToCalenderReward(obj);
                                    calenderRewards.put(calenderReward.getId(), calenderReward);
                                }
                                account.setTotalTowerTokensEarned((Integer) data.get("totalTowerTokensEarned"));
                                account.setCalenderInfor(calenderRewards);
                                DataService.data.put(email, account);
                            }
                            calenderRewardsCheck = calenderRewards.entrySet().stream()
                                    .filter(x -> CommonUtils.isRewardToday(x.getValue().getDate()) == true)
                                    .map(Map.Entry::getValue)
                                    .collect(Collectors.toList());

                            if (calenderRewardsCheck.size() > 0) {
                                calenderRewardsCheck = calenderRewards.entrySet().stream()
                                        .filter(x -> x.getValue().isUserSignedTransaction() == false)
                                        .map(Map.Entry::getValue)
                                        .collect(Collectors.toList());
                                if (calenderRewardsCheck.size() > 0) {
                                    CalenderReward calenderReward = calenderRewardsCheck.get(0);
                                    System.out.println(account.getEmail() + " Not claim yet");
                                    System.out.println(account.getEmail() + " is siging");
                                    System.out.println(account.getWallet().getWalletAddress() + " sending transaction");

                                    signContractWithPrivateKey(account.getPrivateKey(), calenderReward.getId());
                                } else {
                                    System.out.println(account.getEmail() + " Already claim");
                                }


                            } else {
                                System.out.println(account.getEmail() + " Today not finish 16 start chess");
                            }

                        }
                    } while (response.length() == 0);

                }
            }

            this.join();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
