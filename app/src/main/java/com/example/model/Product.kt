package com.example.model

data class PricingTier(
    val minQty: Int,
    val maxQty: Int?, // null means "and above"
    val pricePerUnit: Double
) {
    val tierLabel: String
        get() = if (maxQty != null) "$minQty–$maxQty pcs" else "$minQty+ pcs"
}

data class ColorOption(
    val name: String,
    val colorHex: Long,
    val pantoneCode: String
)

enum class ProductCategory(val displayName: String) {
    ALL("All Products"),
    HOODIES("Hoodies & Fleece"),
    TEES("Oversized Tees"),
    TROUSERS("Pants & Trousers"),
    OVERSHIRTS("Overshirts & Outerwear"),
    KNITS("Basics & Knits")
}

data class Product(
    val id: String,
    val sku: String,
    val name: String,
    val category: ProductCategory,
    val tagline: String,
    val gsm: Int,
    val composition: String,
    val fit: String,
    val moq: Int, // Minimum Order Quantity in units
    val basePrice: Double, // Starting tier price
    val pricingTiers: List<PricingTier>,
    val colors: List<ColorOption>,
    val sizes: List<String>,
    val defaultPackRatio: String, // e.g. "1:2:2:1 (S:M:L:XL)"
    val leadTime: String,
    val sampleAvailable: Boolean = true,
    val samplePrice: Double = 35.00,
    val imageRes: Int,
    val secondaryImageRes: Int? = null,
    val description: String,
    val highlights: List<String>,
    val yarnCount: String,
    val shrinkageRate: String,
    val cartonDetails: String,
    val isFeatured: Boolean = false,
    val isBestSeller: Boolean = false
) {
    fun getTierPrice(quantity: Int): Double {
        val matching = pricingTiers.firstOrNull { tier ->
            quantity >= tier.minQty && (tier.maxQty == null || quantity <= tier.maxQty)
        }
        return matching?.pricePerUnit ?: pricingTiers.lastOrNull()?.pricePerUnit ?: basePrice
    }
}
