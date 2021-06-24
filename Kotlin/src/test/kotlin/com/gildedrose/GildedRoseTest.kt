package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    internal fun `regular item decreases as expected`() {
        val items = arrayOf(Item("foo", 1, 1))
        val app = GildedRose(items)
        app.updateQuality()

        app.assertQualityAndSellIn(
                expectedSellIn = 0,
                expectedQuality = 0
        )
    }

    @Test
    internal fun `Quality decreases twice as fast after expiry date passed`() {
        val item = Item("foo", 0, 4)
        val app = GildedRose(arrayOf(item))
        app.updateQuality()

        app.assertQualityAndSellIn(
                expectedSellIn = -1,
                expectedQuality = 2
        )
    }

    @Test
    internal fun `Quality decreases normally when reaching sellIn date`() {
        val item = Item("foo", 1, 5)
        val app = GildedRose(arrayOf(item))
        app.updateQuality()

        app.assertQualityAndSellIn(
                expectedSellIn = 0,
                expectedQuality = 4
        )

        app.updateQuality()
        app.assertQualityAndSellIn(
                expectedSellIn = -1,
                expectedQuality = 2
        )
    }

    @Test
    internal fun `Quality decreases after sellIn reached 0 and quality is 0`() {
        val item = Item("foo", 0, 2)
        val app = GildedRose(arrayOf(item))
        app.updateQuality()

        app.assertQualityAndSellIn(
                expectedSellIn = -1,
                expectedQuality = 0
        )
    }

    @Test
    internal fun `Quality can never be negative`() {
        val item = Item("foo", 3, 1)
        val app = GildedRose(arrayOf(item))

        app.updateQuality()
        app.assertQualityAndSellIn(
                expectedSellIn = 2,
                expectedQuality = 0
        )

        app.updateQuality()
        app.assertQualityAndSellIn(
                expectedSellIn = 1,
                expectedQuality = 0
        )
    }

    @Test
    internal fun `Aged Brie increases in quality`() {
        val item = Item("Aged Brie", 3, 3)
        val app = GildedRose(arrayOf(item))

        app.updateQuality()
        app.assertQualityAndSellIn(
                expectedSellIn = 2,
                expectedQuality = 4
        )
    }

    @Test
    internal fun `Aged Brie increases quality after sellIn date`() {
        val item = Item("Aged Brie", 0, 3)
        val app = GildedRose(arrayOf(item))

        app.updateQuality()
        app.assertQualityAndSellIn(
                expectedSellIn = -1,
                expectedQuality = 5
        )

        app.updateQuality()
        app.assertQualityAndSellIn(
                expectedSellIn = -2,
                expectedQuality = 7
        )
    }

    @Test
    internal fun `Aged Brie is case sensitive`() {
        val item = Item("aged brie", 3, 3)
        val app = GildedRose(arrayOf(item))

        app.updateQuality()
        app.assertQualityAndSellIn(
                expectedSellIn = 2,
                expectedQuality = 2
        )
    }

    @Test
    internal fun `Quality can never go over 50`() {
        val item = Item("Aged Brie", 3, 49)
        val app = GildedRose(arrayOf(item))

        app.updateQuality()
        app.assertQualityAndSellIn(
                expectedSellIn = 2,
                expectedQuality = 50
        )

        app.updateQuality()
        app.assertQualityAndSellIn(
                expectedSellIn = 1,
                expectedQuality = 50
        )
    }

    @Test
    internal fun `Sulfuras never decreases in quality`() {
        val item = Item("Sulfuras, Hand of Ragnaros", 20, 20)
        val app = GildedRose(arrayOf(item))

        app.updateQuality()
        app.assertQualityAndSellIn(
                expectedSellIn = 20,
                expectedQuality = 20
        )
    }

    @Test
    internal fun `Backstage passes increase in quality`() {
        val item = Item("Backstage passes to a TAFKAL80ETC concert", 20, 20)
        val app = GildedRose(arrayOf(item))

        app.updateQuality()
        app.assertQualityAndSellIn(
                expectedSellIn = 19,
                expectedQuality = 21
        )
    }

    @Test
    internal fun `Backstage passes increase in quality 11 days before sellIn`() {
        val item = Item("Backstage passes to a TAFKAL80ETC concert", 10, 20)
        val app = GildedRose(arrayOf(item))

        app.updateQuality()
        app.assertQualityAndSellIn(
                expectedSellIn = 9,
                expectedQuality = 22
        )
    }

    @Test
    internal fun `Backstage passes increase in quality 5 days before sellIn`() {
        val item = Item("Backstage passes to a TAFKAL80ETC concert", 4, 20)
        val app = GildedRose(arrayOf(item))

        app.updateQuality()
        app.assertQualityAndSellIn(
                expectedSellIn = 3,
                expectedQuality = 23
        )
    }

    @Test
    internal fun `Backstage passes lose quality after sellIn date`() {
        val item = Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)
        val app = GildedRose(arrayOf(item))

        app.updateQuality()
        app.assertQualityAndSellIn(
                expectedSellIn = -1,
                expectedQuality = 0
        )
    }

    @Test
    internal fun `Backstage passes cannot go over 50 in quality`() {
        val item = Item("Backstage passes to a TAFKAL80ETC concert", 1, 48)

        val app = GildedRose(arrayOf(item))

        app.updateQuality()
        app.assertQualityAndSellIn(
                expectedSellIn = 0,
                expectedQuality = 50
        )
    }

    private fun GildedRose.assertQualityAndSellIn(expectedSellIn: Int, expectedQuality: Int, itemIndex: Int = 0) {
        val item = items[itemIndex]
        assertEquals(expectedSellIn, item.sellIn)
        assertEquals(expectedQuality, item.quality)
    }
}


