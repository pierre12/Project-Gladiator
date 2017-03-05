package de.twins.gladiator.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AbstractFighterTest {

	static class TestFighter extends AbstractFighter {
		public TestFighter(String name, BigDecimal healthPoints, BigDecimal attack, BigDecimal defense) {
			super(name, healthPoints, attack, defense);
			updateStats();
			currentHealthPoints = totalHealthPoints;

		}
		
	

	}
	private AbstractFighter testee;
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		testee = new TestFighter("testfighter",new BigDecimal(100),new BigDecimal(10),new BigDecimal(10));
	}
	@Test
	public void shouldBeAliveWhenCurrentHealthIsGreaterZero() {
		testee.setCurrentHealthPoints(BigDecimal.ONE);
		assertThat(testee.isAlive(), is(true));
	}
	
	@Test
	public void shouldBeDeadWhenCurrentHealthEqualsZero(){
		testee.defend(new BigDecimal(200));
		assertThat(testee.isAlive(), is(false));
	}
	@Test
	public void shouldBeDeadWhenCurrentHealthIsGreaterZero() {
		testee.setCurrentHealthPoints(new BigDecimal(-1));
		assertThat(testee.isAlive(), is(false));
	}

	@Test
	public void shouldLoseHpFromAttack() {
		testee.defend(new BigDecimal(20));
		assertThat(testee.getCurrentHealthPoints().doubleValue(), is(closeTo(84.6, 0.01)));
	}

	@Test
	public void shouldNotIncreaseCurrentHealthIfTotalHealthIncreases() {
		testee.setBaseDefense(BigDecimal.ZERO);
		testee.defend(BigDecimal.TEN);
		assertThat(testee.getCurrentHealthPoints(), is(new BigDecimal(90)));
		testee.setBaseHealthPoints(new BigDecimal(200));
		assertThat(testee.getTotalHealthPoints(), is(new BigDecimal(200)));
		assertThat(testee.getCurrentHealthPoints(), is(new BigDecimal(90)));
	}
	
	@Test
	public void shouldPrintRelevantData(){
		assertThat(testee.toString(), is("[CurrentHP:100, Attack:10, Defense:10]"));
	}
	
	@Test
	public void shouldReduceCurrentHealthPointToTotalHealthPointsIfLower() {
		testee.setBaseHealthPoints(BigDecimal.TEN);
		assertThat(testee.getCurrentHealthPoints(), is(BigDecimal.TEN));
	}
	@Test
	public void shouldSetCurrentHpToZeroWhenHpIsNegative() {
		testee.setCurrentHealthPoints(new BigDecimal(-1));
		assertThat(testee.getCurrentHealthPoints(), is(BigDecimal.ZERO));
	}
	@Test
	public void shouldSetDefense() {
		BigDecimal defense = new BigDecimal(3.33);
		testee.setBaseDefense(defense);
		assertThat(testee.getBaseDefense(), is(defense));
	}
	@Test
	public void shouldSetName() {
		String name = "testfighter";
		testee.setName(name);
		assertThat(testee.getName(), is(name));
	}
	@Test
	public void shouldTestNullOrNegativeWhenSettingBaseAttributes() {
		AbstractFighter spy = spy(testee);
		spy.setBaseAttack(BigDecimal.ONE);
		verify(spy, times(1)).checkIfNullOrNegativeValue(BigDecimal.ONE);
		spy.setBaseDefense(BigDecimal.ONE);
		verify(spy, times(2)).checkIfNullOrNegativeValue(BigDecimal.ONE);
		spy.setBaseAttack(BigDecimal.ONE);
		verify(spy, times(3)).checkIfNullOrNegativeValue(BigDecimal.ONE);
	}
	@Test
	public void shouldThrowExceptionIfBaseAttributeIsNegative(){
		expectedException.expect(GladiatorException.class);
		expectedException.expectMessage("value must not be null");
		testee.setBaseAttack(null);
	}
	@Test
	public void shouldThrowExceptionIfBaseAttributeIsNull(){
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("value must not be null");
		testee.setBaseAttack(null);
	}

	@Test
	public void shouldThrowExceptionIfBaseDefenseIsNull() {
		expectedException.expect(GladiatorException.class);
		expectedException.expectMessage("value must not be negative");
		testee.setBaseDefense(new BigDecimal(-100));
	}
	@Test
	public void shouldUpdateStatsIfBaseStatOrEquipmentIsChanged() {
		AbstractFighter spy = spy(testee);
		spy.setBaseAttack(BigDecimal.ONE);
		verify(spy, times(1)).updateStats();
		spy.setBaseDefense(BigDecimal.ONE);
		verify(spy, times(2)).updateStats();
		spy.setBaseHealthPoints(BigDecimal.ONE);
		verify(spy, times(3)).updateStats();
	}
}
