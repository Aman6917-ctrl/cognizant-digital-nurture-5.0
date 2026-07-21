package com.cognizant.springtesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;

public class UserServiceTest {
    @Test
    public void testGetUserById() {
        UserRepository userRepository = mock(UserRepository.class);
        User user = new User(1L, "Aman");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User result = new UserService(userRepository).getUserById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Aman", result.getName());
        verify(userRepository).findById(1L);
    }
}
