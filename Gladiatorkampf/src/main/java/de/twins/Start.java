package de.twins;

import de.twins.arena.process.OneOnOneArena;
import de.twins.enemy.domain.Minion;
import de.twins.gladiator.domain.Gladiator;
import de.twins.gladiator.process.SimpleEquipFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@SpringBootApplication
@PropertySource(value = "classpath:config/application.properties")
@Configuration
@EnableAutoConfiguration
public class Start {

    public static void main(String[] args) {
        SpringApplication.run(Start.class);
    }

    @Autowired
    OneOnOneArena arena;


    @PostConstruct
    public void init() {
        start();
    }

    public void start() {
        Gladiator g = new Gladiator("Rene", BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE,
                new SimpleEquipFactoryImpl().randomFullSet());

        Minion m = new Minion("Broly", BigDecimal.TEN, BigDecimal.TEN, BigDecimal.ONE);
        arena.addFighter(g);
        arena.addFighter(m);
        arena.startFight();
        System.out.println("Geschafft");

    }
}
