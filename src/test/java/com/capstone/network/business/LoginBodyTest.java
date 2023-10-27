package com.capstone.network.business;

 
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LoginBodyTest {

    @Test
    void getUsername_ReturnsCorrectValue() {
        // Arrange
        String expectedUsername = "testUser";
        LoginBody loginBody = new LoginBody();
        loginBody.setUsername(expectedUsername);

        // Act
        String actualUsername = loginBody.getUsername();

        // Assert
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    void setUsername_SetsCorrectValue() {
        // Arrange
        String username = "testUser";
        LoginBody loginBody = new LoginBody();

        // Act
        loginBody.setUsername(username);

        // Assert
        assertEquals(username, loginBody.getUsername());
    }

    @Test
    void getPassword_ReturnsCorrectValue() {
        // Arrange
        String expectedPassword = "testPassword";
        LoginBody loginBody = new LoginBody();
        loginBody.setPassword(expectedPassword);

        // Act
        String actualPassword = loginBody.getPassword();

        // Assert
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void setPassword_SetsCorrectValue() {
        // Arrange
        String password = "testPassword";
        LoginBody loginBody = new LoginBody();

        // Act
        loginBody.setPassword(password);

        // Assert
        assertEquals(password, loginBody.getPassword());
    }

    @Test
    void defaultConstructor_InitializesFieldsToNull() {
        // Act
        LoginBody loginBody = new LoginBody();

        // Assert
        assertNull(loginBody.getUsername());
        assertNull(loginBody.getPassword());
    }

     
}


