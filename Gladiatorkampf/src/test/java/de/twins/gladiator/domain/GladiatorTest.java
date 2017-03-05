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
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.twins.gladiator.domain.Equipment.BodyPart;

public class GladiatorTest {

	private Gladiator testee;
	@Mock
	private Equipment equipmentMock1;
	@Mock
	private Equipment equipmentReplaceMock;
	@Mock
	private Equipment equipmentMock2;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		testee = new Gladiator("testee", new BigDecimal(100), BigDecimal.TEN, BigDecimal.TEN, null);
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
		testee = new Gladiator("testee", new BigDecimal(100), new BigDecimal(10), new BigDecimal(10), equipmentsMock);
		assertThat(testee.getTotalAttack(), is(new BigDecimal(30)));
		assertThat(testee.getTotalDefense(), is(new BigDecimal(30)));
		assertThat(testee.getTotalHealthPoints(), is(new BigDecimal(300)));
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
	public void shouldUpdateStatsIfEquipmentIsChanged() {
		Gladiator spy = spy(testee);
		spy.addOrReplaceEquipment(equipmentMock1);
		verify(spy, times(1)).updateStats();
		spy.removeEquipment(equipmentMock1);
		verify(spy, times(2)).updateStats();
	}

	
	

	
	

}
