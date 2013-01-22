package com.alexaitken.gildedrose;

public class Inventory {

	private Item[] items;
	private RuleFactory ruleFactory;

	public Inventory(Item[] items, RuleFactory ruleFactory) {
		this.items = items;
		this.ruleFactory = ruleFactory;
	}

	public Inventory() {
		ruleFactory = new RuleFactory();
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
			ruleFactory.getRule(item).apply();
		}
	}

}
