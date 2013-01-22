package com.alexaitken.gildedrose;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FrozenRuleTest {

	@Test
	public void doesNotChangeTheItem() {
		Item item = new Item("Name", 1, 2);
		new FrozenRule().applyTo(item);
		assertThat(item.getName(), is("Name"));
		assertThat(item.getSellIn(), is(1));
		assertThat(item.getQuality(), is(2));
	}

}
