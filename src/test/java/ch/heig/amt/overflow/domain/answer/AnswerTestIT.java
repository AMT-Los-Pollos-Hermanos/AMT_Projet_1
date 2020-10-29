/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.domain.answer;

import ch.heig.amt.overflow.application.ServiceRegistry;
import ch.heig.amt.overflow.application.auth.RegisterCommand;
import ch.heig.amt.overflow.application.auth.RegistrationFailedException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.fail;

@RunWith(Arquillian.class)
public class AnswerTestIT {
    private final static String WARNAME = "arquillian-managed.war";

    @Inject
    ServiceRegistry serviceRegistry;

    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, WARNAME)
                .addPackages(true, "ch.heig.amt.overflow", "org.mindrot.jbcrypt");
        return archive;
    }

    @Test
    public void testRegister() {
        RegisterCommand cmd = RegisterCommand.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@heig.ch")
                .username("johndoe")
                .clearTextPassword("1234")
                .build();
        try {
            serviceRegistry.getAuthFacade().register(cmd);
        } catch (RegistrationFailedException e) {
            fail(e.getMessage());
        }
    }
}




