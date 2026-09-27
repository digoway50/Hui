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

        // Tier 1: 50-99 pcs -> Rs. 5,400
        assertEquals(5400.0, hoodie.getTierPrice(50), 0.01)
        assertEquals(5400.0, hoodie.getTierPrice(99), 0.01)

        // Tier 2: 100-249 pcs -> Rs. 4,750
        assertEquals(4750.0, hoodie.getTierPrice(100), 0.01)

        // Tier 3: 250-499 pcs -> Rs. 4,100
        assertEquals(4100.0, hoodie.getTierPrice(300), 0.01)

        // Tier 4: 500+ pcs -> Rs. 3,500
        assertEquals(3500.0, hoodie.getTierPrice(600), 0.01)
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
            buyerNotes = "DDP Karachi"
        )

        assertTrue(message.contains("FLEEX GARMENTS"))
        assertTrue(message.contains(tee.name))
        assertTrue(message.contains("Linen Oat"))
        assertTrue(message.contains("100 units"))
        assertTrue(message.contains("Rs."))
        assertTrue(message.contains("PKR"))
        assertTrue(message.contains("FLEEX WHOLESALE"))
    }
}
