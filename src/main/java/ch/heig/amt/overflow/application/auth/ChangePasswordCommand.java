package ch.heig.amt.overflow.application.auth;

import ch.heig.amt.overflow.domain.user.UserId;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ChangePasswordCommand {
    UserId userId;
    String oldPassword;
    String newPassword;
    String newPasswordAgain;
}
