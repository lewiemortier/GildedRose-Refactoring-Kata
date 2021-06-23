package com.gildedrose

import com.gildedrose.rules.AgedBrieRule
import com.gildedrose.rules.DefaultRule
import com.gildedrose.rules.GildedRoseRule

class GildedRose(var items: Array<Item>) {

    companion object {
        private const val AGED_BRIE = "Aged Brie"
        private const val BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert"
        private const val SULFURAS = "Sulfuras, Hand of Ragnaros"
    }

    /**
     * A list of rules that should be applied to the items when the quality should be updated.
     *
     * Keep in mind that the order of the rules matter.
     * The first rule that can handle a specific item, will be applied.
     * None of the other rules will be.
     */
    private val rules: List<GildedRoseRule> = listOf(
            AgedBrieRule(),
            DefaultRule()
    )

    fun updateQuality() {
        if (rules.isEmpty()) {
            // Sanity check, but should never happen as we actually own the rules.
            throw IllegalArgumentException("Can't update quality without rules")
        }

        for (item in items) {
            for (rule in rules) {
                if (rule.shouldApplyRule(item)) {
                    rule.adjustQuality(item)
                    break
                }
            }
        }
    }
}

