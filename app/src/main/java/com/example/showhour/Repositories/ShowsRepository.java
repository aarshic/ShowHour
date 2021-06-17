package com.example.showhour.Repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.showhour.Response.ShowsResponse;
import com.example.showhour.network.ApiClient;
import com.example.showhour.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowsRepository {

	private ApiService apiService;

	public ShowsRepository() {
		apiService = ApiClient.getRetrofit().create(ApiService.class);
	}

	public LiveData<ShowsResponse> getShows(int page) {
		MutableLiveData<ShowsResponse> data = new MutableLiveData<>();
		apiService.getShows(page).enqueue(new Callback<ShowsResponse>() {
			@Override
			public void onResponse(@NonNull Call<ShowsResponse> call, @NonNull Response<ShowsResponse> response) {
				data.setValue(response.body());
			}

			@Override
			public void onFailure(@NonNull Call<ShowsResponse> call, @NonNull Throwable t) {
				data.setValue(null);
			}
		});
		return data;
	}
}
