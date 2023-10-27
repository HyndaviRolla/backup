package com.capstone.network.exception;

import static org.junit.jupiter.api.Assertions.*;
import com.capstone.network.exception.BadRequestException;
import org.junit.jupiter.api.Test;

public class BadRequestExceptionTest {

    @Test
    public void testBadRequestException() {
        // Arrange
        String errorMessage = "Bad request message";

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            throw new BadRequestException(errorMessage);
        });

        // Verify the error message
        String actualMessage = exception.getMessage();
        assert actualMessage.contains(errorMessage) : "Expected error message not found";
    }
}