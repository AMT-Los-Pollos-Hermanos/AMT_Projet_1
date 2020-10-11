package ch.heig.amt.overflow.application.auth;

import ch.heig.amt.overflow.domain.user.IUserRepository;
import ch.heig.amt.overflow.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthFacadeTest {

    @Mock
    IUserRepository userRepository;

    AuthFacade facade;

    @BeforeEach
    void setUp() {
        facade = new AuthFacade(userRepository);
    }

    @Test
    void testRegister() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        RegisterCommand cmd = RegisterCommand.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@heig.ch")
                .username("johndoe")
                .clearTextPassword("1234")
                .build();
        assertDoesNotThrow(() -> facade.register(cmd));
        verify(userRepository).save(any());
    }

    @Test
    void testRegisterExistingUser() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(User.builder().build()));
        RegisterCommand cmd = RegisterCommand.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@heig.ch")
                .username("johndoe")
                .clearTextPassword("1234")
                .build();
        assertThrows(RegistrationFailedException.class, () -> facade.register(cmd));
        verify(userRepository, never()).save(any());
    }

    @Test
    void testAuthenticateNoUserFound() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        AuthenticateCommand cmd = AuthenticateCommand.builder()
                .username("johndoe")
                .clearTextPassword("1234")
                .build();
        assertThrows(AuthenticationFailedException.class, () -> facade.authenticate(cmd));
    }

    @Test
    void testAuthenticateSuccess() {
        lenient().when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(User.builder()
                .username("johndoe")
                .clearTextPassword("1234")
                .firstName("John")
                .lastName("Doe")
                .build()));

        AuthenticateCommand cmd = AuthenticateCommand.builder()
                .username("johndoe")
                .clearTextPassword("1234")
                .build();

        CurrentUserDTO dto = assertDoesNotThrow(() -> facade.authenticate(cmd));
        assertNotNull(dto);
        assertEquals("John", dto.getFirstName());
        assertEquals("Doe", dto.getLastName());
        assertEquals("johndoe", dto.getUsername());
    }

    @Test
    void testAuthenticateWrongPassword() {
        lenient().when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(User.builder()
                .username("johndoe")
                .clearTextPassword("4321")
                .firstName("John")
                .lastName("Doe")
                .build()));

        AuthenticateCommand cmd = AuthenticateCommand.builder()
                .username("johndoe")
                .clearTextPassword("1234")
                .build();

        assertThrows(AuthenticationFailedException.class, () -> facade.authenticate(cmd));
    }

}