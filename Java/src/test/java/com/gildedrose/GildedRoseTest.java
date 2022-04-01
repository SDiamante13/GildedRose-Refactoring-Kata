package com.gildedrose;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    // region normal item tests
    @Test
    void updateQuality_decreasesQualityBy1_forNormalItem() {
        Item[] items = new Item[]{new Item("normalItem", 3, 3)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(2, app.items[0].quality);
        assertEquals(2, app.items[0].sellIn);
    }

    @Test
    void updateQuality_decreasesQualityBy2_forNormalItem_whenSellByDateHasPassed() {
        Item[] items = new Item[]{new Item("normalItem", 0, 3)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(1, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    void updateQuality_minimumQualityIsZero_forNormalItem() {
        Item[] items = new Item[]{new Item("normalItem", 2, 0)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
    }
    // endregion normal item tests

    // region aged brie tests
    @Test
    void updateQuality_increasesQualityBy1_forAgedBrie() {
        Item[] items = new Item[]{new Item("Aged Brie", 2, 0)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(1, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
    }

    @Test
    void updateQuality_increasesQualityBy2_forAgedBrie_whenItsSellInDatePasses() {
        Item[] items = new Item[]{new Item("Aged Brie", 0, 3)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(5, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    void updateQuality_cannotExceed50Quality_forAgedBrie() {
        Item[] items = new Item[]{new Item("Aged Brie", 3, 50)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(50, app.items[0].quality);
        assertEquals(2, app.items[0].sellIn);
    }
    // endregion aged brie tests

    // region sulfuras tests
    @Test
    void updateQuality_doesNotChangeProperties_forSulfuras() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", -1, 3)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(3, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }
    // endregion sulfuras tests

    // region backstage pass tests
    @Test
    void updateQuality_increasesQualityBy1_forBackStagePasses_whenSellInIsGreaterThan10() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(21, app.items[0].quality);
        assertEquals(14, app.items[0].sellIn);
    }

    @Test
    void updateQuality_increasesQualityBy2_forBackStagePasses_whenSellInIsGreaterThan5_andLessThan10() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 8, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(22, app.items[0].quality);
        assertEquals(7, app.items[0].sellIn);
    }

    @Test
    void updateQuality_increasesQualityBy3_forBackStagePasses_whenSellInIsLessThanOrEqualTo5() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(23, app.items[0].quality);
        assertEquals(4, app.items[0].sellIn);
    }

    @Test
    void updateQuality_maxesOutAtQualityOf50_forBackStagePasses() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 6, 49)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(50, app.items[0].quality);
        assertEquals(5, app.items[0].sellIn);
    }

    @Test
    void updateQuality_decreasesToZeroQuality_forBackStagePasses_whenSellInIsLessThanOrEqualToZero() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }
    // endregion backstage pass tests

    // region conjured items tests

    @Test
    void updateQuality_decreasesQualityBy2_forConjuredItem() {
        Item[] items = new Item[]{new Item("Conjured Mana Cake", 3, 10)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(8, app.items[0].quality);
        assertEquals(2, app.items[0].sellIn);
    }

    @Test
    void updateQuality_decreasesQualityBy2WhenExpired_forConjuredItem() {
        Item[] items = new Item[]{new Item("Conjured Mana Cake", -1, 10)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(6, app.items[0].quality);
        assertEquals(-2, app.items[0].sellIn);
    }
    // endregion conjured items tests

}
