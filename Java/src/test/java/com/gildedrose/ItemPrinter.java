package com.gildedrose;

public class ItemPrinter {

    public static String printQualityOverNDays(int days) {
        Item[] items = new Item[]{
            new Item("+5 Dexterity Vest", 10, 20),
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
