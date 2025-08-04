package com.org.rickandmorty.features.characterDetails

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
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.transformations
import coil3.size.Size
import coil3.transform.Transformation
import com.org.rickandmorty.R
import com.org.rickandmorty.domain.model.Character

@Composable
fun CharacterDetailsRoute(
    modifier: Modifier = Modifier,
    textPalette: Int? = null,
    character: Character? = null,
    backgroundPalette: Int? = null,
    onDismissRequest: () -> Unit = {},
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        CharacterDetailsScreen(
            modifier = modifier,
            character = character,
            textPalette = textPalette,
            backgroundPalette = backgroundPalette,
        )
    }
}

@Composable
fun CharacterDetailsScreen(
    modifier: Modifier = Modifier,
    textPalette: Int? = null,
    character: Character? = null,
    backgroundPalette: Int? = null,
    context: Context = LocalContext.current,
) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(context)
            .data(character?.image)
            .crossfade(true)
            .transformations(
                object : Transformation() {
                    override val cacheKey: String
                        get() = character?.id.toString()

                    override suspend fun transform(
                        input: Bitmap,
                        size: Size,
                    ): Bitmap = input
                }
            ).build(),
    )

    Box(modifier = modifier) {
        Card(
            modifier = Modifier.aspectRatio(2 / 3f),
            colors = CardDefaults.cardColors(
                containerColor = Color(backgroundPalette ?: 0),
            )
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painter,
                    contentScale = ContentScale.FillWidth,
                    contentDescription = character?.name,
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    maxLines = 1,
                    fontSize = 20.sp,
                    text = character?.name.orEmpty(),
                    fontWeight = FontWeight.Bold,
                    color = Color(textPalette ?: 0),
                    overflow = TextOverflow.Ellipsis,
                )

                val detailsTextStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(textPalette ?: 0),
                )

                Text(
                    modifier = Modifier.offset(x = 8.dp),
                    style = detailsTextStyle,
                    text = stringResource(R.string.status, character?.status.orEmpty()),
                )

                Text(
                    modifier = Modifier.offset(x = 8.dp),
                    style = detailsTextStyle,
                    text = stringResource(R.string.gender, character?.gender.orEmpty()),
                )

                Text(
                    modifier = Modifier.offset(x = 8.dp),
                    style = detailsTextStyle,
                    text = stringResource(R.string.origin, character?.origin?.name.orEmpty()),
                )

                Text(
                    modifier = Modifier.offset(x = 8.dp),
                    style = detailsTextStyle,
                    text = stringResource(
                        R.string.current_location,
                        character?.location?.name.orEmpty()
                    )
                )
            }
        }
    }
}
