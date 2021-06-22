package com.gildedrose.rules

import com.gildedrose.Item
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class DefaultRuleTest {

    private val rule: DefaultRule by lazy {
        DefaultRule()
    }

    @Test
    internal fun `Default rule should always be applied`() {
        val foo = Item("foo", 3, 3)
        assertTrue(rule.shouldApplyRule(foo))

        val bar = Item("bar", -3, 500)
        assertTrue(rule.shouldApplyRule(bar))
    }


    @Test
    internal fun `Quality of item decreases as expected`() {
        val item = Item("foo", 1, 1)
        rule.adjustQuality(item)

        assertEquals(0, item.sellIn)
        assertEquals(0, item.quality)
    }

    @Test
    internal fun `Quality can never be negative`() {
        val item = Item("foo", 5, 0)
        rule.adjustQuality(item)

        assertEquals(0, item.quality)
    }

    @Test
    internal fun `Quality decreases twice as fast after sellIn`() {
        val item = Item("foo", -5, 4)
        rule.adjustQuality(item)

        assertEquals(2, item.quality)
    }

    @Test
    internal fun `Quality decreases normally when reaching sellIn date`() {
        val item = Item("foo", 1, 5)
        rule.adjustQuality(item)
        assertEquals(4, item.quality)

        rule.adjustQuality(item)
        assertEquals(2, item.quality)
    }
}