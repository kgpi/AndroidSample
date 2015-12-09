package com.example.kgpi01.uisample;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kgpi01.uisample.models.PersonModel;

import java.util.ArrayList;

/**
 * カスタムListViewアダプタクラス
 */
public class PersonListViewAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<PersonModel> personModels;

    /**
     * コンストラクタ
     * @param cxt コンテキスト
     */
    public PersonListViewAdapter(Context cxt) {
        this.context = cxt;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * 人リストの設定
     * @param person 人リスト
     */
    public void setPersonList(ArrayList<PersonModel> person) {
        if(person == null) person = new ArrayList<>();
        personModels = person;
    }

    /**
     * リスト数の取得
     * @return リスト数
     */
    @Override
    public int getCount() {
        return personModels.size();
    }

    /**
     * 指定したアイテムの取得
     * @param position インデックス
     * @return アイテム
     */
    @Override
    public Object getItem(int position) {
        return personModels.get(position);
    }

    /**
     * 指定したインデックスのアイテムIDの取得
     * @param position インデックス
     * @return ID
     */
    @Override
    public long getItemId(int position) {
        return personModels.get(position).getId();
    }

    /**
     * Viewの取得
     * @param position position
     * @param convertView convertView
     * @param parent parent
     * @return View
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.person_list_item, parent, false);

        ((TextView)convertView.findViewById(R.id.noTextView)).setText(String.valueOf(personModels.get(position).getNo()));
        ((TextView)convertView.findViewById(R.id.lastNameTextView)).setText(personModels.get(position).getLastName());
        ((TextView)convertView.findViewById(R.id.ageTextView)).setText(String.valueOf(personModels.get(position).getAge()));

        return convertView;
    }
}
