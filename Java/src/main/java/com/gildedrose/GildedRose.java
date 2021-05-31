package com.gildedrose;

import static com.gildedrose.Constants.*;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {

        // iterate through list of items, update sellin and quality according to name
        for (int i = 0; i < items.length; i++) {

            if (SULFURAS.equals(items[i].name)) {
                continue;
            } else if (AGED_BRIE.equals(items[i].name)) {
                setItemQuality(items[i], 1);
            } else if (BACKSTAGE_PASS.equals(items[i].name)) {
                updateQualityForBackstagePass(items[i]);
            } else if (items[i].name.contains(CONJURED_ITEM)) {
                setItemQuality(items[i], -2);
            } else {
                setItemQuality(items[i], -1);
            }

            items[i].sellIn = items[i].sellIn - 1;

            handleExpiredItem(items[i]);
        }
    }

    private void setItemQuality(Item item, int qualityDifference) {
        if (item.quality < MIN_QUALITY || item.quality >= MAX_QUALITY) return;
        if (item.quality == MIN_QUALITY && !item.name.equals(AGED_BRIE)) return;
        item.quality = item.quality + qualityDifference;
        if (item.quality > 50) {
            item.quality = 50;
        }
    }

    private void updateQualityForBackstagePass(Item item) {
        if (item.sellIn > 10) {
            setItemQuality(item, 1);
        } else if (item.sellIn > 5) {
            setItemQuality(item, 2);
        } else {
            setItemQuality(item, 3);
        }
    }

    private void handleExpiredItem(Item item) {
        if (item.sellIn >= EXPIRATION_DATE) return;

        if (item.name.equals(AGED_BRIE)) {
            setItemQuality(item, 1);
        } else if (item.name.equals(BACKSTAGE_PASS)) {
            item.quality = 0;
        } else {
            setItemQuality(item, -1);
        }
    }
}
