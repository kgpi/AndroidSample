package com.example.kgpi01.volleysample.lib;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.RequestFuture;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * 標準的なVolleyクライアント
 * 
 * 機能
 *   ・HTTP/GETを使用したレスポンス文字列の取得
 *       public void getStringAsync
 *   ・HTTP/GETを使用した画像ファイルをBitmapオブジェクトで取得
 *       public void getImageAsync
 *   ・HTTP/GETを使用した同期処理
 *       public String getString
 *       ※失敗時は例外がスローされる
 *
 */
public class BasicVolleyClient {

    public BasicVolleyClient(){    }

    /**
     * 文字列をGETメソッドで非同期取得する
     * @param baseUri URL
     * @param params クエリパラメータ
     * @param resultCallback 結果呼び出しコールバック
     */
    public void getStringAsync(String baseUri, Map<String, String> params, final StringResultCallback resultCallback) {
        if(params == null)
            params = new HashMap<>();

        // HTTP.GETで送信
        Utf8StringRequest req = new Utf8StringRequest(Request.Method.GET, buildUri(baseUri, params), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                resultCallback.onResult(new StringResult(response, 200, null));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                int statusCode = 0;
                if(error.networkResponse != null) statusCode = error.networkResponse.statusCode;
                resultCallback.onResult(new StringResult(null, statusCode, error));
            }
        });
        Log.d("Volley", "RequestUri:" + req.getUrl());
        VolleyFactory.getRequestQueue().add(req);
    }

    /**
     * 画像ファイルを非同期取得する
     * @param baseUri URL
     * @param params パラメータ
     * @param resultCallback 結果コールバック
     */
    public void getImageAsync(String baseUri, Map<String, String> params, final BitmapResultCallback resultCallback) {
        if(params == null)
            params = new HashMap<>();

        // HTTP.GETで送信
        ImageRequest req = new ImageRequest(buildUri(baseUri, params), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                resultCallback.onResult(new BitmapResult(response, 200, null));
            }
        }, 0, 0, Bitmap.Config.ARGB_8888 , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                int statusCode = 0;
                if(error.networkResponse != null) statusCode = error.networkResponse.statusCode;
                resultCallback.onResult(new BitmapResult(null, statusCode, error));
            }
        });
        Log.d("Volley", "RequestUri:" + req.getUrl());
        VolleyFactory.getRequestQueue().add(req);
    }

    /**
     * GETメソッドを使用した非同期取得
     * @param baseUri URL
     * @param params クエリ
     * @return 結果取得コールバック
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws TimeoutException
     */
    public String getString(String baseUri, Map<String, String> params) throws ExecutionException, InterruptedException, TimeoutException {
        if(params == null)
            params = new HashMap<>();

        RequestFuture<String> future = RequestFuture.newFuture();
        Utf8StringRequest req = new Utf8StringRequest(Request.Method.GET, buildUri(baseUri, params), future, future);
        VolleyFactory.getRequestQueue().add(req);

        return future.get();
    }

    private String buildUri(String baseUri, Map<String, String> getParams) {
        // URI組み立て
        Uri.Builder uri = Uri.parse(baseUri)
                .buildUpon();
        for(String key : getParams.keySet()) {
            uri.appendQueryParameter(key, getParams.get(key));
        }
        return uri.build().toString();
    }

    public interface BitmapResultCallback {
        void onResult(BitmapResult result);
    }

    public interface StringResultCallback {
        void onResult(StringResult result);
    }

}
