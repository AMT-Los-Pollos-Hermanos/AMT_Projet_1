package ch.heig.amt.overflow.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
public class Answer extends Content {
    @Getter
    @Setter
    private List<Comment> comments;
}