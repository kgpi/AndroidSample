package com.example.kgpi01.volleysample.lib;

import android.graphics.Bitmap;

import com.android.volley.VolleyError;

/**
 * Created by KGPI01 on 2015/11/22.
 */
public class BitmapResult extends AbstractResult {
    /**
     * コンストラクタ
     * @param response レスポンスデータ、空の配列
     * @param statusCode ステータスコード、未指定の場合は0
     * @param volleyError エラー情報、未指定の場合はnull
     */
    public BitmapResult(Bitmap response, int statusCode, VolleyError volleyError) {
        super(statusCode, volleyError);
        setResponse(response);
    }

    public Bitmap getResponse() {
        return response;
    }
    public void setResponse(Bitmap data) {
        response = data;
    }
    private Bitmap response;
}
