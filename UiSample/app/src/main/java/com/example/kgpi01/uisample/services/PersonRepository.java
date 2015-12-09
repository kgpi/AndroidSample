package com.example.kgpi01.uisample.services;

import com.example.kgpi01.uisample.models.PersonModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by KGPI01 on 2015/12/09.
 */
public class PersonRepository {

    /**
     * テストデータの取得
     * @return テストデータ
     */
    public static ArrayList<PersonModel> getSampleList() {
        ArrayList<PersonModel> list = new ArrayList<>();
        for(int idx = 1;idx<=10;idx++) {
            PersonModel p = new PersonModel(idx, idx, String.format("名前%c", idx), idx * 10);
            list.add(p);
        }

        return list;
    }

    /**
     * 空データの取得
     * @return 空データ
     */
    public static ArrayList<PersonModel> getEmptyList() {
        return new ArrayList<PersonModel>();
    }
}
