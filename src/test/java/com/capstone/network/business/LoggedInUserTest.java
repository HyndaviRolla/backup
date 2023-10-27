package com.capstone.network.business;
 
import com.capstone.network.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LoggedInUserTest {

    private LoggedInUser loggedInUser;

    @BeforeEach
    void setUp() {
        loggedInUser = new LoggedInUser();
    }

    @Test
    void getLoggedInUser_UserNotSet_ReturnsNull() {
        // Act
        User result = loggedInUser.getLoggedInUser();

        // Assert
        assertNull(result);
    }

    @Test
    void setAndGetLoggedInUser_UserSet_ReturnsCorrectUser() {
        // Arrange
        User user = new User("testUser");

        // Act
        loggedInUser.setLoggedInUser(user);
        User result = loggedInUser.getLoggedInUser();

        // Assert
        assertEquals(user, result);
    }

    @Test
    void setLoggedInUser_NullUser_UserIsNull() {
        // Act
        loggedInUser.setLoggedInUser(null);
        User result = loggedInUser.getLoggedInUser();

        // Assert
        assertNull(result);
    }
    
    // Add more tests as needed
}
