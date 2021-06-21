package com.example.showhour.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.showhour.model.EpisodesModel;
import com.example.showhour.model.ShowDetailModel;
import com.example.showhour.repositories.ShowDetailRepository;
import com.example.showhour.response.ShowDetailResponse;

import java.util.List;

public class ShowDetailViewModel extends ViewModel {

	private ShowDetailRepository showDetailRepository;

	public ShowDetailViewModel() {
		showDetailRepository = new ShowDetailRepository();
	}

	public MutableLiveData<ShowDetailResponse> getShowDetail() {
		return showDetailRepository.getShowDetail();
	}

	public void fetchShowDetails(String permalink) {
		showDetailRepository.fetchShowDetails(permalink);
	}

	public int getId() {
		if(showDetailRepository.getShowDetailModel() != null) {
			return showDetailRepository.getShowDetailModel().getId();
		}
		return 0;
	}

	public String getName() {
		if(showDetailRepository.getShowDetailModel() != null) {
			return showDetailRepository.getShowDetailModel().getName();
		}
		return null;
	}

	public String getPermalink() {
		if(showDetailRepository.getShowDetailModel() != null) {
			return showDetailRepository.getShowDetailModel().getPermalink();
		}
		return null;
	}

	public String getUrl() {
		if(showDetailRepository.getShowDetailModel() != null) {
			return showDetailRepository.getShowDetailModel().getUrl();
		}
		return null;
	}

	public String getDescription() {
		if(showDetailRepository.getShowDetailModel() != null) {
			return showDetailRepository.getShowDetailModel().getDescription();
		}
		return null;
	}

	public String getDescription_source() {
		if(showDetailRepository.getShowDetailModel() != null) {
			return showDetailRepository.getShowDetailModel().getDescription_source();
		}
		return null;
	}

	public String getStart_date() {
		if(showDetailRepository.getShowDetailModel() != null) {
			return showDetailRepository.getShowDetailModel().getStart_date();
		}
		return null;
	}

	public String getEnd_date() {
		if(showDetailRepository.getShowDetailModel() != null) {
			return showDetailRepository.getShowDetailModel().getEnd_date();
		}
		return null;
	}

	public String getCountry() {
		if(showDetailRepository.getShowDetailModel() != null) {
			return showDetailRepository.getShowDetailModel().getCountry();
		}
		return null;
	}

	public String getStatus() {
		if(showDetailRepository.getShowDetailModel() != null) {
			return showDetailRepository.getShowDetailModel().getStatus();
		}
		return null;
	}

	public String getRuntime() {
		if(showDetailRepository.getShowDetailModel() != null) {
			return showDetailRepository.getShowDetailModel().getRuntime();
		}
		return null;
	}

	public String getNetwork() {
		if(showDetailRepository.getShowDetailModel() != null) {
			return showDetailRepository.getShowDetailModel().getNetwork();
		}
		return null;
	}

	public String getYoutube_link() {
		if(showDetailRepository.getShowDetailModel() != null) {
			return showDetailRepository.getShowDetailModel().getYoutube_link();
		}
		return null;
	}

	public String getImage_path() {
		if(showDetailRepository.getShowDetailModel() != null) {
			return showDetailRepository.getShowDetailModel().getImage_path();
		}
		return null;
	}

	public String getImage_thumbnail_path() {
		if(showDetailRepository.getShowDetailModel() != null) {
			return showDetailRepository.getShowDetailModel().getImage_thumbnail_path();
		}
		return null;
	}

	public String getRating() {
		if(showDetailRepository.getShowDetailModel() != null) {
			return showDetailRepository.getShowDetailModel().getRating();
		}
		return null;
	}

	public String getRating_count() {
		if(showDetailRepository.getShowDetailModel() != null) {
			return showDetailRepository.getShowDetailModel().getRating_count();
		}
		return null;
	}

	public String getCountdown() {
		if(showDetailRepository.getShowDetailModel() != null) {
			return showDetailRepository.getShowDetailModel().getCountdown();
		}
		return null;
	}

	public String[] getGenres() {
		if(showDetailRepository.getShowDetailModel() != null) {
			return showDetailRepository.getShowDetailModel().getGenres();
		}
		return null;
	}

	public String[] getPictures() {
		if(showDetailRepository.getShowDetailModel() != null) {
			return showDetailRepository.getShowDetailModel().getPictures();
		}
		return null;
	}

	public List<EpisodesModel> getEpisodes() {
		if(showDetailRepository.getShowDetailModel() != null) {
			return showDetailRepository.getShowDetailModel().getEpisodes();
		}
		return null;
	}

	public ShowDetailModel getShowDetailModel() {
		return showDetailRepository.getShowDetailModel();
	}

	public void setShowDetailModel(ShowDetailModel showDetailModel) {
		showDetailRepository.setShowDetailModel(showDetailModel);
	}
}
