package utils;

import model.Account;
import model.Wallet;
import service.DataService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class FileUtil {
    public final static String HEADER_FILE = "useragents.txt";
    public final static String PRIVATE_FILE = "private.txt";
    public final static String ACCOUNT_FILE = "accounts.txt";
    public static String DATA_FILE = "data.bat";

    public static String getHeader() throws IOException {
        FileReader fileReader = new FileReader(HEADER_FILE);
        String line = "";
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            while ((line = bufferedReader.readLine()) != null) {

            }
        }
        return line;
    }

    public static void readData() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(DATA_FILE);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            DataService.data = (ConcurrentHashMap<String, Account>) objectInputStream.readObject();
        }
    }

    public static void readAccount() throws IOException {
        checkExistToCreateOne(new File(DATA_FILE), true);
        FileReader fileReader = new FileReader(ACCOUNT_FILE);
        List<String> privaties = readFile(PRIVATE_FILE);
        int i = 0;
        int count = 0;
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line = "";
            DataService.data = new ConcurrentHashMap<>();
            while ((line = bufferedReader.readLine()) != null) {
                String privateKey = privaties.get(i++);
                Wallet wallet = new Wallet();
                wallet.setWalletAddress(Web3Util.getAddressByPrivateKey(privateKey.subSequence(2, privateKey.length()).toString()));

                Account account = new Account();
                account.setPrivateKey(privateKey.subSequence(2, privateKey.length()).toString());
                account.setWallet(wallet);
                account.setEmail(line);
                DataService.data.put(line, account);
                count++;
            }
        }
        System.out.println("Import accout success");
        System.out.println("total: " + count);
        writeData();
    }

    public static List<String> readPrivateList() throws IOException {
        FileReader fileReader = new FileReader(PRIVATE_FILE);
        List<String> privaties = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                String privateKey = line.trim();
                if (line.startsWith("0x")) {
                    privateKey = line.subSequence(2, privateKey.length()).toString();
                }
                privaties.add(privateKey);
            }
        }
        return privaties;
    }

    public static void writeData() throws IOException {
        checkExistToCreateOne(new File(DATA_FILE), true);
        FileOutputStream outputStream = new FileOutputStream(DATA_FILE);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(DataService.data);
            objectOutputStream.flush();
        }
        System.out.println("Save data to file success");
    }

    public static void checkExistToCreateOne(File file, boolean isCreate) throws IOException {
        try {
            if (!file.isFile()) {

                throw new IOException("You dont have data file. Created one!");
            }
        } finally {
            if (isCreate) {
                file.createNewFile();
            }
        }
    }

    public static List<String> readFile(String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        int count = 0;
        List<String> data = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                data.add(line);
            }
        }
        return data;
    }
}
