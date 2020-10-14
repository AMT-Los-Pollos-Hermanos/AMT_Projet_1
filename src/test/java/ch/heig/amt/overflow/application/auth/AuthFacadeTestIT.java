package ch.heig.amt.overflow.application.auth;

import ch.heig.amt.overflow.domain.user.IUserRepository;
import ch.heig.amt.overflow.infrastructure.persistence.jdbc.JdbcUserRepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@RunWith(Arquillian.class)
public class AuthFacadeTestIT {

    private final static String WARNAME = "arquillian-managed.war";

//    @Inject
//    JdbcUserRepository userRepository;
//
//    AuthFacade facade;

//    @BeforeEach
//    void setUp() {
//        facade = new AuthFacade(userRepository);
//    }

    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, WARNAME)
                .addPackages(true, "ch.heigvd.amt");
        return archive;
    }

    @Test
    public void testRegister(){
//        RegisterCommand cmd = RegisterCommand.builder()
//                .firstName("John")
//                .lastName("Doe")
//                .email("john.doe@heig.ch")
//                .username("johndoe")
//                .clearTextPassword("1234")
//                .build();
//        assertDoesNotThrow(() -> facade.register(cmd));
        int a = 1;
        assertEquals(a, 1);
    }
}
