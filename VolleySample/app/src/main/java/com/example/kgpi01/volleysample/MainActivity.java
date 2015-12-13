package com.example.kgpi01.volleysample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.kgpi01.volleysample.lib.BasicVolleyClient;
import com.example.kgpi01.volleysample.lib.BitmapResult;
import com.example.kgpi01.volleysample.lib.StringResult;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickGetStringAsync(View v) {
        BasicVolleyClient client = new BasicVolleyClient();
        HashMap<String, String> mp = new HashMap<>();
        mp.put("name", "S ato");
        mp.put("age", "21");
        client.getStringAsync("https://40.74.142.185/", null, new BasicVolleyClient.StringResultCallback() {
            @Override
            public void onResult(StringResult result) {
                Log.d("Volley", String.valueOf(result.getStatusCode()));
                Log.d("Volley", result.getResponse());
                if (result.getStatusCode() == 0)
                    Log.d("Volley", result.getVolleyError().getMessage());
                //String str = result.getResponse();
                //Log.d("Volley", str + result.getVolleyError().getMessage() + result.getVolleyError().networkResponse.toString());
            }
        });
        Log.d("Volley", "Exit");
    }

    public void onClickGetBitmapAsync(View v) {
        BasicVolleyClient client = new BasicVolleyClient();
        client.getImageAsync("http://k.yimg.jp/images/top/sp2/cmn/logo-ns-131205.png", null, new BasicVolleyClient.BitmapResultCallback() {
            @Override
            public void onResult(BitmapResult result) {
                if (result.isOk()) {
                    ((ImageView) findViewById(R.id.imageView)).setImageBitmap(result.getResponse());
                } else {
                    Log.d("Volley", String.valueOf(result.getStatusCode()));
                    ((ImageView) findViewById(R.id.imageView)).setImageBitmap(null);
                }
            }
        });
    }

    public void onClickGetStringButton(View v) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BasicVolleyClient client = new BasicVolleyClient();
                    String result = client.getString("http://www.yahoo.co.jp/fafa.htm", null);
                    Log.d("Volley", "同期処理：" + result);
                    Log.d("Volley", "Exit");
                } catch(ExecutionException ex) {
                    ex.printStackTrace();
                } catch(Exception e) {
                    //Log.d("Volley", e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
