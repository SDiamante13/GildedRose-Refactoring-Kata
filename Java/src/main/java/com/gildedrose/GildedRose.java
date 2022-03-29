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

        updateQualityByCategory(item);
        item.sellIn -= 1;

        if (isExpired(item)) {
            if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                item.quality -= item.quality;
            }

            if (item.name.equals("Aged Brie")) {
                item.quality = item.quality >= 50 ? item.quality : item.quality + 1;
            }

            if (isNormalItem(item)) {
                item.quality -= 1;
            }
        }
    }

    private void updateQualityByCategory(Item item) {
        if (item.name.equals("Aged Brie")) {
            updateQualityForAgedBrieAndBackstagePass(item);
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            updateQualityForAgedBrieAndBackstagePass(item);
        } else if (item.quality > 0) {
                item.quality -= 1;
        }
    }

    private boolean isNormalItem(Item item) {
        return !item.name.equals("Aged Brie") && !item.name.equals("Backstage passes to a TAFKAL80ETC concert") && item.quality > 0;
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


    private boolean isExpired(Item item) {
        return item.sellIn < 0;
    }
}
