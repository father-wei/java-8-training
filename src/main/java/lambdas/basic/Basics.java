package lambdas.basic;

import java.util.function.BiConsumer;
import java.util.function.Supplier;


public class Basics {

    /*
    *  Syntax of Lambdas
    *
    *  expression lambda
    *
    */

    public Supplier<String > expressionLambda = () ->  "Hello World";

    /*
    *  Syntax of Lambdas
    *
    *  statement lambda
    *
    */
    public Supplier<String> statementLambda = () -> { return  "Hello World"; };

    /*
    *  Syntax of Lambdas
    *
    *  lambda with multiple parameters
    *
    *  Without explicit type
    */
    public BiConsumer<String, String> biConsumerTest1 = (s1, s2) -> System.out.println(String.format("s1: %s,  s2: %s", s1, s2));


    /*
    *  Syntax of Lambdas
    *
    *  lambda with multiple parameters
    *
    *  With explicit type
    */
    public BiConsumer<String, String> biConsumerTest2 = (String s1, String s2) -> System.out.println(String.format("s1: %s,  s2: %s", s1, s2));


    /*
    *
    * Lambdas vs Anonymous Inner Classes
    * */
    


}
