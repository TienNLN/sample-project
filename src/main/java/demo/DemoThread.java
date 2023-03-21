package demo;

import java.io.IOException;

import static demo.DemoRequest.postRequest;

public class DemoThread extends Thread implements Runnable {
    private Integer i;

    public DemoThread(Integer i) {
        this.i = i;
    }

    @Override
    public void run() {
        try {
            postRequest(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
