package com.gildedrose.rules

import com.gildedrose.Item
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class AgedBrieRuleTest {

    private val rule by lazy {
        AgedBrieRule()
    }

    @Test
    internal fun `Only apply rule if name is exactly Aged Brie`() {
        val item = Item("Aged Brie", 5, 5)
        assertTrue(rule.shouldApplyRule(item))
    }

    @Test
    internal fun `Rule should be case sensitive`() {
        val item = Item("aged brie", 5, 5)
        assertFalse(rule.shouldApplyRule(item))
    }

    @Test
    internal fun `SellIn is decreased when adjusting quality`() {
        val item = generateItem(sellIn = 5, quality = 5)
        rule.adjustQuality(item)

        assertEquals(4, item.sellIn)
    }

    @Test
    internal fun `Quality increases the older it gets`() {
        val item = generateItem(5)
        rule.adjustQuality(item)

        assertEquals(6, item.quality)
    }

    @Test
    internal fun `Quality never goes over 50`() {
        val item = generateItem(49)

        rule.adjustQuality(item)
        assertEquals(50, item.quality)

        rule.adjustQuality(item)
        assertEquals(50, item.quality)
    }

    @Test
    internal fun `When item goes out of date, quality increases twice as fast`() {
        val item = generateItem(4, sellIn = 0)

        rule.adjustQuality(item)
        assertEquals(6, item.quality)

        rule.adjustQuality(item)
        assertEquals(8, item.quality)
    }

    private fun generateItem(quality: Int, sellIn: Int = 5) = Item("Aged Brie", sellIn, quality)
}