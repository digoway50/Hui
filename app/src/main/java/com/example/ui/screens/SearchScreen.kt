package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ProductRepository
import com.example.data.WishlistManager
import com.example.model.Product
import com.example.model.ProductCategory
import com.example.ui.components.ProductCard
import com.example.ui.theme.FleexBorder
import com.example.ui.theme.FleexCream
import com.example.ui.theme.FleexCreamLight
import com.example.ui.theme.FleexGold
import com.example.ui.theme.FleexPine
import com.example.ui.theme.FleexPineDark
import com.example.ui.theme.FleexPineLight
import com.example.ui.theme.FleexSage
import com.example.ui.theme.FleexSurfaceLight
import com.example.ui.theme.FleexTextPrimary
import com.example.ui.theme.FleexTextSecondary

enum class GsmFilter(val label: String) {
    ALL("All GSM"),
    HEAVY("400+ GSM"),
    MID("250–399 GSM"),
    LIGHT("< 250 GSM")
}

@Composable
fun SearchScreen(
    onProductClick: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(ProductCategory.ALL) }
    var selectedGsmFilter by remember { mutableStateOf(GsmFilter.ALL) }

    val wishlistIds by WishlistManager.wishlistIds.collectAsState()

    val filteredProducts = remember(searchQuery, selectedCategory, selectedGsmFilter) {
        ProductRepository.allProducts.filter { product ->
            val matchesQuery = searchQuery.isBlank() ||
                    product.name.contains(searchQuery, ignoreCase = true) ||
                    product.composition.contains(searchQuery, ignoreCase = true) ||
                    product.sku.contains(searchQuery, ignoreCase = true) ||
                    product.description.contains(searchQuery, ignoreCase = true)

            val matchesCategory = selectedCategory == ProductCategory.ALL || product.category == selectedCategory

            val matchesGsm = when (selectedGsmFilter) {
                GsmFilter.ALL -> true
                GsmFilter.HEAVY -> product.gsm >= 400
                GsmFilter.MID -> product.gsm in 250..399
                GsmFilter.LIGHT -> product.gsm < 250
            }

            matchesQuery && matchesCategory && matchesGsm
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(FleexSurfaceLight)
    ) {
        // Search Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(FleexPine)
                .statusBarsPadding()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Column {
                Text(
                    text = "WHOLESALE CATALOG SEARCH",
                    color = FleexGold,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Find Garments & Blanks",
                    color = FleexCream,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
                Spacer(modifier = Modifier.height(12.dp))

                // Search Bar Input
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search by silhouette, GSM, yarn...", color = FleexCream.copy(alpha = 0.6f), fontSize = 13.sp) },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = FleexGold)
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(imageVector = Icons.Default.Close, contentDescription = "Clear", tint = FleexCream)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .testTag("search_input_field"),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = FleexCream,
                        unfocusedTextColor = FleexCream,
                        focusedContainerColor = FleexPineDark,
                        unfocusedContainerColor = FleexPineDark,
                        focusedBorderColor = FleexGold,
                        unfocusedBorderColor = FleexPineLight
                    ),
                    singleLine = true
                )
            }
        }

        // Filters Scrollable Rows
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(FleexCreamLight)
                .padding(vertical = 10.dp)
        ) {
            // Category Chips
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(ProductCategory.entries) { cat ->
                    val isSelected = cat == selectedCategory
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (isSelected) FleexPine else FleexSurfaceLight)
                            .border(1.dp, if (isSelected) FleexGold else FleexBorder, RoundedCornerShape(16.dp))
                            .clickable { selectedCategory = cat }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = cat.displayName,
                            color = if (isSelected) FleexCream else FleexPineDark,
                            fontSize = 11.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // GSM Weight Chips
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(GsmFilter.entries) { gsm ->
                    val isSelected = gsm == selectedGsmFilter
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (isSelected) FleexSage else FleexSurfaceLight)
                            .border(1.dp, if (isSelected) FleexGold else FleexBorder, RoundedCornerShape(16.dp))
                            .clickable { selectedGsmFilter = gsm }
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = gsm.label,
                            color = if (isSelected) Color.White else FleexPineDark,
                            fontSize = 10.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
        }

        // Results Count Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${filteredProducts.size} styles available",
                color = FleexTextSecondary,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )

            if (searchQuery.isNotEmpty() || selectedCategory != ProductCategory.ALL || selectedGsmFilter != GsmFilter.ALL) {
                Text(
                    text = "Reset Filters",
                    color = FleexSage,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        searchQuery = ""
                        selectedCategory = ProductCategory.ALL
                        selectedGsmFilter = GsmFilter.ALL
                    }
                )
            }
        }

        // Results Grid
        if (filteredProducts.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(FleexCreamLight),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = FleexSage,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "No matching wholesale styles",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = FleexPineDark
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Try adjusting your GSM weight or category filters.",
                        fontSize = 12.sp,
                        color = FleexTextSecondary
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
            ) {
                val chunked = filteredProducts.chunked(2)
                items(chunked) { rowItems ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        rowItems.forEach { product ->
                            Box(modifier = Modifier.weight(1f)) {
                                ProductCard(
                                    product = product,
                                    isWishlisted = wishlistIds.contains(product.id),
                                    onWishlistToggle = { WishlistManager.toggleWishlist(product.id) },
                                    onClick = { onProductClick(product) }
                                )
                            }
                        }
                        if (rowItems.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}
