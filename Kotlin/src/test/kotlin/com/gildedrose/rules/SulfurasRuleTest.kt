package com.gildedrose.rules

import com.gildedrose.Item
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class SulfurasRuleTest {

    private val rule by lazy {
        SulfurasRule()
    }

    @Test
    internal fun `Apply rule when name starts with Sulfuras`() {
        val item = Item("Sulfuras", 2, 2)
        assertTrue(rule.shouldApplyRule(item))
    }

    @Test
    internal fun `Apply rule on item name is not case sensitive`() {
        val item = Item("SulfuraS", 2, 2)
        assertTrue(rule.shouldApplyRule(item))
    }

    @Test
    internal fun `Nothing happens when adjusting quality`() {
        val item = Item("Sulfuras", 5, 10)
        rule.adjustQuality(item)
        assertEquals(5, item.sellIn)
        assertEquals(10, item.quality)
    }
}