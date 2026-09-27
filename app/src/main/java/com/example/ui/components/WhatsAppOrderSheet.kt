package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Product
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun WhatsAppOrderSheet(
    product: Product,
    initialColor: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var selectedColor by remember { mutableStateOf(initialColor) }
    var selectedPackType by remember { mutableStateOf("Prepack Ratio (${product.defaultPackRatio})") }
    var orderQuantity by remember { mutableIntStateOf(product.moq) }
    var customBranding by remember { mutableStateOf(true) }
    var buyerNotes by remember { mutableStateOf("") }

    val unitPrice = product.getTierPrice(orderQuantity)
    val estimatedTotal = unitPrice * orderQuantity
    val nextTier = product.pricingTiers.firstOrNull { it.minQty > orderQuantity }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = FleexSurfaceLight,
        dragHandle = null,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp, vertical = 18.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "WHOLESALE DIRECT ORDER",
                        color = FleexSage,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = product.name,
                        color = FleexPineDark,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif
                    )
                }

                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(FleexCreamLight)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = FleexPineDark,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider(color = FleexBorder)
            Spacer(modifier = Modifier.height(14.dp))

            // 1. Color Selection
            Text(
                text = "1. CHOOSE COLORWAY",
                color = FleexPine,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                product.colors.forEach { colorOpt ->
                    val isSelected = colorOpt.name == selectedColor
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isSelected) FleexPine else FleexCreamLight)
                            .border(
                                width = 1.5.dp,
                                color = if (isSelected) FleexGold else FleexBorder,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { selectedColor = colorOpt.name }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(14.dp)
                                    .clip(CircleShape)
                                    .background(Color(colorOpt.colorHex))
                                    .border(0.5.dp, FleexBorder, CircleShape)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = colorOpt.name,
                                color = if (isSelected) FleexCream else FleexPineDark,
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // 2. Size / Pack Configuration
            Text(
                text = "2. SIZE PACK CONFIGURATION",
                color = FleexPine,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            val packOptions = listOf(
                "Standard Ratio (${product.defaultPackRatio})",
                "Custom Size Breakdown (S, M, L, XL, 2XL)"
            )
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                packOptions.forEach { option ->
                    val isSelected = selectedPackType == option
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isSelected) FleexCreamLight else Color.Transparent)
                            .border(
                                1.dp,
                                if (isSelected) FleexSage else FleexBorder,
                                RoundedCornerShape(8.dp)
                            )
                            .clickable { selectedPackType = option }
                            .padding(horizontal = 12.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(18.dp)
                                .clip(CircleShape)
                                .background(if (isSelected) FleexPine else Color.Transparent)
                                .border(1.5.dp, if (isSelected) FleexGold else FleexBorder, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isSelected) {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .clip(CircleShape)
                                        .background(FleexGold)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = option,
                            color = FleexPineDark,
                            fontSize = 13.sp,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // 3. Wholesale Order Volume (Units)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "3. ORDER QUANTITY (MOQ: ${product.moq} pcs)",
                    color = FleexPine,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
                Text(
                    text = "Total: $orderQuantity pcs",
                    color = FleexSage,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Quick Volume Presets
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

            Spacer(modifier = Modifier.height(8.dp))

            // Stepper
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(FleexCreamLight)
                    .border(1.dp, FleexBorder, RoundedCornerShape(8.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Custom units:",
                    color = FleexTextSecondary,
                    fontSize = 13.sp
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = {
                            if (orderQuantity > product.moq) {
                                orderQuantity -= 10
                                if (orderQuantity < product.moq) orderQuantity = product.moq
                            }
                        },
                        enabled = orderQuantity > product.moq
                    ) {
                        Icon(imageVector = Icons.Default.Remove, contentDescription = "Decrease", tint = FleexPine)
                    }

                    Text(
                        text = "$orderQuantity",
                        color = FleexPineDark,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )

                    IconButton(
                        onClick = { orderQuantity += 10 }
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Increase", tint = FleexPine)
                    }
                }
            }

            // Next Tier Discount Callout
            if (nextTier != null) {
                Spacer(modifier = Modifier.height(6.dp))
                val needed = nextTier.minQty - orderQuantity
                Text(
                    text = "💡 Add $needed more pcs to unlock Rs. ${String.format(Locale.US, "%,.0f", nextTier.pricePerUnit)}/pc rate!",
                    color = FleexGold,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // 4. Custom Private Labeling Switch
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(FleexCreamLight)
                    .border(1.dp, FleexBorder, RoundedCornerShape(10.dp))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Custom Private Labeling",
                        color = FleexPineDark,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Include custom woven neck tags & printed eco polybags",
                        color = FleexTextSecondary,
                        fontSize = 11.sp
                    )
                }

                Switch(
                    checked = customBranding,
                    onCheckedChange = { customBranding = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = FleexCream,
                        checkedTrackColor = FleexPine,
                        uncheckedTrackColor = FleexBorder
                    )
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Buyer Notes (Optional)
            OutlinedTextField(
                value = buyerNotes,
                onValueChange = { buyerNotes = it },
                label = { Text("Special requirements / shipping destination") },
                placeholder = { Text("e.g. DDP to Los Angeles, sample approval required first") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = FleexPine,
                    unfocusedBorderColor = FleexBorder,
                    focusedLabelColor = FleexPine
                ),
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Summary Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(FleexPineDark)
                    .padding(16.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Unit Wholesale Rate:",
                            color = FleexCream.copy(alpha = 0.8f),
                            fontSize = 12.sp
                        )
                        Text(
                            text = "Rs. ${String.format(Locale.US, "%,.0f", unitPrice)} / unit",
                            color = FleexGold,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Volume Units:",
                            color = FleexCream.copy(alpha = 0.8f),
                            fontSize = 12.sp
                        )
                        Text(
                            text = "$orderQuantity units",
                            color = FleexCream,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    HorizontalDivider(color = FleexPine)
                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "ESTIMATED TOTAL",
                                color = FleexGold,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                            Text(
                                text = "Rs. ${String.format(Locale.US, "%,.0f", estimatedTotal)} PKR",
                                color = FleexCream,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Serif
                            )
                        }

                        Text(
                            text = "Ex-Factory / FOB",
                            color = FleexCream.copy(alpha = 0.7f),
                            fontSize = 10.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // WhatsApp Direct Action Button
            Button(
                onClick = {
                    val message = WhatsAppOrderHelper.generateOrderMessage(
                        product = product,
                        selectedColor = selectedColor,
                        selectedSizeSummary = selectedPackType,
                        quantity = orderQuantity,
                        customBranding = customBranding,
                        buyerNotes = buyerNotes
                    )
                    WhatsAppOrderHelper.launchWhatsApp(context, message)
                    onDismiss()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .testTag("whatsapp_confirm_order_button"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = WhatsAppGreen,
                    contentColor = Color.White
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "💬  Direct Order via WhatsApp",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.3.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Orders are directly coordinated with Fleex Garments production managers via verified WhatsApp business line.",
                color = FleexTextSecondary,
                fontSize = 10.sp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
