package com.example.showhour.network;

import com.example.showhour.response.ShowDetailResponse;
import com.example.showhour.response.ShowsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

	@GET("most-popular")
	Call<ShowsResponse> getShows(@Query("page") int page);

	@GET("show-details")
	Call<ShowDetailResponse> getShowDetail(@Query("q") String showId);
}
