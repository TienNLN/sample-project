package common;

import model.Account;

import java.util.concurrent.ConcurrentHashMap;

public class Constant {
    public final static String URL_CRAZY_LOGIN = "https://tower-token-firestore.df.r.appspot.com/login/id";
    public final static String URL_GOOGLE_VERIFY_PASS = "https://www.googleapis.com/identitytoolkit/v3/relyingparty/verifyPassword?key=AIzaSyDDnsV0OdAnfWGwEZDGrFiORA6qWe_3r2A";
    public final static String URL_GOOGLE_ACCOUNT_INFO = "https://www.googleapis.com/identitytoolkit/v3/relyingparty/getAccountInfo?key=AIzaSyDDnsV0OdAnfWGwEZDGrFiORA6qWe_3r2A";
    public final static String URL_CRAZY_GET_WALLET = "https://tower-token-firestore.df.r.appspot.com/token/get_wallet";
    public final static String URL_GET_ID_GENERATE = "https://cdh-web-api-dot-tower-token-firestore.df.r.appspot.com/star_chest/id_generator";
    public final static String URL_GET_CHEST_INFO = "https://cdh-web-api-dot-tower-token-firestore.df.r.appspot.com/star_chest/get_star_chest_info";
    public final static String URL_GET_REWARD_HISTORY = "https://cdh-web-api-dot-tower-token-firestore.df.r.appspot.com/star_chest/get_reward_history";
    public final static String URL_POST_BIND_WALLET = "https://tower-token-firestore.df.r.appspot.com/token/bind_wallet";
    public final static String METHOD_POST = "POST";
    public final static String METHOD_GET = "GET";
    public final static String METHOD_OPTIONS = "OPTIONS";
    public static ConcurrentHashMap<String, Account> data;
}
