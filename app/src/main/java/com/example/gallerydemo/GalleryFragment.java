package com.example.gallerydemo;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class GalleryFragment extends Fragment {
    SwipeRefreshLayout swipeLayout;
    private GalleryViewModel galleryViewModel;

    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gallery_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Activity activity = requireActivity();
        RecyclerView recyclerView = activity.findViewById(R.id.recyclerView);
        swipeLayout = activity.findViewById(R.id.swiperLayout);
        final GalleryAdapter galleryAdapter = new GalleryAdapter();
        recyclerView.setAdapter(galleryAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        galleryViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(activity.getApplication())).get(GalleryViewModel.class);
        galleryViewModel.getPhotoListLive().observe(getViewLifecycleOwner() , new Observer<List<PhotoItem>>() {
            @Override
            public void onChanged(List<PhotoItem> photoItems) {
                galleryAdapter.submitList(photoItems);
                if (swipeLayout.isRefreshing()) {
                    swipeLayout.setRefreshing(false);
                }
            }
        });
        if (galleryViewModel.getPhotoListLive().getValue() == null) {
            galleryViewModel.fetchData();
        }

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                galleryViewModel.fetchData();
            }
        });
    }

}