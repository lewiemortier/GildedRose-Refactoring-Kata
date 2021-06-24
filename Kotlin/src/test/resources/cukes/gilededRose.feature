Feature: GildedRose

  Scenario Outline: The quality of a regular item is adjusted as expected
    Given An item with name "<name>", sell in is <startSellIn> has a quality of <startQuality>
    When The quality is adjusted
    Then the new sellIn is <expectedSellIn>
    And the new quality is <expectedQuality>
    Examples:
      | name                                  | startSellIn | startQuality | expectedSellIn | expectedQuality |
      | item 1                                | 5           | 10           | 4              | 9               |
      | regular item                          | 0           | 10           | -1             | 8               |
      | item without quality                  | 5           | 0            | 4              | 0               |
      | item with negative sellIn and quality | 0           | 0            | -1             | 0               |

  Scenario Outline: The quality of aged brie is adjusted as expected
    Given An item with name "<name>", sell in is <startSellIn> has a quality of <startQuality>
    When The quality is adjusted
    Then the new sellIn is <expectedSellIn>
    And the new quality is <expectedQuality>
    Examples:
      | name      | startSellIn | startQuality | expectedSellIn | expectedQuality |
      | Aged Brie | 5           | 10           | 4              | 11              |
      | Aged Brie | 0           | 10           | -1             | 12              |
      | Aged Brie | 5           | 0            | 4              | 1               |
      | Aged Brie | 0           | 0            | -1             | 2               |
      | Aged Brie | 0           | 50           | -1             | 50              |

  Scenario Outline: Sulfuras never has to be sold or decreased in quality
    Given An item with name "<name>", sell in is <startSellIn> has a quality of <startQuality>
    When The quality is adjusted
    Then the new sellIn is <expectedSellIn>
    And the new quality is <expectedQuality>
    Examples:
      | name                       | startSellIn | startQuality | expectedSellIn | expectedQuality |
      | Sulfuras, Hand of Ragnaros | 5           | 10           | 5              | 10              |
      | Sulfuras, Hand of Ragnaros | 0           | 10           | 0              | 10              |
      | Sulfuras, Hand of Ragnaros | 5           | 0            | 5              | 0               |
      | Sulfuras, Hand of Ragnaros | 0           | 0            | 0              | 0               |
      | Sulfuras, Hand of Ragnaros | 0           | 80           | 0              | 80              |

  Scenario Outline: The quality of backstage passes is adjusted as expected
    Given An item with name "<name>", sell in is <startSellIn> has a quality of <startQuality>
    When The quality is adjusted
    Then the new sellIn is <expectedSellIn>
    And the new quality is <expectedQuality>
    Examples:
      | name                                      | startSellIn | startQuality | expectedSellIn | expectedQuality |
      | Backstage passes to a TAFKAL80ETC concert | 20          | 10           | 19             | 11              |
      | Backstage passes to a TAFKAL80ETC concert | 20          | 50           | 19             | 50              |
      | Backstage passes to a TAFKAL80ETC concert | 11          | 10           | 10             | 11              |
      | Backstage passes to a TAFKAL80ETC concert | 10          | 10           | 9              | 12              |
      | Backstage passes to a TAFKAL80ETC concert | 10          | 49           | 9              | 50              |
      | Backstage passes to a TAFKAL80ETC concert | 6           | 10           | 5              | 12              |
      | Backstage passes to a TAFKAL80ETC concert | 5           | 10           | 4              | 13              |
      | Backstage passes to a TAFKAL80ETC concert | 5           | 49           | 4              | 50              |
      | Backstage passes to a TAFKAL80ETC concert | 1           | 40           | 0              | 43              |
      | Backstage passes to a TAFKAL80ETC concert | 0           | 50           | -1             | 0               |

  Scenario Outline: Conjured items degrade twice as fast as normal items
    Given An item with name "<name>", sell in is <startSellIn> has a quality of <startQuality>
    When The quality is adjusted
    Then the new sellIn is <expectedSellIn>
    And the new quality is <expectedQuality>
    Examples:
      | name                                           | startSellIn | startQuality | expectedSellIn | expectedQuality |
      | Conjured item 1                                | 5           | 10           | 4              | 8               |
      | Conjured regular item                          | 0           | 10           | -1             | 6               |
      | Conjured item without quality                  | 5           | 0            | 4              | 0               |
      | Conjured item with negative sellIn and quality | 0           | 0            | -1             | 0               |