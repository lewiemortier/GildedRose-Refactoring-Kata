package com.gildedrose.rules

import com.gildedrose.Item

class DefaultRule : GildedRoseRule {

    override fun shouldApplyRule(item: Item): Boolean = true

    override fun adjustQuality(item: Item) {
        item.sellIn = item.sellIn.dec()

        if (item.sellIn < 0) {
            item.quality = item.quality - 2
        } else {
            item.quality = item.quality - 1
        }

        if (item.quality < 0) {
            item.quality = 0
        }
    }
}