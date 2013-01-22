package com.alexaitken.gildedrose;

public class AppreciatingQualityRule implements Rule {

	private static final int QUALITY_CAP = 50;

	@Override
	public void applyTo(Item item) {
		adjustQuality(item);
		adjustSellIn(item);
	}

	private void adjustQuality(Item item) {
		int qualityIncrement = 1;
		if (item.getSellIn() <= 0) {
			qualityIncrement++;
		}
		item.setQuality(item.getQuality() + qualityIncrement);
		if (item.getQuality() > QUALITY_CAP) {
			item.setQuality(50);
		}
	}

	private void adjustSellIn(Item item) {
		item.setSellIn(item.getSellIn() - 1);
	}

}
