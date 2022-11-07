package se.miun.jasv2000.dt031g.dialer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



import se.miun.jasv2000.dt031g.dialer.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        openActivityDial();



    }

    public void openActivityDial() {
        //Button button_dial = (Button) findViewById(R.id.button_dial);
        binding.buttonDial.setOnClickListener(v -> {
            Intent intent = new Intent("android.intent.category.DIAL");
            startActivity(intent);
        }
        );
    }



}