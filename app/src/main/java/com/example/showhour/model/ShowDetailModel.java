package com.example.showhour.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShowDetailModel {

	@SerializedName("id")
	private int id;

	@SerializedName("name")
	private String name;

	@SerializedName("permalink")
	private String permalink;

	@SerializedName("url")
	private String url;

	@SerializedName("description")
	private String description;

	@SerializedName("description_source")
	private String description_source;

	@SerializedName("start_date")
	private String start_date;

	@SerializedName("end_date")
	private String end_date;

	@SerializedName("country")
	private String country;

	@SerializedName("status")
	private String status;

	@SerializedName("runtime")
	private String runtime;

	@SerializedName("network")
	private String network;

	@SerializedName("youtube_link")
	private String youtube_link;

	@SerializedName("image_path")
	private String image_path;

	@SerializedName("image_thumbnail_path")
	private String image_thumbnail_path;

	@SerializedName("rating")
	private String rating;

	@SerializedName("rating_count")
	private String rating_count;

	@SerializedName("countdown")
	private String countdown;

	@SerializedName("genres")
	private String[] genres;

	@SerializedName("pictures")
	private String[] pictures;

	@SerializedName("episodes")
	private List<EpisodesModel> episodes;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPermalink() {
		return permalink;
	}

	public String getUrl() {
		return url;
	}

	public String getDescription() {
		return description;
	}

	public String getDescription_source() {
		return description_source;
	}

	public String getStart_date() {
		return start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public String getCountry() {
		return country;
	}

	public String getStatus() {
		return status;
	}

	public String getRuntime() {
		return runtime;
	}

	public String getNetwork() {
		return network;
	}

	public String getYoutube_link() {
		return youtube_link;
	}

	public String getImage_path() {
		return image_path;
	}

	public String getImage_thumbnail_path() {
		return image_thumbnail_path;
	}

	public String getRating() {
		return rating;
	}

	public String getRating_count() {
		return rating_count;
	}

	public String getCountdown() {
		return countdown;
	}

	public String[] getGenres() {
		return genres;
	}

	public String[] getPictures() {
		return pictures;
	}

	public List<EpisodesModel> getEpisodes() {
		return episodes;
	}
}
