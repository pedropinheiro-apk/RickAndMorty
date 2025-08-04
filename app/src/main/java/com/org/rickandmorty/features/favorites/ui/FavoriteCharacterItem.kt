package com.org.rickandmorty.features.favorites.ui

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.transformations
import coil3.size.Size
import coil3.transform.Transformation
import com.org.rickandmorty.domain.model.Character

@Composable
fun FavoriteCharacterItem(
    character: Character,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    onClick: () -> Unit = {},
    onToggleFavoriteClicked: () -> Unit = { },
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
                    ): Bitmap = input
                }
            ).build(),
    )

    Card(
        modifier = modifier.aspectRatio(2 / 3f),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        onClick = onClick,
    ) {
        Box {
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
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    text = character.status,
                    color = Color.White
                )

                Text(
                    text = character.gender,
                    color = Color.White
                )
            }

            val icon = if (character.isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Filled.FavoriteBorder
            }

            IconButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = onToggleFavoriteClicked,
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.White.copy(alpha = 0.5f)
                )
            ) {
                Icon(
                    contentDescription = null,
                    imageVector = icon,
                    tint = if (character.isFavorite) Color.Red else Color.Black,
                )
            }
        }
    }
}
