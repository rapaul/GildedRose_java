package com.alexaitken.gildedrose;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class AppreciatingQualityRuleTest {

	private AppreciatingQualityRule appreciatingQualityRule = new AppreciatingQualityRule();

	@Test
	public void qualityAppreciatesByOne() {
		int quality = 1;
		int sellIn = 100;
		Item item = new Item("Name", sellIn, quality);
		appreciatingQualityRule.applyTo(item);
		assertThat(item.getQuality(), is(2));
	}

	@Test
	public void qualityCapsOutAt50() throws Exception {
		int sellIn = 100;
		int quality = 50;
		Item item = new Item("Name", sellIn, quality);
		appreciatingQualityRule.applyTo(item);
		assertThat(item.getQuality(), is(quality));
	}

	@Test
	public void qualityAppreciatesTwiceAsFastAfterSellByDate() throws Exception {
		int sellIn = 0;
		int quality = 4;
		Item item = new Item("Name", sellIn, quality);
		appreciatingQualityRule.applyTo(item);
		assertThat(item.getQuality(), is(6));
	}

	@Test
	public void qualityCapsOutAt50EvenWhenAppreciatingTwiceAsFast() throws Exception {
		int sellIn = -1;
		int quality = 49;
		Item item = new Item("Name", sellIn, quality);
		appreciatingQualityRule.applyTo(item);
		assertThat(item.getQuality(), is(50));
	}

	@Test
	public void sellInDecreasesByOne() throws Exception {
		Item item = new Item("Name", 10, 1);
		appreciatingQualityRule.applyTo(item);
		assertThat(item.getSellIn(), is(9));
	}

	@Test
	public void sellInDecreasesBelowZero() throws Exception {
		Item item = new Item("Name", 0, 1);
		appreciatingQualityRule.applyTo(item);
		assertThat(item.getSellIn(), is(-1));
	}

}
