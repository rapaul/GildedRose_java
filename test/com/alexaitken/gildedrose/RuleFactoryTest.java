package com.alexaitken.gildedrose;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.alexaitken.gildedrose.RuleFactory.DoEverythingRule;

public class RuleFactoryTest {

	@Test
	public void choosesTheDoEverythingRuleIfItDoesntKnowAboutTheSpecialCase() throws Exception {
		Rule rule = new RuleFactory().getRule(new Item("Unknown Item", 0, 0));
		assertThat(rule, instanceOf(DoEverythingRule.class));
	}

	@Test
	public void chooseFrozenRuleForSulfuras() {
		Rule rule = new RuleFactory().getRule(new Item("Sulfuras, Hand of Ragnaros", 5, 80));
		assertThat(rule, instanceOf(FrozenRule.class));
	}

}
