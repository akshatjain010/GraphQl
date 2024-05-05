/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package learn.graphql.exception;

/**
 *
 * @author Akshat Jain
 */
public class IllegalArgsException extends RuntimeException{
    public IllegalArgsException(String message) {
        super(message);
    }
}
