package com.deepspc.workshop.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpUtil {

    public static String post(final String spec, final Map<String, Object> params) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(spec);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(30000);
            connection.setDoOutput(true);
            if (null != params && !params.isEmpty()) {
                String data = "";
                int i = 0;
                for (Map.Entry<String, Object> map : params.entrySet()) {
                    if (i > 0) {
                        data += "&";
                    }
                    i++;
                    data += map.getKey() + "=" + map.getValue();
                }

                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(data.getBytes());
                outputStream.flush();
                outputStream.close();
            }
            connection.connect();
            int code = connection.getResponseCode();
            if (200 == connection.getResponseCode()) {
                InputStream is = connection.getInputStream();
                String state = getStringFromInputStream(is);
                connection.disconnect();
                return state;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != connection) {
                connection.disconnect();
            }
        }

        return null;
    }

    private static String getStringFromInputStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        is.close();
        isr.close();
        br.close();
        return sb.toString();
    }
}
