package com.gildedrose.rules

import com.gildedrose.Item

class BackstagePassesRule : GildedRoseRule {

    private companion object {
        private const val PREFIX = "Backstage passes to "
    }

    override fun shouldApplyRule(item: Item): Boolean {
        return item.name.startsWith(PREFIX, ignoreCase = true)
    }

    override fun adjustQuality(item: Item) {
        item.sellIn = item.sellIn.dec()
        when {
            item.sellIn >= 10 -> item.quality = minOf(50, item.quality.inc())
            item.sellIn >= 5 -> item.quality = minOf(50, item.quality + 2)
            item.sellIn >= 0 -> item.quality = minOf(50, item.quality + 3)
            item.sellIn < 0 -> item.quality = 0
        }
    }
}