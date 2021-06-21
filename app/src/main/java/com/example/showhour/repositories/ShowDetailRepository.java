package com.example.showhour.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.showhour.model.ShowDetailModel;
import com.example.showhour.network.ApiClient;
import com.example.showhour.network.ApiService;
import com.example.showhour.response.ShowDetailResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowDetailRepository {

	private ApiService apiService;
	MutableLiveData<ShowDetailResponse> detailData = new MutableLiveData<>();
	private ShowDetailModel showDetailModel;

	public ShowDetailRepository() {
		apiService = ApiClient.getRetrofit().create(ApiService.class);
	}

	public MutableLiveData<ShowDetailResponse> getShowDetail() {
		return detailData;
	}

	public void fetchShowDetails(int id) {
		apiService.getShowDetail(String.valueOf(id)).enqueue(new Callback<ShowDetailResponse>() {
			@Override
			public void onResponse(@NonNull Call<ShowDetailResponse> call, @NonNull Response<ShowDetailResponse> response) {
				setShowDetailModel(response.body().getTvShowDetail());
				detailData.setValue(response.body());
			}

			@Override
			public void onFailure(@NonNull Call<ShowDetailResponse> call, @NonNull Throwable t) {
				detailData.setValue(null);
			}
		});
	}

	public ShowDetailModel getShowDetailModel() {
		return showDetailModel;
	}

	public void setShowDetailModel(ShowDetailModel showDetailModel) {
		this.showDetailModel = showDetailModel;
	}
}
