package com.org.rickandmorty.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.org.rickandmorty.domain.model.Character
import com.org.rickandmorty.domain.model.Location

@Entity(
    tableName = "characters",
    indices = [Index(value = ["id"], unique = true)]
)
data class CharacterEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo
    val image: String? = null,
    @ColumnInfo
    val gender: String? = null,
    @ColumnInfo
    val species: String? = null,
    @ColumnInfo
    val created: String? = null,
    @ColumnInfo
    val origin: String? = null,
    @ColumnInfo
    val originUrl: String? = null,
    @ColumnInfo
    val name: String? = null,
    @ColumnInfo
    val location: String? = null,
    @ColumnInfo
    val locationUrl: String? = null,
    @ColumnInfo
    val episode: String? = null,
    @ColumnInfo
    val type: String? = null,
    @ColumnInfo
    val url: String? = null,
    @ColumnInfo
    val status: String? = null,
) {
    fun toCharacter() = Character(
        id = id,
        url = url.orEmpty(),
        type = type.orEmpty(),
        name = name.orEmpty(),
        image = image.orEmpty(),
        status = status.orEmpty(),
        gender = gender.orEmpty(),
        species = species.orEmpty(),
        created = created.orEmpty(),
        episode = episode?.split(",").orEmpty(),
        origin = Location(name = origin.orEmpty(), url = originUrl.orEmpty()),
        location = Location(name = location.orEmpty(), url = locationUrl.orEmpty()),
    )
}
