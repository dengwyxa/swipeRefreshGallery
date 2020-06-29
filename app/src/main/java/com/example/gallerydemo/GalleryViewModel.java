package com.example.gallerydemo;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GalleryViewModel extends AndroidViewModel {
    private MutableLiveData<List<PhotoItem>> _photoListLive = new MutableLiveData<List<PhotoItem>>();
    LiveData<List<PhotoItem>> photoListLive;
    private static final String TAG = "galleryDemo";
    String curKey;

    public LiveData<List<PhotoItem>> getPhotoListLive() {
        return _photoListLive;
    }

    public GalleryViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchData() {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                getUrl(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        _photoListLive.setValue(gson.fromJson(response, Pixabay.class).hits);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.toString());
                    }
                }
        );
        VolleySingleton.getInstance(getApplication()).getRequestQueue().add(stringRequest);
    }

    private final List<String> keywords = new ArrayList<String>(
            Arrays.asList("拜仁慕尼黑", "西安", "北京", "天津", "东京", "京都", "北海道", "冲绳", "琦玉"));
    private  String getUrl() {
        int randomIdx = new Random().nextInt(7);
        curKey = keywords.get(randomIdx);
        Log.d(TAG, "key: " + curKey);
        return "https://pixabay.com/api/?key=17115680-36b85063fdf3b37b583e2dd11&lang=zh&q=" + curKey.replaceAll(" ", "+") + "&per_page=100";
    }


}