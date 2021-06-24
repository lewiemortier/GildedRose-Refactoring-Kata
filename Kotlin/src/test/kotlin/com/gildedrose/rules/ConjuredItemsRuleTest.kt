package com.gildedrose.rules

import com.gildedrose.Item
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ConjuredItemsRuleTest {

    private val rule by lazy {
        ConjuredItemsRule()
    }

    @Test
    internal fun `Apply rule when item starts with conjured`() {
        val item = Item("Conjured item", 5, 2)
        assertTrue(rule.shouldApplyRule(item))

        val itemWithTypo = Item("Conjures", 5, 3)
        assertFalse(rule.shouldApplyRule(itemWithTypo))
    }

    @Test
    internal fun `Name of item is checked while ignoring case`() {
        val item = Item("conJURed item", 5, 5)
        assertTrue(rule.shouldApplyRule(item))
    }

    @Test
    internal fun `SellIn decreases when adjusting quality`() {
        val item = generateItem(5, 6)
        rule.adjustQuality(item)

        assertEquals(4, item.sellIn)
    }

    @Test
    internal fun `Quality degrades twice as fast`() {
        val item = generateItem(5, 6)
        rule.adjustQuality(item)

        assertEquals(4, item.quality)
    }

    @Test
    internal fun `Quality can never go below 0`() {
        val item = generateItem(10, 0)
        rule.adjustQuality(item)
        assertEquals(0, item.quality)
    }

    @Test
    internal fun `Quality never goes below 0 if sellIn is below 0`() {
        val item = generateItem(-5, 0)
        rule.adjustQuality(item)

        assertEquals(0, item.quality)
        assertEquals(-6, item.sellIn)
    }

    @Test
    internal fun `When sellIn passed, quality degrades twice as fast`() {
        val item = generateItem(0, 10)
        rule.adjustQuality(item)

        assertEquals(6, item.quality)
    }

    private fun generateItem(sellIn: Int, quality: Int) = Item("Conjured item", sellIn, quality)
}