package se.miun.jasv2000.dt031g.dialer;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CallListActivity extends AppCompatActivity {
    File localDir;
    // adding file-name to the end of path
    File filePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_list);
        localDir = getApplicationContext().getFilesDir();

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


}