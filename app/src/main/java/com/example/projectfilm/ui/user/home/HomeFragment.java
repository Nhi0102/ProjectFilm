package com.example.projectfilm.ui.user.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.projectfilm.R;
import com.example.projectfilm.data.viewmodels.user.home.HomeViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {

    private RecyclerView trendingRecyclerView;
    private TrendingAdapter trendingAdapter;
    private HomeViewModel homeViewModel;

    private ViewPager2 bannerViewPager;
    private BannerAdapter bannerAdapter;
    private TabLayout bannerIndicator;

    private Handler sliderHandler = new Handler(Looper.getMainLooper());
    private Runnable sliderRunnable;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_home, container, false);

        // ⬇️ Thêm dòng này để mở Drawer khi bấm menu
        ImageView menuIcon = view.findViewById(R.id.menuIcon);
        menuIcon.setOnClickListener(v -> {
            DrawerLayout drawerLayout = requireActivity().findViewById(R.id.drawerLayout);
            if (drawerLayout != null) {
                drawerLayout.openDrawer(androidx.core.view.GravityCompat.START);
            }
        });

        // Banner ViewPager2 setup
        bannerViewPager = view.findViewById(R.id.bannerViewPager);
        bannerIndicator = view.findViewById(R.id.bannerIndicator);
        bannerAdapter = new BannerAdapter();
        bannerViewPager.setAdapter(bannerAdapter);

        // RecyclerView setup
        trendingRecyclerView = view.findViewById(R.id.trendingRecyclerView);
        trendingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        trendingAdapter = new TrendingAdapter(getContext());
        trendingRecyclerView.setAdapter(trendingAdapter);

        // ViewModel setup
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        homeViewModel.getMovies().observe(getViewLifecycleOwner(), movies -> {
            trendingAdapter.setMovieList(movies);
        });

        homeViewModel.getBannerUrls().observe(getViewLifecycleOwner(), urls -> {
            bannerAdapter.setImageUrls(urls);
            new TabLayoutMediator(bannerIndicator, bannerViewPager,
                    (tab, position) -> {}).attach();

            for (int i = 0; i < bannerIndicator.getTabCount(); i++) {
                TabLayout.Tab tab = bannerIndicator.getTabAt(i);
                if (tab != null) {
                    View tabView = ((ViewGroup) bannerIndicator.getChildAt(0)).getChildAt(i);
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) tabView.getLayoutParams();
                    params.setMargins(12, 0, 12, 0);
                    tabView.setLayoutParams(params);
                    tabView.requestLayout();
                }
            }

            // Auto slide mỗi 5 giây
            sliderRunnable = new Runnable() {
                @Override
                public void run() {
                    int currentItem = bannerViewPager.getCurrentItem();
                    int totalItem = bannerAdapter.getItemCount();
                    if (currentItem < totalItem - 1) {
                        bannerViewPager.setCurrentItem(currentItem + 1);
                    } else {
                        bannerViewPager.setCurrentItem(0);
                    }
                    sliderHandler.postDelayed(this, 5000);
                }
            };
            sliderHandler.postDelayed(sliderRunnable, 5000);
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        sliderHandler.removeCallbacks(sliderRunnable); // Dọn handler tránh memory leak
    }
}
