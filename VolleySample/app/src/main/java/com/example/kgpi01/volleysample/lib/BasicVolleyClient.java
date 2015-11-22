package com.example.kgpi01.volleysample.lib;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 標準的なVolleyクライアント
 * 
 * 機能
 *   ・HTTP/GETを使用したレスポンス文字列の取得
 *       public void getStringAsync
 *   ・HTTP/GETを使用した画像ファイルをBitmapオブジェクトで取得
 *       public void getImageAsync
 *
 */
public class BasicVolleyClient {

    public BasicVolleyClient(){    }

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
