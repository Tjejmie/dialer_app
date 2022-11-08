package se.miun.jasv2000.dt031g.dialer;


import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import se.miun.jasv2000.dt031g.dialer.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        se.miun.jasv2000.dt031g.dialer.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Onclick listeners for buttons on start-page
        binding.buttonDial.setOnClickListener(v -> startActivity(new Intent(this, DialActivity.class)));
        binding.buttonCallList.setOnClickListener(v -> startActivity(new Intent(this, CallListActivity.class)));
        binding.buttonSettings.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        binding.buttonMap.setOnClickListener(v -> startActivity(new Intent(this, MapsActivity.class)));

        // Create dialog
        binding.buttonAbout.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("About");
            builder.setMessage("This app is supposed to mimic the keypad on a phone. " +
                    "The app will consist of activities to: \r\n" +
                    "• Enter numbers to dial \r\n" +
                    "• See previously dialed numbers \r\n" +
                    "• Change the keypad settings \r\n" +
                    "• Show on a Map where previously calls are dialed from \r\n");
            builder.setCancelable(true);

            builder.setPositiveButton(
                    "OK",
                    (dialog, id) -> dialog.cancel());

            AlertDialog alert = builder.create();
            alert.show();

        });
    }

}