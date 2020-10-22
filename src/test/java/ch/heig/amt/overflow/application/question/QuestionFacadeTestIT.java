package ch.heig.amt.overflow.application.question;

import ch.heig.amt.overflow.application.ServiceRegistry;
import ch.heig.amt.overflow.application.auth.RegisterCommand;
import ch.heig.amt.overflow.application.auth.RegistrationFailedException;
import ch.heig.amt.overflow.domain.question.IQuestionRepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

@RunWith(Arquillian.class)
public class QuestionFacadeTestIT {
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
    public void testAddNewEmptyQuestion() {
        NewQuestionCommand cmd = NewQuestionCommand.builder()
                .authorId(null)
                .title("")
                .content("")
                .build();
        assertThrows(IllegalArgumentException.class, () -> serviceRegistry.getQuestionFacade().addNewQuestion(cmd));

    }

}
