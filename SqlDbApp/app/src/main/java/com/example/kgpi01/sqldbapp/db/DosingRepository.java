package com.example.kgpi01.sqldbapp.db;


import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by KGPI01 on 2015/12/13.
 */
public class DosingRepository {

    private AomDbHealper aomDbHealper;

    public DosingRepository(AomDbHealper aomDbHealper) {
        if(aomDbHealper == null) throw new NullPointerException("aomDbhealper is null");
        this.aomDbHealper = aomDbHealper;
    }

    public List<Dosing> getAllList() throws SQLException {
        Dao<Dosing, Integer> dao = aomDbHealper.getDao(Dosing.class);
        return dao.queryForAll();
    }

    public Dosing getById(int id) throws SQLException {
        Dao<Dosing, Integer> dao = aomDbHealper.getDao(Dosing.class);
        return dao.queryForId(id);
    }

    public int add(Dosing dto) throws SQLException {
        Dao<Dosing, Integer> dao = aomDbHealper.getDao(Dosing.class);
        int result = dao.create(dto);
        if(result != 1) return 0;

        return dto.getId();
    }

    public int updateSendDosingDate(int id, Date sendDate, int errorRowNo) throws SQLException {
        Dao<Dosing, Integer> dao = aomDbHealper.getDao(Dosing.class);
        Dosing before = dao.queryForId(id);
        if(before == null) return 0;
        before.setSendDosingDate(sendDate);
        before.setResponseErrorRow(errorRowNo);
        before.setUpdateDate(new Date(System.currentTimeMillis()));

        return dao.update(before);
    }

    public int updateAddDosing(int id, int kind) throws SQLException {
        Dao<Dosing, Integer> dao = aomDbHealper.getDao(Dosing.class);
        Dosing before = dao.queryForId(id);
        if(before == null) return 0;
        before.setAddDosingKind(kind);
        before.setUpdateDate(new Date(System.currentTimeMillis()));

        return dao.update(before);
    }

    public int deleteById(int id) throws SQLException {
        Dao<Dosing, Integer> dao = aomDbHealper.getDao(Dosing.class);
        return dao.deleteById(id);
    }
}
