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

        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn -= 1;
        }

        if (item.sellIn < 0) {
            updateExpired(item);
        }
    }

    private void updateQuality(Item item) {
        if (item.name.equals("Aged Brie")) {
            updateQualityForAgedBrieAndBackstagePass(item);
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            updateQualityForAgedBrieAndBackstagePass(item);
        } else if (item.name.equals("Sulfuras, Hand of Ragnaros")) {

        } else {
            if (item.quality > 0) {
                item.quality -= 1;
            }
        }
    }

    private void updateExpired(Item item) {
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            item.quality -= item.quality;
        } else if (item.name.equals("Aged Brie")) {
            item.quality = item.quality >= 50 ? item.quality : item.quality + 1;
        } else if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
        } else {
            if (item.quality > 0) {
                item.quality -= 1;
            }
        }
    }

    private void updateQualityForAgedBrieAndBackstagePass(Item item) {
        if (item.quality >= 50) {
            return;
        }
        if (item.name.equals("Aged Brie")) {
            item.quality += 1;
            return;
        }

        item.quality += 1;

        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert") && item.quality < 50) {
            if (item.sellIn < 11) {
                item.quality += 1;
            }

            if (item.sellIn < 6) {
                item.quality += 1;
            }
        }
    }


}
