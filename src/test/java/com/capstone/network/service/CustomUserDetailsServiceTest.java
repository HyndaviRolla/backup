// package com.capstone.network.service;
// 
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
// 
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.test.util.ReflectionTestUtils;
// 
//import com.capstone.network.entities.User;
// 
//import java.util.Optional;
// 
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//class CustomUserDetailsServiceTest {
// 
//    @InjectMocks
//    private CustomUserDetailsService userDetailsService;
// 
//    @Mock
//    private UserService userService;
// 
//    @BeforeEach
//    public void setUp() {
//        // Using ReflectionTestUtils to set the userService field
//        ReflectionTestUtils.setField(userDetailsService, "userService", userService);
//    }
// 
//    @Test
//    void loadUserByUsername_UserFound_ReturnsUserDetails() {
//        // Arrange
//        String username = "testUser";
//        String password = "password";
//        User user = new User();
//        user.setName(username);
//        user.setPassword(password);
//        user.setRole("ROLE_USER");
// 
//        when(userService.getByName(username)).thenReturn(Optional.of(user));
// 
//        // Act
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
// 
//        // Assert
//        assertEquals(username, userDetails.getUsername());
//        assertEquals(password, userDetails.getPassword());
//        assertTrue(userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));
//    }
// 
//    // ... other test methods
//}