package com.gildedrose.rules

import com.gildedrose.Item
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class BackstagePassesRuleTest {

    private companion object {
        private const val DEFAULT_EVENT_NAME = "Backstage passes to a TAFKAL80ETC concert"
    }

    private val rule by lazy {
        BackstagePassesRule()
    }

    @Test
    internal fun `Only apply rule when name starts with backstage passes to`() {
        val item = Item("Backstage passes to Rock Werchter", 5, 5)
        assertTrue(rule.shouldApplyRule(item))

        val item2 = Item("Backstage passes to blabla", 5, 5)
        assertTrue(rule.shouldApplyRule(item2))
    }

    @Test
    internal fun `Backstage rule check is not case sensitive`() {
        val item = Item("Backstage PASSES to Rock Werchter", 5, 5)
        assertTrue(rule.shouldApplyRule(item))
    }

    @Test
    internal fun `Decrease sellIn value when adjusting quality`() {
        val item = generateItem(sellIn = 20, quality = 10)
        rule.adjustQuality(item)

        assertEquals(19, item.sellIn)
    }

    @Test
    internal fun `Increase quality when sellin value approaches`() {
        val item = generateItem(sellIn = 20, quality = 10)
        rule.adjustQuality(item)

        assertEquals(11, item.quality)
    }

    @Test
    internal fun `Increase quality when there are 10 days`() {
        val item = generateItem(sellIn = 11, quality = 10)
        // sellIn 11 --> sellIn 10
        rule.adjustQuality(item)

        assertEquals(12, item.quality)
    }

    @Test
    internal fun `Increase quality when there are less than 10 days`() {
        val item = generateItem(sellIn = 10, quality = 10)
        // sellIn 11 --> sellIn 10
        rule.adjustQuality(item)

        assertEquals(12, item.quality)
    }

    @Test
    internal fun `Increase quality when there are 5 days left`() {
        val item = generateItem(sellIn = 6, quality = 10)
        // sellIn 11 --> sellIn 10
        rule.adjustQuality(item)

        assertEquals(13, item.quality)
    }

    @Test
    internal fun `Increase quality when there are less than 5 days left`() {
        val item = generateItem(sellIn = 4, quality = 10)
        // sellIn 11 --> sellIn 10
        rule.adjustQuality(item)

        assertEquals(13, item.quality)
    }

    @Test
    internal fun `Loose quality when sellIn expires`() {
        val item = generateItem(sellIn = 0, quality = 10)
        // sellIn 11 --> sellIn 10
        rule.adjustQuality(item)

        assertEquals(0, item.quality)
    }

    @Test
    internal fun `Quality can not go over 50 when more than 10 days left`() {
        val item = generateItem(sellIn = 20, quality = 50)
        // sellIn 11 --> sellIn 10
        rule.adjustQuality(item)

        assertEquals(50, item.quality)
    }

    @Test
    internal fun `Quality can not go over 50 when when sellIn between 5 and 10 left`() {
        val item = generateItem(sellIn = 7, quality = 49)
        // sellIn 11 --> sellIn 10
        rule.adjustQuality(item)

        assertEquals(50, item.quality)
    }

    @Test
    internal fun `Quality can not go over 50 when when sellIn is less than 5`() {
        val item = generateItem(sellIn = 2, quality = 49)
        // sellIn 11 --> sellIn 10
        rule.adjustQuality(item)

        assertEquals(50, item.quality)
    }

    private fun generateItem(sellIn: Int, quality: Int) = Item(DEFAULT_EVENT_NAME, sellIn, quality)
}