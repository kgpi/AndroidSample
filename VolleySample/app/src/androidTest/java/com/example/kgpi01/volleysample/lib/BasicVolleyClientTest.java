package com.example.kgpi01.volleysample.lib;

import android.content.res.AssetManager;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by KGPI01 on 2015/11/22.
 */
public class BasicVolleyClientTest extends TestCase {

    public void testGetStringAsync_Ok() throws Exception {
        BasicVolleyClient client = new BasicVolleyClient();
        client.getStringAsync("http://www.yahoo.co.jp", null, new BasicVolleyClient.StringResultCallback() {
            @Override
            public void onResult(StringResult result) {
                /*
                Assert.assertEquals(true, result.isOk());
                Assert.assertEquals(false, result.getResponse().isEmpty());
                Assert.assertEquals(null, result.getVolleyError());
                Assert.assertEquals(201, result.getStatusCode());
                */
            }
        });
    }

    public void testGetStringAsync_404NotFound() throws Exception {
        BasicVolleyClient client = new BasicVolleyClient();
        client.getStringAsync("http://www.yahoo.co.jp/dffdfdfd.htm", null, new BasicVolleyClient.StringResultCallback() {
            @Override
            public void onResult(StringResult result) {
                /*
                Assert.assertEquals(false, result.isOk());
                Assert.assertEquals(null, result.getResponse());
                Assert.assertEquals(404, result.getStatusCode());
                Assert.assertEquals(403, result.getVolleyError().networkResponse.statusCode);
                */
            }
        });
    }

    public void testGetImageAsync() throws Exception {

    }
}