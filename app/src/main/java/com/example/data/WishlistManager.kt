package com.example.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object WishlistManager {
    private val _wishlistIds = MutableStateFlow<Set<String>>(setOf("flx-hoodie-01", "flx-tee-02"))
    val wishlistIds: StateFlow<Set<String>> = _wishlistIds.asStateFlow()

    fun toggleWishlist(productId: String) {
        val current = _wishlistIds.value.toMutableSet()
        if (current.contains(productId)) {
            current.remove(productId)
        } else {
            current.add(productId)
        }
        _wishlistIds.value = current
    }

    fun isWishlisted(productId: String): Boolean {
        return _wishlistIds.value.contains(productId)
    }

    fun clear() {
        _wishlistIds.value = emptySet()
    }
}
