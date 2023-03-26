package dadm.aperher.QuotationShake.data.favourites.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dadm.aperher.QuotationShake.data.favourites.FavouritesContract.FavouritesQuotes.COLUMN_ID
import dadm.aperher.QuotationShake.data.favourites.FavouritesContract.FavouritesQuotes.TABLE_NAME
import dadm.aperher.QuotationShake.data.favourites.FavouritesContract.FavouritesQuotes.COLUMN_TEXT
import dadm.aperher.QuotationShake.data.favourites.FavouritesContract.FavouritesQuotes.COLUMN_AUTHOR

@Entity(tableName = TABLE_NAME)
data class QuotationDto(
    @PrimaryKey @ColumnInfo(name = COLUMN_ID) val id: String,
    @ColumnInfo(name = COLUMN_TEXT) val text: String,
    @ColumnInfo(name = COLUMN_AUTHOR) val author: String
)