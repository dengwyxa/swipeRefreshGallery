package com.example.gallerydemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class PagerPhotoFragment extends Fragment {
    private ViewPager2 viewPager2;
    private TextView photoTag;

    public PagerPhotoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pager_photo, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PagerPhotoListAdapter adapter = new PagerPhotoListAdapter();
        viewPager2 = requireActivity().findViewById(R.id.viewpage2);
        viewPager2.setAdapter(adapter);
        final ArrayList<PhotoItem> photoList = getArguments().getParcelableArrayList("PHOTO_LIST");
        adapter.submitList(photoList);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                photoTag = requireActivity().findViewById(R.id.photoTag);
                photoTag.setText((position + 1) + "/" + photoList.size());
            }
        });
        int pos = getArguments().getInt("PHOTO_POS");
        viewPager2.setCurrentItem(pos, false);
    }
}