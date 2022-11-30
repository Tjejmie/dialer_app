package se.miun.jasv2000.dt031g.dialer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import android.os.Bundle;

import se.miun.jasv2000.dt031g.dialer.databinding.ActivityMainBinding;

public class DialActivity extends AppCompatActivity implements DefaultLifecycleObserver {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dial);

    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onDestroy(owner);
        SoundPlayer.destroy();
    }
}