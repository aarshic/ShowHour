package com.example.showhour.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.showhour.R;
import com.example.showhour.databinding.SliderImageItemBinding;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder> {

	private String[] sliderImages;
	private LayoutInflater layoutInflater;

	public ImageSliderAdapter(String[] sliderImages) {
		this.sliderImages = sliderImages;
	}

	@NonNull
	@Override
	public ImageSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		if (layoutInflater == null) {
			layoutInflater = LayoutInflater.from(parent.getContext());
		}
		SliderImageItemBinding sliderImageItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.slider_image_item, parent, false);
		return new ImageSliderViewHolder(sliderImageItemBinding);
	}

	@Override
	public void onBindViewHolder(@NonNull ImageSliderAdapter.ImageSliderViewHolder holder, int position) {
		holder.bindSliderImage(sliderImages[position]);
	}

	@Override
	public int getItemCount() {
		return sliderImages.length;
	}

	static class ImageSliderViewHolder extends RecyclerView.ViewHolder {

		private SliderImageItemBinding sliderImageItemBinding;

		public ImageSliderViewHolder(@NonNull SliderImageItemBinding sliderImageItemBinding) {
			super(sliderImageItemBinding.getRoot());
			this.sliderImageItemBinding = sliderImageItemBinding;
		}

		public void bindSliderImage(String imageUrl) {
			sliderImageItemBinding.setImageUrl(imageUrl);
		}
	}

}
