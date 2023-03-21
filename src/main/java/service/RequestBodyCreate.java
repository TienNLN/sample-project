package service;

import common.Constant;
import model.Account;
import model.Wallet;
import parameter.*;
import utils.Web3Util;

public class RequestBodyCreate {
    public static RequestBody createChestRequestBody(String email) {
        String chid = DataService.data.get(email).getCdhPlayerId();
        RequestBody requestBody = new RequestBody();
        ChdIdParameter chdIdParameter = new ChdIdParameter(chid);
        chdIdParameter.setCachedData(false);
        requestBody.setData(chdIdParameter);
        requestBody.setUrl(Constant.URL_GET_CHEST_INFO);
        return requestBody;
    }

    public static RequestBody createRewardHistoryBody(String email) {
        String chid = DataService.data.get(email).getCdhPlayerId();
        RequestBody requestBody = new RequestBody();
        requestBody.setData(new ChdIdParameter(chid));
        requestBody.setUrl(Constant.URL_GET_REWARD_HISTORY);
        return requestBody;
    }

    public static RequestBody createIdGenerateBody(String email) {
        String chid = DataService.data.get(email).getCdhPlayerId();
        RequestBody requestBody = new RequestBody();
        requestBody.setData(new ChdIdParameter(chid));
        requestBody.setUrl(Constant.URL_GET_ID_GENERATE);
        return requestBody;
    }

    public static RequestBody createLoginBody(Account account) {
        RequestBody requestBody = new RequestBody();
        UserVerify userVerify = new UserVerify(account.getEmail(), account.getPassword());
        requestBody.setUrl(Constant.URL_GOOGLE_VERIFY_PASS);
        requestBody.setData(userVerify);
        return requestBody;
    }

    public static RequestBody createWalletBody(String localId) {
        RequestBody requestBody = new RequestBody();
        requestBody.setMethod(Constant.METHOD_GET);
        requestBody.setFirebaseuuid(localId);
        requestBody.setUrl(Constant.URL_CRAZY_GET_WALLET);
        return requestBody;
    }

    public static RequestBody createChdFetchBody(String localId) {
        RequestBody requestBody = new RequestBody();
        ChdLogin chdLogin = new ChdLogin(localId);
        requestBody.setData(chdLogin);
        requestBody.setUrl(Constant.URL_CRAZY_LOGIN);
        return requestBody;
    }

    public static RequestBody createBindWallet(Account account) {
        try {
            String localId = account.getLocalId();
            RequestBody requestBody = new RequestBody();
            Wallet wallet = account.getWallet();
            wallet.setWalletSignature(Web3Util.generateSignature(localId, account.getPrivateKey()));
            BindWallet bindWallet = new BindWallet(account.getLocalId(), wallet.getWalletSignature(), wallet.getWalletAddress(), account.getCdhPlayerId());
            requestBody.setData(bindWallet);
            requestBody.setMethod("POST");
            requestBody.setUrl(Constant.URL_POST_BIND_WALLET);
            return requestBody;
        } catch (NullPointerException e) {
            System.out.println(account.getEmail() + " " + e.getMessage());
            return null;
        }

    }
}
