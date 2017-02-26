package de.twins;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import de.twins.enemy.domain.Minion;
import de.twins.enemy.persistence.MinionPersistence;
import de.twins.gladiator.domain.Equipment;
import de.twins.gladiator.persistence.EquipmentPersistence;
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
	public void start(MinionPersistence p, EquipmentPersistence ep) {
		p.save(new Minion("First Monster", BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

		SimpleEquipFactoryImpl simpleEquipFactoryImpl = new SimpleEquipFactoryImpl();
		for (int i = 100; i > 0; i--) {
			Equipment createRandomEquipment = simpleEquipFactoryImpl.createRandomEquipment();
			ep.save(createRandomEquipment);
		}
	}
}
