package de.twins.context;

import org.springframework.context.annotation.ComponentScan;

import de.twins.gladiator.domain.Gladiator;

@ComponentScan(basePackageClasses = { Gladiator.class })
public class SpringConfig {

}
