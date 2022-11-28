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

        SoundPlayer.destroy();
        //När användaren lämnar DialActivity måste du frigöra resurserna som din
        //SoundPlayer använder genom att anropa destroy-metoden i SoundPlayer. Gör
        //detta anrop i ett lämplig callback-metod i DialActivity livscykel.

    }
}