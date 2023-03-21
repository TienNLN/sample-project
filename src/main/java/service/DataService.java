package service;

import common.Constant;
import model.Account;
import model.CalenderReward;
import model.Wallet;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import parameter.*;
import thread.*;
import utils.*;

import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import service.RequestBodyCreate;

import static service.RequestBodyCreate.*;
import static utils.Web3Util.getGasEstimate;
import static utils.Web3Util.signContractWithPrivateKey;

public class DataService implements Serializable {
    public static ConcurrentHashMap<String, Account> data = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, Account> dataFail;
    public static Queue<String> failFetch = new LinkedList<>();
    public static String userAgent = "";

    public static void fetchToday() {
        ScheduledExecutorService fixedPool =
                Executors.newScheduledThreadPool(6);
        CompletionService<String> service = new ExecutorCompletionService<String>(fixedPool);
        for (String email : data.keySet()) {

            fixedPool.schedule(new FetchChestThread(createChestRequestBody(email), email), 6, TimeUnit.SECONDS);
        }
        fixedPool.shutdown();
        while (!fixedPool.isTerminated()) {

        }
        System.out.println("finish fetch Today");
        try {
            FileUtil.writeData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        checkStatusToday();
        totalRewardEarn();


    }

    public static void fetchRewardHistory() {

        ScheduledExecutorService fixedPool =
                Executors.newScheduledThreadPool(6);
        CompletionService<String> service = new ExecutorCompletionService<String>(fixedPool);
        for (String email : data.keySet()) {
            fixedPool.schedule(new FetchRewardHistory(createRewardHistoryBody(email), email), 3, TimeUnit.SECONDS);
        }
        fixedPool.shutdown();
        while (!fixedPool.isTerminated()) {

        }
        System.out.println("finish fetch again");
        try {
            FileUtil.writeData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        totalRewardEarn();

    }

    public static void checkStatusToday() {
        int countFail = 0;
        int countClaim = 0;
        int countAlready = 0;
        for (String email : DataService.data.keySet()) {

            Account account = DataService.data.get(email);
            HashMap<String, CalenderReward> rewards = account.getCalenderInfor();

            try {
                List<CalenderReward> calenderRewards = rewards.entrySet().stream()
                        .filter(x -> CommonUtils.isRewardToday(x.getValue().getDate()) == true)
                        .map(Map.Entry::getValue)
                        .collect(Collectors.toList());

                if (calenderRewards.size() > 0) {
                    calenderRewards = rewards.entrySet().stream()
                            .filter(x -> x.getValue().isUserSignedTransaction() == false)
                            .map(Map.Entry::getValue)
                            .collect(Collectors.toList());
                    if (calenderRewards.size() > 0) {
                        CalenderReward calenderReward = calenderRewards.get(0);
                        countClaim++;
                        System.out.println(account.getEmail() + " Not claim yet");
                    } else {
                        System.out.println(account.getEmail() + " Already claim");
                        countAlready++;
                    }


                } else {
                    countFail++;
                    System.out.println(account.getEmail() + " Today not finish 16 start chess");
                }


            } catch (NullPointerException nullPointerException) {
                System.out.println(nullPointerException.getMessage());
            }

        }
        System.out.println("Total fail: " + countFail);
        System.out.println("Total claim: " + countClaim);
        System.out.println("Total already: " + countAlready);
        System.out.println("Total account: " + DataService.data.size());
    }

    public static void totalRewardEarn() {
        Integer total = 0;
        for (String email : DataService.data.keySet()) {
            Account account = DataService.data.get(email);
            total += account.getTotalTowerTokensEarned();
        }
        System.out.println("Total Earned: " + total);
    }

    public static void fetchFirsTime() {
        try {
            ScheduledExecutorService schedule =
                    Executors.newScheduledThreadPool(4);
            CompletionService<String> service = new ExecutorCompletionService<String>(schedule);
            FileUtil.readData();
            for (String email : DataService.data.keySet()) {
                Account account = DataService.data.get(email);
                if (account.getCdhPlayerId() == null) {
                    RequestBody requestBody = createLoginBody(DataService.data.get(email));
                    schedule.schedule(new LoginThread(requestBody), 10, TimeUnit.SECONDS);
                } else if (account.getWallet().getId() == null) {
                    RequestBody requestBody = createBindWallet(account);
                    schedule.schedule(new BindWalletThread(requestBody, email), 5, TimeUnit.SECONDS);
                }


            }
            schedule.shutdown();
            while (!schedule.isTerminated()) {

            }
            System.out.println("finish fetch first time");
            FileUtil.writeData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void setPassword() {
        try {
            Scanner sc = new Scanner(System.in);
            String confirm = "N";
            String password = "";
            do {
                System.out.print("Input your password: ");
                password = sc.nextLine().trim();
                System.out.println("Your password is: " + password);
                System.out.print("Confirm your password ? Y/N: ");
                confirm = sc.nextLine().trim().toUpperCase();
            }
            while (!"Y".equals(confirm));
            FileUtil.readData();
            for (String email : DataService.data.keySet()) {
                Account account = DataService.data.get(email);
                account.setPassword(password);

            }
            FileUtil.writeData();
            System.out.println("Update your password success.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
