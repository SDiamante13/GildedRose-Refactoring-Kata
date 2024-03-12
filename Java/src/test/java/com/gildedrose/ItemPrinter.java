package com.gildedrose;

import java.security.cert.CertPathBuilder;

import static com.gildedrose.ItemTestDataBuilder.anItem;

class ItemTestDataBuilder {

    private String name;
    private int sellIn;
    private int quality;

    public static ItemTestDataBuilder anItem() {
        return new ItemTestDataBuilder();
    }

    public ItemTestDataBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ItemTestDataBuilder expiresIn(int sellIn) {
        this.sellIn = sellIn;
        return this;
    }

    public ItemTestDataBuilder withQuality(int quality) {
        this.quality = quality;
        return this;
    }

    public Item build() {
        return new Item(name, sellIn, quality);
    }
}

public class ItemPrinter {

    public static String printQualityOverNDays(int days) {
        Item[] items = new Item[]{
            anItem()
                .withName("+5 Dexterity Vest")
                .expiresIn(10)
                .withQuality(20)
                .build(),
            new Item("Aged Brie", 2, 0),
            new Item("Elixir of the Mongoose", 5, 7),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            new Item("Conjured Mana Cake", 3, 6)};

        GildedRose app = new GildedRose(items);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < days; i++) {
            sb.append("-------- day ")
                .append(i)
                .append(" --------\n")
                .append("name, sellIn, quality\n");
            for (Item item : items) {
                sb.append(item).append("\n");
            }
            sb.append("\n");
            app.updateQuality();
        }

        return sb.toString();
    }
}
