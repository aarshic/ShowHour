package com.example.showhour.response;

import com.example.showhour.model.ShowDetailModel;
import com.google.gson.annotations.SerializedName;

public class ShowDetailResponse {
	@SerializedName("tvShow")
	private ShowDetailModel tvShowDetail;

	public ShowDetailModel getTvShowDetail() {
		return tvShowDetail;
	}
}
