package com.example.kgpi01.volleysample.lib;

import com.android.volley.VolleyError;

/**
 *
 */
public abstract class AbstractResult {


    /**
     * コンストラクタ
     * @param statusCode ステータスコード、未指定の場合は0
     * @param volleyError エラー情報、未指定の場合はnull
     */
    protected AbstractResult(int statusCode, VolleyError volleyError) {
        super();
        setStatusCode(statusCode);
        setVolleyError(volleyError);
    }

    /**
     * ステータスコードの取得
     * @return ステータスコード
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * ステータスコードの設定
     * @param code ステータスコード
     */
    public void setStatusCode(int code) {
        statusCode = code;
    }
    private int statusCode;

    /**
     * ステータスコードが200かの判定
     * @return True:200、False:200以外
     */
    public boolean isOk() {
        return getStatusCode() == 200;
    }

    /**
     * エラー情報の取得
     * @return エラー情報、存在しない場合はnull
     */
    public VolleyError getVolleyError() {
        return volleyError;
    }

    /**
     * エラー情報の設定
     * @param error エラー情報
     */
    public void setVolleyError(VolleyError error) {
        volleyError = error;
    }
    private VolleyError volleyError;

}
