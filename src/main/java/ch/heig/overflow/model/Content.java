package ch.heig.overflow.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Content {
    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    private List<Integer> upVote;

    @Getter
    @Setter
    private List<Integer> downVote;

    @Getter
    @Setter
    private User author;
}