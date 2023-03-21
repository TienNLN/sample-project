package utils;

import io.netty.handler.timeout.ReadTimeoutException;
import parameter.RequestBody;

import javax.net.ssl.SSLHandshakeException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

public class RequestUtil {
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36";
    public static int PORT = 10001;

    public synchronized static String postRequest(RequestBody requestBody) throws IOException {
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        URL uri = new URL(requestBody.getUrl());
        Proxy webProxy
                = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", PORT));
        HttpURLConnection httpURLConnection
                = (HttpURLConnection) uri.openConnection(webProxy);
        /*HttpURLConnection httpURLConnection = (HttpURLConnection) uri.openConnection();*/
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod(requestBody.getMethod());
        httpURLConnection.setRequestProperty("Origin", "https://crazydefenseheroes.com");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
        httpURLConnection.setReadTimeout(180000);

        if (requestBody.getFirebaseuuid() != null) {
            httpURLConnection.setRequestProperty("firebaseuuid", requestBody.getFirebaseuuid());
        }
        StringBuilder stringBuilder = new StringBuilder();

        if (requestBody.getData() != null) {
            try (OutputStream outputStream = httpURLConnection.getOutputStream()) {
                outputStream.write(requestBody.getJsonBody().getBytes());
                outputStream.flush();
            }
        }
        try {

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))) {
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null) {

                        stringBuilder.append(line);
                    }
                }
            }
        } catch (ReadTimeoutException e) {
            System.out.println("Request timeout try again: ");
            postRequest(requestBody);
        } catch (SocketTimeoutException | SSLHandshakeException e) {
            try {
                Thread.sleep(5000);
                System.out.println("Socket time out. Waiting...");

                postRequest(requestBody);
            } catch (InterruptedException ex) {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {

            if (httpURLConnection.getResponseCode() == 500) {
                System.out.println("Server busy");

            }
            if (e.getMessage().contains("handshake")) {
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            postRequest(requestBody);
        }
       /* try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return stringBuilder.toString();

    }


}
