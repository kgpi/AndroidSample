package com.example.kgpi01.sqldbapp.db;

import java.lang.annotation.RetentionPolicy;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by KGPI01 on 2015/12/13.
 */
public class UnsendDosingModel {

    private static final int ExpirationMonth = 30;

    private DosingRepository dosingService;

    /**
     * コンストラクタ
     * @param service Dosingリポジトリ
     */
    public UnsendDosingModel(DosingRepository service) {
        if(service == null) throw new NullPointerException("service is null");
    }

    /**
     * 読取実績、追加実績の完了を判断
     *
     * 読取実績送信済、追加実績送信済
     * @param dosing
     * @return
     */
    public static boolean isCompleted(Dosing dosing) {
        return dosing.getSendDosingDate() != null && dosing.getCompletedDate() != null;
    }

    /**
     * 期限切れ(削除体操)を判断
     *
     * 読取実績送信完了日時から7日以上経過
     * @param dosing
     * @return
     */
    public static boolean isExpiration(Dosing dosing) {
        Date send = dosing.getSendDosingDate();
        if(send == null) return false;
        Calendar c = Calendar.getInstance();
        c.setTime(send);
        c.add(Calendar.MONTH, ExpirationMonth);

        return send.after(c.getTime());
    }

    /**
     * 読取実績送信済から指定した日付以上経過した場合は削除
     * @param dosing
     * @return
     */
    //public static boolean deleteIfCompletedOrExpiration(Dosing dosing) {

    //
    //

    public static boolean isUnsend(Dosing dosing) {
        return dosing.getSendDosingDate() == null;
    }

    /**
     * 読取実績を送信し、完了時DB更新
     * ※読取情報失敗時はDB更新なし
     * @param dosing 更新情報
     * @return 結果、0:通信失敗、その他はHTTP Status
     */
    public int sendDosing(Dosing dosing) {
        // 送信

        // 送信結果確認

        // 送信完了、OKの場合は送信完了日時、ErrorRowNoを更新
        dosing.setSendDosingDate(new Date(System.currentTimeMillis()));
        dosing.setResponseErrorRow(Dosing.DefaultErrorRowNo);
        // 送信完了、NGの場合は送信完了日時、ErrorRowNoを更新

        // 送信失敗の場合は更新なし

        return 0;   // stub
    }

    /**
     * 追加実績を送信し、完了時DB更新
     * @param dosing 更新情報
     * @return 結果、0:通信失敗、その他はHttp Status
     */
    public static int sendAddDosing(Dosing dosing) {
        // 追加実績送信


        // 送信結果確認

        // 送信完了の場合は完了日時更新
        dosing.setCompletedDate(new Date(System.currentTimeMillis()));

        // 送信失敗の場合は更新なし

        return 0;   // stub
    }
}
