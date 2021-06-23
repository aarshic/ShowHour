package com.example.showhour.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.showhour.model.ShowsModel;
import com.example.showhour.network.ApiClient;
import com.example.showhour.network.ApiService;
import com.example.showhour.response.ShowsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRepository {

	private ApiService apiService;
	public MutableLiveData<ShowsResponse> search = new MutableLiveData<>();
	private List<ShowsModel> searchShowsModelList = new ArrayList<>();

	public SearchRepository() {
		apiService = ApiClient.getRetrofit().create(ApiService.class);
	}

	public MutableLiveData<ShowsResponse> searchShow() {
		return search;
	}

	public void fetchSearchResults(String query, int page) {
		apiService.searchShows(query, page).enqueue(new Callback<ShowsResponse>() {
			@Override
			public void onResponse(@NonNull Call<ShowsResponse> call, @NonNull Response<ShowsResponse> response) {
				for (ShowsModel showsModel : response.body().getTvShows()) {
					searchShowsModelList.add(showsModel);
				}
				search.setValue(response.body());
			}

			@Override
			public void onFailure(@NonNull Call<ShowsResponse> call, @NonNull Throwable t) {
				search.setValue(null);
			}
		});
	}

	public List<ShowsModel> getSearchShowsModelList() {
		return searchShowsModelList;
	}

	public void setSearchShowsModelList(List<ShowsModel> searchShowsModel) {
		this.searchShowsModelList = searchShowsModel;
	}
}
