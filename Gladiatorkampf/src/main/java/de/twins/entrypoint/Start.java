package de.twins.entrypoint;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.twins.arena.process.Arena;
import de.twins.context.SpringConfig;
import de.twins.enemy.Minion;
import de.twins.gladiator.domain.Equipment;
import de.twins.gladiator.domain.Equipment.BodyPart;
import de.twins.gladiator.domain.Fightable;
import de.twins.gladiator.domain.Gladiator;
import de.twins.gladiator.domain.GladiatorImpl;
import de.twins.gladiator.process.EquipmentFactory;

public class Start {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

		EquipmentFactory equipmentFactory = context.getBean(EquipmentFactory.class);
		Arena arena = context.getBean(Arena.class);
		Gladiator g1 = new GladiatorImpl("Rene", new BigDecimal("10000"), new BigDecimal("10000"),
				new BigDecimal("10000"),
				equipmentFactory.randomFullSet());
		Fightable g2 = new Minion("Broly", new BigDecimal("10000"), new BigDecimal("10000"), new BigDecimal("10000"));
		Map<BodyPart, Equipment> randomFullSet = equipmentFactory.randomFullSet();
		g1.addOrReplaceEquipments(randomFullSet.values());
		arena.addFighter(g1);
		arena.addFighter(g2);
		arena.startFight();
		System.out.println("end");
	}
}
