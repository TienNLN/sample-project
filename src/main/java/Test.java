import service.DataService;
import utils.FileUtil;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileUtil.readData();
        System.out.println(DataService.data.get("tower4@labworld.org"));
    }
}
