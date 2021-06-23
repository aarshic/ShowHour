package com.example.showhour.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.example.showhour.R;
import com.example.showhour.databinding.EpisodesItemBinding;
import com.example.showhour.model.EpisodesModel;
import com.example.showhour.viewModel.ShowDetailViewModel;

import java.util.List;

public class EpisodesAdapter extends RecyclerView.Adapter<EpisodesAdapter.EpisodesViewHolder> {

	private List<EpisodesModel> episodesModelList;
	private ShowDetailViewModel showDetailViewModel;
	private LayoutInflater layoutInflater;

	public EpisodesAdapter(ShowDetailViewModel showDetailViewModel, List<EpisodesModel> episodesModelList) {
		this.episodesModelList = episodesModelList;
		this.showDetailViewModel = showDetailViewModel;
	}

	@NonNull
	@Override
	public EpisodesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		if (layoutInflater == null) {
			layoutInflater = LayoutInflater.from(parent.getContext());
		}
		EpisodesItemBinding episodesItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.episodes_item, parent, false);
		return new EpisodesViewHolder(episodesItemBinding);
	}

	@Override
	public void onBindViewHolder(@NonNull EpisodesAdapter.EpisodesViewHolder holder, int position) {
		holder.bindEpisodes(showDetailViewModel, position);
	}

	@Override
	public int getItemCount() {
		return episodesModelList.size();
	}

	static class EpisodesViewHolder extends RecyclerView.ViewHolder {

		private EpisodesItemBinding episodesItemBinding;

		public EpisodesViewHolder(EpisodesItemBinding episodesItemBinding) {
			super(episodesItemBinding.getRoot());
			this.episodesItemBinding = episodesItemBinding;
		}

		private void bindEpisodes(ShowDetailViewModel showDetailViewModel, Integer position) {
			episodesItemBinding.setVariable(BR.showDetailViewModel, showDetailViewModel);
			episodesItemBinding.setVariable(BR.position, position);
			episodesItemBinding.executePendingBindings();
			String title ="S";
			String season = showDetailViewModel.getEpisodeSeason(position);
			if (season.length() == 1) {
				season = "0".concat(season);
			}
			String episodeNumber = showDetailViewModel.getEpisodeDetail(position);
			if (episodeNumber.length() == 1){
				episodeNumber = "0".concat(episodeNumber);
			}
			episodeNumber = "E".concat(episodeNumber);
			title = title.concat(season).concat(episodeNumber);
			episodesItemBinding.episodesHeading.setText(title);
		}
	}

}
