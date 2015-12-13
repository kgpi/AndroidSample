package com.example.kgpi01.volleysample.lib;

import android.util.AndroidRuntimeException;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.NoCache;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * RequestQueueの管理
 *
 * Created by KGPI01 on 2015/11/21.
 */
public class VolleyFactory {

    // スレッドプール数
    private static final int DefaultThreadPoolSize = 16;
    // RequestQueue
    private static RequestQueue requestQueue;

    /**
     * RequestQueueの取得.
     * シングルトン(公式リファレンスで推奨)
     * @return RequestQueue
     */
    synchronized public static RequestQueue getRequestQueue() {
        if(requestQueue == null) {
            // SSL事故証明書を無視、キャッシュ無効化、スレッドプールサイズをカスタマイズ
            requestQueue = new RequestQueue(new NoCache(), new BasicNetwork(new HurlStack(null, getAllAllowsSocketFactory())), DefaultThreadPoolSize);
            requestQueue.start();
        }
        return requestQueue;
    }

    /**
     * RequestQueueの開放
     */
    synchronized public static void releaseRequestQueue() {
        if(requestQueue != null) {
            requestQueue.stop();
        }
    }

    /**
     * 自己証明書を使用したSSL接続時に使用する。
     * 検証をスキップする
     * @return SSlSocketFactory
     */
    private static SSLSocketFactory getAllAllowsSocketFactory() {
        try {
            // ホスト名検証をスキップする
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            // 証明書検証スキップする空の TrustManager
            final TrustManager[] manager = {new X509TrustManager() {

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    // do nothing
                }

                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    // do nothing
                }
            }};
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, manager, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new AndroidRuntimeException(e);
        }
    }
}
