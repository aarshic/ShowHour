package com.example.showhour.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.showhour.model.ShowsModel;
import com.example.showhour.repositories.ShowsRepository;
import com.example.showhour.response.ShowsResponse;

import java.util.ArrayList;
import java.util.List;

public class ShowsViewModel extends ViewModel {

	private ShowsRepository showsRepository;
//	private List<ShowsModel> showsModelList;
	private ShowsResponse showsResponse;

	public void init() {
		showsRepository = new ShowsRepository();
//		showsModelList = new ArrayList<>();
		showsResponse = new ShowsResponse();
	}

	public MutableLiveData<ShowsResponse> getShows() {
		return showsRepository.getShows();
	}

	public void fetchShows(int page) {
		showsRepository.fetchShows(page);
	}

	public int getId(Integer index) {
		if (showsRepository.getShowsModelList() != null && index != null && showsRepository.getShowsModelList().size() > index) {
			return showsRepository.getShowsModelList().get(index).getId();
		}
		return 0;
//		return showsModel.getId();
	}

	public String getName(Integer index) {
		if (showsRepository.getShowsModelList() != null && index != null && showsRepository.getShowsModelList().size() > index) {
			return showsRepository.getShowsModelList().get(index).getName();
		}
		return null;
//		return showsModel.getName();
	}

	public String getStart_date(Integer index) {
		if (showsRepository.getShowsModelList() != null && index != null && showsRepository.getShowsModelList().size() > index) {
			return showsRepository.getShowsModelList().get(index).getStart_date();
		}
		return null;
//		return showsModel.getStart_date();
	}

	public String getEnd_date(Integer index) {
		if (showsRepository.getShowsModelList() != null && index != null && showsRepository.getShowsModelList().size() > index) {
			return showsRepository.getShowsModelList().get(index).getEnd_date();
		}
		return null;
//		return showsModel.getEnd_date();
	}

	public String getCountry(Integer index) {
		if (showsRepository.getShowsModelList() != null && index != null && showsRepository.getShowsModelList().size() > index) {
			return showsRepository.getShowsModelList().get(index).getCountry();
		}
		return null;
//		return showsModel.getCountry();
	}

	public String getNetwork(Integer index) {
		if (showsRepository.getShowsModelList() != null && index != null && showsRepository.getShowsModelList().size() > index) {
			return showsRepository.getShowsModelList().get(index).getNetwork();
		}
		return null;
//		return showsModel.getNetwork();
	}

	public String getStatus(Integer index) {
		if (showsRepository.getShowsModelList() != null && index != null && showsRepository.getShowsModelList().size() > index) {
			return showsRepository.getShowsModelList().get(index).getStatus();
		}
		return null;
//		return showsModel.getStatus();
	}

	public String getImage_thumbnail_path(Integer index) {
		if (showsRepository.getShowsModelList() != null && index != null && showsRepository.getShowsModelList().size() > index) {
			return showsRepository.getShowsModelList().get(index).getImage_thumbnail_path();
		}
		return null;
//		return showsModel.getImage_thumbnail_path();
	}

	public List<ShowsModel> getShowsModelList() {
		return showsRepository.getShowsModelList();
	}

	public void setShowsModelList(List<ShowsModel> showsModelList) {
		showsRepository.setShowsModelList(showsModelList);
	}

	public int size() {
		return showsRepository.getShowsModelList().size();
	}
}



















