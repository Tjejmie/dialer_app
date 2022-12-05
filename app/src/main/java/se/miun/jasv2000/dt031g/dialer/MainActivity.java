package se.miun.jasv2000.dt031g.dialer;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.atomic.AtomicBoolean;

import se.miun.jasv2000.dt031g.dialer.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    boolean aboutClicked;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        se.miun.jasv2000.dt031g.dialer.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        // Check if default voice dir already exist in internal app storage,
        // if false, copy all voices to app storage.
        if(!Util.defaultVoiceExist(this)){
            Util.copyDefaultVoiceToInternalStorage(this);
        }


        // Onclick listeners for buttons on start-page
        binding.buttonDial.setOnClickListener(v -> startActivity(new Intent(this, DialActivity.class)));
        binding.buttonCallList.setOnClickListener(v -> startActivity(new Intent(this, CallListActivity.class)));
        binding.buttonSettings.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        binding.buttonMap.setOnClickListener(v -> startActivity(new Intent(this, MapsActivity.class)));


        binding.buttonAbout.setOnClickListener(v -> {
            if(!aboutClicked)
            {
                aboutClicked = true;
                String aboutInfo = getResources().getString(R.string.about_info);
                String aboutHeader = getResources().getString(R.string.about_header);
                String ok = getResources().getString(R.string.ok);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(aboutHeader);
                builder.setMessage(aboutInfo);
                builder.setCancelable(true);
                builder.setPositiveButton(
                        ok,
                        (dialog, id) -> dialog.cancel());

                AlertDialog alert = builder.create();
                alert.show();

            }
            else{
                Snackbar mySnackbar = Snackbar.make(view, R.string.about_snackbar, Snackbar.LENGTH_SHORT);
                mySnackbar.show();
            }
        });


    }



    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putBoolean("key_value", aboutClicked);
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        aboutClicked = savedInstanceState.getBoolean("key_value", false);

    }

}





