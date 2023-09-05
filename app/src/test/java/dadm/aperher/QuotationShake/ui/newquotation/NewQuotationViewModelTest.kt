package dadm.aperher.QuotationShake.ui.newquotation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import dadm.aperher.QuotationShake.data.favourites.FakeFavouritesRepository
import dadm.aperher.QuotationShake.domain.model.Quotation
import upv.dadm.quotationshake.data.newquotation.FakeNewQuotationManager
import upv.dadm.quotationshake.data.settings.FakeSettingsRepository
import dadm.aperher.QuotationShake.getOrAwaitValue

@Config(sdk = [30]) // https://github.com/robolectric/robolectric/pull/6776
@RunWith(AndroidJUnit4::class)
class NewQuotationViewModelTest {

    private lateinit var favouritesRepository: FakeFavouritesRepository
    private lateinit var newQuotationManager: FakeNewQuotationManager
    private lateinit var settingsRepository: FakeSettingsRepository
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testQuotation = Quotation(id = "testQ", text = "quotation text", author = "testAuthor")

    @Before
    fun setUp() {
        newQuotationManager = FakeNewQuotationManager()
        favouritesRepository = FakeFavouritesRepository()
        settingsRepository = FakeSettingsRepository()
    }

    @Test
    fun getNewQuotation_providesOneQuotation() {
        // Given
        val newQuotationViewModel = NewQuotationViewModel(
            settingsRepository = settingsRepository,
            newQuotationManager = newQuotationManager,
            favouritesRepository = favouritesRepository
        )
        newQuotationManager.addQuotation(testQuotation)

        // When
        newQuotationViewModel.getNewQuotation()

        // Then
        val value = newQuotationViewModel.quotation.getOrAwaitValue()

        assertEquals(testQuotation.id, value.id)
    }

    @Test
    fun getNewQuotation_throws_error_when_fails() {
        // Given
        val newQuotationViewModel = NewQuotationViewModel(
            settingsRepository = settingsRepository,
            newQuotationManager = newQuotationManager,
            favouritesRepository = favouritesRepository
        )

        // When
        newQuotationViewModel.getNewQuotation()

        //Then
        val value = newQuotationViewModel.error.getOrAwaitValue()

        assertNotNull(value)
        assertTrue(value is Throwable)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun addToFavourite_stores_quotation_in_favouritesRepository() = runTest {
        // Given
        val newQuotationViewModel = NewQuotationViewModel(
            settingsRepository = settingsRepository,
            newQuotationManager = newQuotationManager,
            favouritesRepository = favouritesRepository
        )
        newQuotationManager.addQuotation(testQuotation)
        newQuotationViewModel.getNewQuotation()

        // When
        newQuotationViewModel.addToFavourite()

        // Then
        val favouriteQuotation = favouritesRepository.getAllQuotes().first().first()

        assertEquals(testQuotation.id, favouriteQuotation.id)
    }
}