package com.example.kgpi01.sqldbapp;

import com.example.kgpi01.sqldbapp.db.AomDbHealper;
import com.example.kgpi01.sqldbapp.db.Dosing;
import com.example.kgpi01.sqldbapp.db.DosingRepository;
import com.example.kgpi01.sqldbapp.db.UnsendDosingModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KGPI01 on 2015/12/13.
 */
public class NewUnsendThread extends Thread {

    private AomDbHealper aomDbHealper;
    private boolean haltValue = false;
    private DosingRepository dosingService;

    public NewUnsendThread(AomDbHealper aomDbHealper) {
        this.aomDbHealper = aomDbHealper;
        dosingService = new DosingRepository(aomDbHealper);
    }

    public void halt() {
        haltValue = true;
    }

    @Override
    public void run() {

        while(!haltValue) {

            // dosing_tableからリスト取得(以下順番にループ)]
            List<Dosing> records = new ArrayList<>();
            try {
                records = dosingService.getAllList();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            for(Dosing dosing : records) {

                if(dosing.isSendDosingDate() || dosing.isCompleted()) {
                    // 一定期間以上経過しているレコードは削除
                    /* 読取結果送信後、追加実績を選択していないデータはisCompletedにはならない*/
                    //todo クリーンアップコード追加
                    continue;
                }

                if(!dosing.isSendDosingDate()) {
                    // 読取データ送信
                    //todo 読取データ送信、送信完了後、送信完了日時を立てる
                    // ErrorRowNo　== ０の場合は完了
                }

                if(dosing.isAddDosingKind()) {
                    //todo 追加実績送信
                    // 送信完了後はCompleteDateを更新
                }

                if(dosing.isSendDosingDate()) {
                    // クリーンアップ
                }

                if(UnsendDosingModel.isCompleted(dosing) && UnsendDosingModel.isExpiration(dosing)) {
                    // 削除
                    continue;
                }
                if(UnsendDosingModel.isCompleted(dosing)) {
                    continue;
                }
                if(UnsendDosingModel.isUnsend(dosing)) {
                    // 読取実績送信後、追加実績が存在する場合は送信
                }

            }

            // 送信履歴が存在し、追加実績が存在、追加実績送信日時がnullの場合は追加実績を送信
            // 送信成功後は追加実績送信日時を更新


            // 送信履歴がnullの場合は送信し、送信日時を更新
            // 送信後、ErrorRowNoが0の場合は削除して終了
            // 追加実績が存在し、未送信の場合は送信後、レコード削除
            // 追加実績が存在しない場合は終了(※あとから追加実績が来る可能性があるのでレコード削除はしない)

            // クリーンアップ処理
            // 送信日時が存在かつ24時間前、追加実績が存在しないレコードは削除
            // ※追加実績を選択せずにアプリを終了したと判断、追加実績は来ない。

            try {
                Thread.sleep(5000);
            } catch (Exception ex) {
                // ignore
            }
        }
    }
}
