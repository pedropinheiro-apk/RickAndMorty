package com.org.rickandmorty.features.home.ui

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.palette.graphics.Palette
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.transformations
import coil3.size.Size
import coil3.transform.Transformation
import com.org.rickandmorty.domain.model.Character

@Composable
fun CharacterItem(
    character: Character,
    modifier: Modifier = Modifier,
    textPalette: Int? = null,
    backgroundPalette: Int? = null,
    context: Context = LocalContext.current,
    onPaletteReady: (Long, Int) -> Unit = { _, _ -> },
    onTextPaletteReady: (Long, Int) -> Unit = { _, _ -> },
) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(context)
            .data(character.image)
            .crossfade(true)
            .transformations(
                object : Transformation() {
                    override val cacheKey: String
                        get() = character.id.toString()

                    override suspend fun transform(
                        input: Bitmap,
                        size: Size,
                    ): Bitmap {
                        Palette.from(input).generate { palette ->
                            palette?.dominantSwatch?.let { swatch ->
                                onPaletteReady(character.id, swatch.rgb)
                                onTextPaletteReady(character.id, swatch.bodyTextColor)
                            }
                        }

                        return input
                    }
                }
            ).build(),
    )

    Card(
        modifier = modifier.aspectRatio(2 / 3f),
        colors = CardDefaults.cardColors(
            containerColor = Color(backgroundPalette ?: 0),
        )
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painter,
                contentScale = ContentScale.FillWidth,
                contentDescription = character.name,
            )

            Spacer(Modifier.height(8.dp))

            Text(
                maxLines = 1,
                fontSize = 20.sp,
                text = character.name,
                fontWeight = FontWeight.Bold,
                color = Color(textPalette ?: 0),
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                text = character.status,
                color = Color(textPalette ?: 0)
            )

            Text(
                text = character.gender,
                color = Color(textPalette ?: 0)
            )
        }
    }
}
