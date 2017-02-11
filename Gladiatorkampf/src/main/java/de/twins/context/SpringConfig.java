package de.twins.context;

import org.springframework.context.annotation.ComponentScan;

import de.twins.gladiator.domain.GladiatorImpl;

@ComponentScan(basePackageClasses = { GladiatorImpl.class })
public class SpringConfig {

}
