package ch.heig.amt.overflow.application.auth;

import ch.heig.amt.overflow.domain.user.IUserRepository;
import ch.heig.amt.overflow.domain.user.User;

public class AuthFacade {

    private final IUserRepository userRepository;

    public AuthFacade(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(RegisterCommand command) throws RegistrationFailedException {
        User existingUser = userRepository.findByUsername(command.getUsername()).orElse(null);

        if(existingUser != null) {
            throw new RegistrationFailedException("Username is already used");
        }

        try {
            User newUser = User.builder()
                    .username(command.getUsername())
                    .firstName(command.getFirstName())
                    .lastName(command.getLastName())
                    .email(command.getEmail())
                    .clearTextPassword(command.getClearTextPassword())
                    .build();
            userRepository.save(newUser);
        } catch (Exception e) {
            throw new RegistrationFailedException(e.getMessage());
        }
    }

    public CurrentUserDTO authenticate(AuthenticateCommand command) throws AuthenticationFailedException {
        User user = userRepository.findByUsername(command.getUsername()).orElse(null);

        if(!(user != null && user.authenticate(command.getClearTextPassword()))) {
            throw new AuthenticationFailedException("Invalid credentials");
        }

        CurrentUserDTO currentUser = CurrentUserDTO.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();

        return currentUser;
    }

}