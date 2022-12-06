package se.miun.jasv2000.dt031g.dialer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import java.io.File;
import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Settings");
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            // Handle click on delete preference
            findPreference(getString(R.string.key_delete_phoneNumbers))
                    .setOnPreferenceClickListener(preference -> {
                        // Call method in SettingsActivity to reset number of clicks
                        return deletePhoneNumbers(getContext());
                    });

        }
    }
    public static String getFilename(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        return sharedPreferences.getString(context.getString(R.string.pref_filename_key),
                context.getString(R.string.pref_filename_default_value));
    }


    public static boolean shouldStoreNumbers(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.
                getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(
                context.getString(R.string.key_save_phoneNumbers), true); // default true
    }


    public static boolean deletePhoneNumbers(Context context) {
        File localDir = context.getFilesDir();
        File filePath = new File(localDir + "/" + SettingsActivity.getFilename(context));

        File phoneNumersFile = new File(String.valueOf(filePath));
        return phoneNumersFile.delete();
    }


}