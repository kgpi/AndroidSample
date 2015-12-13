package com.example.kgpi01.sqldbapp.db;

import android.content.Context;

/**
 * Created by KGPI01 on 2015/12/13.
 */
public class AomDbHealperFactory {

    private static AomDbHealper aomDbHealper;

    public static AomDbHealper getInstance(Context context) {
        if(aomDbHealper == null) aomDbHealper = new AomDbHealper(context);
        return aomDbHealper;
    }
}
