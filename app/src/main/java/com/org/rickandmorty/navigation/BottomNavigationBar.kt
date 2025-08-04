@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)

package com.org.rickandmorty.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FlexibleBottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.org.rickandmorty.R

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    selectedRoute: Routes = Routes.Home,
    scrollBehavior: BottomAppBarScrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior(),
    onHomeClicked: () -> Unit = { },
    onFavoriteClicked: () -> Unit = { },
) {
    FlexibleBottomAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        horizontalArrangement = Arrangement.SpaceEvenly,
        contentPadding = PaddingValues(horizontal = 0.dp),
        content = {
            BottomNavigationItem(
                tint = Color.White,
                icon = Icons.Filled.Person,
                text = stringResource(R.string.home),
                isSelected = selectedRoute == Routes.Home,
                onClick = onHomeClicked
            )

            BottomNavigationItem(
                tint = Color.White,
                icon = Icons.Filled.Star,
                text = stringResource(R.string.favorites),
                isSelected = selectedRoute == Routes.Favorites,
                onClick = onFavoriteClicked
            )
        }
    )
}

@Composable
private fun BottomNavigationItem(
    modifier: Modifier = Modifier,
    text: String = "",
    isSelected: Boolean = false,
    tint: Color = Color.Unspecified,
    icon: ImageVector = Icons.Filled.Person,
    onClick: () -> Unit = { },
) {
    FilledIconButton(
        modifier = modifier
            .size(width = 80.dp, height = 52.dp),
        shape = RoundedCornerShape(12.dp),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = if (isSelected) Color.Black else Color.Gray
        ),
        onClick = onClick,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                tint = tint,
                imageVector = icon,
                contentDescription = null,
            )

            Text(
                text = text,
                color = Color.White,
            )
        }
    }
}
