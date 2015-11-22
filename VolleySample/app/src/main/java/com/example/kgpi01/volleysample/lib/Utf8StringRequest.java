package com.example.kgpi01.volleysample.lib;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;

/**
 * レスポンス文字列をUTF-8でエンコード指定したStringRequestの派生クラス
 *
 * Created by KGPI01 on 2015/11/22.
 */
public class Utf8StringRequest extends StringRequest {

    public Utf8StringRequest(int method, String url, Response.Listener<String> listener,
                         Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {

            parsed = new String(response.data, "UTF-8");//ここの第二引数を任意の文字コードに変更
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }
}
