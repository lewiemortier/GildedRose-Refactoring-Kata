package com.gildedrose

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
    private val rules: List<GildedRoseRule>

    init {
        rules = listOf(
                DefaultRule()
        )
    }

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
//        for (i in items.indices) {
//            if (items[i].name != AGED_BRIE && items[i].name != BACKSTAGE_PASSES) {
//                if (items[i].quality > 0) {
//                    if (items[i].name != SULFURAS) {
//                        items[i].quality = items[i].quality - 1
//                    }
//                }
//            } else {
//                if (items[i].quality < 50) {
//                    items[i].quality = items[i].quality + 1
//
//                    if (items[i].name == BACKSTAGE_PASSES) {
//                        if (items[i].sellIn < 11) {
//                            if (items[i].quality < 50) {
//                                items[i].quality = items[i].quality + 1
//                            }
//                        }
//
//                        if (items[i].sellIn < 6) {
//                            if (items[i].quality < 50) {
//                                items[i].quality = items[i].quality + 1
//                            }
//                        }
//                    }
//                }
//            }
//
//            if (items[i].name != SULFURAS) {
//                items[i].sellIn = items[i].sellIn - 1
//            }
//
//            if (items[i].sellIn < 0) {
//                if (items[i].name != AGED_BRIE) {
//                    if (items[i].name != BACKSTAGE_PASSES) {
//                        if (items[i].quality > 0) {
//                            if (items[i].name != SULFURAS) {
//                                items[i].quality = items[i].quality - 1
//                            }
//                        }
//                    } else {
//                        items[i].quality = items[i].quality - items[i].quality
//                    }
//                } else {
//                    if (items[i].quality < 50) {
//                        items[i].quality = items[i].quality + 1
//                    }
//                }
//            }
//        }
    }
}

