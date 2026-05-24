package edu.unisabana.tyvs.tdd.domain.model;

/** Represents the result of a voter registration attempt. */
public enum RegisterResult {

    /** Registration was successful. */
    VALID,

    /** Person is already registered. */
    DUPLICATED,

    /** Registration data is invalid. */
    INVALID,

    /** Person is deceased. */
    DEAD,

    /** Person is underage. */
    UNDERAGE
}
