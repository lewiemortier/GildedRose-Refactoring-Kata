package com.gildedrose.rules

import com.gildedrose.Item

/**
 * A rule that only applies to conjured items.
 * A conjured item degrades in quality twice as fast as regular items
 */
class ConjuredItemsRule : GildedRoseRule {

    override fun shouldApplyRule(item: Item): Boolean {
        return item.name.startsWith("Conjured", ignoreCase = true)
    }

    override fun adjustQuality(item: Item) {
        item.sellIn = item.sellIn.dec()
        if (item.sellIn < 0) {
            item.quality = maxOf(
                    0,
                    item.quality - 4
            )
        } else {
            item.quality = maxOf(
                    0,
                    item.quality - 2
            )
        }
    }
}