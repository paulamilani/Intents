package br.edu.ifsp.scl.sdm.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import br.edu.ifsp.scl.sdm.intents.databinding.ActivityActionBinding;

public class ActionActivity extends AppCompatActivity {

    private ActivityActionBinding activityActionBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityActionBinding = ActivityActionBinding.inflate(getLayoutInflater());
        setContentView(activityActionBinding.getRoot());

        activityActionBinding.mainTb.appTb.setTitle("intents");
        activityActionBinding.mainTb.appTb.setSubtitle("intents");
        setSupportActionBar(activityActionBinding.mainTb.appTb);
        activityActionBinding.parameterTv.setText(getIntent().getStringExtra(Intent.EXTRA_TEXT));


    }
}