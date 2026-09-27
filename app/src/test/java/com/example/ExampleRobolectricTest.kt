package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.ProductRepository
import com.example.util.WhatsAppOrderHelper
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Fleex Garments", appName)
    }

    @Test
    fun `product tier pricing calculates volume discounts correctly`() {
        val hoodie = ProductRepository.getProductById("flx-hoodie-01")
        requireNotNull(hoodie)

        // Tier 1: 50-99 pcs -> $19.50
        assertEquals(19.50, hoodie.getTierPrice(50), 0.01)
        assertEquals(19.50, hoodie.getTierPrice(99), 0.01)

        // Tier 2: 100-249 pcs -> $17.00
        assertEquals(17.00, hoodie.getTierPrice(100), 0.01)

        // Tier 3: 250-499 pcs -> $14.80
        assertEquals(14.80, hoodie.getTierPrice(300), 0.01)

        // Tier 4: 500+ pcs -> $12.50
        assertEquals(12.50, hoodie.getTierPrice(600), 0.01)
    }

    @Test
    fun `whatsapp order inquiry message formats properly`() {
        val tee = ProductRepository.getProductById("flx-tee-02")
        requireNotNull(tee)

        val message = WhatsAppOrderHelper.generateOrderMessage(
            product = tee,
            selectedColor = "Linen Oat",
            selectedSizeSummary = "Standard Ratio 1:2:3:2:1",
            quantity = 100,
            customBranding = true,
            buyerNotes = "DDP Los Angeles"
        )

        assertTrue(message.contains("FLEEX GARMENTS"))
        assertTrue(message.contains(tee.name))
        assertTrue(message.contains("Linen Oat"))
        assertTrue(message.contains("100 units"))
        assertTrue(message.contains("FLEEXGARMENTS.COM"))
    }
}
