package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

@Composable
fun AccountScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var brandName by remember { mutableStateOf("Studio A Atelier") }
    var buyerName by remember { mutableStateOf("Elena Rostova") }
    var taxId by remember { mutableStateOf("US-94810283") }
    var destinationCountry by remember { mutableStateOf("United States (Los Angeles, CA)") }
    var isEditingProfile by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(FleexSurfaceLight)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(FleexPine)
                .statusBarsPadding()
                .padding(horizontal = 20.dp, vertical = 18.dp)
        ) {
            Column {
                Text(
                    text = "B2B WHOLESALE ACCOUNT",
                    color = FleexGold,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Buyer Profile & Factory Hub",
                    color = FleexCream,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            // 1. BRAND / BUYER PROFILE CARD
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, FleexBorder, RoundedCornerShape(12.dp)),
                    colors = CardDefaults.cardColors(containerColor = FleexCreamLight)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                        .background(FleexPine),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Business,
                                        contentDescription = null,
                                        tint = FleexGold,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                Column {
                                    Text(
                                        text = brandName,
                                        color = FleexPineDark,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif
                                    )
                                    Text(
                                        text = "Verified Wholesale Buyer Account",
                                        color = FleexSage,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }

                            IconButton(
                                onClick = { isEditingProfile = !isEditingProfile },
                                modifier = Modifier.testTag("edit_profile_btn")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit Profile",
                                    tint = FleexPine,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))
                        HorizontalDivider(color = FleexBorder)
                        Spacer(modifier = Modifier.height(12.dp))

                        if (isEditingProfile) {
                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                OutlinedTextField(
                                    value = brandName,
                                    onValueChange = { brandName = it },
                                    label = { Text("Brand / Store Name") },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = FleexPine,
                                        unfocusedBorderColor = FleexBorder
                                    )
                                )
                                OutlinedTextField(
                                    value = buyerName,
                                    onValueChange = { buyerName = it },
                                    label = { Text("Purchasing Contact Name") },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = FleexPine,
                                        unfocusedBorderColor = FleexBorder
                                    )
                                )
                                OutlinedTextField(
                                    value = taxId,
                                    onValueChange = { taxId = it },
                                    label = { Text("Reseller Tax ID / VAT") },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = FleexPine,
                                        unfocusedBorderColor = FleexBorder
                                    )
                                )
                                OutlinedTextField(
                                    value = destinationCountry,
                                    onValueChange = { destinationCountry = it },
                                    label = { Text("Destination Port / Country") },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = FleexPine,
                                        unfocusedBorderColor = FleexBorder
                                    )
                                )
                                Button(
                                    onClick = { isEditingProfile = false },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = FleexPine,
                                        contentColor = FleexCream
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Save Wholesale Details")
                                }
                            }
                        } else {
                            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                ProfileInfoRow(label = "Contact Person", value = buyerName)
                                ProfileInfoRow(label = "Reseller Tax ID", value = taxId)
                                ProfileInfoRow(label = "Destination", value = destinationCountry)
                                ProfileInfoRow(label = "Wholesale Tier", value = "Tier 1 Boutique Partner")
                            }
                        }
                    }
                }
            }

            // 2. DIRECT WHATSAPP FACTORY HOTLINE
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, FleexGold.copy(alpha = 0.5f), RoundedCornerShape(12.dp)),
                    colors = CardDefaults.cardColors(containerColor = FleexPineDark)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Chat, contentDescription = null, tint = WhatsAppGreen, modifier = Modifier.size(20.dp))
                            Text(
                                text = "OFFICIAL WHATSAPP BUSINESS",
                                color = FleexGold,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Direct Factory Access: ${WhatsAppOrderHelper.WHATSAPP_DISPLAY_NUMBER}",
                            color = FleexCream,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Our senior merchandising team coordinates fabric lab dips, physical swatches, digital tech packs, and real-time production status directly over WhatsApp.",
                            color = FleexCream.copy(alpha = 0.8f),
                            fontSize = 12.sp,
                            lineHeight = 16.sp
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Button(
                            onClick = {
                                val message = "Hello Fleex Garments Wholesale Management! I am representing $brandName ($buyerName) and would like to initiate an introductory wholesale account inquiry."
                                WhatsAppOrderHelper.launchWhatsApp(context, message)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = WhatsAppGreen,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(44.dp)
                                .testTag("account_open_whatsapp_btn")
                        ) {
                            Text("Open WhatsApp Support Line", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        }
                    }
                }
            }

            // 3. WHOLESALE TERMS & FAQ
            item {
                Text(
                    text = "WHOLESALE TERMS & PRODUCTION POLICIES",
                    color = FleexPine,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    FaqCard(
                        question = "What is the Minimum Order Quantity (MOQ)?",
                        answer = "Our standard wholesale MOQ is 50 pieces per style/color. We allow split sizing within the 50 pieces (e.g. standard 1:2:2:1 ratio S:M:L:XL). Custom milled pantone colors require 150 pieces per color."
                    )
                    FaqCard(
                        question = "How does Sampling work?",
                        answer = "Single fit & fabric sample pieces are dispatched via DHL Express in 5–7 business days (Rs. 7,000–Rs. 14,000/piece depending on silhouette). 100% of sample fees are credited toward your subsequent bulk run."
                    )
                    FaqCard(
                        question = "What Private Labeling options are provided?",
                        answer = "Every garment can be delivered blank or with custom high-definition woven neck labels, satin care/content tags, and recycled frosted polybags featuring your brand logo."
                    )
                    FaqCard(
                        question = "Production Lead Times & Freight",
                        answer = "Stock blanks ship within 3–5 business days. Custom printed/labeled runs require 12–15 business days. We provide global DDP (Delivered Duty Paid) air freight (4–6 days) and ocean container freight."
                    )
                }
            }

            // 4. CERTIFICATIONS & MILL DETAILS
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, FleexBorder, RoundedCornerShape(12.dp)),
                    colors = CardDefaults.cardColors(containerColor = FleexCreamLight)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "MILL SPECIFICATIONS & COMPLIANCE",
                            color = FleexPineDark,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            ComplianceBadge("OEKO-TEX®", "Standard 100")
                            ComplianceBadge("GOTS", "Organic Cotton")
                            ComplianceBadge("ISO 9001", "Quality Assured")
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Fleex Garments International Ltd. • All orders verified under Fleex Wholesale charter.",
                            color = FleexTextSecondary,
                            fontSize = 10.sp,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = FleexTextSecondary, fontSize = 12.sp)
        Text(text = value, color = FleexPineDark, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun ComplianceBadge(title: String, subtitle: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(FleexSurfaceLight)
            .border(1.dp, FleexBorder, RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(text = title, color = FleexPine, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        Text(text = subtitle, color = FleexSage, fontSize = 9.sp)
    }
}

@Composable
fun FaqCard(question: String, answer: String) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, FleexBorder, RoundedCornerShape(8.dp))
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(containerColor = FleexSurfaceLight)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = question,
                    color = FleexPineDark,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null,
                    tint = FleexPine
                )
            }

            AnimatedVisibility(visible = expanded) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = answer,
                        color = FleexTextSecondary,
                        fontSize = 12.sp,
                        lineHeight = 17.sp
                    )
                }
            }
        }
    }
}
