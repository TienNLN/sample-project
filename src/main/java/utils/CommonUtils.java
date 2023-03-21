package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.CalenderReward;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CommonUtils {
    public static String convertObjectToStringJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(object);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            return json;
        }
    }

    public static HashMap<String, Object> convertResponseToHashMap(String response) throws JsonProcessingException {
        HashMap<String, Object> map = new ObjectMapper().readValue(response, HashMap.class);
        return map;
    }

    public static List<Object> convertObjectToList(Object object) throws JsonProcessingException {
        List<Object> calenderRewards = new ObjectMapper().readValue(convertObjectToStringJson(object), List.class);
        return calenderRewards;
    }

    public static CalenderReward calenderObjectToCalenderReward(Object object) throws JsonProcessingException {
        CalenderReward calenderReward = new ObjectMapper().readValue(convertObjectToStringJson(object), CalenderReward.class);
        return calenderReward;
    }

    public static HashMap<String, CalenderReward> sortCalenderReward(HashMap<String, CalenderReward> calenderInfor) {
        HashMap<String, CalenderReward> calenderRewards = calenderInfor.entrySet().stream()
                .sorted((e1, e2) -> e1.getValue().getTowerTokenEarned().compareTo(e2.getValue().getTowerTokenEarned()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return calenderRewards;
    }

    public static int getDifferDate() {
        return LocalDate.now().getDayOfMonth();
    }

    public static String subtract(int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -day);
        Date date = calendar.getTime();
        return sdf.format(date);
    }

    public static boolean isRewardToday(String date) {
        String now = Instant.now().toString();
        String[] date1 = date.split("-");
        String[] date2 = now.split("T")[0].split("-");
        return Integer.parseInt(date1[2]) == Integer.parseInt(date2[2]);
    }

}
