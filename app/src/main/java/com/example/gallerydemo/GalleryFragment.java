package com.example.gallerydemo;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

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
    SwipeRefreshLayout swipeRefreshLayout;
    private GalleryViewModel mViewModel;

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

        RecyclerView recyclerView = getActivity().findViewById(R.id.recyclerView);
        swipeRefreshLayout = getActivity().findViewById(R.id.swipeRefreshLayout);
        final GalleryAdapter galleryAdapter = new GalleryAdapter();
        recyclerView.setAdapter(galleryAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        mViewModel = ViewModelProviders.of(this, new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())).get(GalleryViewModel.class);
        mViewModel.getPhotoListLive().observe(requireActivity() , new Observer<List<PhotoItem>>() {
            @Override
            public void onChanged(List<PhotoItem> photoItems) {
                galleryAdapter.submitList(photoItems);
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
        if (mViewModel.getPhotoListLive().getValue() == null) {
            mViewModel.fetchData();
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mViewModel.fetchData();
            }
        });
    }

}