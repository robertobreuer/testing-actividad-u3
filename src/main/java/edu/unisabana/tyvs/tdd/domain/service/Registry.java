package edu.unisabana.tyvs.tdd.domain.service;

import java.util.HashSet;
import java.util.Set;

import edu.unisabana.tyvs.tdd.domain.model.Person;
import edu.unisabana.tyvs.tdd.domain.model.RegisterResult;

/** Service responsible for registering voters. */
public final class Registry {

    /** Minimum allowed age to register. */
    private static final int MIN_AGE = 18;

    /** Maximum allowed age to register. */
    private static final int MAX_AGE = 120;

    /** Regex allowing only letters, accents and spaces. */
    private static final String VALID_NAME_REGEX =
        "^[a-zA-Z\u00e1\u00e9\u00ed\u00f3\u00fa"
        + "\u00c1\u00c9\u00cd\u00d3\u00da\u00f1\u00d1 ]+$";

    /** Set of already registered IDs. */
    private final Set<Integer> registeredIds = new HashSet<>();

    /**
     * Registers a voter if all validations pass.
     *
     * @param p the person to register
     * @return the result of the registration attempt
     */
    public RegisterResult registerVoter(final Person p) {
        if (p == null) {
            return RegisterResult.INVALID;
        }
        if (!p.isAlive()) {
            return RegisterResult.DEAD;
        }
        if (p.getId() <= 0) {
            return RegisterResult.INVALID;
        }
        if (p.getAge() < MIN_AGE || p.getAge() > MAX_AGE) {
            if (p.getAge() < MIN_AGE) {
                return RegisterResult.UNDERAGE;
            }
            return RegisterResult.INVALID;
        }
        if (p.getName() == null || p.getName().trim().isEmpty()) {
            return RegisterResult.INVALID;
        }
        if (!p.getName().matches(VALID_NAME_REGEX)) {
            return RegisterResult.INVALID;
        }
        if (!registeredIds.add(p.getId())) {
            return RegisterResult.DUPLICATED;
        }
        return RegisterResult.VALID;
    }
}
