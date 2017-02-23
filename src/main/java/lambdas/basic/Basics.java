package lambdas.basic;

import java.util.function.Supplier;

/**
 * Created by bwei3 on 2/23/17.
 */
public class Basics {

    /*
    *  Syntax of Lambdas
    *
    *  expression lambda
    *
    * */

    public Supplier<String > expressionLambda = () ->  "Hello World";

    /*
    *  Syntax of Lambdas
    *
    *  statement lambda
    *
    * */
    public Supplier<String> statementLambda = () -> { return  "Hello World"; };




}
