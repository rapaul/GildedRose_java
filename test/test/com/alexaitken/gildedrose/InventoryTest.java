package test.com.alexaitken.gildedrose;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.alexaitken.gildedrose.Inventory;
import com.alexaitken.gildedrose.Item;
import com.alexaitken.gildedrose.Rule;
import com.alexaitken.gildedrose.RuleFactory;

/* Unit test for Inventory */
public class InventoryTest {

	private RuleFactory ruleFactory = mock(RuleFactory.class);
	
	@Test
	public void appliesRuleForASingleItem() {
		Item item = new Item("Example item", 1, 1);
		given(ruleFactory.getRule(item)).willReturn(new SellInDecrementsByOneRule());
		new Inventory(new Item[] { item }, ruleFactory).updateQuality();
		assertThat(item.getSellIn(), is(0));
	}
	
	@Test
	public void appliesRulesForMultipleItems() throws Exception {
		Item item1 = new Item("Item 1", 1, 1);
		Item item2 = new Item("Item 2", 2, 2);
		given(ruleFactory.getRule(any(Item.class))).willReturn(new SellInDecrementsByOneRule());
		new Inventory(new Item[] { item1, item2 }, ruleFactory).updateQuality();
		assertThat(item1.getSellIn(), is(0));
		assertThat(item2.getSellIn(), is(1));
	}
	
	class SellInDecrementsByOneRule implements Rule {

		@Override
		public void applyTo(Item item) {
			item.setSellIn(item.getSellIn() - 1);
		}

	}

}
