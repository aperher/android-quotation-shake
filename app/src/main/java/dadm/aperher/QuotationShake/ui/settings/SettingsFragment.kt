package dadm.aperher.QuotationShake.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import dadm.aperher.QuotationShake.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)
    }
}