package ch.heig.amt.overflow.domain;

public interface IPasswordEncoder {

    String hash(String clearTextPassword);

    boolean verify(String clearTextPassword, String hash);

}
