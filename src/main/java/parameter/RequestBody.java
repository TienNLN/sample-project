package parameter;

import common.Constant;
import service.DataService;
import utils.CommonUtils;
import utils.FileUtil;

import java.io.Serializable;

public class RequestBody implements Serializable {
    private String url;
    private String method = Constant.METHOD_POST;
    private Object data;
    private String userAgent;
    private String firebaseuuid;

    public RequestBody() {
        this.userAgent = DataService.userAgent;
    }

    public String getJsonBody() {
        return CommonUtils.convertObjectToStringJson(this.data);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getFirebaseuuid() {
        return firebaseuuid;
    }

    public void setFirebaseuuid(String firebaseuuid) {
        this.firebaseuuid = firebaseuuid;
    }
}
