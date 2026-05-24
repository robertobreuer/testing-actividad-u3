package edu.unisabana.tyvs.tdd.domain.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import edu.unisabana.tyvs.tdd.domain.model.Gender;
import edu.unisabana.tyvs.tdd.domain.model.Person;
import edu.unisabana.tyvs.tdd.domain.model.RegisterResult;

/** Unit tests for {@link Registry#registerVoter(Person)}. */
class RegistryTest {

    /** Registry instance under test. */
    private final Registry registry = new Registry();

    /**
     * Given la persona es null.
     * When intento registrarla.
     * Then el resultado debe ser INVALID.
     */
    @Test
    void shouldReturnInvalid_WhenPersonIsNull() {
        // Arrange
        // no se necesita objeto, la entrada es null

        // Act
        RegisterResult result = registry.registerVoter(null);

        // Assert
        Assertions.assertEquals(RegisterResult.INVALID, result);
    }

    /**
     * Given la persona tiene id = 0, edad 25 y esta viva.
     * When intento registrarla.
     * Then el resultado debe ser INVALID.
     */
    @Test
    void shouldReturnInvalid_WhenIdIsZero() {
        // Arrange
        Person person = new Person("Pedro", 0, 25, Gender.MALE, true);

        // Act
        RegisterResult result = registry.registerVoter(person);

        // Assert
        Assertions.assertEquals(RegisterResult.INVALID, result);
    }

    /**
     * Given la persona tiene id = -5, edad 25 y esta viva.
     * When intento registrarla.
     * Then el resultado debe ser INVALID.
     */
    @Test
    void shouldReturnInvalid_WhenIdIsNegative() {
        // Arrange
        Person person = new Person("Maria", -5, 25, Gender.FEMALE, true);

        // Act
        RegisterResult result = registry.registerVoter(person);

        // Assert
        Assertions.assertEquals(RegisterResult.INVALID, result);
    }

    /**
     * Given la persona tiene 17 anos, esta viva y su id es valido.
     * When intento registrarla.
     * Then el resultado debe ser UNDERAGE.
     */
    @Test
    void shouldReturnUnderage_WhenAgeIs17() {
        // Arrange
        Person minor = new Person("Luis", 3, 17, Gender.MALE, true);

        // Act
        RegisterResult result = registry.registerVoter(minor);

        // Assert
        Assertions.assertEquals(RegisterResult.UNDERAGE, result);
    }

    /**
     * Given la persona tiene 18 anos, esta viva y su id es valido.
     * When intento registrarla.
     * Then el resultado debe ser VALID.
     */
    @Test
    void shouldReturnValid_WhenAgeIs18() {
        // Arrange
        Person person = new Person("Sofia", 4, 18, Gender.FEMALE, true);

        // Act
        RegisterResult result = registry.registerVoter(person);

        // Assert
        Assertions.assertEquals(RegisterResult.VALID, result);
    }

    /**
     * Given la persona tiene 120 anos, esta viva y su id es valido.
     * When intento registrarla.
     * Then el resultado debe ser VALID.
     */
    @Test
    void shouldReturnValid_WhenAgeIs120() {
        // Arrange
        Person person = new Person("Rosa", 6, 120, Gender.FEMALE, true);

        // Act
        RegisterResult result = registry.registerVoter(person);

        // Assert
        Assertions.assertEquals(RegisterResult.VALID, result);
    }

    /**
     * Given la persona tiene 121 anos, esta viva y su id es valido.
     * When intento registrarla.
     * Then el resultado debe ser INVALID.
     */
    @Test
    void shouldReturnInvalid_WhenAgeIsOver120() {
        // Arrange
        Person person = new Person("Viejo", 7, 121, Gender.MALE, true);

        // Act
        RegisterResult result = registry.registerVoter(person);

        // Assert
        Assertions.assertEquals(RegisterResult.INVALID, result);
    }

    /**
     * Given la persona esta muerta.
     * When intento registrarla.
     * Then el resultado debe ser DEAD.
     */
    @Test
    void shouldReturnDead_WhenPersonIsNotAlive() {
        // Arrange
        Person dead = new Person("Carlos", 2, 40, Gender.MALE, false);

        // Act
        RegisterResult result = registry.registerVoter(dead);

        // Assert
        Assertions.assertEquals(RegisterResult.DEAD, result);
    }

    /**
     * Given la persona con id=777 ya fue registrada.
     * When intento registrarla de nuevo.
     * Then el resultado debe ser DUPLICATED.
     */
    @Test
    void shouldReturnDuplicated_WhenVoterIsAlreadyRegistered() {
        // Arrange
        Person person1 = new Person("Ana", 777, 25, Gender.FEMALE, true);
        Person person2 = new Person("Ana", 777, 25, Gender.FEMALE, true);
        registry.registerVoter(person1);

        // Act
        RegisterResult result = registry.registerVoter(person2);

        // Assert
        Assertions.assertEquals(RegisterResult.DUPLICATED, result);
    }

    /**
     * Given la persona tiene nombre null.
     * When intento registrarla.
     * Then el resultado debe ser INVALID.
     */
    @Test
    void shouldReturnInvalid_WhenNameIsNull() {
        // Arrange
        Person person = new Person(null, 11, 25, Gender.MALE, true);

        // Act
        RegisterResult result = registry.registerVoter(person);

        // Assert
        Assertions.assertEquals(RegisterResult.INVALID, result);
    }

    /**
     * Given la persona tiene nombre vacio.
     * When intento registrarla.
     * Then el resultado debe ser INVALID.
     */
    @Test
    void shouldReturnInvalid_WhenNameIsEmpty() {
        // Arrange
        Person person = new Person("", 12, 25, Gender.MALE, true);

        // Act
        RegisterResult result = registry.registerVoter(person);

        // Assert
        Assertions.assertEquals(RegisterResult.INVALID, result);
    }

    /**
     * Given la persona tiene nombre con solo espacios.
     * When intento registrarla.
     * Then el resultado debe ser INVALID.
     */
    @Test
    void shouldReturnInvalid_WhenNameIsBlank() {
        // Arrange
        Person person = new Person("   ", 13, 25, Gender.MALE, true);

        // Act
        RegisterResult result = registry.registerVoter(person);

        // Assert
        Assertions.assertEquals(RegisterResult.INVALID, result);
    }

    /**
     * Given la persona tiene nombre con numeros.
     * When intento registrarla.
     * Then el resultado debe ser INVALID.
     */
    @Test
    void shouldReturnInvalid_WhenNameContainsNumbers() {
        // Arrange
        Person person = new Person("Ana123", 14, 25, Gender.FEMALE, true);

        // Act
        RegisterResult result = registry.registerVoter(person);

        // Assert
        Assertions.assertEquals(RegisterResult.INVALID, result);
    }

    /**
     * Given la persona tiene nombre con intento de SQL injection.
     * When intento registrarla.
     * Then el resultado debe ser INVALID.
     */
    @Test
    void shouldReturnInvalid_WhenNameContainsSqlInjection() {
        // Arrange
        Person person = new Person(
            "'; DROP TABLE voters;--", 15, 25, Gender.MALE, true);

        // Act
        RegisterResult result = registry.registerVoter(person);

        // Assert
        Assertions.assertEquals(RegisterResult.INVALID, result);
    }

    /**
     * Given la persona tiene nombre con caracteres especiales.
     * When intento registrarla.
     * Then el resultado debe ser INVALID.
     */
    @Test
    void shouldReturnInvalid_WhenNameContainsSpecialChars() {
        // Arrange
        Person person = new Person("@user!", 16, 25, Gender.FEMALE, true);

        // Act
        RegisterResult result = registry.registerVoter(person);

        // Assert
        Assertions.assertEquals(RegisterResult.INVALID, result);
    }

    /**
     * Given la persona tiene nombre con tildes y espacios.
     * When intento registrarla.
     * Then el resultado debe ser VALID.
     */
    @Test
    void shouldReturnValid_WhenNameContainsAccents() {
        // Arrange
        Person person = new Person("Jos\u00e9 Mar\u00eda", 17, 25, Gender.MALE, true);

        // Act
        RegisterResult result = registry.registerVoter(person);

        // Assert
        Assertions.assertEquals(RegisterResult.VALID, result);
    }
}
