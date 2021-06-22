package com.example.showhour.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.showhour.R;
import com.example.showhour.adapters.EpisodesAdapter;
import com.example.showhour.adapters.ImageSliderAdapter;
import com.example.showhour.databinding.ActivityShowDetailBinding;
import com.example.showhour.databinding.EpisodesLayoutBinding;
import com.example.showhour.viewModel.ShowDetailViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Locale;

public class ShowDetailActivity extends AppCompatActivity {

	private ActivityShowDetailBinding activityShowDetailBinding;
	private ShowDetailViewModel showDetailViewModel;
	private BottomSheetDialog episodesBottomSheetDialog;
	private EpisodesLayoutBinding episodesLayoutBinding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	private void init() {
		activityShowDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_show_detail);
		showDetailViewModel = new ViewModelProvider(this).get(ShowDetailViewModel.class);
		showDetailViewModel.init();
		activityShowDetailBinding.setShowDetailViewModel(showDetailViewModel);
		activityShowDetailBinding.backIcon.setOnClickListener(v -> onBackPressed());
		activityShowDetailBinding.showDetailScrollview.setVisibility(View.GONE);
		getShowDetail();
	}

	private void getShowDetail() {
		int id = getIntent().getIntExtra("id", - 1);
		String permalink = getIntent().getStringExtra("permalink");
		activityShowDetailBinding.setIsLoading(true);
		showDetailViewModel.getShowDetail().observe(this, showDetailResponse -> {
			activityShowDetailBinding.setIsLoading(false);
			if (showDetailViewModel.getShowDetailModel() == null) {
				activityShowDetailBinding.backIcon.setVisibility(View.GONE);
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
				activityShowDetailBinding.showDetailScrollview.setVisibility(View.VISIBLE);
				if (showDetailViewModel.getPictures() != null) {
					loadImageSlider(showDetailViewModel.getPictures());
				}
				activityShowDetailBinding.showDetailDescription.setText(String.valueOf(HtmlCompat.fromHtml(showDetailViewModel.getDescription(), HtmlCompat.FROM_HTML_MODE_LEGACY)));
				activityShowDetailBinding.showDetailRatingText.setText(String.format(Locale.getDefault(), "%.2f", Double.parseDouble(showDetailViewModel.getRating())));
				if (showDetailViewModel.getGenres() == null) {
					activityShowDetailBinding.showDetailGenre.setText("N/A");
				}
				activityShowDetailBinding.showDetailReadMore.setOnClickListener(v -> {
					if (activityShowDetailBinding.showDetailReadMore.getText().toString().equals("Read More")) {
						activityShowDetailBinding.showDetailDescription.setMaxLines(Integer.MAX_VALUE);
						activityShowDetailBinding.showDetailDescription.setEllipsize(null);
						activityShowDetailBinding.showDetailReadMore.setText(R.string.read_less);
					} else {
						activityShowDetailBinding.showDetailDescription.setMaxLines(4);
						activityShowDetailBinding.showDetailDescription.setEllipsize(TextUtils.TruncateAt.END);
						activityShowDetailBinding.showDetailReadMore.setText(R.string.read_more);
					}
				});

				activityShowDetailBinding.showDetailWebsiteBtn.setOnClickListener(v1 -> {
					Intent websiteIntent = new Intent(Intent.ACTION_VIEW);
					websiteIntent.setData(Uri.parse(showDetailViewModel.getUrl()));
					startActivity(websiteIntent);
				});

				activityShowDetailBinding.showDetailEpisodesBtn.setOnClickListener(v13 -> {
					if (episodesBottomSheetDialog == null) {
						episodesBottomSheetDialog = new BottomSheetDialog(ShowDetailActivity.this);
						episodesLayoutBinding = DataBindingUtil.inflate(
								LayoutInflater.from(ShowDetailActivity.this), R.layout.episodes_layout,
								ShowDetailActivity.this.findViewById(R.id.episodes_container), false
						);
						episodesBottomSheetDialog.setContentView(episodesLayoutBinding.getRoot());
						episodesLayoutBinding.episodesRecyclerview.setAdapter(new EpisodesAdapter(showDetailViewModel, showDetailViewModel.getEpisodes()));
						episodesLayoutBinding.episodesTitle.setText(String.format("Episodes | %s", showDetailViewModel.getName()));
						episodesLayoutBinding.closeIcon.setOnClickListener(v12 -> episodesBottomSheetDialog.dismiss());
					}

					FrameLayout frameLayout = episodesBottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
					if (frameLayout != null) {
						BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from(frameLayout);
						bottomSheetBehavior.setPeekHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
						bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
					}
					episodesBottomSheetDialog.show();
				});
			}

			activityShowDetailBinding.invalidateAll();
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

	private void setVisibilityOfComponents() {
		activityShowDetailBinding.showImage.setVisibility(View.VISIBLE);
		activityShowDetailBinding.showDetailName.setVisibility(View.VISIBLE);
		activityShowDetailBinding.showDetailNetworkCountry.setVisibility(View.VISIBLE);
		activityShowDetailBinding.showDetailStatus.setVisibility(View.VISIBLE);
		activityShowDetailBinding.showDetailStarted.setVisibility(View.VISIBLE);
		activityShowDetailBinding.showDetailDescription.setVisibility(View.VISIBLE);
		activityShowDetailBinding.showDetailReadMore.setVisibility(View.VISIBLE);
		activityShowDetailBinding.showDetailViewDivider.setVisibility(View.VISIBLE);
		activityShowDetailBinding.showDetailDividerLayout.setVisibility(View.VISIBLE);
		activityShowDetailBinding.showDetailViewDivider2.setVisibility(View.VISIBLE);
		activityShowDetailBinding.showDetailWebsiteBtn.setVisibility(View.VISIBLE);
		activityShowDetailBinding.showDetailEpisodesBtn.setVisibility(View.VISIBLE);
	}
}
