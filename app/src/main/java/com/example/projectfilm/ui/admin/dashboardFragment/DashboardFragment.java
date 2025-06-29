package com.example.projectfilm.ui.admin.dashboardFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectfilm.R;
import com.example.projectfilm.ui.admin.user.UserManageFragment;
import com.example.projectfilm.ui.admin.movie.MovieManage;

public class DashboardFragment extends Fragment {

    private Button btnManageMovies, btnManageUsers;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.admin_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize buttons
        btnManageMovies = view.findViewById(R.id.btn_manage_movies);
        btnManageUsers = view.findViewById(R.id.btn_manage_users);

        if (btnManageMovies == null) {
            Log.e("DashboardFragment", "btn_manage_movies is NULL! Check admin_dashboard.xml ID.");
        }
        if (btnManageUsers == null) {
            Log.e("DashboardFragment", "btn_manage_users is NULL! Check admin_dashboard.xml ID.");
        } else {
            Log.d("DashboardFragment", "btn_manage_users found successfully.");
        }

        // ✅ Mở Activity quản lý phim bằng Intent
        btnManageMovies.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), MovieManage.class);
            startActivity(intent);
        });

        // ✅ Mở Fragment quản lý người dùng như cũ
        btnManageUsers.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.admin_fragment_container, new UserManageFragment())
                    .addToBackStack(null)
                    .commit();
        });
    }
}
