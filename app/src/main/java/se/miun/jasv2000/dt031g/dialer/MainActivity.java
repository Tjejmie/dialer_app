package se.miun.jasv2000.dt031g.dialer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



import se.miun.jasv2000.dt031g.dialer.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        se.miun.jasv2000.dt031g.dialer.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.buttonDial.setOnClickListener(v -> startActivity(new Intent(this, DialActivity.class)));

        binding.buttonCallList.setOnClickListener(v -> startActivity(new Intent(this, CallListActivity.class)));
        binding.buttonSettings.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        binding.buttonMap.setOnClickListener(v -> startActivity(new Intent(this, MapsActivity.class)));


    }

    //public void openActivityDial() {
        //Button button_dial = (Button) findViewById(R.id.button_dial);
      //  binding.buttonDial.setOnClickListener(v -> {
            //startActivity(new Intent(this, DialActivity.class));

            //Intent intent = new Intent("android.intent.category.DIAL");
            //startActivity(intent);
        //}
        //);
    //}



}