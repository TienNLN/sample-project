import model.Account;
import service.DataService;
import utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static utils.Web3Util.getGasEstimate;

public class MutilSender {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("File bat: ");
        String file = sc.nextLine();
        FileUtil.DATA_FILE = file;
        FileUtil.checkExistToCreateOne(new File(FileUtil.DATA_FILE), false);
        FileUtil.readData();
        getGasEstimate();

        String address = "0x8d4Eb94791ad2673B91c4b7bB44686840B2Ba026";
        for (String email : DataService.data.keySet()) {
            Account account = DataService.data.get(email);
            System.out.println(email);
            /*sendTower(account.getPrivateKey(),address);*/
        }
         /*String privaty = "a409b70596c0cc3bc7a06c697ca7445b550175e629aa44d43f99bd9718a2f80f";

        sendTower(privaty,address);*/

    }
}
