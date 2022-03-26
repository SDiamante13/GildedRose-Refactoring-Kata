package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            update(item);
        }
    }

    private void update(Item item) {
        if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return;
        }

        if (!item.name.equals("Aged Brie") && !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.quality > 0) {
                item.quality = item.quality - 1;
            }
        } else {
            updateQualityForAgedBrieAndBackstagePass(item);
        }

        item.sellIn -= 1;

        if (isExpired(item)) {
            if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                item.quality = 0;
            }

            if (item.name.equals("Aged Brie") && item.quality < 50) {
                item.quality++;
            }

            if (isNormalItem(item)) {
                    item.quality--;
            }
        }
    }

    private boolean isNormalItem(Item item) {
        return !item.name.equals("Aged Brie") && !item.name.equals("Backstage passes to a TAFKAL80ETC concert") && item.quality > 0;
    }

    private void updateQualityForAgedBrieAndBackstagePass(Item item) {
        if (item.quality < 50) {
            item.quality++;

            if (item.name.equals("Backstage passes to a TAFKAL80ETC concert") && item.quality < 50) {
                if (item.sellIn < 11) {
                    item.quality = item.quality + 1;
                }

                if (item.sellIn < 6) {
                    item.quality = item.quality + 1;
                }
            }
        }
    }

    private boolean isExpired(Item item) {
        return item.sellIn < 0;
    }
}
