package se.miun.jasv2000.dt031g.dialer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import se.miun.jasv2000.dt031g.dialer.databinding.ActivityMainBinding;

public class DialActivity extends AppCompatActivity implements DefaultLifecycleObserver {
    private ActivityMainBinding binding;

    static EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dial);
        editText = findViewById(R.id.editText);

    }
    public void buttonClickEvent(View v) {
        String phoneNo = editText.getText().toString();
        try {

            switch (v.getId()) {
                case R.id.btndel:
                    if (phoneNo != null && phoneNo.length() > 0) {
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
    public void onDestroy(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onDestroy(owner);

        SoundPlayer.soundPlayerInstance.destroy();


    }
}