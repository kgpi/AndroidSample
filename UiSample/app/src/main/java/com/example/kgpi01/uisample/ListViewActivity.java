package com.example.kgpi01.uisample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.kgpi01.uisample.services.PersonRepository;

public class ListViewActivity extends AppCompatActivity {

    private PersonListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        // ListView初期化
        ListView listView = (ListView)findViewById(R.id.listView);
        adapter = new PersonListViewAdapter(ListViewActivity.this);
        adapter.setPersonList(PersonRepository.getSampleList());
        listView.setAdapter(adapter);


    }

}
