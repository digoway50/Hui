package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.FleexBorder
import com.example.ui.theme.FleexCream
import com.example.ui.theme.FleexCreamLight
import com.example.ui.theme.FleexGold
import com.example.ui.theme.FleexPine
import com.example.ui.theme.FleexPineDark
import com.example.ui.theme.FleexSage
import com.example.ui.theme.FleexTextSecondary

enum class NavDestination(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    HOME("home", "Home", Icons.Filled.Home, Icons.Outlined.Home),
    SEARCH("search", "Search", Icons.Filled.Search, Icons.Outlined.Search),
    WISHLIST("wishlist", "Wishlist", Icons.Filled.Bookmark, Icons.Outlined.BookmarkBorder),
    ACCOUNT("account", "Account", Icons.Filled.Business, Icons.Outlined.Business)
}

@Composable
fun FleexBottomNav(
    currentDestination: NavDestination,
    onNavigate: (NavDestination) -> Unit,
    wishlistCount: Int = 0,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        color = FleexPine,
        tonalElevation = 8.dp,
        shadowElevation = 16.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 0.5.dp, color = FleexPineDark)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NavDestination.entries.forEach { destination ->
                    val isSelected = destination == currentDestination
                    val iconColor by animateColorAsState(
                        targetValue = if (isSelected) FleexGold else FleexCream.copy(alpha = 0.65f),
                        label = "nav_icon_color"
                    )
                    val textColor by animateColorAsState(
                        targetValue = if (isSelected) FleexGold else FleexCream.copy(alpha = 0.65f),
                        label = "nav_text_color"
                    )

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { onNavigate(destination) }
                            .padding(vertical = 4.dp)
                            .testTag("nav_${destination.route}"),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            if (destination == NavDestination.WISHLIST && wishlistCount > 0) {
                                BadgedBox(
                                    badge = {
                                        Badge(
                                            containerColor = FleexGold,
                                            contentColor = FleexPineDark
                                        ) {
                                            Text(
                                                text = wishlistCount.toString(),
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (isSelected) destination.selectedIcon else destination.unselectedIcon,
                                        contentDescription = destination.title,
                                        tint = iconColor,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            } else {
                                Icon(
                                    imageVector = if (isSelected) destination.selectedIcon else destination.unselectedIcon,
                                    contentDescription = destination.title,
                                    tint = iconColor,
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(3.dp))

                            Text(
                                text = destination.title,
                                color = textColor,
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                letterSpacing = 0.2.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
