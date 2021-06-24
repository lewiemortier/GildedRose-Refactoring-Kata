package com.gildedrose.rules

import com.gildedrose.Item

/**
 * Special case: Sulfuras
 *
 * This item never changes quality, and never has to be sold. It always remains the same
 */
class SulfurasRule : GildedRoseRule {
    override fun shouldApplyRule(item: Item): Boolean {
        return item.name.startsWith("Sulfuras", ignoreCase = true)
    }

    override fun adjustQuality(item: Item) {
        // Nothing to do in this case
    }
}