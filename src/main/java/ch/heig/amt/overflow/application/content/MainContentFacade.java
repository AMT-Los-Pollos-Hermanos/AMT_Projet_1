package ch.heig.amt.overflow.application.content;

import ch.heig.amt.overflow.domain.MainContentId;
import ch.heig.amt.overflow.infrastructure.persistence.jdbc.JdbcMainContentRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MainContentFacade {

    @Inject
    private JdbcMainContentRepository repository;

    public Object getContentFromId(MainContentId id) {
        return repository.findById(id).orElse(null);
    }

}
