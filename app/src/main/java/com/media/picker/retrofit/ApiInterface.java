package com.media.picker.retrofit;


import com.media.picker.Profile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
        @GET("profiles") // Replace with your actual API endpoint
        Call<List<Profile>> getProfiles();


}
