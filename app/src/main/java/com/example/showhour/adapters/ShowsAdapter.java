package com.example.showhour.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.showhour.BR;
import com.example.showhour.R;
import com.example.showhour.databinding.ShowsItemBinding;
import com.example.showhour.listeners.ShowsListener;
import com.example.showhour.model.ShowsModel;
import com.example.showhour.viewModel.ShowsViewModel;

import java.util.List;

public class ShowsAdapter extends RecyclerView.Adapter<ShowsAdapter.ShowsViewHolder> {

	private ShowsViewModel showsViewModels;
	private LayoutInflater layoutInflater;
	private List<ShowsModel> showsModelsList;
	private ShowsListener showsListener;

	public ShowsAdapter(ShowsViewModel showsViewModel, ShowsListener showsListener) {
		this.showsViewModels = showsViewModel;
		this.showsListener = showsListener;
	}

	public void setShowsModelsList(List<ShowsModel> showsModelsList) {
		this.showsModelsList = showsModelsList;
	}

	@NonNull
	@Override
	public ShowsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		if (layoutInflater == null) {
			layoutInflater = LayoutInflater.from(parent.getContext());
		}
		ShowsItemBinding showsItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.shows_item, parent, false);
		return new ShowsViewHolder(showsItemBinding);
	}

	@Override
	public void onBindViewHolder(@NonNull ShowsAdapter.ShowsViewHolder holder, int position) {
		holder.bindingShow(showsViewModels, position);
	}

	@Override
	public int getItemCount() {
		if (showsModelsList != null) {
			return showsModelsList.size();
		}
		return 0;
	}

	class ShowsViewHolder extends RecyclerView.ViewHolder {

		private ShowsItemBinding showsItemBinding;

		public ShowsViewHolder(ShowsItemBinding showsItemBinding) {
			super(showsItemBinding.getRoot());
			this.showsItemBinding = showsItemBinding;
		}

		public void bindingShow(ShowsViewModel showsViewModel, Integer position) {
			showsItemBinding.setVariable(BR.showsViewModel, showsViewModel);
			showsItemBinding.setVariable(BR.position, position);
			showsItemBinding.executePendingBindings();
			showsItemBinding.getRoot().setOnClickListener(v ->
					showsListener.onShowClicked(showsViewModel.getShowsModelList().get(position))
			);
		}
	}
}

















