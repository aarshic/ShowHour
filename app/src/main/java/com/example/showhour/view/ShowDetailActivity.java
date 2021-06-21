package com.example.showhour.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.showhour.R;
import com.example.showhour.adapters.ImageSliderAdapter;
import com.example.showhour.databinding.ActivityShowDetailBinding;
import com.example.showhour.viewModel.ShowDetailViewModel;

public class ShowDetailActivity extends AppCompatActivity {

	private ActivityShowDetailBinding activityShowDetailBinding;
	private ShowDetailViewModel showDetailViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	private void init() {
		activityShowDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_show_detail);
		showDetailViewModel = new ViewModelProvider(this).get(ShowDetailViewModel.class);
		getShowDetail();
	}

	private void getShowDetail() {
		int id = getIntent().getIntExtra("id", - 1);
		String permalink = getIntent().getStringExtra("permalink");
		activityShowDetailBinding.setIsLoading(true);
		showDetailViewModel.getShowDetail().observe(this, showDetailResponse -> {
			activityShowDetailBinding.setIsLoading(false);
			if (showDetailViewModel.getShowDetailModel() == null) {
				Dialog dialog = new Dialog(this);
				dialog.setContentView(R.layout.no_detail_dialog);
				dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				Button okBtn = dialog.findViewById(R.id.ok_btn);
				okBtn.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						finish();
					}
				});
				dialog.show();
			} else {
				if (showDetailViewModel.getPictures() != null) {
					loadImageSlider(showDetailViewModel.getPictures());
				}
			}
		});
		showDetailViewModel.fetchShowDetails(permalink);
	}

	private void loadImageSlider(String[] sliderImages) {
		activityShowDetailBinding.sliderViewPager.setOffscreenPageLimit(1);
		activityShowDetailBinding.sliderViewPager.setAdapter(new ImageSliderAdapter(sliderImages));
		activityShowDetailBinding.sliderViewPager.setVisibility(View.VISIBLE);
		activityShowDetailBinding.viewFadingEdge.setVisibility(View.VISIBLE);
		setUpSliderIndicator(sliderImages.length);
		activityShowDetailBinding.sliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
			@Override
			public void onPageSelected(int position) {
				super.onPageSelected(position);
				setCurrentSliderIndicator(position);
			}
		});
	}

	private void setUpSliderIndicator(int count) {
		ImageView[] indicators = new ImageView[count];
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(8, 0, 8, 0);
		for (int i = 0; i < indicators.length; i++) {
			indicators[i] = new ImageView(this);
			indicators[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.background_slider_indicator_inactive));
			indicators[i].setLayoutParams(layoutParams);
			activityShowDetailBinding.layoutSliderIndicator.addView(indicators[i]);
		}
		activityShowDetailBinding.layoutSliderIndicator.setVisibility(View.VISIBLE);
		setCurrentSliderIndicator(0);
	}

	private void setCurrentSliderIndicator(int position) {
		int childCount = activityShowDetailBinding.layoutSliderIndicator.getChildCount();
		for (int i = 0; i < childCount; i++) {
			ImageView imageView = (ImageView) activityShowDetailBinding.layoutSliderIndicator.getChildAt(i);
			if (i == position) {
				imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.background_slider_indicator_active));
			} else {
				imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.background_slider_indicator_inactive));
			}
		}
	}
}
