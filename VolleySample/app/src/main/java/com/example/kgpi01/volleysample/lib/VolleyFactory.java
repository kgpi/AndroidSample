package com.example.kgpi01.volleysample.lib;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.NoCache;

/**
 * RequestQueueの管理
 *
 * Created by KGPI01 on 2015/11/21.
 */
public class VolleyFactory {

    // スレッドプール数
    private static final int DefaultThreadPoolSize = 4;
    // RequestQueue
    private static RequestQueue requestQueue;
    // 排他処理用ロックオブジェクト
    private static Object lockObject = new Object();

    /**
     * RequestQueueの取得.
     * シングルトン(公式リファレンスで推奨)
     * @return RequestQueue
     */
    synchronized public static RequestQueue getRequestQueue() {
        if(requestQueue == null) {
            requestQueue = new RequestQueue(new NoCache(), new BasicNetwork(new HurlStack()), DefaultThreadPoolSize);
            requestQueue.start();
        }
        return requestQueue;
    }

    /**
     * RequestQueueの開放
     */
    synchronized public static void releaseRequestQueue() {
        if(requestQueue != null) {
            requestQueue.stop();
        }
    }
}
