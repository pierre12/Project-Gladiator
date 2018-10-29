package de.twins;

import de.twins.enemy.domain.Minion;
import de.twins.gladiator.domain.Gladiator;
import de.twins.gladiator.process.SimpleEquipFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@SpringBootApplication(exclude ={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class Start {

    public static void main(String[] args) {
        SpringApplication.run(Start.class);
    }


    @Autowired
    SimpleEquipFactory simpleEquip;

    @PostConstruct
    public void init() {
        start();
    }

    public void start() {
        //TODO: Boot application with actual game
    }
}
