package com.example.showhour.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.showhour.model.ShowsModel;
import com.example.showhour.response.ShowsResponse;
import com.example.showhour.network.ApiClient;
import com.example.showhour.network.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowsRepository {

	private ApiService apiService;
	MutableLiveData<ShowsResponse> data = new MutableLiveData<>();
	private List<ShowsModel> showsModelList = new ArrayList<>();

	public ShowsRepository() {
		apiService = ApiClient.getRetrofit().create(ApiService.class);
	}

	public MutableLiveData<ShowsResponse> getShows() {
		return data;
	}

	public void fetchShows(int page) {
		apiService.getShows(page).enqueue(new Callback<ShowsResponse>() {
			@Override
			public void onResponse(@NonNull Call<ShowsResponse> call, @NonNull Response<ShowsResponse> response) {
				for (ShowsModel showsModel : response.body().getTvShows()){
					showsModelList.add(showsModel);
				}
				data.setValue(response.body());
			}

			@Override
			public void onFailure(@NonNull Call<ShowsResponse> call, @NonNull Throwable t) {
				data.setValue(null);
			}
		});
	}

	public List<ShowsModel> getShowsModelList() {
		return showsModelList;
	}

	public void setShowsModelList(List<ShowsModel> showsModelList) {
		this.showsModelList = showsModelList;
	}
}
