package ch.heig.overflow.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
public class Question extends Content {
    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private List<Comment> comments;

    @Getter
    @Setter
    private List<Answer> answers;
}
