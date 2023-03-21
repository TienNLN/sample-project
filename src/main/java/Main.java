import service.DataService;
import utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            DataService.userAgent = FileUtil.getHeader();

            Integer choice = 0;
            System.out.println("File bat: ");
            String file = sc.nextLine();
            FileUtil.DATA_FILE = file;
            FileUtil.checkExistToCreateOne(new File(FileUtil.DATA_FILE), false);
            do {
                System.out.println("1. Import account form list.");
                System.out.println("2. Fetch data for first time.");
                System.out.println("3. Fetch data today");
                System.out.println("4. Check status");
                System.out.println("5. Check total tower Earn.");
                System.out.println("6. Fetch reward history.");
                System.out.print("Your choice: ");
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        FileUtil.readAccount();
                        DataService.setPassword();
                        break;
                    case 2:
                        FileUtil.readData();
                        DataService.fetchFirsTime();
                        break;
                    case 3:
                        FileUtil.readData();
                        DataService.fetchToday();
                        break;
                    case 4:
                        FileUtil.readData();
                        DataService.checkStatusToday();
                        break;
                    case 5:
                        FileUtil.readData();
                        DataService.totalRewardEarn();
                        break;
                    case 6:
                        FileUtil.readData();
                        DataService.fetchRewardHistory();
                        break;

                    default:
                        System.out.println("dit me may");
                        FileUtil.writeData();
                        break;
                }
            }
            while (choice <= 6);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
