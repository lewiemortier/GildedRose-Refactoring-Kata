package com.gildedrose.rules

import com.gildedrose.Item

/**
 * A rule is used to determine of the quality should be adjusted or not.
 */
interface GildedRoseRule {

    /**
     * Does this rule apply to the provided [item]
     *
     * @return true if this rule can handle the provided [item], false if we should skip to the next rule
     */
    fun shouldApplyRule(item: Item): Boolean

    /**
     * Adjust the quality of the item.
     *
     * Note: the sellIn property of the [item] is already adjusted when this is called
     */
    fun adjustQuality(item: Item)
}