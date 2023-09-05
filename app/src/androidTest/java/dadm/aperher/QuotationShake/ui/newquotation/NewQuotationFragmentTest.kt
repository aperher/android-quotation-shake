package dadm.aperher.QuotationShake.ui.newquotation

import androidx.annotation.UiThread
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import dadm.aperher.QuotationShake.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import upv.dadm.quotationshake.Constants
import dadm.aperher.QuotationShake.R

@MediumTest
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@HiltAndroidTest
class NewQuotationFragmentTest {

    @get:Rule(order = 0)
    var hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @UiThread
    @Test
    fun swipeDown_retrieves_new_quotation() {

        val scenario = activityScenarioRule.scenario
        scenario.use {
            onView(withId(R.id.swipeToRefresh)).perform(swipeDown())
            onView(withId(R.id.tvQuotationText)).check(matches(withText(Constants.quotationText)))
            onView(withId(R.id.tvQuotationAuthor)).check(matches(withText(Constants.quotationAuthor)))
        }
    }
}