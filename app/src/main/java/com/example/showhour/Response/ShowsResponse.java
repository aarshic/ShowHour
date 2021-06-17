package com.example.showhour.Response;

import com.example.showhour.model.ShowsModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShowsResponse {

	@SerializedName("page")
	private int page;

	@SerializedName("pages")
	private int pages;

	@SerializedName("tv_shows")
	private List<ShowsModel> tv_shows;

	public int getPage() {
		return page;
	}

	public int getTotalPages() {
		return pages;
	}

	public List<ShowsModel> getTv_shows() {
		return tv_shows;
	}
}
