package com.example.showhour.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.example.showhour.R;
import com.example.showhour.adapters.SearchAdapter;
import com.example.showhour.databinding.ActivitySearchBinding;
import com.example.showhour.listeners.ShowsListener;
import com.example.showhour.model.ShowsModel;
import com.example.showhour.viewModel.SearchViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class SearchActivity extends AppCompatActivity implements ShowsListener {

	private ActivitySearchBinding activitySearchBinding;
	private SearchViewModel searchViewModel;
	private SearchAdapter searchAdapter;
	private int currentPage = 1;
	private int totalAvailablePage = 1;
	private Timer timer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	private void init() {
		activitySearchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
		activitySearchBinding.backIcon.setOnClickListener(v -> onBackPressed());
		activitySearchBinding.searchRecyclerview.setHasFixedSize(true);
		searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
		searchAdapter = new SearchAdapter(searchViewModel, this);
		activitySearchBinding.searchRecyclerview.setAdapter(searchAdapter);
		activitySearchBinding.searchInput.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (timer != null) {
					timer.cancel();
				}
			}

			@Override
			public void afterTextChanged(Editable s) {
				if (! s.toString().trim().isEmpty()) {
					timer = new Timer();
					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							new Handler(Looper.getMainLooper()).post(() -> {
								currentPage = 1;
								totalAvailablePage = 1;
								searchShows(s.toString());
							});
						}
					}, 1000);
				} else {
					if (searchViewModel.getSearchShowsModelList() != null) {
						searchViewModel.getSearchShowsModelList().clear();
						searchAdapter.notifyDataSetChanged();
					}
				}
			}
		});
		activitySearchBinding.searchRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				if (! activitySearchBinding.searchInput.getText().toString().isEmpty()) {
					if (currentPage < totalAvailablePage) {
						currentPage += 1;
						searchShows(activitySearchBinding.searchInput.getText().toString());
					}
				}
			}
		});
		activitySearchBinding.searchIcon.setOnClickListener(v -> activitySearchBinding.searchInput.requestFocus());
		activitySearchBinding.searchInput.requestFocus();
	}

	private void searchShows(String query) {
		toggleLoading();
		searchViewModel.getSearchShows().observe(this, showsResponse -> {
			toggleLoading();
			if (showsResponse != null) {
				totalAvailablePage = showsResponse.getTotalPages();
				if (showsResponse.getTvShows() != null) {
					activitySearchBinding.setIsLoadingMore(false);
					searchAdapter.setSearchShowsModelList(searchViewModel.getSearchShowsModelList());
					if (searchViewModel.getSearchShowsModelList().size() == 0) {
						Toast.makeText(SearchActivity.this, "No Results Found", Toast.LENGTH_SHORT).show();
					}
					searchAdapter.notifyDataSetChanged();
				}
			}
			activitySearchBinding.invalidateAll();
		});
		searchViewModel.fetchSearchShows(query, currentPage);
	}

	private void toggleLoading() {
		if (currentPage == 1) {
			if (activitySearchBinding.getIsLoading() != null && activitySearchBinding.getIsLoading()) {
				activitySearchBinding.setIsLoading(false);
			} else {
				activitySearchBinding.setIsLoading(true);
			}
		} else {
			if (activitySearchBinding.getIsLoadingMore() != null && activitySearchBinding.getIsLoadingMore()) {
				activitySearchBinding.setIsLoadingMore(false);
			} else {
				activitySearchBinding.setIsLoadingMore(true);
			}
		}
	}

	@Override
	public void onShowClicked(ShowsModel showsModel) {
		Intent intent = new Intent(this, ShowDetailActivity.class);
		intent.putExtra("id", showsModel.getId());
		startActivity(intent);
	}
}
