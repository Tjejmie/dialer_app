package se.miun.jasv2000.dt031g.dialer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import se.miun.jasv2000.dt031g.dialer.databinding.ActivityMainBinding;

public class DialActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dial);

    }
}