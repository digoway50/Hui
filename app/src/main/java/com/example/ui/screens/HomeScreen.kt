package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.ProductRepository
import com.example.data.WishlistManager
import com.example.model.Product
import com.example.model.ProductCategory
import com.example.ui.components.FleexBrandHeader
import com.example.ui.components.ProductCard
import com.example.ui.components.ProductLineSection
import com.example.ui.theme.FleexBorder
import com.example.ui.theme.FleexCream
import com.example.ui.theme.FleexCreamDark
import com.example.ui.theme.FleexCreamLight
import com.example.ui.theme.FleexGold
import com.example.ui.theme.FleexPine
import com.example.ui.theme.FleexPineDark
import com.example.ui.theme.FleexPineLight
import com.example.ui.theme.FleexSage
import com.example.ui.theme.FleexSurfaceLight
import com.example.ui.theme.FleexTextPrimary
import com.example.ui.theme.FleexTextSecondary
import com.example.ui.theme.WhatsAppGreen
import com.example.util.WhatsAppOrderHelper

@Composable
fun HomeScreen(
    onProductClick: (Product) -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val wishlistIds by WishlistManager.wishlistIds.collectAsState()
    var selectedCategory by remember { mutableStateOf(ProductCategory.ALL) }

    val featuredProducts = remember { ProductRepository.allProducts.filter { it.isFeatured || it.isBestSeller } }
    val filteredProducts = remember(selectedCategory) {
        ProductRepository.getProductsByCategory(selectedCategory)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(FleexSurfaceLight)
    ) {
        // Sticky Brand Header
        FleexBrandHeader(onSearchClick = onSearchClick)

        // Main Scrollable Body
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            // 1. FULL-SCREEN HERO SECTION
            item {
                FullHeroSection(
                    onExploreClick = { selectedCategory = ProductCategory.ALL },
                    onWhatsAppInquiry = {
                        val message = "Hello Fleex Garments Wholesale Team! I am reviewing your wholesale catalog and would like to request the 2026 digital line-sheet and sample price list in PKR."
                        WhatsAppOrderHelper.launchWhatsApp(context, message)
                    }
                )
            }

            // 2. WHOLESALE TRUST BADGES BAR
            item {
                WholesaleTrustBar()
            }

            // 3. SMOOTH SCROLLING PRODUCT LINE SECTION
            item {
                Spacer(modifier = Modifier.height(24.dp))
                ProductLineSection(
                    products = featuredProducts,
                    onProductClick = onProductClick
                )
            }

            // 4. CATEGORIZED PRODUCT GRID SECTION
            item {
                Spacer(modifier = Modifier.height(32.dp))
                CategoryHeaderSection(
                    selectedCategory = selectedCategory,
                    onSelectCategory = { selectedCategory = it }
                )
            }

            // Categorized Product Grid Items (2 per row)
            val chunkedProducts = filteredProducts.chunked(2)
            items(chunkedProducts) { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp),
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

            // 5. WHOLESALE MILLING & PRIVATE LABEL BANNER
            item {
                Spacer(modifier = Modifier.height(28.dp))
                WholesaleCapabilitiesBanner(
                    onContactManager = {
                        val message = "Hello Fleex Garments! I would like to inquire about custom fabric milling (specific GSM) and private label package requirements."
                        WhatsAppOrderHelper.launchWhatsApp(context, message)
                    }
                )
            }
        }
    }
}

@Composable
fun FullHeroSection(
    onExploreClick: () -> Unit,
    onWhatsAppInquiry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(520.dp)
            .background(FleexPineDark)
    ) {
        // High Fashion Hero Photography
        Image(
            painter = painterResource(id = R.drawable.img_hero_fashion),
            contentDescription = "Fleex Garments Wholesale Lookbook",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Luxury Gradient Overlay using Brand Colors
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            FleexPineDark.copy(alpha = 0.55f),
                            FleexPine.copy(alpha = 0.75f),
                            FleexPineDark.copy(alpha = 0.95f)
                        )
                    )
                )
        )

        // Hero Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            // Season Tag
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(FleexGold)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "NEW 2026 WHOLESALE COLLECTION",
                        color = FleexPineDark,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 1.sp
                    )
                }

                Text(
                    text = "LOW MOQ 50 PCS",
                    color = FleexCream,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Main Tagline
            Text(
                text = "Architectural Basics & Heavyweight Fleece",
                color = FleexCream,
                fontSize = 28.sp,
                lineHeight = 34.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Precision-milled luxury blanks engineered for boutique labels. Custom dyeing, pre-shrunk organic cottons, and seamless factory direct WhatsApp ordering.",
                color = FleexCream.copy(alpha = 0.85f),
                fontSize = 13.sp,
                lineHeight = 19.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Dual CTAs
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = onWhatsAppInquiry,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = WhatsAppGreen,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .weight(1.1f)
                        .height(48.dp)
                        .testTag("hero_whatsapp_btn")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Chat,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Direct Inquiry",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                OutlinedButton(
                    onClick = onExploreClick,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = FleexCream
                    ),
                    border = androidx.compose.foundation.BorderStroke(1.5.dp, FleexGold),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                ) {
                    Text(
                        text = "View Catalog",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = FleexCream
                    )
                }
            }
        }
    }
}

@Composable
fun WholesaleTrustBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(FleexPine)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TrustItem(icon = Icons.Default.Verified, title = "Milled Fabric", subtitle = "280–500 GSM")
        TrustItem(icon = Icons.Default.CheckCircle, title = "Low MOQ", subtitle = "50 Units/Style")
        TrustItem(icon = Icons.Default.Description, title = "Custom Labels", subtitle = "Woven & Print")
        TrustItem(icon = Icons.Default.LocalShipping, title = "Global DDP", subtitle = "Air & Ocean")
    }
}

@Composable
fun TrustItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = FleexGold,
            modifier = Modifier.size(16.dp)
        )
        Column {
            Text(
                text = title,
                color = FleexCream,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle,
                color = FleexCream.copy(alpha = 0.7f),
                fontSize = 9.sp
            )
        }
    }
}

@Composable
fun CategoryHeaderSection(
    selectedCategory: ProductCategory,
    onSelectCategory: (ProductCategory) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column {
                Text(
                    text = "CURATED DIRECTORY",
                    color = FleexSage,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp
                )
                Text(
                    text = "Categorized Products",
                    color = FleexPineDark,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Horizontal Category Tabs
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(ProductCategory.entries) { category ->
                val isSelected = category == selectedCategory
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (isSelected) FleexPine else FleexCreamLight)
                        .border(
                            1.dp,
                            if (isSelected) FleexGold else FleexBorder,
                            RoundedCornerShape(20.dp)
                        )
                        .clickable { onSelectCategory(category) }
                        .padding(horizontal = 14.dp, vertical = 8.dp)
                        .testTag("cat_tab_${category.name}"),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = category.displayName,
                        color = if (isSelected) FleexCream else FleexPineDark,
                        fontSize = 12.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun WholesaleCapabilitiesBanner(
    onContactManager: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, FleexGold.copy(alpha = 0.5f), RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = FleexPineDark)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(FleexGold)
                )
                Text(
                    text = "FLEEX BESPOKE MANUFACTURING",
                    color = FleexGold,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Need Custom GSM or Proprietary Dyeing?",
                color = FleexCream,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "We produce custom loopback weights (300–650 GSM), custom reactive & acid wash treatments, and complete turnkey private labeling for established clothing brands worldwide.",
                color = FleexCream.copy(alpha = 0.8f),
                fontSize = 12.sp,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onContactManager,
                colors = ButtonDefaults.buttonColors(
                    containerColor = WhatsAppGreen,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .testTag("whatsapp_factory_inquiry_btn")
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(imageVector = Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(16.dp))
                    Text(
                        text = "Talk to Production Manager on WhatsApp",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
