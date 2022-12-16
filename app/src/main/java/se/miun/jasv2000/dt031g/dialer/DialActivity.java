package se.miun.jasv2000.dt031g.dialer;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import se.miun.jasv2000.dt031g.dialer.databinding.ActivityMainBinding;

public class DialActivity extends AppCompatActivity implements DefaultLifecycleObserver {


    File localDir;
    File filePath;
    static EditText editText;
    String phoneNo;
    Intent callIntent = new Intent(Intent.ACTION_CALL);
    Intent dialIntent = new Intent(Intent.ACTION_DIAL);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dial);
        editText = findViewById(R.id.editText);
        SoundPlayer.getInstance(getApplicationContext());
        // Declare path to the directory on the filesystem where app-specific files are stored
        localDir = getApplicationContext().getFilesDir();
        // adding file-name to the end of path
        filePath = new File(localDir + "/" + SettingsActivity.getFilename(this));

    }
    public void buttonClickEvent(View v) {
        phoneNo = editText.getText().toString();
        try {

            switch (v.getId()) {
                case R.id.btndel:
                    if (phoneNo.length() > 0) {
                        phoneNo = phoneNo.substring(0, phoneNo.length() - 1);
                    }
                    editText.setText(phoneNo);
                    break;
                case R.id.btncall:

                    if (phoneNo.contains("#")){
                        phoneNo = phoneNo.replace("#","%23");
                    }
                    if (phoneNo.contains("✻")){
                        phoneNo = phoneNo.replace("✻","%2A");
                    }

                    if(SettingsActivity.shouldStoreNumbers(this)){
                        saveNumber();
                    }

                    // If permission for call_phone is not granted
                    if (ActivityCompat.checkSelfPermission(DialActivity.this,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(DialActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    }
                    // If permission for call_phone already is accepted
                    else{
                        callIntent.setData(Uri.parse("tel:"+phoneNo));
                        startActivity(callIntent);
                    }
                    break;
            }
        } catch (Exception ex) {
            Log.e("Exception", "Error with pressing button: " + ex);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {// If permission is accepted
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callIntent.setData(Uri.parse("tel:" + phoneNo));
                startActivity(callIntent);
                // If permission is denied
            } else {
                dialIntent.setData(Uri.parse("tel:" + phoneNo));
                startActivity(dialIntent);
            }
        }
    }


    private void saveNumber() {
            try {
                if(!phoneNumberFileExist()){
                    System.out.println("Creating new file");
                    filePath.createNewFile();
                }
                if(!String.valueOf(editText.getText()).equals("")){
                    FileWriter fileWriter = new FileWriter(filePath, true);
                    fileWriter.append(String.valueOf(editText.getText())).append("\n");
                    fileWriter.close();
                }
            } catch (IOException e) {
                // If an IOException occurs, we just ignore it
                Log.e("Exception", "File write failed: " + e);
            }
    }

    public boolean phoneNumberFileExist() {
        if(filePath.exists()){
            return true;
        }
        return false;
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
        if (item.getItemId() == R.id.menu_download_voices) {

            // DownloadActivity, putExtra to send url and filepath
            Intent i = new Intent(this, DownloadActivity.class);

            i.putExtra(getResources().getString(R.string.URL_KEY), getResources().getString(R.string.download_voices_address));
            i.putExtra(getResources().getString(R.string.FILEPATH_KEY), getResources().getString(R.string.path));
            //Starts activity for settings
            startActivity(i);
            return true;
        }
        // Invoke superclass if the action was not recognized
        return super.onOptionsItemSelected(item);
    }
}
