package com.example.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.example.model.Product
import java.net.URLEncoder
import java.util.Locale

object WhatsAppOrderHelper {
    // Official Fleex Garments Wholesale WhatsApp Line
    const val WHATSAPP_PHONE_NUMBER = "15558923412" // Configurable wholesale hotline
    const val WHATSAPP_DISPLAY_NUMBER = "+1 (555) 892-3412"
    const val OFFICIAL_WEBSITE = "FLEEXGARMENTS.COM"

    fun generateOrderMessage(
        product: Product,
        selectedColor: String,
        selectedSizeSummary: String,
        quantity: Int,
        customBranding: Boolean,
        buyerNotes: String = ""
    ): String {
        val unitPrice = product.getTierPrice(quantity)
        val estimatedTotal = unitPrice * quantity

        return buildString {
            append("📦 *FLEEX GARMENTS • WHOLESALE ORDER INQUIRY*\n")
            append("Official Portal: $OFFICIAL_WEBSITE\n")
            append("────────────────────────\n")
            append("• *Style:* ${product.name}\n")
            append("• *SKU:* ${product.sku}\n")
            append("• *Fabric:* ${product.gsm} GSM • ${product.composition}\n")
            append("• *Selected Color:* $selectedColor\n")
            append("• *Size Pack / Distribution:* $selectedSizeSummary\n")
            append("• *Order Volume:* $quantity units (MOQ: ${product.moq} pcs)\n")
            append("• *Wholesale Rate:* $${String.format(Locale.US, "%.2f", unitPrice)} / unit\n")
            append("• *Estimated Subtotal:* $${String.format(Locale.US, "%.2f", estimatedTotal)} USD\n")
            append("• *Private Label / Tags:* ${if (customBranding) "Yes (Woven neck labels & custom polybags)" else "Blank standard"}\n")
            append("• *Estimated Lead Time:* ${product.leadTime}\n")
            if (buyerNotes.isNotBlank()) {
                append("• *Buyer Notes:* $buyerNotes\n")
            }
            append("────────────────────────\n")
            append("Please provide production schedule, shipping quote (Air / Sea DDP), and sample digital approval details.")
        }
    }

    fun generateWishlistQuoteMessage(
        products: List<Product>
    ): String {
        return buildString {
            append("📦 *FLEEX GARMENTS • MULTI-PRODUCT WHOLESALE INQUIRY*\n")
            append("Official Portal: $OFFICIAL_WEBSITE\n")
            append("I would like to request bulk quotations and line-sheet specs for the following saved styles:\n\n")
            products.forEachIndexed { index, product ->
                append("${index + 1}. *${product.name}* (SKU: ${product.sku})\n")
                append("   • Weight: ${product.gsm} GSM | MOQ: ${product.moq} pcs\n")
                append("   • Starting Rate: $${String.format(Locale.US, "%.2f", product.basePrice)}/pc\n")
            }
            append("\nPlease send complete bulk volume price sheets and sample swatches catalog.\n")
            append("Thank you!")
        }
    }

    fun launchWhatsApp(
        context: Context,
        message: String,
        phoneNumber: String = WHATSAPP_PHONE_NUMBER
    ) {
        val encodedMessage = try {
            URLEncoder.encode(message, "UTF-8")
        } catch (e: Exception) {
            Uri.encode(message)
        }

        val url = "https://api.whatsapp.com/send?phone=$phoneNumber&text=$encodedMessage"
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            // If WhatsApp is not installed, copy message and show Toast with option to open web
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
            clipboard?.setPrimaryClip(ClipData.newPlainText("Fleex Garments Order", message))
            Toast.makeText(
                context,
                "Inquiry details copied to clipboard. Opening WhatsApp Web...",
                Toast.LENGTH_LONG
            ).show()

            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                context.startActivity(browserIntent)
            } catch (err: Exception) {
                Toast.makeText(
                    context,
                    "Order inquiry copied to clipboard!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
