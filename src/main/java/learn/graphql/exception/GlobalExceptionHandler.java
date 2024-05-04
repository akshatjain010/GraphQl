/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package learn.graphql.exception;

import graphql.GraphQLError;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 *
 * @author Akshat Jain
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @GraphQlExceptionHandler
    public GraphQLError handleBookNotFoundException(BookNotFoundException e) {    
        return GraphQLError.newError()
                .message(e.getMessage())
                .build();
    }
}
