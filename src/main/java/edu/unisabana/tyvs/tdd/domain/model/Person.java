package edu.unisabana.tyvs.tdd.domain.model;

/** Represents a person to be registered as a voter. */
public final class Person {

    /** Full name of the person. */
    private final String name;

    /** Unique identifier of the person. */
    private final int id;

    /** Age of the person. */
    private final int age;

    /** Gender of the person. */
    private final Gender gender;

    /** Whether the person is alive. */
    private final boolean alive;

    /**
     * Creates a new Person.
     *
     * @param personName   full name
     * @param personId     unique identifier
     * @param personAge    age
     * @param personGender gender
     * @param personAlive  alive status
     */
    public Person(
            final String personName,
            final int personId,
            final int personAge,
            final Gender personGender,
            final boolean personAlive) {
        this.name = personName;
        this.id = personId;
        this.age = personAge;
        this.gender = personGender;
        this.alive = personAlive;
    }

    /**
     * Returns the name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the id.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the age.
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the gender.
     *
     * @return gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Returns whether the person is alive.
     *
     * @return true if alive
     */
    public boolean isAlive() {
        return alive;
    }
}
