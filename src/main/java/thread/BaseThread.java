package thread;

import parameter.RequestBody;

public class BaseThread extends Thread {
    private RequestBody requestBody;

    protected BaseThread(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    protected BaseThread() {

    }

    private static BaseThread instance;
    private final static Object LOCK = new Object();
    private static boolean suspended = false;

    public static BaseThread getInstance() {
        synchronized (LOCK) {
            if (instance == null) {
                instance = new BaseThread();
            }
        }
        return instance;
    }

    public static boolean isSuspended() {
        return suspended;
    }

    public static void setSuspended(boolean suspended) {
        BaseThread.suspended = suspended;
    }

    public void suspendThread() {
        setSuspended(true);
        System.out.println("suspended");
    }

    public synchronized void resumeThread() {
        setSuspended(false);
        notifyAll();
        System.out.println("resume");
    }

    public RequestBody getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

}
