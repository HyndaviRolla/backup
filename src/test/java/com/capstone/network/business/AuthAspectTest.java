package com.capstone.network.business;

import static org.junit.jupiter.api.Assertions.*;
 
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.capstone.network.entities.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthAspectTest {

    @Mock
    private LoggedInUser loggedInUser;

    @InjectMocks
    private AuthAspect authAspect;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void demandLogin_UserLoggedIn() throws Throwable {
        // Arrange
        ProceedingJoinPoint pjp = mock(ProceedingJoinPoint.class);
        NeedAuth needsAuth = mock(NeedAuth.class);

        when(loggedInUser.getLoggedInUser()).thenReturn(new User("testUser"));

        // Act
        Object result = authAspect.demandLogin(pjp, needsAuth);

        // Assert
        verify(pjp, times(1)).proceed();
        assertEquals(result, pjp.proceed());
    }

    @Test
    void demandLogin_UserNotLoggedIn() throws Throwable {
        // Arrange
        ProceedingJoinPoint pjp = mock(ProceedingJoinPoint.class);
        NeedAuth needsAuth = mock(NeedAuth.class);

        when(loggedInUser.getLoggedInUser()).thenReturn(null);
        when(needsAuth.loginPage()).thenReturn("/login");
 
        Object result = authAspect.demandLogin(pjp, needsAuth);
 
        verify(pjp, never()).proceed();
        assertEquals(result, "redirect:/login");
    }
     
}
