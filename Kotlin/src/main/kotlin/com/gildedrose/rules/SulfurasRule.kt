package com.gildedrose.rules

import com.gildedrose.Item

class SulfurasRule : GildedRoseRule {
    override fun shouldApplyRule(item: Item): Boolean {
        return item.name.startsWith("Sulfuras", ignoreCase = true)
    }

    override fun adjustQuality(item: Item) {
        // Nothing to do in this case
    }
}