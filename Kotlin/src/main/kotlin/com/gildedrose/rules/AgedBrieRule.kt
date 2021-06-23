package com.gildedrose.rules

import com.gildedrose.Item

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