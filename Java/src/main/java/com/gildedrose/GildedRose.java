package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemCategory category = categorize(item);
            updateOne(item, category);
        }
    }

    private ItemCategory categorize(Item item) {
        if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return new Legendary();
        } else if (item.name.equals("Aged Brie")) {
            return new Cheese();
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            return new BackstagePass();
        } else if (item.name.startsWith("Conjured")) {
            return new Conjured();
        }
        return new ItemCategory();
    }

    private void updateOne(Item item, ItemCategory category) {
        category.updateQuality(item);

        category.updateSellIn(item);

        if (item.sellIn < 0) {
            category.updateExpired(item);
        }
    }

    private class ItemCategory {
        protected void updateQuality(Item item) {
            decrementQuality(item);
        }

        protected void updateSellIn(Item item) {
            item.sellIn -= 1;
        }

        protected void updateExpired(Item item) {
            decrementQuality(item);
        }

        protected void incrementQuality(Item item) {
            if (item.quality < 50) {
                item.quality += 1;
            }
        }

        protected void decrementQuality(Item item) {
            if (item.quality > 0) {
                item.quality -= 1;
            }
        }
    }

    private class Legendary extends ItemCategory {
        @Override
        protected void updateQuality(Item item) {
            // Legendary Items do not change
        }

        @Override
        protected void updateSellIn(Item item) {
            // Legendary Items do not change
        }

        @Override
        protected void updateExpired(Item item) {
            // Legendary Items do not change
        }

    }

    private class Cheese extends ItemCategory {
        @Override
        protected void updateQuality(Item item) {
            incrementQuality(item);
        }

        @Override
        protected void updateSellIn(Item item) {
            item.sellIn -= 1;
        }

        @Override
        protected void updateExpired(Item item) {
            incrementQuality(item);
        }
    }

    private class BackstagePass extends ItemCategory {
        @Override
        protected void updateQuality(Item item) {
            incrementQuality(item);
            if (item.sellIn < 11) {
                incrementQuality(item);
            }
            if (item.sellIn < 6) {
                incrementQuality(item);
            }
        }

        @Override
        protected void updateSellIn(Item item) {
            item.sellIn -= 1;
        }

        @Override
        protected void updateExpired(Item item) {
            item.quality = 0;
        }
    }

    private class Conjured extends ItemCategory {
        @Override
        protected void updateQuality(Item item) {
            decrementQuality(item);
            decrementQuality(item);
        }

        @Override
        protected void updateSellIn(Item item) {
            item.sellIn -= 1;
        }

        @Override
        protected void updateExpired(Item item) {
            decrementQuality(item);
            decrementQuality(item);
        }
    }
}
