/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.domain;

public interface IPasswordEncoder {

    String hash(String clearTextPassword);

    boolean verify(String clearTextPassword, String hash);

}
