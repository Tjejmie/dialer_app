package se.miun.jasv2000.dt031g.dialer;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.PatternSyntaxException;

public class SettingsActivity extends AppCompatActivity {
    static ArrayList names;
    public static String voiceName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.settings_title));
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

        String path = getResources().getString(R.string.path);
        names = getFileNames(path);

    }


    public static class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            // Handle click on delete preference
            findPreference(getString(R.string.key_delete_phoneNumbers))
                    .setOnPreferenceClickListener(preference -> {
                        // Call method to delete stored numbers
                        return deletePhoneNumbers(getContext());
                    });

            ListPreference listPreferenceCategory = findPreference(getResources().getString(R.string.voice_preference));

            CharSequence[] entries = new String[names.size()];
            CharSequence[] entryValues = new String[names.size()];
            getEntrieValues(entries, entryValues);

            listPreferenceCategory.setEntries(entries);
            listPreferenceCategory.setEntryValues(entryValues);

            listPreferenceCategory.setSummaryProvider(ListPreference.SimpleSummaryProvider.getInstance());
            voiceName = (String) listPreferenceCategory.getEntry();

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(getResources().getString(R.string.key_name),voiceName);
            editor.apply();

        }

        private void getEntrieValues(CharSequence[] entries, CharSequence[] entryValues) {
            int i = 0;
            entryValues[0] = "default";
            for (Object category : names) {
                entries[i] = category.toString();
                entryValues[i] = Integer.toString(i);
                i++;
            }
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            ListPreference listPreferenceCategory = findPreference(getResources().getString(R.string.voice_preference));

            CharSequence[] entries = new String[names.size()];
            CharSequence[] entryValues = new String[names.size()];
            getEntrieValues(entries, entryValues);

            listPreferenceCategory.setEntries(entries);
            listPreferenceCategory.setEntryValues(entryValues);

            voiceName = (String) listPreferenceCategory.getEntry();
            if (key.equals(getResources().getString(R.string.voice_preference)))
            {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(getResources().getString(R.string.key_name),voiceName);
                editor.apply();

            }
        }
        @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
            super.onPause();
        }
    }



    /**
     * Get a list of filenames in this folder.
     * @param folder  Full path of directory

     * @return list of filenames (the names only, not the full path),
     *     or <tt>null</tt> if <tt>folder</tt> doesn't exist or isn't a directory,
     *     or if nothing matches <tt>fileNameFilterPattern</tt>
     * @throws PatternSyntaxException if <tt>fileNameFilterPattern</tt> is non-null and isn't a
     *     valid Java regular expression
     */
    public static ArrayList<String> getFileNames
            (final String folder)
            throws PatternSyntaxException
    {
        ArrayList<String> myData = new ArrayList<String>();
        File fileDir = new File(folder);
        if(!fileDir.exists() || !fileDir.isDirectory()){
            return null;
        }

        String[] files = fileDir.list();

        if(files.length == 0){
            return null;
        }

        myData.addAll(Arrays.asList(files));
        if (myData.size() == 0)
            return null;

        return myData;
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }



}