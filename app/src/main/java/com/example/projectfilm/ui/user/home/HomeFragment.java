package com.example.projectfilm.ui.user.home;

<<<<<<< HEAD
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
=======
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
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

<<<<<<< HEAD
=======
    private ImageView searchIcon;
    private EditText searchEditText;
    private boolean isSearchVisible = false;

>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_home, container, false);

<<<<<<< HEAD
        // ⬇️ Thêm dòng này để mở Drawer khi bấm menu
=======
        // Drawer toggle
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
        ImageView menuIcon = view.findViewById(R.id.menuIcon);
        menuIcon.setOnClickListener(v -> {
            DrawerLayout drawerLayout = requireActivity().findViewById(R.id.drawerLayout);
            if (drawerLayout != null) {
                drawerLayout.openDrawer(androidx.core.view.GravityCompat.START);
            }
        });

<<<<<<< HEAD
        // Banner ViewPager2 setup
=======
        // Search setup
        searchIcon = view.findViewById(R.id.searchIcon);
        searchEditText = view.findViewById(R.id.searchEditText);

        searchIcon.setOnClickListener(v -> toggleSearchBar());

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchMovies(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        // Xử lý Enter trên bàn phím
        searchEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH
                    || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                    && event.getAction() == KeyEvent.ACTION_DOWN)) {

                String keyword = searchEditText.getText().toString().trim();
                Log.d("SEARCH", "Search triggered: " + keyword);

                hideKeyboard();
                searchMovies(keyword);
                return true;
            }
            return false;
        });

        // Banner setup
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
        bannerViewPager = view.findViewById(R.id.bannerViewPager);
        bannerIndicator = view.findViewById(R.id.bannerIndicator);
        bannerAdapter = new BannerAdapter();
        bannerViewPager.setAdapter(bannerAdapter);

        // RecyclerView setup
        trendingRecyclerView = view.findViewById(R.id.trendingRecyclerView);
<<<<<<< HEAD
        trendingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
=======
        trendingRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
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

<<<<<<< HEAD
=======
            // Setup margin cho dot indicator
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
            for (int i = 0; i < bannerIndicator.getTabCount(); i++) {
                TabLayout.Tab tab = bannerIndicator.getTabAt(i);
                if (tab != null) {
                    View tabView = ((ViewGroup) bannerIndicator.getChildAt(0)).getChildAt(i);
<<<<<<< HEAD
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) tabView.getLayoutParams();
=======
                    ViewGroup.MarginLayoutParams params =
                            (ViewGroup.MarginLayoutParams) tabView.getLayoutParams();
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
                    params.setMargins(12, 0, 12, 0);
                    tabView.setLayoutParams(params);
                    tabView.requestLayout();
                }
            }

<<<<<<< HEAD
            // Auto slide mỗi 5 giây
=======
            // Auto slide banner
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
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
<<<<<<< HEAD
=======
    private void toggleSearchBar() {
        if (isSearchVisible) {
            searchEditText.setVisibility(View.GONE);
            showBanner(true);
            isSearchVisible = false;
        } else {
            searchEditText.setVisibility(View.VISIBLE);
            searchEditText.requestFocus();
            isSearchVisible = true;
        }
    }

    private void searchMovies(String keyword) {
        Log.d("SEARCH", "Searching for movie: " + keyword);
        trendingAdapter.filter(keyword);

        if (keyword.isEmpty()) {
            showBanner(true);
        } else {
            showBanner(false);
        }
    }

    private void showBanner(boolean show) {
        bannerViewPager.setVisibility(show ? View.VISIBLE : View.GONE);
        bannerIndicator.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = requireActivity().getCurrentFocus();
        if (view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8

    @Override
    public void onDestroyView() {
        super.onDestroyView();
<<<<<<< HEAD
        sliderHandler.removeCallbacks(sliderRunnable); // Dọn handler tránh memory leak
=======
        sliderHandler.removeCallbacks(sliderRunnable);
>>>>>>> 1cc6dedaea0ceef2fffdf93b90c74e6dde435aa8
    }
}
