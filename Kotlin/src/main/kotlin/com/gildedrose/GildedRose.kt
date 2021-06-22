package com.gildedrose

class GildedRose(var items: Array<Item>) {

    companion object {
        private const val AGED_BRIE = "Aged Brie"
        private const val BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert"
        private const val SULFURAS = "Sulfuras, Hand of Ragnaros"
    }

    fun updateQuality() {
        for (i in items.indices) {
            if (items[i].name != AGED_BRIE && items[i].name != BACKSTAGE_PASSES) {
                if (items[i].quality > 0) {
                    if (items[i].name != SULFURAS) {
                        items[i].quality = items[i].quality - 1
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1

                    if (items[i].name == BACKSTAGE_PASSES) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1
                            }
                        }
                    }
                }
            }

            if (items[i].name != SULFURAS) {
                items[i].sellIn = items[i].sellIn - 1
            }

            if (items[i].sellIn < 0) {
                if (items[i].name != AGED_BRIE) {
                    if (items[i].name != BACKSTAGE_PASSES) {
                        if (items[i].quality > 0) {
                            if (items[i].name != SULFURAS) {
                                items[i].quality = items[i].quality - 1
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1
                    }
                }
            }
        }
    }
}

