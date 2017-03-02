package de.twins;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import de.twins.arena.process.OneOnOneArena;
import de.twins.enemy.domain.Minion;
import de.twins.enemy.persistence.MinionPersistence;
import de.twins.gladiator.domain.GladiatorImpl;
import de.twins.gladiator.persistence.GladiatorPersistence;
import de.twins.gladiator.process.SimpleEquipFactoryImpl;

@SpringBootApplication
@PropertySource(value = "file:config/application.properties")
@Configuration
@EnableAutoConfiguration
public class Start {

	public static void main(String[] args) {
		SpringApplication.run(Start.class);
	}

	@Autowired
	public void start(GladiatorPersistence gp, OneOnOneArena arena, MinionPersistence mp) {
		GladiatorImpl g = new GladiatorImpl("Rene", BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE,
				new SimpleEquipFactoryImpl().randomFullSet());
		
		gp.save(g);
		Minion m = new Minion("Broly", BigDecimal.TEN, BigDecimal.TEN, BigDecimal.ONE);
		mp.save(m);
		arena.addFighter(g);
		arena.addFighter(m);
		arena.startFight();
		System.out.println("Geschafft");


	}
}
