package com.example.ui.screens

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.WishlistManager
import com.example.model.Product
import com.example.ui.components.WhatsAppOrderSheet
import com.example.ui.theme.FleexBorder
import com.example.ui.theme.FleexCream
import com.example.ui.theme.FleexCreamDark
import com.example.ui.theme.FleexCreamLight
import com.example.ui.theme.FleexGold
import com.example.ui.theme.FleexPine
import com.example.ui.theme.FleexPineDark
import com.example.ui.theme.FleexSage
import com.example.ui.theme.FleexSurfaceLight
import com.example.ui.theme.FleexTextPrimary
import com.example.ui.theme.FleexTextSecondary
import com.example.ui.theme.WhatsAppGreen
import com.example.util.WhatsAppOrderHelper
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductDetailScreen(
    product: Product,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val wishlistIds by WishlistManager.wishlistIds.collectAsState()
    val isWishlisted = wishlistIds.contains(product.id)

    var selectedImageIndex by remember { mutableIntStateOf(0) }
    var selectedColor by remember { mutableStateOf(product.colors.first().name) }
    var selectedSize by remember { mutableStateOf(product.sizes.firstOrNull() ?: "M") }
    var sizePackMode by remember { mutableStateOf("Prepack Ratio (${product.defaultPackRatio})") }
    var orderQuantity by remember { mutableIntStateOf(product.moq) }
    var showOrderConfigSheet by remember { mutableStateOf(false) }
    var specsExpanded by remember { mutableStateOf(true) }

    val images = remember(product) {
        listOfNotNull(product.imageRes, product.secondaryImageRes)
    }

    val currentUnitPrice = product.getTierPrice(orderQuantity)
    val estimatedTotal = currentUnitPrice * orderQuantity

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(FleexSurfaceLight)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Sticky Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(FleexPine)
                    .statusBarsPadding()
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.testTag("detail_back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = FleexCream
                    )
                }

                Text(
                    text = product.sku,
                    color = FleexGold,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )

                Row {
                    IconButton(
                        onClick = {
                            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_SUBJECT, "Fleex Garments Wholesale: ${product.name}")
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "Check out ${product.name} (${product.gsm} GSM) on Fleex Garments Wholesale (fleexgarments.com). MOQ: ${product.moq} pcs."
                                )
                            }
                            context.startActivity(Intent.createChooser(shareIntent, "Share Product"))
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = FleexCream
                        )
                    }

                    IconButton(
                        onClick = { WishlistManager.toggleWishlist(product.id) },
                        modifier = Modifier.testTag("detail_wishlist_toggle")
                    ) {
                        Icon(
                            imageVector = if (isWishlisted) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                            contentDescription = "Save to Wishlist",
                            tint = if (isWishlisted) FleexGold else FleexCream
                        )
                    }
                }
            }

            // Scrollable Content
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(bottom = 120.dp)
            ) {
                // 1. PRODUCT IMAGE GALLERY
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1.05f)
                            .background(FleexCreamLight)
                    ) {
                        Image(
                            painter = painterResource(id = images[selectedImageIndex.coerceIn(0, images.size - 1)]),
                            contentDescription = product.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        // Top Badges
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(FleexPineDark)
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "${product.gsm} GSM WEIGHT",
                                    color = FleexCream,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 0.5.sp
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(FleexGold)
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "MOQ: ${product.moq} UNITS",
                                    color = FleexPineDark,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                        }

                        // Thumbnail dots if multiple images
                        if (images.size > 1) {
                            Row(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 12.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                images.indices.forEach { idx ->
                                    val isCurrent = idx == selectedImageIndex
                                    Box(
                                        modifier = Modifier
                                            .size(if (isCurrent) 22.dp else 8.dp, 8.dp)
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(if (isCurrent) FleexGold else Color.White.copy(alpha = 0.7f))
                                            .clickable { selectedImageIndex = idx }
                                    )
                                }
                            }
                        }
                    }
                }

                // 2. PRODUCT HEADLINE & DESCRIPTION
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(
                            text = product.category.displayName.uppercase(),
                            color = FleexSage,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.5.sp
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = product.name,
                            color = FleexPineDark,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = product.tagline,
                            color = FleexTextSecondary,
                            fontSize = 13.sp,
                            lineHeight = 18.sp
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Specs pills
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            SpecChip(label = "Fit", value = product.fit)
                            SpecChip(label = "Material", value = product.composition)
                            SpecChip(label = "Lead Time", value = product.leadTime)
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = product.description,
                            color = FleexTextPrimary,
                            fontSize = 14.sp,
                            lineHeight = 22.sp
                        )
                    }
                }

                // 3. WHOLESALE TIERED PRICING MATRIX
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Text(
                            text = "TIERED WHOLESALE PRICING",
                            color = FleexPine,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, FleexBorder, RoundedCornerShape(12.dp)),
                            colors = CardDefaults.cardColors(containerColor = FleexCreamLight)
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("VOLUME TIER", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = FleexTextSecondary)
                                    Text("UNIT PRICE (PKR)", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = FleexTextSecondary)
                                }

                                Spacer(modifier = Modifier.height(8.dp))
                                HorizontalDivider(color = FleexBorder)
                                Spacer(modifier = Modifier.height(8.dp))

                                product.pricingTiers.forEach { tier ->
                                    val isTierActive = orderQuantity >= tier.minQty && (tier.maxQty == null || orderQuantity <= tier.maxQty)
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(if (isTierActive) FleexPine else Color.Transparent)
                                            .padding(horizontal = 8.dp, vertical = 6.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            if (isTierActive) {
                                                Icon(
                                                    imageVector = Icons.Default.Check,
                                                    contentDescription = null,
                                                    tint = FleexGold,
                                                    modifier = Modifier.size(14.dp)
                                                )
                                                Spacer(modifier = Modifier.width(4.dp))
                                            }
                                            Text(
                                                text = tier.tierLabel,
                                                color = if (isTierActive) FleexCream else FleexPineDark,
                                                fontSize = 13.sp,
                                                fontWeight = if (isTierActive) FontWeight.Bold else FontWeight.Medium
                                            )
                                        }

                                        Text(
                                            text = "Rs. ${String.format(Locale.US, "%,.0f", tier.pricePerUnit)} / pc",
                                            color = if (isTierActive) FleexGold else FleexPine,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // 4. COLOR SELECTION
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "AVAILABLE COLORWAYS",
                                color = FleexPine,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                            Text(
                                text = selectedColor,
                                color = FleexSage,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            product.colors.forEach { colorOpt ->
                                val isSelected = colorOpt.name == selectedColor
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (isSelected) FleexPine else FleexCreamLight)
                                        .border(
                                            1.5.dp,
                                            if (isSelected) FleexGold else FleexBorder,
                                            RoundedCornerShape(8.dp)
                                        )
                                        .clickable { selectedColor = colorOpt.name }
                                        .padding(horizontal = 12.dp, vertical = 8.dp)
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Box(
                                            modifier = Modifier
                                                .size(16.dp)
                                                .clip(CircleShape)
                                                .background(Color(colorOpt.colorHex))
                                                .border(0.5.dp, FleexBorder, CircleShape)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Column {
                                            Text(
                                                text = colorOpt.name,
                                                color = if (isSelected) FleexCream else FleexPineDark,
                                                fontSize = 12.sp,
                                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                            )
                                            Text(
                                                text = colorOpt.pantoneCode,
                                                color = if (isSelected) FleexCream.copy(alpha = 0.7f) else FleexTextSecondary,
                                                fontSize = 9.sp
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // 5. SIZE SELECTION & RATIO BREAKDOWN
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Text(
                            text = "SIZE SELECTION & RATIO PACKS",
                            color = FleexPine,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Choose individual size focus or standard wholesale carton pack ratio:",
                            color = FleexTextSecondary,
                            fontSize = 12.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Size Buttons
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            product.sizes.forEach { sizeLabel ->
                                val isSelected = sizeLabel == selectedSize
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(44.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (isSelected) FleexPine else FleexCreamLight)
                                        .border(
                                            1.5.dp,
                                            if (isSelected) FleexGold else FleexBorder,
                                            RoundedCornerShape(8.dp)
                                        )
                                        .clickable { selectedSize = sizeLabel }
                                        .testTag("size_btn_$sizeLabel"),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = sizeLabel,
                                        color = if (isSelected) FleexCream else FleexPineDark,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Pack Ratio Info Card
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, FleexBorder, RoundedCornerShape(8.dp)),
                            colors = CardDefaults.cardColors(containerColor = FleexCreamLight)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(FleexPine),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("📦", fontSize = 14.sp)
                                }
                                Column {
                                    Text(
                                        text = "Standard Prepack Ratio: ${product.defaultPackRatio}",
                                        color = FleexPineDark,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "Custom breakdown also available upon WhatsApp order confirmation",
                                        color = FleexTextSecondary,
                                        fontSize = 11.sp
                                    )
                                }
                            }
                        }
                    }
                }

                // 6. VOLUME PRESETS & SAMPLE ORDER ACTION
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(
                            text = "ORDER VOLUME PRESETS",
                            color = FleexPine,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf(product.moq, 100, 250, 500).forEach { qty ->
                                val isSelected = orderQuantity == qty
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(if (isSelected) FleexPine else FleexCreamLight)
                                        .border(
                                            1.dp,
                                            if (isSelected) FleexGold else FleexBorder,
                                            RoundedCornerShape(8.dp)
                                        )
                                        .clickable { orderQuantity = qty }
                                        .padding(vertical = 8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "$qty pcs",
                                        color = if (isSelected) FleexCream else FleexPineDark,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Order Sample Piece Option
                        OutlinedButton(
                            onClick = {
                                val sampleMsg = "Hello Fleex Garments Wholesale! I would like to order a sample piece of *${product.name}* (SKU: ${product.sku}), Color: $selectedColor, Size: $selectedSize for Rs. ${String.format(Locale.US, "%,.0f", product.samplePrice)} PKR before placing our bulk run."
                                WhatsAppOrderHelper.launchWhatsApp(context, sampleMsg)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(44.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = FleexPine
                            ),
                            border = androidx.compose.foundation.BorderStroke(1.dp, FleexPine)
                        ) {
                            Text(
                                text = "Request Single Fit Sample (Rs. ${String.format(Locale.US, "%,.0f", product.samplePrice)}) via WhatsApp",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                // 7. TECHNICAL SPECIFICATIONS ACCORDION
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                            .background(FleexCreamLight)
                            .clickable { specsExpanded = !specsExpanded }
                            .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "GARMENT TECHNICAL SPECIFICATIONS",
                                color = FleexPineDark,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.5.sp
                            )
                            Icon(
                                imageVector = if (specsExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                contentDescription = null,
                                tint = FleexPine
                            )
                        }

                        AnimatedVisibility(visible = specsExpanded) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                TechSpecRow(label = "Yarn Specification", value = product.yarnCount)
                                TechSpecRow(label = "Shrinkage Rate", value = product.shrinkageRate)
                                TechSpecRow(label = "Carton Packaging", value = product.cartonDetails)
                                TechSpecRow(label = "Fabric Weight", value = "${product.gsm} Grams per Square Meter")
                                TechSpecRow(label = "Cut & Sew Finish", value = "Double-needle clean interior coverstitching")
                                TechSpecRow(label = "Private Label", value = "Custom neck labels & polybags available on all orders")
                            }
                        }
                    }
                }
            }

            // STICKY BOTTOM DIRECT WHATSAPP ACTION BAR
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding(),
                color = FleexPineDark,
                shadowElevation = 16.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "$orderQuantity UNITS @ Rs. ${String.format(Locale.US, "%,.0f", currentUnitPrice)}/pc",
                            color = FleexCream.copy(alpha = 0.8f),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Rs. ${String.format(Locale.US, "%,.0f", estimatedTotal)} PKR",
                            color = FleexGold,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif
                        )
                    }

                    // Direct WhatsApp Order CTA
                    Button(
                        onClick = { showOrderConfigSheet = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = WhatsAppGreen,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .height(48.dp)
                            .testTag("direct_whatsapp_order_btn")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Chat,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = "Order via WhatsApp",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        // WhatsApp Order Configuration Sheet
        if (showOrderConfigSheet) {
            WhatsAppOrderSheet(
                product = product,
                initialColor = selectedColor,
                onDismiss = { showOrderConfigSheet = false }
            )
        }
    }
}

@Composable
fun SpecChip(label: String, value: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(FleexCreamLight)
            .border(1.dp, FleexBorder, RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "$label: ",
                color = FleexTextSecondary,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = value,
                color = FleexPineDark,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun TechSpecRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = FleexTextSecondary,
            fontSize = 12.sp,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            color = FleexPineDark,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1.2f)
        )
    }
    HorizontalDivider(color = FleexBorder.copy(alpha = 0.5f))
}
