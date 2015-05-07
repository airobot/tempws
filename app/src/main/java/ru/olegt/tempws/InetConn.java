package ru.olegt.tempws;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

public class InetConn {
    //откуда получаем данные
    public String USER_URL = "http://temp.olegt.ru";


    public String getInet() {
        String result = "";
        String url = USER_URL;
        String proxyHost = android.net.Proxy.getDefaultHost();
        int proxyPort = android.net.Proxy.getDefaultPort();
        HttpClient httpClient = new DefaultHttpClient();
        if (proxyPort > 0) {
            HttpHost proxy = new HttpHost(proxyHost, proxyPort);
            httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        }
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                sb.append('°');
                String answer = sb.toString();
                result = answer;
            }
        } catch (Exception e) {

        }
        return result;
    }
}