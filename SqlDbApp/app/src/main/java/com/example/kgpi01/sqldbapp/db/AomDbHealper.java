package com.example.kgpi01.sqldbapp.db;

import android.app.ActionBar;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Ormliteを使用するためのヘルパークラス
 *
 * Created by KGPI01 on 2015/12/12.
 */
public class AomDbHealper extends OrmLiteSqliteOpenHelper {

    // DBファイル名は固定でよい
    private static final String DataBaseFileName = "aom.db";
    // DB定義に変更が発生した場合はインクリメントをする
    // この値をインクリメントした場合、onUpgradeが発生する(マイグレーションは自分で記述)
    private static final int DataBaseVersion = 7;

    /**
     * コンストラクタ
     * @param context コンテキスト
     */
    public AomDbHealper(Context context) {
        super(context, DataBaseFileName, null, DataBaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
           // TableUtils.dropTable(connectionSource, Dosing.class, false);
            TableUtils.createTableIfNotExists(connectionSource, Dosing.class);
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Dosing.class, false);
            TableUtils.createTableIfNotExists(connectionSource, Dosing.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
