package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.data.WishlistManager
import com.example.model.Product
import com.example.ui.components.FleexBottomNav
import com.example.ui.components.NavDestination
import com.example.ui.screens.AccountScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ProductDetailScreen
import com.example.ui.screens.SearchScreen
import com.example.ui.screens.WishlistScreen
import com.example.ui.theme.FleexGarmentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FleexGarmentsTheme {
                FleexWholesaleApp()
            }
        }
    }
}

@Composable
fun FleexWholesaleApp() {
    var currentDestination by remember { mutableStateOf(NavDestination.HOME) }
    var selectedProduct by remember { mutableStateOf<Product?>(null) }
    val wishlistIds by WishlistManager.wishlistIds.collectAsState()

    // Back handling
    BackHandler(enabled = selectedProduct != null || currentDestination != NavDestination.HOME) {
        if (selectedProduct != null) {
            selectedProduct = null
        } else if (currentDestination != NavDestination.HOME) {
            currentDestination = NavDestination.HOME
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            // Only show the bottom navigation when not viewing product details
            if (selectedProduct == null) {
                FleexBottomNav(
                    currentDestination = currentDestination,
                    onNavigate = { destination ->
                        selectedProduct = null
                        currentDestination = destination
                    },
                    wishlistCount = wishlistIds.size
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = if (selectedProduct == null) innerPadding.calculateBottomPadding() else 0.dp)
        ) {
            AnimatedContent(
                targetState = selectedProduct,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "product_detail_transition"
            ) { product ->
                if (product != null) {
                    ProductDetailScreen(
                        product = product,
                        onBackClick = { selectedProduct = null }
                    )
                } else {
                    AnimatedContent(
                        targetState = currentDestination,
                        transitionSpec = { fadeIn() togetherWith fadeOut() },
                        label = "tab_transition"
                    ) { destination ->
                        when (destination) {
                            NavDestination.HOME -> HomeScreen(
                                onProductClick = { selectedProduct = it },
                                onSearchClick = { currentDestination = NavDestination.SEARCH }
                            )
                            NavDestination.SEARCH -> SearchScreen(
                                onProductClick = { selectedProduct = it }
                            )
                            NavDestination.WISHLIST -> WishlistScreen(
                                onProductClick = { selectedProduct = it },
                                onExploreClick = { currentDestination = NavDestination.HOME }
                            )
                            NavDestination.ACCOUNT -> AccountScreen()
                        }
                    }
                }
            }
        }
    }
}
