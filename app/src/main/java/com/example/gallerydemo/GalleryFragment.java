package com.example.gallerydemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        galleryViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(activity.getApplication())).get(GalleryViewModel.class);
        galleryViewModel.getPhotoListLive().observe(getViewLifecycleOwner() , new Observer<List<PhotoItem>>() {
            @Override
            public void onChanged(List<PhotoItem> photoItems) {
                galleryAdapter.submitList(photoItems);
                getActivity().setTitle(galleryViewModel.curKey);
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