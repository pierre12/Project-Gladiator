package de.twins.gladiator.domain;

/**
 * Exception for gladiator related problems.
 *
 * @author Pierre
 */
public class GladiatorException extends RuntimeException {
    public GladiatorException(String string) {
        super(string);
    }
}
