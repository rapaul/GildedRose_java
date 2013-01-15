package com.alexaitken.gildedrose;

public class Inventory {

	private Item[] items;

	public Inventory(Item[] items) {
		super();
		this.items = items;
	}

	public Inventory() {
		super();
		items = new Item[] {
					new Item("+5 Dexterity Vest", 10, 20), 
					new Item("Aged Brie", 2, 0),
					new Item("Elixir of the Mongoose", 5, 7),
					new Item("Sulfuras, Hand of Ragnaros", 0, 80),
					new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
					new Item("Conjured Mana Cake", 3, 6) 
				};

	}

	public void updateQuality() {
		for (Item item : items) {
			updateQuality(item);
			updateSellIn(item);
			updateQualityForExpiredItems(item);
		}
	}

	private void updateQuality(Item item) {
		if (itemGetsBetterWithAge(item)) {
			updateQualityOfItemsThatGetBetterWithAge(item);
		} else {
			if (item.getQuality() > 0) {
				if (!item.getName().equals("Sulfuras, Hand of Ragnaros")) {
					item.setQuality(item.getQuality() - 1);
				}
			}
		}
	}

	private boolean itemGetsBetterWithAge(Item item) {
		return item.getName().equals("Aged Brie")
				|| item.getName().equals("Backstage passes to a TAFKAL80ETC concert");
	}

	private void updateQualityOfItemsThatGetBetterWithAge(Item item) {
		if (item.getQuality() < 50) {
			item.setQuality(item.getQuality() + 1);

			if (item.getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
				if (item.getSellIn() < 11) {
					if (item.getQuality() < 50) {
						item.setQuality(item.getQuality() + 1);
					}
				}

				if (item.getSellIn() < 6) {
					if (item.getQuality() < 50) {
						item.setQuality(item.getQuality() + 1);
					}
				}
			}
		}
	}

	private void updateSellIn(Item item) {
		if (!item.getName().equals("Sulfuras, Hand of Ragnaros")) {
			item.setSellIn(item.getSellIn() - 1);
		}
	}

	private void updateQualityForExpiredItems(Item item) {
		if (item.getSellIn() < 0) {
			if (!item.getName().equals("Aged Brie")) {
				if (!item.getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
					if (item.getQuality() > 0) {
						if (!item.getName().equals("Sulfuras, Hand of Ragnaros")) {
							item.setQuality(item.getQuality() - 1);
						}
					}
				} else {
					item.setQuality(item.getQuality()
							- item.getQuality());
				}
			} else {
				if (item.getQuality() < 50) {
					item.setQuality(item.getQuality() + 1);
				}
			}
		}
	}
}
