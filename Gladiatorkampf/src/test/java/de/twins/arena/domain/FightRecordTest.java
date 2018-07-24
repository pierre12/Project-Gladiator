package de.twins.arena.domain;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FightRecordTest {

    private FightRecord testee;

    @Before
    public void setUp() throws Exception {
        testee = new FightRecord(null);
    }

    @Test
    public void shouldAddDmgInflicted() throws Exception {
        assertThat(testee.getDmgInflicted(), is(BigDecimal.ZERO));
        testee.addDmgInflicted(BigDecimal.ONE);
        testee.addDmgInflicted(BigDecimal.ONE);
        assertThat(testee.getDmgInflicted().longValueExact(), is(2l));
    }

    @Test
    public void shouldAddDmgTaken() throws Exception {
        assertThat(testee.getDmgTaken(), is(BigDecimal.ZERO));
        testee.addDmgTaken(BigDecimal.ONE);
        testee.addDmgTaken(BigDecimal.ONE);
        assertThat(testee.getDmgTaken().longValueExact(), is(2l));
    }

}
