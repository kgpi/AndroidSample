package com.example.kgpi01.sqldbapp.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 未送信テーブルに対応するエンティティ定義
 * Ormliteで使用するためのアノテーションを付加している.
 *
 * Created by KGPI01 on 2015/12/12.
 */
@DatabaseTable(tableName = "dosing_table")
public class Dosing {

    /**
     * ErrorRowNo初期値
     */
    public static final int DefaultErrorRowNo = -1;
    /**
     * 追加実績区分初期値
     */
    public static final int DefaultDosingKind = -1;

    public Dosing(){}

    /**
     * IDの取得
     * @return ID
     */
    public int getId() { return _id; }

    /**
     * IDの設定
     * @param id ID
     */
    public void setId(int id) { this._id = id; }

    @DatabaseField(generatedId = true)
    private int _id = 0;

    /**
     * スキャン日時の取得
     * @return スキャン日時
     */
    public Date getScanDate() { return scanDate; }

    /**
     * スキャン日時の設定
     * @param scanDate スキャン日時
     */
    public void setScanDate(Date scanDate) { this.scanDate = scanDate; }
    @DatabaseField(canBeNull = false)
    private Date scanDate = null;

    /**
     * 職員RowNoの取得
     * @return 職員RowNo
     */
    public String getStaffRow() { return this.staffRow; }

    /**
     * 職員RowNoの設定
     * @param staffRow 職員職員RowNo
     */
    public void setStaffRow(String staffRow) { this.staffRow = nullToEmpty(staffRow); }
    @DatabaseField(canBeNull = false)
    private String staffRow = "";

    /**
     * 職員QRコードの取得
     * @return 職員QRコード
     */
    public String getStaffQr() { return this.staffQr; }

    /**
     * 職員QRコードの設定
     * @param staffQr 職員QRコード
     */
    public void setStaffQr(String staffQr) { this.staffQr = nullToEmpty(staffQr); }
    @DatabaseField(canBeNull = false)
    private String staffQr = "";

    /**
     * 入居者RowNoの取得
     * @return 入居者RowNo
     */
    public String getResidentRow() { return this.residentRow; }

    /**
     * 入居者RowNoの設定
     * @param residentRow 入居者RowNo
     */
    public void setResidentRow(String residentRow) { this.residentRow = nullToEmpty(residentRow); }
    @DatabaseField(canBeNull = false)
    private String residentRow = "";

    /**
     * 入居者QRの取得
     * @return 入居者QR
     */
    public String getResidentQr() { return this.residentQr; }

    /**
     * 入居者QRの設定
     * @param residentQr 入居者QR
     */
    public void setResidentQr(String residentQr) { this.residentQr = nullToEmpty(residentQr); }
    @DatabaseField(canBeNull = false)
    private String residentQr = "";

    /**
     * 薬袋RowNoの取得
     * @return 薬袋RowNo
     */
    public String getPillRow() { return this.pillRow; }

    /**
     * 薬袋RowNoの設定
     * @param pillRow 薬袋RowNo
     */
    public void setPillRow(String pillRow) { this.pillRow = nullToEmpty(pillRow); }
    @DatabaseField(canBeNull = false)
    private String pillRow = "";

    /**
     * 薬袋Qrの取得
     * @return 薬袋Qr
     */
    public String getPillQr() { return this.pillQr; }

    /**
     * 薬袋Qrの設定
     * @param pillQr 薬袋Qr
     */
    public void setPillQr(String pillQr) { this.pillQr = nullToEmpty(pillQr); }
    @DatabaseField(canBeNull = false)
    private String pillQr = "";

    /**
     * 服薬日の取得
     * @return 服薬日
     */
    public Date getPillDate() { return pillDate; }

    /**
     * 服薬日の設定
     * @param pillDate 服薬日
     */
    public void setPillDate(Date pillDate) { this.pillDate = pillDate; }
    @DatabaseField(canBeNull = false)
    private Date pillDate = null;

    /**
     * 服薬カテゴリの取得
     * @return 服薬カテゴリ
     */
    public String getPillCategory() { return this.pillCategory; }

    /**
     * 服薬カテゴリの設定
     * @param pillCategory 服薬カテゴリ
     */
    public void setPillCategory(String pillCategory) { this.pillCategory = nullToEmpty(pillCategory); }
    @DatabaseField(canBeNull = false)
    private String pillCategory = "";

    /**
     * 読取情報送信完了日時が存在するか
     * @return True:存在、False:NULL
     */
    public boolean isSendDosingDate() { return sendDosingDate != null; }

    /**
     * 読取情報送信完了日時の取得
     * @return 読取情報送信完了日時
     */
    public Date getSendDosingDate() { return sendDosingDate; }

    /**
     * 読取情報送信完了日時の設定
     * @param tick 読取情報送信完了日時
     */
    public void setSendDosingDate(Date tick) { this.sendDosingDate = tick; }
    @DatabaseField(canBeNull = true)
    private Date sendDosingDate = null;

    /**
     * 読取結果エラーRowNoの取得
     * @return エラーRowNo
     */
    public int getResponseErrorRow() { return responseErrorRow; }

    /**
     * 読取結果エラーRowNoの設定
     * @param errorRow エラーRowNo
     */
    public void setResponseErrorRow(int errorRow) { this.responseErrorRow = errorRow; }
    @DatabaseField(canBeNull = false)
    private int responseErrorRow = DefaultErrorRowNo;

    /**
     * 追加実績区分が存在するか
     * @return
     */
    public boolean isAddDosingKind() {return addDosingKind != DefaultDosingKind;}

    /**
     * 追加実績区分の取得
     * @return 追加実績区分
     */
    public int getAddDosingKind() { return addDosingKind; }

    /**
     * 追加実績区分の設定
     * @param kind 追加実績区分
     */
    public void setAddDosingKind(int kind) { this.addDosingKind = kind; }
    @DatabaseField(canBeNull = false)
    private int addDosingKind = DefaultDosingKind;

    /**
     * 完了日時が存在するか
     * @return True:存在、False:NULL
     */
    public boolean isCompleted() { return completedDate != null; }

    /**
     * 完了日時の取得
     * @return 完了日時
     */
    public Date getCompletedDate() { return completedDate; }

    /**
     * 完了日時の設定
     * @param tick 完了日時
     */
    public void setCompletedDate(Date tick) { this.completedDate = tick; }
    @DatabaseField(canBeNull = true)
    private Date completedDate = null;

    /**
     * レコード作成日時の取得
     * @return レコード作成日時
     */
    public Date getCreateDate() { return createDate; }

    /**
     * レコード作成日時の設定
     * @param dateTick レコード作成日時
     */
    public void setCreateDate(Date dateTick) { this.createDate = dateTick; }
    @DatabaseField(canBeNull = false)
    private Date createDate = new Date(System.currentTimeMillis());

    /**
     * レコード最終更新日時の取得
     * @return レコード最終更新日時
     */
    public Date getUpdateDate() { return updateDate; }

    /**
     * レコード最終更新日時の設定
     * @param dateTick レコード最終更新日時
     */
    public void setUpdateDate(Date dateTick) { this.updateDate = dateTick; }
    @DatabaseField(canBeNull = false)
    private Date updateDate = new Date(System.currentTimeMillis());

    /**
     * オブジェクトの状態を文字列形式で取得
     * @return オブジェクトの状態
     */
    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("Id:" + String.valueOf(getId()));
        buf.append(" ScanDate:" + dateToString(getScanDate()));
        buf.append(" StaffRow:" + nullToEmpty(getStaffRow()));
        buf.append(" StaffQr:" + nullToEmpty(getStaffQr()));
        buf.append(" ResidentRow:" + nullToEmpty(getResidentRow()));
        buf.append(" ResidentQr:" + nullToEmpty(getResidentQr()));
        buf.append(" PillRow:" + nullToEmpty(getPillRow()));
        buf.append(" PillQr:" + nullToEmpty(getPillQr()));
        buf.append(" PillDate:" + dateToString(getPillDate()));
        buf.append(" PillCategory:" + nullToEmpty(getPillCategory()));
        buf.append(" SendDosingDate:" + dateToString(getSendDosingDate()));
        buf.append(" ResponseErrorRow:" + String.valueOf(getResponseErrorRow()));
        buf.append(" AddDosingKind:" + String.valueOf(getAddDosingKind()));
        buf.append(" CompletedDate:" + dateToString(getCompletedDate()));
        buf.append(" CreateDate:" + dateToString(getCreateDate()));
        buf.append(" UpdateDate:" + dateToString(getUpdateDate()));

        return buf.toString();
    }

    // Null to Empty
    private String nullToEmpty(String s) {
        return s == null ? "" : s;
    }

    // Date to String
    private String dateToString(Date date) {
        if(date == null) return "null";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(date);
    }
}
