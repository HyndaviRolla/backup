package com.capstone.network.business;

import com.capstone.network.business.LoggedInUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(SessionBeanConfig.class)
class SessionBeanConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void loggedInUserBeanConfigured() {
        // Act
        LoggedInUser loggedInUser = applicationContext.getBean(LoggedInUser.class);

        // Assert
        assertNotNull(loggedInUser);
    }

    // Add more tests as needed
}

