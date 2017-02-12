package de.twins.gladiator.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.twins.gladiator.domain.Equipment.BodyPart;

public class GladiatorImplTest {

	private GladiatorImpl testee;
	@Mock
	private Equipment equipmentMock1;
	@Mock
	private Equipment equipmentReplaceMock;
	@Mock
	private Equipment equipmentMock2;
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		testee = new GladiatorImpl("testee", new BigDecimal(100), BigDecimal.TEN, BigDecimal.TEN, null);
		when(equipmentMock1.getBodyPart()).thenReturn(BodyPart.HEAD);
		when(equipmentMock1.getAttack()).thenReturn(BigDecimal.TEN);
		when(equipmentMock1.getDefense()).thenReturn(BigDecimal.TEN);
		when(equipmentMock1.getHealthPoints()).thenReturn(new BigDecimal(100));

		when(equipmentReplaceMock.getBodyPart()).thenReturn(BodyPart.HEAD);
		when(equipmentReplaceMock.getAttack()).thenReturn(new BigDecimal(20));
		when(equipmentReplaceMock.getDefense()).thenReturn(new BigDecimal(20));
		when(equipmentReplaceMock.getHealthPoints()).thenReturn(new BigDecimal(200));

		when(equipmentMock2.getBodyPart()).thenReturn(BodyPart.BODY);
		when(equipmentMock2.getAttack()).thenReturn(BigDecimal.TEN);
		when(equipmentMock2.getDefense()).thenReturn(BigDecimal.TEN);
		when(equipmentMock2.getHealthPoints()).thenReturn(new BigDecimal(100));
	}

	@Test
	public void shouldAddEquipmentStats() {
		testee.addOrReplaceEquipment(equipmentMock1);
		assertThat(testee.getTotalAttack(), is(new BigDecimal(20)));
		assertThat(testee.getTotalDefense(), is(new BigDecimal(20)));
		assertThat(testee.getTotalHealthPoints(), is(new BigDecimal(200)));
	}

	@Test
	public void shouldAddMultipleEquipments() {
		Map<BodyPart, Equipment> equipmentsMock = new HashMap<>();
		equipmentsMock.put(equipmentMock1.getBodyPart(), equipmentMock1);
		equipmentsMock.put(equipmentMock2.getBodyPart(), equipmentMock2);
		testee = new GladiatorImpl("testee", new BigDecimal(100), new BigDecimal(10), new BigDecimal(10), equipmentsMock);
		assertThat(testee.getTotalAttack(), is(new BigDecimal(30)));
		assertThat(testee.getTotalDefense(), is(new BigDecimal(30)));
		assertThat(testee.getTotalHealthPoints(), is(new BigDecimal(300)));
	}

	@Test
	public void shouldLoseHpFromAttack() {
		testee.defend(new BigDecimal(20));
		assertThat(testee.getCurrentHealthPoints(), is(new BigDecimal(90)));
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
	public void shouldNullifyAttackWhenDefenseIsEqualsOrHigher() {
		testee.defend(new BigDecimal(10));
		assertThat(testee.getCurrentHealthPoints(), is(new BigDecimal(100)));
	}

	@Test
	public void shouldReduceCurrentHealthPointToTotalHealthPointsIfLower() {
		testee.setBaseHealthPoints(BigDecimal.TEN);
		assertThat(testee.getCurrentHealthPoints(), is(BigDecimal.TEN));
	}

	@Test
	public void shouldRemoveEquipment() {
		testee.addOrReplaceEquipment(equipmentMock1);
		testee.removeEquipment(equipmentMock1);
		assertThat(testee.getTotalAttack(), is(new BigDecimal(10)));
		assertThat(testee.getTotalDefense(), is(new BigDecimal(10)));
		assertThat(testee.getTotalHealthPoints(), is(new BigDecimal(100)));
	}

	@Test
	public void shouldReplaceEquipment() {
		testee.addOrReplaceEquipment(equipmentMock1);
		testee.addOrReplaceEquipment(equipmentReplaceMock);
		assertThat(testee.getTotalAttack(), is(new BigDecimal(30)));
		assertThat(testee.getTotalDefense(), is(new BigDecimal(30)));
		assertThat(testee.getTotalHealthPoints(), is(new BigDecimal(300)));
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
		String name = "gladiator";
		testee.setName(name);
		assertThat(testee.getName(), is(name));
	}

	@Test
	public void shouldUpdateStatsIfBaseStatOrEquipmentIsChanged() {
		GladiatorImpl spy = spy(testee);
		spy.setBaseAttack(BigDecimal.ONE);
		verify(spy, times(1)).updateStats();
		spy.setBaseDefense(BigDecimal.ONE);
		verify(spy, times(2)).updateStats();
		spy.setBaseHealthPoints(BigDecimal.ONE);
		verify(spy, times(3)).updateStats();
		spy.addOrReplaceEquipment(equipmentMock1);
		verify(spy, times(4)).updateStats();
		spy.removeEquipment(equipmentMock1);
		verify(spy, times(5)).updateStats();
	}

	@Test
	public void shouldTestNullOrNegativeWhenSettingBaseAttributes() {
		GladiatorImpl spy = spy(testee);
		spy.setBaseAttack(BigDecimal.ONE);
		verify(spy, times(1)).checkIfNullOrNegativeValue(BigDecimal.ONE);
		spy.setBaseDefense(BigDecimal.ONE);
		verify(spy, times(2)).checkIfNullOrNegativeValue(BigDecimal.ONE);
		spy.setBaseAttack(BigDecimal.ONE);
		verify(spy, times(3)).checkIfNullOrNegativeValue(BigDecimal.ONE);
	}
	@Test
	public void shouldThrowExceptionIfBaseAttributeIsNull(){
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("value must not be null");
		testee.setBaseAttack(null);
	}
	@Test
	public void shouldThrowExceptionIfBaseAttributeIsNegative(){
		expectedException.expect(GladiatorException.class);
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
	public void shouldBeAliveWhenCurrentHealthIsGreaterZero(){
		assertThat(testee.isAlive(), is(true));
	}
	@Test
	public void shouldBeDeadWhenCurrentHealthEqualsZero(){
		testee.defend(new BigDecimal(200));
		assertThat(testee.isAlive(), is(false));
	}
}
