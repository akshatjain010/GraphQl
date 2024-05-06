/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package learn.graphql.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import graphql.ErrorType;

import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

//import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//    
//    @GraphQlExceptionHandler
//    public GraphQLError handleBookNotFoundException(BookNotFoundException e) {    
//        return GraphQLError.newError()
//                .message(e.getMessage())
//                .build();
//    }
//    
//    @GraphQlExceptionHandler
//    public GraphQLError handleIllegalArgsException(IllegalArgsException e) {
//        return GraphQLError.newError()
//                .message(e.getMessage())
//                .build();
//    }
//    
//    @GraphQlExceptionHandler
//    public GraphQLError handleEmptyResultException(EmptyResultException e) {
//        return GraphQLError.newError()
//                .message(e.getMessage())
//                .build();
//    }
//}

@Component
public class GlobalExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if(ex instanceof BookNotFoundException || ex instanceof EmptyResultException) {
            return GraphqlErrorBuilder.newError()
              .errorType(ErrorType.DataFetchingException)
              .message(ex.getMessage())
              .path(env.getExecutionStepInfo().getPath())
              .location(env.getField().getSourceLocation())
              .build();
        }
        else if(ex instanceof IllegalArgsException) {
            return GraphqlErrorBuilder.newError()
              .errorType(ErrorType.ValidationError)
              .message(ex.getMessage())
              .path(env.getExecutionStepInfo().getPath())
              .location(env.getField().getSourceLocation())
              .build();
        }
        else return null;
    }
}