package ch.heig.amt.overflow.domain.message;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class FlashMessage {

    String message;
    
    @Builder.Default
    String type = "success";

}
