package com.gildedrose

import com.gildedrose.rules.*

class GildedRose(var items: Array<Item>) {

    /**
     * A list of rules that should be applied to the items when the quality should be updated.
     *
     * Keep in mind that the order of the rules matter.
     * The first rule that can handle a specific item, will be applied.
     * None of the other rules will be.
     */
    private val rules: List<GildedRoseRule> = listOf(
            SulfurasRule(),
            AgedBrieRule(),
            BackstagePassesRule(),
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

