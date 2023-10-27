package com.capstone.network.business;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserFormTest {

    @Test
    void getName_ReturnsCorrectValue() {
        // Arrange
        String expectedName = "John Doe";
        UserForm userForm = new UserForm();
        userForm.setName(expectedName);

        // Act
        String actualName = userForm.getName();

        // Assert
        assertEquals(expectedName, actualName);
    }

    @Test
    void setName_SetsCorrectValue() {
        // Arrange
        String name = "Jane Doe";
        UserForm userForm = new UserForm();

        // Act
        userForm.setName(name);

        // Assert
        assertEquals(name, userForm.getName());
    }

    @Test
    void getPassword_ReturnsCorrectValue() {
        // Arrange
        String expectedPassword = "securePassword";
        UserForm userForm = new UserForm();
        userForm.setPassword(expectedPassword);

        // Act
        String actualPassword = userForm.getPassword();

        // Assert
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void setPassword_SetsCorrectValue() {
        // Arrange
        String password = "strongPassword";
        UserForm userForm = new UserForm();

        // Act
        userForm.setPassword(password);

        // Assert
        assertEquals(password, userForm.getPassword());
    }

    @Test
    void getPasswordRepeat_ReturnsCorrectValue() {
        // Arrange
        String expectedPasswordRepeat = "securePassword";
        UserForm userForm = new UserForm();
        userForm.setPasswordRepeat(expectedPasswordRepeat);

        // Act
        String actualPasswordRepeat = userForm.getPasswordRepeat();

        // Assert
        assertEquals(expectedPasswordRepeat, actualPasswordRepeat);
    }

    @Test
    void setPasswordRepeat_SetsCorrectValue() {
        // Arrange
        String passwordRepeat = "strongPassword";
        UserForm userForm = new UserForm();

        // Act
        userForm.setPasswordRepeat(passwordRepeat);

        // Assert
        assertEquals(passwordRepeat, userForm.getPasswordRepeat());
    }

    // Add more tests as needed
}
