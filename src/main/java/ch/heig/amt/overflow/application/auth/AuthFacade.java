package ch.heig.amt.overflow.application.auth;

import ch.heig.amt.overflow.domain.user.IUserRepository;
import ch.heig.amt.overflow.domain.user.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

public class AuthFacade {

    IUserRepository userRepository;

    public AuthFacade(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(RegisterCommand command) throws RegistrationFailedException {
        User existingUser = userRepository.findByUsername(command.getUsername()).orElse(null);

        if(existingUser != null) {
            throw new RegistrationFailedException("Nom d'utilisateur déjà utilisé");
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

    public UserDTO authenticate(AuthenticateCommand command) throws AuthenticationFailedException {
        User user = userRepository.findByUsername(command.getUsername()).orElse(null);

        if(!(user != null && user.authenticate(command.getClearTextPassword()))) {
            throw new AuthenticationFailedException("Identifiants invalides");
        }

        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

}
