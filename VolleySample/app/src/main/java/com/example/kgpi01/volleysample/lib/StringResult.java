package com.example.kgpi01.volleysample.lib;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by KGPI01 on 2015/11/22.
 */
public class StringResult extends AbstractResult {

    /**
     * コンストラクタ
     * @param response レスポンス文字列、未指定の場合はnull
     * @param statusCode ステータスコード、未指定の場合は0
     * @param volleyError エラー情報、未指定の場合はnull
     */
    public StringResult(String response, int statusCode, VolleyError volleyError) {
        super(statusCode, volleyError);
        setResponse(response);
    }

    /**
     * レスポンス文字列の取得
     * @return レスポンス文字列の取得
     */
    public String getResponse() {
        return response;
    }

    /**
     * レスポンス文字列の設定
     * @param res レスポンス文字列
     */
    private void setResponse(String res) {
        response = res;
    }
    private String response;


}
