package com.media.picker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.media.picker.retrofit.ApiClient;
import com.media.picker.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProfileAdapter profileAdapter;
    private ProgressBar progressBar;
    private List<Profile> profileList = new ArrayList<>();

    public ProfileFragment() {
        // Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progress_bar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        profileAdapter = new ProfileAdapter(profileList, getContext());
        recyclerView.setAdapter(profileAdapter);

        fetchProfiles(); // Call API to fetch profile list

        return view;
    }

    private void fetchProfiles() {
        ApiInterface apiService = ApiClient.getRetrofitInstance(getContext())
                .create(ApiInterface.class);
        Call<List<Profile>> call = apiService.getProfiles();

        call.enqueue(new Callback<List<Profile>>() {
            @Override
            public void onResponse(Call<List<Profile>> call, Response<List<Profile>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    profileList.clear();
                    profileList.addAll(response.body());
                    profileAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Profile>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to load profiles", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

