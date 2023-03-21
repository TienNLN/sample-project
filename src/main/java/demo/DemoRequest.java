package demo;

import io.netty.handler.timeout.ReadTimeoutException;
import parameter.RequestBody;
import thread.FetchIdThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.concurrent.*;

import static service.RequestBodyCreate.createIdGenerateBody;

public class DemoRequest {
    public static Integer i = 0;

    public synchronized static String postRequest(int i) throws IOException {

        String json = "{\"email\": \"crazy" + i + "@labworld.org\",\"password\": \"sasuke903\", \"vars\": {\"ua\": \"\\\"Mozilla\\/5.0 (Macintosh; Intel Mac OS X 10.9; rv:46.0) Gecko\\/20100101 Firefox\\/46.0\\\"\",\"location\": \"{\\\"code\\\":\\\"VN\\\",\\\"name\\\":\\\"Vietnam\\\"}\",\"cookie_consent\": null,\"invite\": \"user-342243\",\"utm_medium\": \"Invite\",\"utm_source\": \"undefined\",\"utm_campaign\": \"@user-342243\"}}";
        URL uri = new URL("https://live.digitap.eu/v2/account/authenticate/email?create=true&");
        Proxy webProxy
                = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.0.162", 10000));
        HttpURLConnection httpURLConnection
                = (HttpURLConnection) uri.openConnection(webProxy);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:46.0) Gecko/20100101 Firefox/46.0");
        httpURLConnection.setReadTimeout(60000);
        StringBuilder stringBuilder = new StringBuilder();
        try (OutputStream outputStream = httpURLConnection.getOutputStream()) {
            outputStream.write(json.getBytes());
            outputStream.flush();
        }

        try {
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))) {
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    System.out.println(stringBuilder.toString());
                }
            }
        } catch (ReadTimeoutException e) {
            System.out.println("Request timeout try again: ");
            postRequest(i);
        } catch (IOException e) {
            if (httpURLConnection.getResponseCode() == 500) {
                System.out.println("Server busy");
                postRequest(i);
            }
        }
        return stringBuilder.toString();

    }

    public static void main(String[] args) {

        ScheduledExecutorService fixedPool =
                Executors.newScheduledThreadPool(5);
        CompletionService<String> service = new ExecutorCompletionService<String>(fixedPool);
        for (i = 500; i < 100000; i++) {
            System.out.println(i);
            fixedPool.schedule(new DemoThread(i), 5, TimeUnit.SECONDS);
        }
        fixedPool.shutdown();
        while (!fixedPool.isTerminated()) {

        }
    }

}