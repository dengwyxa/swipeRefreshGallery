package com.example.gallerydemo;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.Cache;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
//    String url1 = "https://pixabay.com/get/57e3d64b4355a514f6da8c7dda793679143edbe35b516c48702679d1924bc45dba_1280.jpg";
//    String url2 = "https://pixabay.com/get/57e3d3414d55a814f6da8c7dda793679143edbe35b516c48702679d1924bc45dba_1280.jpg";
//    ImageView imageView;
//    private static final String TAG = "galleryDemo";
//    private SwipeRefreshLayout refreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
//        imageView = findViewById(R.id.imageView);
//        refreshLayout = findViewById(R.id.swipeRefreshLayout);
//
//        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                loadImage();
//            }
//        });
//
//        loadImage();
    }

//    void loadImage() {
//        Random random = new Random();
//        String url = random.nextBoolean() ? url1 : url2;
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        ImageLoader imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
//            private LruCache<String, Bitmap> cache = new LruCache<>(50);
//            @Override
//            public Bitmap getBitmap(String url) {
//                return cache.get(url);
//            }
//
//            @Override
//            public void putBitmap(String url, Bitmap bitmap) {
//                cache.put(url, bitmap);
//            }
//        });
//        imageLoader.get(url, new ImageLoader.ImageListener() {
//            @Override
//            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
//                imageView.setImageBitmap(response.getBitmap());
//                if (refreshLayout.isRefreshing()) {
//                    refreshLayout.setRefreshing(false);
//                }
//            }
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d(TAG, "onErrorResponse: " + error);
//            }
//        });
//    }
}