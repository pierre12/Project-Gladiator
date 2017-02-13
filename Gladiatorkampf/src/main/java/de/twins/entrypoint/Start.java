package de.twins.entrypoint;

import java.math.BigDecimal;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.twins.arena.Arena;
import de.twins.context.SpringConfig;
import de.twins.gladiator.domain.Gladiator;
import de.twins.gladiator.domain.GladiatorImpl;
import de.twins.gladiator.process.EquipmentFactory;

public class Start {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

		EquipmentFactory equipmentFactory = context.getBean(EquipmentFactory.class);
		Arena arena = context.getBean(Arena.class);
		Gladiator g1 = new GladiatorImpl("Rene", new BigDecimal("10"), new BigDecimal("10"), new BigDecimal("10"),
				equipmentFactory.randomFullSet());
		Gladiator g2 = new GladiatorImpl("Pierre", new BigDecimal("10"), new BigDecimal("10"), new BigDecimal("10"),
				equipmentFactory.randomFullSet());

		arena.addGladiator(g1);
		arena.addGladiator(g2);
		arena.startFight();
		System.out.println("end");
	}
}
