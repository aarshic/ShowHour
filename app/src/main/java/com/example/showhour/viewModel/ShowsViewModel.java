package com.example.showhour.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.showhour.Repositories.ShowsRepository;
import com.example.showhour.Response.ShowsResponse;

public class ShowsViewModel extends ViewModel {

	private ShowsRepository showsRepository;

	public ShowsViewModel() {
		showsRepository = new ShowsRepository();
	}

	public LiveData<ShowsResponse> getShows(int page) {
		return showsRepository.getShows(page);
	}

}



















