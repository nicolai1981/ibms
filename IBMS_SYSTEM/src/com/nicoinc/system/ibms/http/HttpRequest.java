package com.nicoinc.system.ibms.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.nicoinc.system.ibms.command.RequestResult;
import com.nicoinc.system.ibms.command.RequestResult.CODE;
import com.nicoinc.system.ibms.main.Application;

public class HttpRequest {
    private String mURL;
    private RequestResult mResult;
    private List <NameValuePair> mParamList;
    
    public HttpRequest(String url, RequestResult result) {
        mURL = url;
        mResult = result;
        mParamList = new ArrayList <NameValuePair>();
        mParamList.add(new BasicNameValuePair("token", Application.getInstance().getToken()));
    }

    public void addParam(String key, String value) {
        mParamList.add(new BasicNameValuePair(key, value));
    }

    public void start() {
        System.out.println("HttpRequest - " + mURL);

        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(mURL);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(mParamList));
            HttpResponse response = httpclient.execute(httpPost);

            System.out.println("Headers");
            Iterator<NameValuePair> iterator = mParamList.iterator();
            while (iterator.hasNext()) {
                NameValuePair header = iterator.next();
                System.out.println(" " + header.getName() + ":" + header.getValue());
            }

            System.out.println("Status: " + response.getStatusLine());
            for (Header header : response.getAllHeaders()) {
                if ("Content-Type".equals(header.getName())) {
                    System.out.println("Content-Type: " + header.getValue());
                }
            }

            HttpEntity entity = response.getEntity();
            String content = inputStreamToString(entity.getContent());
            System.out.println("Content-lenght:" + entity.getContentLength() );
            System.out.println("Content:<" + content + ">\n");
            EntityUtils.consume(entity);

            mResult.setJSON(new JsonParser().parse(content.toString()).getAsJsonObject());
            mResult.setCode(CODE.OK);
        } catch (JsonSyntaxException e) {
            mResult.setCode(CODE.SERVER_ERROR);
            e.printStackTrace();
        } catch (IOException e) {
            mResult.setCode(CODE.WITHOUT_CONNECTION);
            e.printStackTrace();
        } finally {
            httpPost.releaseConnection();
        }
    }

    private String inputStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
        }
        return null;
    }
}
