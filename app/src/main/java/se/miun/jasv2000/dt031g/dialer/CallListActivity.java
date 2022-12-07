package se.miun.jasv2000.dt031g.dialer;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;

public class CallListActivity extends AppCompatActivity {
    File localDir;
    File filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_list);
        localDir = getApplicationContext().getFilesDir();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Call List");
        filePath = new File(localDir + "/" + SettingsActivity.getFilename(this));
        String phoneNumbers = readFile();
        TextView textView = findViewById(R.id.textView_call_list);
        if (phoneNumbers.isEmpty()){
            textView.setText(R.string.phoneNumbersEmpty);
        }
        else{
            textView.setText(phoneNumbers);
        }
    }

    private String readFile()
    {
        String phoneNumbers = "";
        File myExternalFile = new File(String.valueOf(filePath));
        try {
            FileInputStream fis = new FileInputStream(myExternalFile);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String strLine;
            while ((strLine = br.readLine()) != null) {
                phoneNumbers = phoneNumbers + strLine + "\n";
            }
            br.close();
            in.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phoneNumbers;
    }

    /**
     * Override this method to inflate your menu to the activity.
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_call_list, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.deletePhoneNumbers) {
            SettingsActivity.deletePhoneNumbers(this);
            startActivity(new Intent(this, CallListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

}