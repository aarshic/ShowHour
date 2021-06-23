package com.example.showhour.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.showhour.BR;
import com.example.showhour.R;
import com.example.showhour.databinding.SearchItemBinding;
import com.example.showhour.listeners.ShowsListener;
import com.example.showhour.model.ShowsModel;
import com.example.showhour.viewModel.SearchViewModel;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

	private SearchViewModel searchViewModel;
	private LayoutInflater layoutInflater;
	private List<ShowsModel> searchShowsModelList;
	private ShowsListener showsListener;

	public SearchAdapter(SearchViewModel searchViewModel, ShowsListener showsListener) {
		this.searchViewModel = searchViewModel;
		this.showsListener = showsListener;
	}

	public void setSearchShowsModelList(List<ShowsModel> searchShowsModelList) {
		this.searchShowsModelList = searchShowsModelList;
	}

	@NonNull
	@Override
	public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		if (layoutInflater == null) {
			layoutInflater = LayoutInflater.from(parent.getContext());
		}
		SearchItemBinding searchItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.search_item, parent, false);
		return new SearchViewHolder(searchItemBinding);
	}

	@Override
	public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position) {
		holder.bindingShow(searchViewModel, position);
	}

	@Override
	public int getItemCount() {
		if (searchShowsModelList != null) {
			return searchShowsModelList.size();
		}
		return 0;
	}

	class SearchViewHolder extends RecyclerView.ViewHolder {

		private SearchItemBinding searchItemBinding;

		public SearchViewHolder(SearchItemBinding searchItemBinding) {
			super(searchItemBinding.getRoot());
			this.searchItemBinding = searchItemBinding;
		}

		public void bindingShow(SearchViewModel searchViewModel, Integer position) {
			searchItemBinding.setVariable(BR.searchViewModel, searchViewModel);
			searchItemBinding.setVariable(BR.position, position);
			searchItemBinding.executePendingBindings();
			searchItemBinding.getRoot().setOnClickListener(v ->
					showsListener.onShowClicked(searchViewModel.getSearchShowsModelList().get(position))
			);
		}
	}
}
