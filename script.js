// FLEEX GARMENTS WHOLESALE PLATFORM JAVASCRIPT
const WHATSAPP_PHONE = "923702265968";
const WHATSAPP_DISPLAY = "03702265968";
const OFFICIAL_PORTAL = "FLEEX WHOLESALE";

// 1. PRODUCTS DATA (IN PAKISTANI RUPEES - PKR)
const PRODUCTS = [
  {
    id: "flx-hoodie-01",
    sku: "FLX-H500-PNE",
    name: "Heavyweight French Terry Hoodie",
    category: "HOODIES",
    tagline: "500 GSM Luxury Combed Cotton Fleece with Architectural Boxy Fit",
    gsm: 500,
    composition: "100% Combed Organic Ring-Spun Cotton",
    fit: "Oversized Boxy Silhouette / Dropped Shoulders",
    moq: 50,
    basePrice: 5400,
    pricingTiers: [
      { min: 50, max: 99, price: 5400, label: "50–99 pcs" },
      { min: 100, max: 249, price: 4750, label: "100–249 pcs" },
      { min: 250, max: 499, price: 4100, label: "250–499 pcs" },
      { min: 500, max: null, price: 3500, label: "500+ pcs" }
    ],
    colors: [
      { name: "Pine Green", hex: "#123F36", pantone: "19-5411 TCX" },
      { name: "Linen Oat", hex: "#E8DCC4", pantone: "13-0607 TCX" },
      { name: "Sage Forest", hex: "#2A6B5C", pantone: "18-5612 TCX" },
      { name: "Washed Onyx", hex: "#1A1D1C", pantone: "19-4004 TCX" },
      { name: "Antique Gold", hex: "#C49A45", pantone: "16-0947 TCX" }
    ],
    sizes: ["S", "M", "L", "XL", "2XL"],
    defaultPack: "1:2:2:1 per carton (24 pcs)",
    leadTime: "12–15 business days",
    samplePrice: 11000,
    image: "assets/images/img_hoodie_line.jpg",
    description: "Engineered specifically for high-end boutique streetwear brands. Milled from ultra-dense 500 GSM combed French Terry cotton with brushed interior. Features an oversized double-layered hood without drawstrings, heavy 2x2 ribbed cuffs, and blind hem stitching.",
    highlights: [
      "Pre-shrunk with less than 2% wash shrinkage guarantee",
      "Blind cover-stitch construction across all stress points",
      "Custom woven neck tag & wash label replacement ready",
      "Ultra-dense 500 GSM custom milled yarn"
    ],
    yarn: "20s/2 + 10s heavy backing",
    shrinkage: "< 2% post garment-wash",
    carton: "24 pcs / export grade carton (18.5 kg)",
    isFeatured: true
  },
  {
    id: "flx-tee-02",
    sku: "FLX-T280-OAT",
    name: "Boxy Drop-Shoulder Heavy Tee",
    category: "TEES",
    tagline: "280 GSM Single Jersey with Thick 1.25\" Bound Collar",
    gsm: 280,
    composition: "100% Ring-Spun Combed Cotton",
    fit: "Drop-Shoulder Boxy Cut / Relaxed Width",
    moq: 50,
    basePrice: 2900,
    pricingTiers: [
      { min: 50, max: 99, price: 2900, label: "50–99 pcs" },
      { min: 100, max: 249, price: 2450, label: "100–249 pcs" },
      { min: 250, max: 499, price: 2000, label: "250–499 pcs" },
      { min: 500, max: null, price: 1650, label: "500+ pcs" }
    ],
    colors: [
      { name: "Linen Oat", hex: "#E8DCC4", pantone: "13-0607 TCX" },
      { name: "Pine Green", hex: "#123F36", pantone: "19-5411 TCX" },
      { name: "Raw White", hex: "#F5F5F3", pantone: "11-0601 TCX" },
      { name: "Washed Onyx", hex: "#1A1D1C", pantone: "19-4004 TCX" },
      { name: "Sage Forest", hex: "#2A6B5C", pantone: "18-5612 TCX" }
    ],
    sizes: ["XS", "S", "M", "L", "XL", "2XL"],
    defaultPack: "1:2:3:2:1 (S:M:L:XL:2XL)",
    leadTime: "10–12 business days",
    samplePrice: 6800,
    image: "assets/images/img_oversized_tee.jpg",
    description: "The cornerstone blank for premium streetwear labels. Milled at 280 GSM, this heavyweight t-shirt provides a stiff, clean drape that maintains its boxy architecture throughout wear. Features a tight 1.25-inch high-density ribbed collar.",
    highlights: [
      "Tight 1.25-inch high-density ribbed neckband",
      "Enzyme-washed for smooth, lint-free surface",
      "Ideal substrate for high-density screen printing & puff ink",
      "Pre-laundered for dimensional stability"
    ],
    yarn: "16s compact ring spun",
    shrinkage: "< 1.5% garment-washed",
    carton: "48 pcs / export carton (16.2 kg)",
    isFeatured: true
  },
  {
    id: "flx-cargo-03",
    sku: "FLX-C340-SGE",
    name: "Tailored Relaxed Utility Cargo Trousers",
    category: "TROUSERS",
    tagline: "340 GSM Japanese Cotton Twill with Clean Accordion Pleats",
    gsm: 340,
    composition: "98% Heavy Cotton Twill, 2% Mechanical Stretch",
    fit: "Relaxed Tapered / Structured Silhouette",
    moq: 50,
    basePrice: 7200,
    pricingTiers: [
      { min: 50, max: 99, price: 7200, label: "50–99 pcs" },
      { min: 100, max: 249, price: 6250, label: "100–249 pcs" },
      { min: 250, max: 499, price: 5500, label: "250–499 pcs" },
      { min: 500, max: null, price: 4700, label: "500+ pcs" }
    ],
    colors: [
      { name: "Sage Forest", hex: "#2A6B5C", pantone: "18-5612 TCX" },
      { name: "Pine Green", hex: "#123F36", pantone: "19-5411 TCX" },
      { name: "Linen Oat", hex: "#E8DCC4", pantone: "13-0607 TCX" },
      { name: "Washed Onyx", hex: "#1A1D1C", pantone: "19-4004 TCX" }
    ],
    sizes: ["30", "32", "34", "36", "38"],
    defaultPack: "1:2:2:1 (30:32:34:36)",
    leadTime: "15–18 business days",
    samplePrice: 14000,
    image: "assets/images/img_cargo_trousers.jpg",
    description: "Elevated utilitarian workwear for modern streetwear brands. Designed with streamlined bellows pockets that lay completely flat when empty, knee darts for ergonomic drape, and antique brass hardware.",
    highlights: [
      "YKK antique brass hardware throughout",
      "Reinforced bar-tacking on all cargo stress points",
      "Enzyme stone-washed for soft vintage hand feel",
      "Hidden coin pocket & magnetic pocket closures"
    ],
    yarn: "21s x 16s high density twill",
    shrinkage: "< 2% pre-shrunk",
    carton: "20 pcs / export carton (17.0 kg)",
    isFeatured: true
  },
  {
    id: "flx-overshirt-04",
    sku: "FLX-O260-LIN",
    name: "Minimalist Linen-Cotton Overshirt",
    category: "OVERSHIRTS",
    tagline: "260 GSM Dense Flax-Cotton Blend with Square Hem & Chest Pocket",
    gsm: 260,
    composition: "55% Natural French Flax Linen, 45% Combed Cotton",
    fit: "Relaxed Overshirt / Straight Hem",
    moq: 40,
    basePrice: 6800,
    pricingTiers: [
      { min: 40, max: 99, price: 6800, label: "40–99 pcs" },
      { min: 100, max: 249, price: 5800, label: "100–249 pcs" },
      { min: 250, max: 499, price: 5100, label: "250–499 pcs" },
      { min: 500, max: null, price: 4400, label: "500+ pcs" }
    ],
    colors: [
      { name: "Linen Oat", hex: "#E8DCC4", pantone: "13-0607 TCX" },
      { name: "Sage Forest", hex: "#2A6B5C", pantone: "18-5612 TCX" },
      { name: "Pine Green", hex: "#123F36", pantone: "19-5411 TCX" }
    ],
    sizes: ["S", "M", "L", "XL", "2XL"],
    defaultPack: "1:2:2:1 (S:M:L:XL)",
    leadTime: "14–16 business days",
    samplePrice: 12500,
    image: "assets/images/img_cargo_trousers.jpg",
    description: "A versatile layering piece crafted from an artisanal flax and combed cotton weave. Delivers breathable structure, natural corozo nut button closures, and understated flat-felled seam construction.",
    highlights: [
      "Natural Corozo vegetable ivory buttons",
      "Breathable textured slub handfeel",
      "Double-needle clean interior seams",
      "Clean architectural camp collar"
    ],
    yarn: "14s linen x 20s cotton",
    shrinkage: "< 2.5%",
    carton: "24 pcs / carton (14.0 kg)",
    isFeatured: true
  },
  {
    id: "flx-crew-05",
    sku: "FLX-CR460-PNE",
    name: "Vintage Pigment Heavy Crewneck",
    category: "HOODIES",
    tagline: "460 GSM Diagonal French Terry with Custom Pigment Wash",
    gsm: 460,
    composition: "100% Combed Organic Cotton",
    fit: "Slightly Cropped Boxy Streetwear Silhouette",
    moq: 50,
    basePrice: 4950,
    pricingTiers: [
      { min: 50, max: 99, price: 4950, label: "50–99 pcs" },
      { min: 100, max: 249, price: 4300, label: "100–249 pcs" },
      { min: 250, max: 499, price: 3750, label: "250–499 pcs" },
      { min: 500, max: null, price: 3100, label: "500+ pcs" }
    ],
    colors: [
      { name: "Pine Green", hex: "#123F36", pantone: "19-5411 TCX" },
      { name: "Antique Gold", hex: "#C49A45", pantone: "16-0947 TCX" },
      { name: "Washed Onyx", hex: "#1A1D1C", pantone: "19-4004 TCX" },
      { name: "Linen Oat", hex: "#E8DCC4", pantone: "13-0607 TCX" }
    ],
    sizes: ["S", "M", "L", "XL", "2XL"],
    defaultPack: "1:2:2:1 (S:M:L:XL)",
    leadTime: "12–15 business days",
    samplePrice: 10500,
    image: "assets/images/img_hoodie_line.jpg",
    description: "Authentic vintage wash aesthetic without sacrificing fabric durability. Crafted with heavy diagonal loopback terry, ribbed triangle gusset at the collar, and wide ribbing at the waist and cuffs.",
    highlights: [
      "Artisan garment pigment dye for unique aged patina",
      "Reinforced rib neckline with twin-needle collar stay",
      "Zero polyester blends – 100% pure combed cotton"
    ],
    yarn: "20s/2 combed",
    shrinkage: "< 1.5%",
    carton: "24 pcs / carton (16.5 kg)",
    isFeatured: false
  },
  {
    id: "flx-thermal-06",
    sku: "FLX-TH320-WHT",
    name: "Thermal Waffle Knit Longsleeve",
    category: "KNITS",
    tagline: "320 GSM Micro-Waffle Knit with Snug Rib Cuffs",
    gsm: 320,
    composition: "100% Long-Staple Cotton",
    fit: "Regular Relaxed / Dropped Hem",
    moq: 50,
    basePrice: 3750,
    pricingTiers: [
      { min: 50, max: 99, price: 3750, label: "50–99 pcs" },
      { min: 100, max: 249, price: 3100, label: "100–249 pcs" },
      { min: 250, max: 499, price: 2650, label: "250–499 pcs" },
      { min: 500, max: null, price: 2200, label: "500+ pcs" }
    ],
    colors: [
      { name: "Linen Oat", hex: "#E8DCC4", pantone: "13-0607 TCX" },
      { name: "Raw White", hex: "#F5F5F3", pantone: "11-0601 TCX" },
      { name: "Pine Green", hex: "#123F36", pantone: "19-5411 TCX" }
    ],
    sizes: ["S", "M", "L", "XL", "2XL"],
    defaultPack: "1:2:2:1",
    leadTime: "10–14 business days",
    samplePrice: 8500,
    image: "assets/images/img_oversized_tee.jpg",
    description: "Essential thermal base layer with three-dimensional waffle texture for maximum insulation and breathability. Garment-washed for ultra-soft hand feel right out of packaging.",
    highlights: [
      "Deep 3D honeycomb waffle weave structure",
      "Snug extended rib cuffs for wind prevention",
      "Flatlock comfort seams preventing skin friction"
    ],
    yarn: "30s/2 micro waffle",
    shrinkage: "< 3%",
    carton: "36 pcs / carton (15.0 kg)",
    isFeatured: false
  }
];

// Helper for formatting PKR currency
function formatPKR(amount) {
  return "Rs. " + Math.round(amount).toLocaleString('en-PK');
}

// 2. WISHLIST MANAGEMENT (LOCAL STORAGE)
let wishlist = JSON.parse(localStorage.getItem("fleex_wishlist") || '["flx-hoodie-01", "flx-tee-02"]');

function saveWishlist() {
  localStorage.setItem("fleex_wishlist", JSON.stringify(wishlist));
  updateWishlistBadges();
}

function toggleWishlist(productId, event) {
  if (event) event.stopPropagation();
  const index = wishlist.indexOf(productId);
  if (index > -1) {
    wishlist.splice(index, 1);
  } else {
    wishlist.push(productId);
  }
  saveWishlist();
  renderProducts();
  renderWishlist();
}

function updateWishlistBadges() {
  const count = wishlist.length;
  const desktopBadge = document.getElementById("wishlist-badge");
  const mobileBadge = document.getElementById("mobile-wishlist-badge");
  if (desktopBadge) desktopBadge.textContent = count;
  if (mobileBadge) mobileBadge.textContent = count;
}

// 3. NAVIGATION (TABS)
function navigateTo(tabId) {
  document.querySelectorAll(".page-tab").forEach(tab => tab.classList.remove("active"));
  document.querySelectorAll(".nav-item").forEach(item => item.classList.remove("active"));
  document.querySelectorAll(".mobile-nav-item").forEach(item => item.classList.remove("active"));

  const targetTab = document.getElementById(`tab-${tabId}`);
  if (targetTab) targetTab.classList.add("active");

  const desktopItem = document.querySelector(`.nav-item[data-tab="${tabId}"]`);
  if (desktopItem) desktopItem.classList.add("active");

  const mobileItem = document.querySelector(`.mobile-nav-item[data-tab="${tabId}"]`);
  if (mobileItem) mobileItem.classList.add("active");

  window.scrollTo({ top: 0, behavior: "smooth" });

  if (tabId === "wishlist") {
    renderWishlist();
  }
}

function scrollToGrid() {
  const el = document.getElementById("catalog-section");
  if (el) el.scrollIntoView({ behavior: "smooth" });
}

// 4. RENDERING PRODUCTS
let currentCategory = "ALL";

function renderProductLine() {
  const carousel = document.getElementById("product-line-carousel");
  if (!carousel) return;

  const featured = PRODUCTS.filter(p => p.isFeatured);
  carousel.innerHTML = featured.map(p => `
    <div class="product-line-card" onclick="openProductModal('${p.id}')">
      <img src="${p.image}" alt="${p.name}">
      <div class="card-overlay">
        <div class="line-card-top">
          <span class="gsm-pill">${p.gsm} GSM</span>
          <span class="moq-pill">MOQ ${p.moq}</span>
        </div>
        <div class="line-card-bottom">
          <h3>${p.name}</h3>
          <p class="fit">${p.fit}</p>
          <div class="line-card-price-row">
            <div class="price-box">
              <span class="from">BULK TIER FROM</span>
              <div class="rate">${formatPKR(p.pricingTiers[p.pricingTiers.length - 1].price)} / pc</div>
            </div>
            <button class="btn-card-specs">View Specs <i class="fa-solid fa-arrow-right"></i></button>
          </div>
        </div>
      </div>
    </div>
  `).join("");
}

let displayedProducts = [];
let isLoadingMoreProducts = false;

function createProductCardHTML(p) {
  const isWish = wishlist.includes(p.id);
  const lowest = p.pricingTiers[p.pricingTiers.length - 1].price;
  return `
    <div class="product-card" onclick="openProductModal('${p.id}')">
      <div class="card-img-box">
        <img src="${p.image}" alt="${p.name}" loading="lazy">
        <button class="btn-wishlist ${isWish ? 'active' : ''}" onclick="toggleWishlist('${p.id}', event)">
          <i class="${isWish ? 'fa-solid' : 'fa-regular'} fa-bookmark"></i>
        </button>
        <div style="position:absolute; bottom:6px; left:6px; display:flex; gap:4px;">
          <span class="gsm-pill">${p.gsm} GSM</span>
          <span class="moq-pill">MOQ ${p.moq}</span>
        </div>
      </div>
      <div class="card-info">
        <span class="card-category">${p.category}</span>
        <h3 class="card-title">${p.name}</h3>
        <p class="card-composition">${p.composition}</p>
        <div class="card-bottom-row">
          <div>
            <div style="font-size:9px; font-weight:700; color:var(--fleex-text-muted);">FROM</div>
            <div class="rate" style="font-size:14px; font-weight:800; color:var(--fleex-pine);">${formatPKR(lowest)} / pc</div>
          </div>
          <div class="color-dots">
            ${p.colors.slice(0, 3).map(c => `<span class="color-dot" style="background-color:${c.hex};" title="${c.name}"></span>`).join("")}
          </div>
        </div>
      </div>
    </div>
  `;
}

function getCategorySource() {
  return currentCategory === "ALL" 
    ? PRODUCTS 
    : PRODUCTS.filter(p => p.category === currentCategory);
}

function renderProducts() {
  const grid = document.getElementById("product-grid");
  if (!grid) return;

  const source = getCategorySource();
  displayedProducts = [...source];
  grid.innerHTML = displayedProducts.map(p => createProductCardHTML(p)).join("");
}

function loadMoreInfiniteProducts() {
  if (isLoadingMoreProducts) return;
  const homeTab = document.getElementById("tab-home");
  if (!homeTab || !homeTab.classList.contains("active")) return;

  const grid = document.getElementById("product-grid");
  const loader = document.getElementById("infinite-scroll-loader");
  if (!grid) return;

  const source = getCategorySource();
  if (source.length === 0) return;

  isLoadingMoreProducts = true;
  if (loader) loader.style.display = "flex";

  setTimeout(() => {
    // Append batch of products infinitely
    const batch = source.map(item => ({
      ...item,
      uniqueId: item.id + "_" + Math.random().toString(36).substring(2, 7)
    }));
    displayedProducts = displayedProducts.concat(batch);
    
    // Append cards directly to DOM for optimal performance
    const newCardsHTML = batch.map(p => createProductCardHTML(p)).join("");
    grid.insertAdjacentHTML("beforeend", newCardsHTML);

    if (loader) loader.style.display = "none";
    isLoadingMoreProducts = false;
  }, 450);
}

function filterCategory(cat) {
  currentCategory = cat;
  document.querySelectorAll(".category-tab").forEach(tab => {
    tab.classList.toggle("active", tab.dataset.category === cat);
  });
  renderProducts();
}

// 5. SEARCH & FILTERS
function handleSearch() {
  const query = (document.getElementById("search-input")?.value || "").toLowerCase();
  const cat = document.getElementById("search-cat-filter")?.value || "ALL";
  const gsm = document.getElementById("search-gsm-filter")?.value || "ALL";
  const sort = document.getElementById("search-sort-filter")?.value || "featured";

  const clearBtn = document.getElementById("clear-search-btn");
  if (clearBtn) clearBtn.style.display = query ? "block" : "none";

  let results = PRODUCTS.filter(p => {
    const matchesQuery = !query || 
      p.name.toLowerCase().includes(query) ||
      p.composition.toLowerCase().includes(query) ||
      p.sku.toLowerCase().includes(query) ||
      p.description.toLowerCase().includes(query);

    const matchesCat = cat === "ALL" || p.category === cat;

    let matchesGsm = true;
    if (gsm === "HEAVY") matchesGsm = p.gsm >= 400;
    else if (gsm === "MID") matchesGsm = p.gsm >= 250 && p.gsm < 400;
    else if (gsm === "LIGHT") matchesGsm = p.gsm < 250;

    return matchesQuery && matchesCat && matchesGsm;
  });

  // Sort
  if (sort === "gsm-desc") results.sort((a, b) => b.gsm - a.gsm);
  else if (sort === "price-asc") results.sort((a, b) => a.basePrice - b.basePrice);
  else if (sort === "price-desc") results.sort((a, b) => b.basePrice - a.basePrice);

  const grid = document.getElementById("search-results-grid");
  const countEl = document.getElementById("results-count");
  if (countEl) countEl.textContent = `Showing ${results.length} styles`;

  if (!grid) return;

  if (results.length === 0) {
    grid.innerHTML = `
      <div style="grid-column:1/-1; text-align:center; padding:60px 20px;">
        <i class="fa-solid fa-magnifying-glass" style="font-size:32px; color:var(--fleex-sage); margin-bottom:12px;"></i>
        <h3>No matching wholesale styles</h3>
        <p style="color:var(--fleex-text-muted); font-size:13px;">Try adjusting your GSM weight or keyword filters.</p>
      </div>
    `;
    return;
  }

  grid.innerHTML = results.map(p => {
    const isWish = wishlist.includes(p.id);
    const lowest = p.pricingTiers[p.pricingTiers.length - 1].price;
    return `
      <div class="product-card" onclick="openProductModal('${p.id}')">
        <div class="card-img-box">
          <img src="${p.image}" alt="${p.name}">
          <button class="btn-wishlist ${isWish ? 'active' : ''}" onclick="toggleWishlist('${p.id}', event)">
            <i class="${isWish ? 'fa-solid' : 'fa-regular'} fa-bookmark"></i>
          </button>
          <div style="position:absolute; bottom:8px; left:8px; display:flex; gap:6px;">
            <span class="gsm-pill">${p.gsm} GSM</span>
            <span class="moq-pill">MOQ ${p.moq}</span>
          </div>
        </div>
        <div class="card-info">
          <span class="card-category">${p.category}</span>
          <h3 class="card-title">${p.name}</h3>
          <p class="card-composition">${p.composition}</p>
          <div class="card-bottom-row">
            <div>
              <div style="font-size:9px; font-weight:700; color:var(--fleex-text-muted);">FROM</div>
              <div style="font-size:14px; font-weight:800; color:var(--fleex-pine);">${formatPKR(lowest)} / pc</div>
            </div>
            <div class="color-dots">
              ${p.colors.map(c => `<span class="color-dot" style="background-color:${c.hex};" title="${c.name}"></span>`).join("")}
            </div>
          </div>
        </div>
      </div>
    `;
  }).join("");
}

function clearSearch() {
  const input = document.getElementById("search-input");
  if (input) input.value = "";
  handleSearch();
}

// 6. WISHLIST RENDERING
function renderWishlist() {
  const emptyState = document.getElementById("wishlist-empty-state");
  const content = document.getElementById("wishlist-content");
  const list = document.getElementById("wishlist-items-list");

  if (!emptyState || !content || !list) return;

  const savedProducts = PRODUCTS.filter(p => wishlist.includes(p.id));

  if (savedProducts.length === 0) {
    emptyState.style.display = "block";
    content.style.display = "none";
  } else {
    emptyState.style.display = "none";
    content.style.display = "block";

    list.innerHTML = savedProducts.map(p => `
      <div class="wishlist-item-row" onclick="openProductModal('${p.id}')" style="cursor:pointer;">
        <img src="${p.image}" alt="${p.name}">
        <div class="wishlist-item-info">
          <h4>${p.name}</h4>
          <p style="font-size:11px; color:var(--fleex-sage); font-weight:700;">${p.gsm} GSM • SKU: ${p.sku}</p>
          <p>Tier Starting from ${formatPKR(p.pricingTiers[p.pricingTiers.length - 1].price)}/pc • MOQ ${p.moq} pcs</p>
        </div>
        <button class="btn-remove-wishlist" onclick="toggleWishlist('${p.id}', event)" title="Remove style">
          <i class="fa-solid fa-trash"></i>
        </button>
      </div>
    `).join("");
  }
}

// 7. PRODUCT DETAIL MODAL & DIRECT WHATSAPP ORDERING
let modalActiveProduct = null;
let modalSelectedColor = "";
let modalSelectedSize = "";
let modalQuantity = 50;
let modalCustomBranding = true;

function openProductModal(productId) {
  const product = PRODUCTS.find(p => p.id === productId);
  if (!product) return;

  modalActiveProduct = product;
  modalSelectedColor = product.colors[0].name;
  modalSelectedSize = product.sizes[0] || "M";
  modalQuantity = product.moq;
  modalCustomBranding = true;

  renderModalContent();
  document.getElementById("product-modal-backdrop").classList.add("open");
  document.body.style.overflow = "hidden";
}

function closeProductModal(e) {
  if (e && e.target && e.target.id !== "product-modal-backdrop" && !e.target.closest(".modal-close-btn")) {
    return;
  }
  document.getElementById("product-modal-backdrop").classList.remove("open");
  document.body.style.overflow = "";
}

function calculateTierPrice(product, qty) {
  for (let i = 0; i < product.pricingTiers.length; i++) {
    const tier = product.pricingTiers[i];
    if (qty >= tier.min && (tier.max === null || qty <= tier.max)) {
      return tier.price;
    }
  }
  return product.pricingTiers[product.pricingTiers.length - 1].price;
}

function renderModalContent() {
  const p = modalActiveProduct;
  if (!p) return;

  const unitRate = calculateTierPrice(p, modalQuantity);
  const totalSubtotal = unitRate * modalQuantity;

  const body = document.getElementById("modal-body-content");
  body.innerHTML = `
    <div class="modal-detail-layout">
      <!-- Gallery Column -->
      <div class="modal-gallery">
        <img src="${p.image}" alt="${p.name}">
        <div style="margin-top:16px; background:var(--fleex-cream-light); border:1px solid var(--fleex-border); border-radius:8px; padding:14px;">
          <h4 style="font-size:12px; font-weight:800; color:var(--fleex-pine); margin-bottom:8px;">MANUFACTURING HIGHLIGHTS</h4>
          <ul style="padding-left:18px; font-size:12px; color:var(--fleex-text-muted); line-height:1.6;">
            ${p.highlights.map(h => `<li>${h}</li>`).join("")}
          </ul>
        </div>
      </div>

      <!-- Specs & Order Column -->
      <div class="modal-specs-col">
        <span class="gold-pill">WHOLESALE SPECIFICATION</span>
        <h2 style="margin-top:8px;">${p.name}</h2>
        <p class="modal-tagline">${p.tagline}</p>
        <p style="font-size:13px; color:var(--fleex-text-muted); margin-bottom:14px;">${p.description}</p>

        <!-- Tiered Pricing Matrix -->
        <div class="tier-table-box">
          <h4>VOLUME TIER PRICING (PKR - PAKISTANI RUPEES)</h4>
          ${p.pricingTiers.map(t => {
            const isActive = modalQuantity >= t.min && (t.max === null || modalQuantity <= t.max);
            return `
              <div class="tier-row ${isActive ? 'active' : ''}">
                <span>${t.label}</span>
                <span class="tier-price">${formatPKR(t.price)} / unit</span>
              </div>
            `;
          }).join("")}
        </div>

        <!-- Color Selection -->
        <div>
          <label style="font-size:12px; font-weight:700; color:var(--fleex-pine-dark);">Select Colorway (${modalSelectedColor}):</label>
          <div class="color-swatches-row">
            ${p.colors.map(c => `
              <button class="swatch-btn ${modalSelectedColor === c.name ? 'active' : ''}" onclick="selectModalColor('${c.name}')">
                <span class="color-dot" style="background-color:${c.hex};"></span>
                <span>${c.name}</span>
              </button>
            `).join("")}
          </div>
        </div>

        <!-- Size Pack Selection -->
        <div>
          <label style="font-size:12px; font-weight:700; color:var(--fleex-pine-dark);">Select Size Focus or Ratio:</label>
          <div class="size-boxes-row">
            ${p.sizes.map(s => `
              <button class="size-box-btn ${modalSelectedSize === s ? 'active' : ''}" onclick="selectModalSize('${s}')">${s}</button>
            `).join("")}
          </div>
          <p style="font-size:11px; color:var(--fleex-text-muted); margin-bottom:12px;">Standard carton ratio: ${p.defaultPack}</p>
        </div>

        <!-- Quantity Stepper -->
        <div>
          <label style="font-size:12px; font-weight:700; color:var(--fleex-pine-dark);">Order Quantity (MOQ: ${p.moq} pcs):</label>
          <div class="volume-selector-row">
            <input type="number" class="volume-input" value="${modalQuantity}" min="${p.moq}" step="10" onchange="updateModalQuantity(this.value)">
            <button class="category-tab" onclick="setPresetQty(50)">50 pcs</button>
            <button class="category-tab" onclick="setPresetQty(100)">100 pcs</button>
            <button class="category-tab" onclick="setPresetQty(250)">250 pcs</button>
            <button class="category-tab" onclick="setPresetQty(500)">500 pcs</button>
          </div>
        </div>

        <!-- Custom Labeling Checkbox -->
        <div style="margin:14px 0; display:flex; align-items:center; gap:8px;">
          <input type="checkbox" id="modal-branding-check" ${modalCustomBranding ? 'checked' : ''} onchange="modalCustomBranding = this.checked">
          <label for="modal-branding-check" style="font-size:12px; font-weight:600; cursor:pointer;">
            Include Custom Private Labeling (Woven neck tags & custom polybag)
          </label>
        </div>

        <!-- Live Subtotal Calculation Box -->
        <div class="order-summary-box">
          <div class="summary-row">
            <span>Unit Wholesale Rate:</span>
            <span style="font-weight:700; color:var(--fleex-gold);">${formatPKR(unitRate)} / pc</span>
          </div>
          <div class="summary-row">
            <span>Order Quantity:</span>
            <span>${modalQuantity} units</span>
          </div>
          <div class="summary-total">
            <span>ESTIMATED SUBTOTAL:</span>
            <span class="total-amount">${formatPKR(totalSubtotal)} PKR</span>
          </div>
        </div>

        <!-- Direct WhatsApp Order Button -->
        <button class="btn-whatsapp-order-modal" onclick="sendWhatsAppOrder()">
          <i class="fa-brands fa-whatsapp" style="font-size:20px;"></i>
          <span>Direct Order via WhatsApp (PKR)</span>
        </button>

        <!-- Secondary Action: Fit Sample -->
        <button class="btn-primary" style="width:100%; margin-top:8px; background:none; color:var(--fleex-pine); border-color:var(--fleex-pine);" onclick="sendSampleInquiry()">
          Request Single Fit Sample (${formatPKR(p.samplePrice)}) via WhatsApp
        </button>
      </div>
    </div>
  `;
}

function selectModalColor(name) {
  modalSelectedColor = name;
  renderModalContent();
}

function selectModalSize(size) {
  modalSelectedSize = size;
  renderModalContent();
}

function updateModalQuantity(val) {
  const num = parseInt(val) || modalActiveProduct.moq;
  modalQuantity = Math.max(num, modalActiveProduct.moq);
  renderModalContent();
}

function setPresetQty(qty) {
  modalQuantity = qty;
  renderModalContent();
}

// 8. WHATSAPP LAUNCHERS & ENCODERS (WITH PAKISTANI RUPEES)
function sendWhatsAppOrder() {
  const p = modalActiveProduct;
  if (!p) return;

  const unitRate = calculateTierPrice(p, modalQuantity);
  const totalSubtotal = unitRate * modalQuantity;

  const message = 
`📦 *FLEEX GARMENTS • WHOLESALE ORDER INQUIRY*
Official Portal: ${OFFICIAL_PORTAL}
────────────────────────
• *Style:* ${p.name}
• *SKU:* ${p.sku}
• *Fabric:* ${p.gsm} GSM • ${p.composition}
• *Selected Color:* ${modalSelectedColor}
• *Size Pack / Focus:* ${modalSelectedSize} (Ratio: ${p.defaultPack})
• *Order Volume:* ${modalQuantity} units (MOQ: ${p.moq} pcs)
• *Wholesale Rate:* ${formatPKR(unitRate)} / unit
• *Estimated Subtotal:* ${formatPKR(totalSubtotal)} PKR
• *Private Label / Tags:* ${modalCustomBranding ? "Yes (Custom woven labels & polybags)" : "Blank Blanks"}
• *Estimated Lead Time:* ${p.leadTime}
────────────────────────
Please provide production schedule, shipping quote (Air / Sea DDP), and sample digital approval details.`;

  const url = `https://api.whatsapp.com/send?phone=${WHATSAPP_PHONE}&text=${encodeURIComponent(message)}`;
  window.open(url, "_blank");
}

function sendSampleInquiry() {
  const p = modalActiveProduct;
  if (!p) return;

  const message = 
`Hello Fleex Garments Wholesale!
I would like to order a sample piece of *${p.name}* (SKU: ${p.sku})
• Color: ${modalSelectedColor}
• Size: ${modalSelectedSize}
• Sample Price: ${formatPKR(p.samplePrice)} PKR
Please send payment details for immediate dispatch.`;

  const url = `https://api.whatsapp.com/send?phone=${WHATSAPP_PHONE}&text=${encodeURIComponent(message)}`;
  window.open(url, "_blank");
}

function requestMultiStyleQuote() {
  const saved = PRODUCTS.filter(p => wishlist.includes(p.id));
  if (saved.length === 0) return;

  let text = `📦 *FLEEX GARMENTS • MULTI-STYLE WHOLESALE INQUIRY*\nOfficial Portal: ${OFFICIAL_PORTAL}\nI would like to request bulk quotations and line-sheet specs for the following saved styles in Pakistani Rupees (PKR):\n\n`;
  saved.forEach((p, idx) => {
    text += `${idx + 1}. *${p.name}* (SKU: ${p.sku})\n   • Weight: ${p.gsm} GSM | MOQ: ${p.moq} pcs\n   • Starting Rate: ${formatPKR(p.basePrice)}/pc\n`;
  });
  text += `\nPlease send complete volume tier price sheets and sample swatches catalog.\nThank you!`;

  const url = `https://api.whatsapp.com/send?phone=${WHATSAPP_PHONE}&text=${encodeURIComponent(text)}`;
  window.open(url, "_blank");
}

function openDirectWholesaleChat(customNote) {
  let text = `Hello Fleex Garments Wholesale Team! I am reviewing your wholesale catalog on ${OFFICIAL_PORTAL} and would like to connect with a factory production manager.`;
  if (customNote) {
    text += ` Subject: ${customNote}`;
  }
  const url = `https://api.whatsapp.com/send?phone=${WHATSAPP_PHONE}&text=${encodeURIComponent(text)}`;
  window.open(url, "_blank");
}

// 9. PROFILE MANAGEMENT
function toggleEditProfile() {
  const displayView = document.getElementById("profile-display-view");
  const editView = document.getElementById("profile-edit-view");
  if (!displayView || !editView) return;

  const isEditing = editView.style.display !== "none";
  displayView.style.display = isEditing ? "block" : "none";
  editView.style.display = isEditing ? "none" : "block";
}

function saveProfile(e) {
  e.preventDefault();
  const brand = document.getElementById("input-brand-name").value;
  const buyer = document.getElementById("input-buyer-name").value;
  const tax = document.getElementById("input-tax-id").value;
  const port = document.getElementById("input-port").value;

  document.getElementById("display-brand-name").textContent = brand;
  document.getElementById("display-buyer-name").textContent = buyer;
  document.getElementById("display-tax-id").textContent = tax;
  document.getElementById("display-port").textContent = port;

  toggleEditProfile();
}

// 10. INITIALIZATION & INFINITE SCROLL LISTENER
document.addEventListener("DOMContentLoaded", () => {
  renderProductLine();
  renderProducts();
  handleSearch();
  updateWishlistBadges();

  // Infinite Scroll Listener for Products Grid
  window.addEventListener("scroll", () => {
    const scrollPosition = window.innerHeight + window.scrollY;
    const threshold = document.documentElement.scrollHeight - 380;
    if (scrollPosition >= threshold) {
      loadMoreInfiniteProducts();
    }
  }, { passive: true });
});
