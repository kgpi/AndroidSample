package com.example.kgpi01.sqldbapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.kgpi01.sqldbapp.db.AomDbHealperFactory;
import com.example.kgpi01.sqldbapp.db.Dosing;
import com.j256.ormlite.dao.Dao;

import junit.framework.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {

    private final String DebugTag = MainActivity.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickTestButton(View v) {

        try {
            Dao<Dosing, Integer> dao = AomDbHealperFactory.getInstance(getApplicationContext()).getDao(Dosing.class);
            List<Dosing> list = dao.queryForAll();

            logWrite("テーブル件数:" + String.valueOf(list.size()));
            for(Dosing record : list) {
                logWrite(record.toString());
            }

            int beforeCount = list.size();

            // 三点データ追加 送信結果OK
            Dosing readDto = new Dosing();
            readDto.setScanDate(strToDate("2015/12/1 01:02:03"));
            readDto.setStaffQr("20000000001");
            readDto.setStaffRow("0000000001");
            readDto.setResidentQr("11000000001");
            readDto.setResidentRow("1000000001");
            readDto.setPillQr("1234567890,2015/12/01,1234");
            readDto.setPillRow("1234567890");
            readDto.setPillDate(strToDateOnly("2015/12/01"));
            readDto.setPillCategory("1234");
            readDto.setSendDosingDate(strToDate("2015/12/1 01:02:10"));

            // 戻り値は新しいRowNoではなかった。
            // 追加に成功した件数らしい
            // 追加時に渡したreadDtoのPKフィールドが挿入後のRowNoに更新されている
            int insertResult = dao.create(readDto);
            Assert.assertEquals(1, insertResult);
            int newRow = readDto.getId();

            logWrite("結果OKデータ追加完了：" + String.valueOf(newRow));

            // 追加データの取得
            Dosing insertDto1 = dao.queryForId(newRow);
                // 取得データの正当性の検証
            Assert.assertTrue(insertDto1 != null);
            Assert.assertEquals(newRow, insertDto1.getId());
            Assert.assertEquals(strToDate("2015/12/1 01:02:03"), insertDto1.getScanDate());
            Assert.assertEquals("20000000001", insertDto1.getStaffQr());
            Assert.assertEquals("0000000001", insertDto1.getStaffRow());
            Assert.assertEquals("11000000001", insertDto1.getResidentQr());
            Assert.assertEquals("1000000001", insertDto1.getResidentRow());
            Assert.assertEquals("1234567890,2015/12/01,1234", insertDto1.getPillQr());
            Assert.assertEquals("1234567890", insertDto1.getPillRow());
            Assert.assertEquals(strToDateOnly("2015/12/01"), insertDto1.getPillDate());
            Assert.assertEquals("1234", insertDto1.getPillCategory());
            Assert.assertEquals(strToDate("2015/12/1 01:02:10"), insertDto1.getSendDosingDate());
            Assert.assertEquals(null, insertDto1.getCompletedDate());
            Assert.assertEquals(-1, insertDto1.getAddDosingKind());     // 追加実績がまだ存在しない
            Assert.assertEquals(-1, insertDto1.getResponseErrorRow());  // 結果OKの場合はErrorRowNoが存在しない

                // 追加実績の挿入
            Dosing dto2 = dao.queryForId(insertDto1.getId());
            dto2.setAddDosingKind(1);   // 投薬
            dto2.setUpdateDate(new Date(System.currentTimeMillis()));
            int ret = dao.update(dto2);
                // 検証
            Assert.assertEquals(1, ret);
            Dosing dto3 = dao.queryForId(insertDto1.getId());
            Assert.assertEquals(dto2.getId(), dto3.getId());
            Assert.assertEquals(dto2.getStaffQr(), dto3.getStaffQr());
            Assert.assertEquals(dto2.getStaffRow(), dto3.getStaffRow());
            Assert.assertEquals(dto2.getResidentQr(), dto3.getResidentQr());
            Assert.assertEquals(dto2.getResidentRow(), dto3.getResidentRow());
            Assert.assertEquals(dto2.getPillCategory(), dto3.getPillCategory());
            Assert.assertEquals(dto2.getPillDate(), dto3.getPillDate());
            Assert.assertEquals(dto2.getPillQr(), dto3.getPillQr());
            Assert.assertEquals(dto2.getPillRow(), dto3.getPillRow());
            Assert.assertEquals(dto2.getResponseErrorRow(), dto3.getResponseErrorRow());
            Assert.assertEquals(dto2.getAddDosingKind(), dto3.getAddDosingKind());
            Assert.assertEquals(dto2.getScanDate(), dto3.getScanDate());
            Assert.assertEquals(dto2.getSendDosingDate(), dto3.getSendDosingDate());
            Assert.assertEquals(dto2.getCompletedDate(), dto3.getCompletedDate());

            // 削除
            ret = dao.deleteById(dto3.getId());
            Assert.assertEquals(1, ret);


            logWrite("Delete");
            for(Dosing record : dao.queryForAll()) {
                logWrite(record.toString());
            }

        } catch(Exception ex) {
            logWrite(ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void logWrite(String log) {
        Log.d(DebugTag, log);
    }

    private Date strToDate(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            return sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    private Date strToDateOnly(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            return sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
