const { Shop, Item } = require('../src/gilded_rose');

describe('Gilded Rose', () => {
	describe('Normal Items', () => {
		it('should decrease quality by 1 each update', () => {
			const shop = new Shop([new Item('normalItem', 3, 3)]);

			const items = shop.updateQuality();

			expect(items[0].sellIn).toBe(2);
			expect(items[0].quality).toBe(2);
		});

		it('should decrease quality by 2 when the sellBy date has passed', () => {
			const shop = new Shop([new Item('normalItem', 0, 3)]);

			const items = shop.updateQuality();

			expect(items[0].sellIn).toBe(-1);
			expect(items[0].quality).toBe(1);
		});

		it('should have a minimum quality of zero', () => {
			const shop = new Shop([new Item('normalItem', 3, 0)]);

			const items = shop.updateQuality();

			expect(items[0].sellIn).toBe(2);
			expect(items[0].quality).toBe(0);
		});
	});

	describe('Aged Brie', () => {
		it('should increase quality by 1 each update', () => {
			const shop = new Shop([new Item('Aged Brie', 2, 0)]);

			const items = shop.updateQuality();

			expect(items[0].sellIn).toBe(1);
			expect(items[0].quality).toBe(1);
		});

		it('should increase quality by 2 when sellByDate passes', () => {
			const shop = new Shop([new Item('Aged Brie', 0, 3)]);

			const items = shop.updateQuality();

			expect(items[0].sellIn).toBe(-1);
			expect(items[0].quality).toBe(5);
		});

		it('should not exceed a quality of 50', () => {
			const shop = new Shop([new Item('Aged Brie', 3, 50)]);

			const items = shop.updateQuality();

			expect(items[0].sellIn).toBe(2);
			expect(items[0].quality).toBe(50);
		});
	});

	describe('Sulfuras', () => {
		it('should not change the sellIn or quality on update', () => {
			const shop = new Shop([new Item('Sulfuras, Hand of Ragnaros', -1, 3)]);

			const items = shop.updateQuality();

			expect(items[0].sellIn).toBe(-1);
			expect(items[0].quality).toBe(3);
		});
	});

  describe('Backstage Pass', () => {
    it('should increase quality by 1 when sellIn is greater than 10', () => {
      const shop = new Shop([
        new Item('Backstage passes to a TAFKAL80ETC concert', 15, 20),
      ]);

      const items = shop.updateQuality();

      expect(items[0].sellIn).toBe(14);
      expect(items[0].quality).toBe(21);
    });

    it('should increase quality by 2 when sellIn is greater than 5 and less than 10', () => {
      const shop = new Shop([
        new Item('Backstage passes to a TAFKAL80ETC concert', 8, 20),
      ]);

      const items = shop.updateQuality();

      expect(items[0].sellIn).toBe(7);
      expect(items[0].quality).toBe(22);
    });

    it('should increase quality by 3 when sellIn is less than or equal to 5', () => {
      const shop = new Shop([
        new Item('Backstage passes to a TAFKAL80ETC concert', 5, 20),
      ]);

      const items = shop.updateQuality();

      expect(items[0].sellIn).toBe(4);
      expect(items[0].quality).toBe(23);
    });

    it('should not exceed a quality of 50', () => {
      const shop = new Shop([
        new Item('Backstage passes to a TAFKAL80ETC concert', 6, 49),
      ]);

      const items = shop.updateQuality();

      expect(items[0].sellIn).toBe(5);
      expect(items[0].quality).toBe(50);
    });

    it('should drop to a quality of 0 when the sellIn is zero or less', () => {
      const shop = new Shop([
        new Item('Backstage passes to a TAFKAL80ETC concert', 0, 20),
      ]);

      const items = shop.updateQuality();

      expect(items[0].sellIn).toBe(-1);
      expect(items[0].quality).toBe(0);
    });
  });
});
