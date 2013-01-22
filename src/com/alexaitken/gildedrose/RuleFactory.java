package com.alexaitken.gildedrose;

import java.util.HashMap;
import java.util.Map;

public class RuleFactory {

	private Map<String, Rule> nameToRuleMapping = new HashMap<>();

	public RuleFactory() {
		nameToRuleMapping.put("Sulfuras, Hand of Ragnaros", new FrozenRule());
		nameToRuleMapping.put("Aged Brie", new AppreciatingQualityRule());
	}

	public Rule getRule(Item item) {
		Rule rule = nameToRuleMapping.get(item.getName());
		if (rule == null) {
			rule = new DoEverythingRule();
		}
		return rule;
	}

	class DoEverythingRule implements Rule {

		@Override
		public void applyTo(Item item) {
			updateQuality(item);
			updateSellIn(item);
			updateQualityForExpiredItems(item);
		}

		private void updateQuality(Item item) {
			if (itemGetsBetterWithAge(item)) {
				updateQualityOfItemsThatGetBetterWithAge(item);
			} else {
				if (item.getQuality() > 0) {
					item.setQuality(item.getQuality() - 1);
				}
			}
		}

		private boolean itemGetsBetterWithAge(Item item) {
			return item.getName().equals("Backstage passes to a TAFKAL80ETC concert");
		}

		private void updateQualityOfItemsThatGetBetterWithAge(Item item) {
			if (item.getQuality() < 50) {
				item.setQuality(item.getQuality() + 1);

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

		private void updateSellIn(Item item) {
			item.setSellIn(item.getSellIn() - 1);
		}

		private void updateQualityForExpiredItems(Item item) {
			if (item.getSellIn() < 0) {
				if (!item.getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
					if (item.getQuality() > 0) {
						item.setQuality(item.getQuality() - 1);
					}
				} else {
					item.setQuality(item.getQuality()
							- item.getQuality());
				}
			}
		}
	}
}
