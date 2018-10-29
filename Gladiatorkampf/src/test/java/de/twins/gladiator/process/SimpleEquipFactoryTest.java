package de.twins.gladiator.process;

import de.twins.gladiator.domain.Equipment;
import de.twins.gladiator.domain.Equipment.BodyPart;
import de.twins.gladiator.domain.Equipment.Rarity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;

import java.util.Map;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class SimpleEquipFactoryTest {
    @InjectMocks
    SimpleEquipFactory testee;

    @Mock
    Environment env;

    @Test
    public void createRandomEquipment() {
        Equipment createRandomEquipment = testee.createRandomEquipment();
        Equipment createRandomEquipment2 = testee.createRandomEquipment();
        Assert.assertNotNull(createRandomEquipment);
        Assert.assertNotNull(createRandomEquipment2);
        Assert.assertNotSame(createRandomEquipment, createRandomEquipment2);

        boolean notSame = false;
        notSame |= createRandomEquipment.getAttack() != createRandomEquipment2.getAttack();
        notSame |= createRandomEquipment.getDefense() != createRandomEquipment2.getDefense();
        notSame |= createRandomEquipment.getHealthPoints() != createRandomEquipment2.getHealthPoints();
        notSame |= createRandomEquipment.getRarity() != createRandomEquipment2.getRarity();
        notSame |= createRandomEquipment.getBodyPart() != createRandomEquipment2.getBodyPart();

        Assert.assertTrue(notSame);

    }

    @Test
    public void randomFullSet() {
        int numberOfBodyParts = BodyPart.values().length;
        Map<BodyPart, Equipment> randomFullSet = testee.randomFullSet();

        verify(testee, times(numberOfBodyParts)).createRandomEquipmentFor(any(BodyPart.class));
        assertThat(randomFullSet.size(), is(numberOfBodyParts));
        assertThat(randomFullSet.keySet(), hasItems(BodyPart.values()));
    }

    @Before
    public void setup() {
        testee = spy(new SimpleEquipFactory());
        MockitoAnnotations.initMocks(this);
        when(env.getProperty(anyString(), anyString())).thenReturn(String.valueOf((int) (Math.random() * 500)));
    }

    @Test
    public void shouldCreateRandomEquipmentWithRarity() {
        Equipment createRandomEquipment = testee.createRandomEquipmentWithRarityGrade(BodyPart.HEAD, Rarity.LEGENDARY);
        Assert.assertNotNull(createRandomEquipment);
        Assert.assertEquals(BodyPart.HEAD, createRandomEquipment.getBodyPart());
        Assert.assertEquals(Rarity.LEGENDARY, createRandomEquipment.getRarity());
    }

}
