package com.example.showhour.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.example.showhour.R;
import com.example.showhour.Response.ShowsResponse;
import com.example.showhour.viewModel.ShowsViewModel;

public class MainScreenActivity extends AppCompatActivity {

    private ShowsViewModel showsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        showsViewModel = new ViewModelProvider(this).get(ShowsViewModel.class);
    }

}



































