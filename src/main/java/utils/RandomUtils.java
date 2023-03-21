package utils;

import java.util.Collections;
import java.util.List;

public class RandomUtils {
    public static String getRandomUserAgent(List<String> useragents) {
        Collections.shuffle(useragents);
        return useragents.get(0);
    }
}
