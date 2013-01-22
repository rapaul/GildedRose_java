package com.alexaitken.gildedrose;

public class RuleFactory {

	public Rule getRule(Item item) {
		return new DoEverythingRule(item);
	}

	class DoEverythingRule implements Rule {

		private Item item;

		public DoEverythingRule(Item item) {
			this.item = item;
		}

		@Override
		public void apply() {
			updateQuality(item);
			updateSellIn(item);
			updateQualityForExpiredItems(item);
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
}
