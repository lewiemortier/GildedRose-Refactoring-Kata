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
            ConjuredItemsRule(),
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
            // Find the first rule that should be applied for this item.
            // If, for some reason there is no rule to apply, use the default one.
            val ruleToApply = rules.firstOrNull { it.shouldApplyRule(item) } ?: DefaultRule()
            ruleToApply.adjustQuality(item)
        }
    }
}

