package com.gildedrose.rules

import com.gildedrose.Item

/**
 * A special rule for Aged Brie
 *
 * Aged Brie is special in the way that, instead of decreasing in quality, it increases in quality.
 * When the sellIn has passed, it increases in quality twice as fast.
 */
class AgedBrieRule : GildedRoseRule {

    override fun shouldApplyRule(item: Item): Boolean {
        return item.name == "Aged Brie"
    }

    override fun adjustQuality(item: Item) {
        item.sellIn = item.sellIn.dec()
        if (item.sellIn < 0) {
            item.quality = minOf(50, item.quality.inc() + 1)
        } else {
            item.quality = minOf(50, item.quality.inc())
        }
    }
}