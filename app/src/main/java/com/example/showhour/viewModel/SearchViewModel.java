package com.example.showhour.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.showhour.model.ShowsModel;
import com.example.showhour.repositories.SearchRepository;
import com.example.showhour.response.ShowsResponse;

import java.util.List;

public class SearchViewModel extends ViewModel {

	private SearchRepository searchRepository;

	public SearchViewModel() {
		searchRepository = new SearchRepository();
	}

	public MutableLiveData<ShowsResponse> getSearchShows() {
		return searchRepository.searchShow();
	}

	public void fetchSearchShows(String query, int page) {
		searchRepository.fetchSearchResults(query, page);
	}

	public int getId(Integer index) {
		if (searchRepository.getSearchShowsModelList() != null && index != null && searchRepository.getSearchShowsModelList().size() > index) {
			return searchRepository.getSearchShowsModelList().get(index).getId();
		}
		return 0;
	}

	public String getName(Integer index) {
		if (searchRepository.getSearchShowsModelList() != null && index != null && searchRepository.getSearchShowsModelList().size() > index) {
			return searchRepository.getSearchShowsModelList().get(index).getName();
		}
		return null;
	}

	public String getPermalink(Integer index) {
		if (searchRepository.getSearchShowsModelList() != null && index != null && searchRepository.getSearchShowsModelList().size() > index) {
			return searchRepository.getSearchShowsModelList().get(index).getPermalink();
		}
		return null;
	}

	public String getStart_date(Integer index) {
		if (searchRepository.getSearchShowsModelList() != null && index != null && searchRepository.getSearchShowsModelList().size() > index) {
			return searchRepository.getSearchShowsModelList().get(index).getStart_date();
		}
		return null;
	}

	public String getEnd_date(Integer index) {
		if (searchRepository.getSearchShowsModelList() != null && index != null && searchRepository.getSearchShowsModelList().size() > index) {
			return searchRepository.getSearchShowsModelList().get(index).getEnd_date();
		}
		return null;
	}

	public String getCountry(Integer index) {
		if (searchRepository.getSearchShowsModelList() != null && index != null && searchRepository.getSearchShowsModelList().size() > index) {
			return searchRepository.getSearchShowsModelList().get(index).getCountry();
		}
		return null;
	}

	public String getNetwork(Integer index) {
		if (searchRepository.getSearchShowsModelList() != null && index != null && searchRepository.getSearchShowsModelList().size() > index) {
			return searchRepository.getSearchShowsModelList().get(index).getNetwork();
		}
		return null;
	}

	public String getStatus(Integer index) {
		if (searchRepository.getSearchShowsModelList() != null && index != null && searchRepository.getSearchShowsModelList().size() > index) {
			return searchRepository.getSearchShowsModelList().get(index).getStatus();
		}
		return null;
	}

	public String getImage_thumbnail_path(Integer index) {
		if (searchRepository.getSearchShowsModelList() != null && index != null && searchRepository.getSearchShowsModelList().size() > index) {
			return searchRepository.getSearchShowsModelList().get(index).getImage_thumbnail_path();
		}
		return null;
	}

	public List<ShowsModel> getSearchShowsModelList() {
		return searchRepository.getSearchShowsModelList();
	}

	public void setSearchShowsModelList(List<ShowsModel> showsModelList) {
		searchRepository.setSearchShowsModelList(showsModelList);
	}
}
