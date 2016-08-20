package com.hansheng.simplenet.requests;

import com.hansheng.simplenet.base.Request;
import com.hansheng.simplenet.base.Response;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hansheng on 2016/8/20.
 */
public class JsonRequest extends Request<JSONObject> {

    public JsonRequest(Request.HttpMethod method, String url, RequestListener<JSONObject> listener) {
        super(method, url, listener);
    }


    /**
     * 将Response的结果转换为JSONObject
     */
    @Override
    public JSONObject parseResponse(Response response) {
        String jsonString = new String(response.getRawData());
        try {
            return new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
