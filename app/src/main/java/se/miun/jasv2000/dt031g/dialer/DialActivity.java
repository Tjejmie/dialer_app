package se.miun.jasv2000.dt031g.dialer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import se.miun.jasv2000.dt031g.dialer.databinding.ActivityMainBinding;

public class DialActivity extends AppCompatActivity implements DefaultLifecycleObserver {
    private ActivityMainBinding binding;


    static EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dial);
        editText = findViewById(R.id.editText);
        SoundPlayer.getInstance(getApplicationContext());
    }
    public void buttonClickEvent(View v) {
        String phoneNo = editText.getText().toString();
        try {

            switch (v.getId()) {
                case R.id.btndel:
                    if (phoneNo.length() > 0) {
                        phoneNo = phoneNo.substring(0, phoneNo.length() - 1);
                    }

                    editText.setText(phoneNo);
                    break;
                case R.id.btncall:
                    Intent intent = new Intent(Intent.ACTION_DIAL);

                    if (phoneNo.contains("#")){
                        phoneNo = phoneNo.replace("#","%23");
                    }
                    if (phoneNo.contains("✻")){
                        phoneNo = phoneNo.replace("✻","%2A");
                    }

                    intent.setData(Uri.parse("tel:"+phoneNo));
                    startActivity(intent);

                    break;
            }
        } catch (Exception ex) {

        }
    }

    public static void addTitleToPhoneNumber(String title){
        String phoneNo = editText.getText().toString();
        phoneNo += title;
        editText.setText(phoneNo);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SoundPlayer.getInstance(getApplicationContext()).destroy();
    }


    /**
     * Override this method to inflate your menu to the activity.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dial_menu, menu);
        return true;
    }

    /**
     * Override this method to handle selections of items in the menu.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.menu_settings) {
            //Starts activity for settings
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        // Invoke superclass if the action was not recognized
        return super.onOptionsItemSelected(item);
    }
}