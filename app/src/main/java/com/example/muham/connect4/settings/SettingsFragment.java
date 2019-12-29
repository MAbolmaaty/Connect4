package com.example.muham.connect4.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.SwitchPreferenceCompat;

import com.example.muham.connect4.R;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.game_preference);

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();

        Preference preference = preferenceScreen.getPreference(0);
        String value = sharedPreferences.getString(getString(R.string.pref_board_color_key),
                getString(R.string.pref_color_blue_value));

        setPreferenceSummary(preference, value);

    }

    private void setPreferenceSummary(Preference preference, String value){
        ListPreference listPreference = (ListPreference) preference;
        int colorIndex = listPreference.findIndexOfValue(value);
        listPreference.setSummary(listPreference.getEntries()[colorIndex]);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        String value = sharedPreferences.getString(key, getString(R.string.pref_color_blue_value));
        setPreferenceSummary(preference, value);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}
