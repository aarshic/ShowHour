package com.example.showhour.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.showhour.R;
import com.example.showhour.adapters.ShowsAdapter;
import com.example.showhour.databinding.ActivityMainScreenBinding;
import com.example.showhour.viewModel.ShowsViewModel;

public class MainScreenActivity extends AppCompatActivity {

	private ActivityMainScreenBinding activityMainScreenBinding;
	private ShowsViewModel showsViewModel;
	private ShowsAdapter showsAdapter;
	private int currentPage = 1;
	private int totalAvailablePages = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	public void init() {
		activityMainScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_screen);
		activityMainScreenBinding.showsRecyclerview.setHasFixedSize(true);
		showsViewModel = new ViewModelProvider(this).get(ShowsViewModel.class);
		showsViewModel.init();
		showsAdapter = new ShowsAdapter(showsViewModel);
		activityMainScreenBinding.showsRecyclerview.setAdapter(showsAdapter);
		activityMainScreenBinding.showsRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				if (!activityMainScreenBinding.showsRecyclerview.canScrollVertically(1)) {
					if(currentPage <= totalAvailablePages) {
						currentPage += 1;
						getShows();
					}
				}
			}
		});
		getShows();
	}

	private void getShows() {
		toggleLoading();
		showsViewModel.getShows().observe(this, showsResponse -> {
			toggleLoading();
			if (showsResponse != null) {
				totalAvailablePages = showsResponse.getTotalPages();
				if (showsResponse.getTvShows() != null) {
					activityMainScreenBinding.setIsLoadingMore(false);
					showsAdapter.setShowsModelsList(showsViewModel.getShowsModelList());
					showsAdapter.notifyDataSetChanged();
				}
			}
			activityMainScreenBinding.invalidateAll();
		});
		showsViewModel.fetchShows(currentPage);
	}

	private void toggleLoading() {
		if (currentPage == 1) {
			if (activityMainScreenBinding.getIsLoading() != null && activityMainScreenBinding.getIsLoading()) {
				activityMainScreenBinding.setIsLoading(false);
			} else {
				activityMainScreenBinding.setIsLoading(true);
			}
		} else {
			if (activityMainScreenBinding.getIsLoadingMore() != null && activityMainScreenBinding.getIsLoadingMore()) {
				activityMainScreenBinding.setIsLoadingMore(false);
			} else {
				activityMainScreenBinding.setIsLoadingMore(true);
			}
		}
	}
}



































