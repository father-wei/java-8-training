package lambdas.basic;


import java.util.function.BiConsumer;
import java.util.function.IntUnaryOperator;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;


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
    *
    * Identity
    * Since lambdas must evaluate to an instance of a class that implements the appropriate functional interface,
    * but no need identity (reference)
    *
    * The follow code won't compile
    *
    * Object l = x -> x + 1
    * */

    public interface AnonymousInterface  {
        public int addOne(int i);
    }

    public int plusOne(int num){
        AnonymousInterface obj = new AnonymousInterface(){
            @Override
            public int addOne(int i) {
                return i + 1;
            }
        };
        return obj.addOne(num);
    }


    /*
    *
    * Lambdas vs Anonymous Inner Classes
    *
    * Scoping
    */

    public String anonymousScope(){
        AnonymousInterface obj = new AnonymousInterface(){
            @Override
            public int addOne(int i) {
                return i + 1;
            }
            @Override
            public String toString(){
                return "Anonymous Inner Class";
            }
        };

        return obj.toString();
    }

    /*
    *
    * Lambdas vs Anonymous Inner Classes
    *
    * Lambda Scoping
    */
    @Override
    public String toString(){
        return "Outer Current Scope";
    }

    public Supplier<String> getScopeToString = () -> toString();


    /*
    *
    * Lambdas vs Anonymous Inner Classes
    *
    * Lambda Scope and Functional Interface
    */

    public void runThread(){
        Runnable r1 = ()-> {
            System.out.println(this);
        };

        Runnable r2 = ()-> {
            System.out.println(toString());
        };
        //"Outer Current Scope" shows twice since this and toString are linking to current object scope
        r1.run();
        r2.run();

    }

    /*
    * Lambdas vs Anonymous Inner Classes
    *
    * Lambdas reference itself
    * */

    // Define in class level
    IntUnaryOperator fact_;

    public int fact1(int num) {
        fact_ = i -> i == 0 ? 1 : i * fact_.applyAsInt(i -1);
        return fact_.applyAsInt(num);
    }


    public IntUnaryOperator fact2 = i -> i == 0 ? 1 : i * this.fact2.applyAsInt( i - 1);

    public static IntUnaryOperator fact3 = i -> i == 0 ? 1 : i * Basics.fact3.applyAsInt( i - 1);




}
