package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            ItemCategory category = categorize(item.name);
            category.updateOne(item);
        }
    }

    public ItemCategory categorize(String name) {
        switch (name) {
            case "Sulfuras, Hand of Ragnaros":
                return new Legendary();
            case "Aged Brie":
                return new Cheese();
            case "Backstage passes to a TAFKAL80ETC concert":
                return new BackstagePass();
            default:
                return new Normal();
        }
    }

    public abstract class ItemCategory {

        protected void updateOne(Item item) {
            updateQuality(item);

            updateSellIn(item);

            if (item.sellIn < 0) {
                updateExpired(item);
            }
        }

        abstract void updateQuality(Item item);

        abstract void updateSellIn(Item item);

        abstract void updateExpired(Item item);

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
        }

        @Override
        protected void updateSellIn(Item item) {
        }

        @Override
        protected void updateExpired(Item item) {
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

    private class Normal extends ItemCategory {
        @Override
        protected void updateQuality(Item item) {
            decrementQuality(item);
        }

        @Override
        protected void updateSellIn(Item item) {
            item.sellIn -= 1;
        }

        @Override
        protected void updateExpired(Item item) {
            decrementQuality(item);
        }
    }
}
