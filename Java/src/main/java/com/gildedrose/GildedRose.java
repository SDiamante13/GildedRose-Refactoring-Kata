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
            String itemName = items[i].name;

            switch (itemName) {
                case SULFURAS:
                    return;
                case AGED_BRIE:
                    setItemQuality(items[i], 1);
                    break;
                case BACKSTAGE_PASS:
                    updateQualityForBackstagePass(items[i]);
                    break;
                default:
                    setItemQuality(items[i], -1);
                    break;
            }

            items[i].sellIn = items[i].sellIn - 1;

            handleExpiredItem(items[i]);
        }
    }

    private void setItemQuality(Item item, int qualityDifference) {
        if (item.quality <= MIN_QUALITY || item.quality >= MAX_QUALITY) return;
        item.quality = item.quality + qualityDifference;
    }

    private void updateQualityForBackstagePass(Item item) {
        int difference;

        if (item.sellIn > 10) {
            difference = 1;
        } else if (item.sellIn > 5) {
            difference = 2;
        } else {
            difference = 3;
        }

        setItemQuality(item, difference);
    }

    private void handleExpiredItem(Item item) {
        if (item.sellIn >= EXPIRATION_DATE) return;

        if (item.name.equals("Aged Brie")) {
            setItemQuality(item, 1);
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            item.quality = 0;
        } else {
            setItemQuality(item, -1);
        }
    }
}
