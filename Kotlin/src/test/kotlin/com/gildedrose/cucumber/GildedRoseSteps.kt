package com.gildedrose.cucumber

import com.gildedrose.GildedRose
import com.gildedrose.Item
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.Assert.assertEquals

class GildedRoseSteps {

    private lateinit var item: Item

    @Given("An item with name {string}, sell in is {int} has a quality of {int}")
    fun `anItemWithNameAndSellInHasQuality`(name: String, sellIn: Int, quality: Int) {
        this.item = Item(name, sellIn, quality)
    }

    @When("The quality is adjusted")
    fun `the quality gets adjusted`() {
        val gildedRose = GildedRose(arrayOf(item))
        gildedRose.updateQuality()
    }

    @Then("the new sellIn is {int}")
    fun theSellInPropertyIsAdjusted(sellIn: Int) {
        assertEquals(sellIn, item.sellIn)
    }

    @Then("the new quality is {int}")
    fun theQualityPropertyIsAdjusted(quality: Int) {
        assertEquals(quality, item.quality)
    }

}