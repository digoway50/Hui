package com.example.data

import com.example.R
import com.example.model.ColorOption
import com.example.model.PricingTier
import com.example.model.Product
import com.example.model.ProductCategory

object ProductRepository {
    private val pineGreen = ColorOption("Pine Green", 0xFF123F36, "PANTONE 19-5411 TCX")
    private val sageGreen = ColorOption("Sage Forest", 0xFF2A6B5C, "PANTONE 18-5612 TCX")
    private val warmLinen = ColorOption("Linen Oat", 0xFFE8DCC4, "PANTONE 13-0607 TCX")
    private val vintageGold = ColorOption("Antique Gold", 0xFFC49A45, "PANTONE 16-0947 TCX")
    private val washedBlack = ColorOption("Washed Onyx", 0xFF1A1D1C, "PANTONE 19-4004 TCX")
    private val opticWhite = ColorOption("Raw Optical White", 0xFFF5F5F3, "PANTONE 11-0601 TCX")

    val allProducts: List<Product> = listOf(
        Product(
            id = "flx-hoodie-01",
            sku = "FLX-H500-PNE",
            name = "Heavyweight French Terry Hoodie",
            category = ProductCategory.HOODIES,
            tagline = "500 GSM Luxury Combed Cotton Fleece with Architectural Boxy Fit",
            gsm = 500,
            composition = "100% Combed Organic Ring-Spun Cotton",
            fit = "Oversized Boxy Silhouette / Dropped Shoulders",
            moq = 50,
            basePrice = 5400.0,
            pricingTiers = listOf(
                PricingTier(50, 99, 5400.0),
                PricingTier(100, 249, 4750.0),
                PricingTier(250, 499, 4100.0),
                PricingTier(500, null, 3500.0)
            ),
            colors = listOf(pineGreen, warmLinen, sageGreen, washedBlack, vintageGold),
            sizes = listOf("S", "M", "L", "XL", "2XL"),
            defaultPackRatio = "1:2:2:1 per carton (24 pcs)",
            leadTime = "12–15 business days",
            samplePrice = 11000.0,
            imageRes = R.drawable.img_hoodie_line,
            secondaryImageRes = R.drawable.img_hero_fashion,
            description = "Engineered specifically for high-end boutique apparel brands. Milled from ultra-dense 500 GSM combed French Terry cotton with a brushed interior. Features an oversized double-layered hood without drawstrings for an uncompromising minimalist look, heavy 2x2 ribbed cuffs, and blind hem stitching.",
            highlights = listOf(
                "Pre-shrunk with less than 2% wash shrinkage guarantee",
                "Blind cover-stitch construction across all stress points",
                "Custom woven neck tag & wash label replacement ready",
                "Ultra-dense 500 GSM custom milled yarn"
            ),
            yarnCount = "20s/2 + 10s heavy backing",
            shrinkageRate = "< 2% post garment-wash",
            cartonDetails = "24 pcs / export grade double-wall carton (18.5 kg)",
            isFeatured = true,
            isBestSeller = true
        ),
        Product(
            id = "flx-tee-02",
            sku = "FLX-T280-OAT",
            name = "Boxy Drop-Shoulder Heavy Tee",
            category = ProductCategory.TEES,
            tagline = "280 GSM Single Jersey with Thick 1.25\" Bound Collar",
            gsm = 280,
            composition = "100% Ring-Spun Combed Cotton",
            fit = "Drop-Shoulder Boxy Cut / Relaxed Width",
            moq = 50,
            basePrice = 2900.0,
            pricingTiers = listOf(
                PricingTier(50, 99, 2900.0),
                PricingTier(100, 249, 2450.0),
                PricingTier(250, 499, 2000.0),
                PricingTier(500, null, 1650.0)
            ),
            colors = listOf(warmLinen, pineGreen, opticWhite, washedBlack, sageGreen),
            sizes = listOf("XS", "S", "M", "L", "XL", "2XL"),
            defaultPackRatio = "1:2:3:2:1 (S:M:L:XL:2XL)",
            leadTime = "10–12 business days",
            samplePrice = 6800.0,
            imageRes = R.drawable.img_oversized_tee,
            secondaryImageRes = R.drawable.img_hero_fashion,
            description = "The cornerstone blank for premium streetwear labels. Milled at 280 GSM, this heavyweight t-shirt provides clean drape that maintains its architectural shape throughout wear. Features a tight, sturdy 1.25-inch ribbed collar that will never bacon or stretch.",
            highlights = listOf(
                "Tight 1.25-inch high-density ribbed neckband",
                "Enzyme-washed for a smooth, lint-free surface",
                "Ideal substrate for high-density screen printing & puff ink",
                "Pre-laundered for dimensional stability"
            ),
            yarnCount = "16s compact ring spun",
            shrinkageRate = "< 1.5% garment-washed",
            cartonDetails = "48 pcs / export carton (16.2 kg)",
            isFeatured = true,
            isBestSeller = true
        ),
        Product(
            id = "flx-cargo-03",
            sku = "FLX-C340-SGE",
            name = "Tailored Relaxed Utility Cargo Trousers",
            category = ProductCategory.TROUSERS,
            tagline = "340 GSM Japanese Cotton Twill with Clean Accordion Pleats",
            gsm = 340,
            composition = "98% Heavy Cotton Twill, 2% Mechanical Stretch",
            fit = "Relaxed Tapered / Structured Silhouette",
            moq = 50,
            basePrice = 7200.0,
            pricingTiers = listOf(
                PricingTier(50, 99, 7200.0),
                PricingTier(100, 249, 6250.0),
                PricingTier(250, 499, 5500.0),
                PricingTier(500, null, 4700.0)
            ),
            colors = listOf(sageGreen, pineGreen, warmLinen, washedBlack),
            sizes = listOf("30", "32", "34", "36", "38"),
            defaultPackRatio = "1:2:2:1 (30:32:34:36)",
            leadTime = "15–18 business days",
            samplePrice = 14000.0,
            imageRes = R.drawable.img_cargo_trousers,
            secondaryImageRes = R.drawable.img_hero_fashion,
            description = "Elevated utilitarian workwear for modern streetwear brands. Designed with streamlined bellows pockets that lay completely flat when empty, darts at the knee for ergonomic drape, and an internal adjustable hem bungee.",
            highlights = listOf(
                "YKK antique brass hardware throughout",
                "Reinforced bar-tacking on all cargo stress points",
                "Enzyme stone-washed for soft vintage hand feel",
                "Hidden coin pocket & magnetic pocket closures"
            ),
            yarnCount = "21s x 16s high density twill",
            shrinkageRate = "< 2% pre-shrunk",
            cartonDetails = "20 pcs / export carton (17.0 kg)",
            isFeatured = true,
            isBestSeller = false
        ),
        Product(
            id = "flx-overshirt-04",
            sku = "FLX-O260-LIN",
            name = "Minimalist Linen-Cotton Overshirt",
            category = ProductCategory.OVERSHIRTS,
            tagline = "260 GSM Dense Flax-Cotton Blend with Square Hem & Chest Pocket",
            gsm = 260,
            composition = "55% Natural French Flax Linen, 45% Combed Cotton",
            fit = "Relaxed Overshirt / Straight Hem",
            moq = 40,
            basePrice = 6800.0,
            pricingTiers = listOf(
                PricingTier(40, 99, 6800.0),
                PricingTier(100, 249, 5800.0),
                PricingTier(250, 499, 5100.0),
                PricingTier(500, null, 4400.0)
            ),
            colors = listOf(warmLinen, sageGreen, pineGreen, opticWhite),
            sizes = listOf("S", "M", "L", "XL", "2XL"),
            defaultPackRatio = "1:2:2:1 (S:M:L:XL)",
            leadTime = "14–16 business days",
            samplePrice = 12500.0,
            imageRes = R.drawable.img_cargo_trousers,
            secondaryImageRes = R.drawable.img_hero_fashion,
            description = "A versatile layering piece crafted from an artisanal flax and combed cotton weave. Delivers breathable structure, corozo nut button closures, and an understated flat-felled seam finish inside and out.",
            highlights = listOf(
                "Natural Corozo vegetable ivory buttons",
                "Breathable textured slub handfeel",
                "Double-needle clean interior seams",
                "Clean architectural camp collar"
            ),
            yarnCount = "14s linen x 20s cotton",
            shrinkageRate = "< 2.5%",
            cartonDetails = "24 pcs / carton (14.0 kg)",
            isFeatured = false,
            isBestSeller = true
        ),
        Product(
            id = "flx-crew-05",
            sku = "FLX-CR460-PNE",
            name = "Vintage Pigment Heavy Crewneck",
            category = ProductCategory.HOODIES,
            tagline = "460 GSM Diagonal French Terry with Custom Pigment Wash",
            gsm = 460,
            composition = "100% Combed Organic Cotton",
            fit = "Slightly Cropped Boxy Streetwear Silhouette",
            moq = 50,
            basePrice = 4950.0,
            pricingTiers = listOf(
                PricingTier(50, 99, 4950.0),
                PricingTier(100, 249, 4300.0),
                PricingTier(250, 499, 3750.0),
                PricingTier(500, null, 3100.0)
            ),
            colors = listOf(pineGreen, vintageGold, washedBlack, warmLinen),
            sizes = listOf("S", "M", "L", "XL", "2XL"),
            defaultPackRatio = "1:2:2:1 (S:M:L:XL)",
            leadTime = "12–15 business days",
            samplePrice = 10500.0,
            imageRes = R.drawable.img_hoodie_line,
            secondaryImageRes = R.drawable.img_hero_fashion,
            description = "Authentic vintage wash aesthetic without sacrificing fabric durability. Crafted with heavy diagonal loopback terry, ribbed triangle gusset at the collar, and wide ribbing at the waist and cuffs.",
            highlights = listOf(
                "Artisan garment pigment dye for unique aged patina",
                "Reinforced rib neckline with twin-needle collar stay",
                "Zero polyester blends – 100% pure combed cotton",
                "Custom brand neck label stitching included"
            ),
            yarnCount = "20s/2 combed",
            shrinkageRate = "< 1.5%",
            cartonDetails = "24 pcs / carton (16.5 kg)",
            isFeatured = false,
            isBestSeller = false
        ),
        Product(
            id = "flx-thermal-06",
            sku = "FLX-TH320-WHT",
            name = "Thermal Waffle Knit Longsleeve",
            category = ProductCategory.KNITS,
            tagline = "320 GSM Micro-Waffle Knit with Snug Rib Cuffs",
            gsm = 320,
            composition = "100% Long-Staple Cotton",
            fit = "Regular Relaxed / Dropped Hem",
            moq = 50,
            basePrice = 3750.0,
            pricingTiers = listOf(
                PricingTier(50, 99, 3750.0),
                PricingTier(100, 249, 3100.0),
                PricingTier(250, 499, 2650.0),
                PricingTier(500, null, 2200.0)
            ),
            colors = listOf(warmLinen, opticWhite, pineGreen, washedBlack),
            sizes = listOf("S", "M", "L", "XL", "2XL"),
            defaultPackRatio = "1:2:2:1",
            leadTime = "10–14 business days",
            samplePrice = 8500.0,
            imageRes = R.drawable.img_oversized_tee,
            secondaryImageRes = R.drawable.img_hero_fashion,
            description = "Essential thermal base layer with three-dimensional waffle texture for maximum insulation and breathability. Garment-washed for ultra-soft hand feel right out of the packaging.",
            highlights = listOf(
                "Deep 3D honeycomb waffle weave structure",
                "Snug extended rib cuffs for wind prevention",
                "Flatlock comfort seams preventing skin friction",
                "Reactive dyed for zero color bleeding"
            ),
            yarnCount = "30s/2 micro waffle",
            shrinkageRate = "< 3%",
            cartonDetails = "36 pcs / carton (15.0 kg)",
            isFeatured = false,
            isBestSeller = false
        )
    )

    fun getProductById(id: String): Product? = allProducts.firstOrNull { it.id == id }

    fun getProductsByCategory(category: ProductCategory): List<Product> {
        return if (category == ProductCategory.ALL) allProducts else allProducts.filter { it.category == category }
    }
}
