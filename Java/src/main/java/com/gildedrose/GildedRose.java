package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateOne(item);
        }
    }

    private void updateOne(Item item) {
        updateQuality(item);

        updateSellIn(item);

        if (item.sellIn < 0) {
            updateExpired(item);
        }
    }

    private void updateQuality(Item item) {
        if (item.name.equals("Aged Brie")) {
            incrementQuality(item);
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            incrementQuality(item);
                if (item.sellIn < 11) {
                    incrementQuality(item);
                }
                if (item.sellIn < 6) {
                    incrementQuality(item);
                }
        } else if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
        } else {
            decrementQuality(item);
        }
    }

    private void updateSellIn(Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn -= 1;
        }
    }

    private void updateExpired(Item item) {
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            item.quality = 0;
        } else if (item.name.equals("Aged Brie")) {
            incrementQuality(item);
        } else if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
        } else {
            decrementQuality(item);
        }
    }

    private void incrementQuality(Item item) {
        if (item.quality < 50) {
            item.quality += 1;
        }
    }

    private void decrementQuality(Item item) {
        if (item.quality > 0) {
            item.quality -= 1;
        }
    }

}
