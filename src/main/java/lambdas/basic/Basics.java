package lambdas.basic;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.*;


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


    /*
    *  Functional Interface
    *  Type is inferred by Functional Interface
    * */
    IntUnaryOperator iup    = x -> x * 2;

    DoubleUnaryOperator duo = x -> x * 2;


    /*
    *  Functional Interface
    *  Let's try a abstract class doesn't work
    *
    *  follow code won't compile


    abstract class AbstractWithOneAbstractMethod{
        public abstract String getString(String s);
    }


    AbstractWithOneAbstractMethod abstractMethod = i -> i;
    */



    /*
    *   Functional Interface
    *   Let's try a a interface
    * */

    interface InterfaceWithOneMethod {
        String apply(String s);
    }

    public InterfaceWithOneMethod interfaceWithOneMethod = x -> x + " World";




    /*
    *  Consumer<T>
    *  Predicate<T>
    *  Supplier<T>
    *  Function<T, U>
    * */
    Consumer<String> consumer = x -> { System.out.println("Hello World");};

    Predicate<Integer> predicate = x -> x == 100;

    Supplier<String> supplier = ()-> "Hello World";

    Function<Integer, String> function = x -> x + "";


    /*
    *  Primitive Functional Interface
    *
    *  interface LongFunction<R>  { R apply(long value); }
    *  interface ToIntFunction<T> { int applyAsInt(T value); }
    *  interface LongToIntFunction { int applyAsInt(long value); }
    * */

    /*
    *  Two arguments Functional Interface
    *  interface BiConsumer <T, U> { void accept(T t, U u); }
    *  interface BiFunction <T, U, R> { R apply(T t, U u); }
    *  interface ToIntBiFunction<T, U> { int apply(T t, U u); }
    * */

    /*
    *  Function require parameter and result same type
    *  interface UnaryOperator<T> extends Function<T, T> { ... }
    *  interface BinaryOperator<T> extends Function <T, T, T> { ... }
    *  interface IntBinaryOperator {int applyAsInt(int left, int right); }
    *
    * */



    /*
    *  Target type
    * */
    @FunctionalInterface
    interface SomeInterface {
        boolean compare();
        boolean equals(Object obj);
    }


    SomeInterface si = () -> true;

    /*
    * Variable declarations and assignments, for which the target type is the type being assigned to
    * */
    Comparator<String> cc = (s1, s2) -> s1.compareToIgnoreCase(s2);

    /*
    * Array initializer, only work for primitive functionalInterface
    * */
    IntBinaryOperator[] calcuatorOps = new IntBinaryOperator[]{
            (x, y) -> x + y, (x,y) -> x -y, (x,y) -> x* y, (x,y) -> x / y
    };

    /*
    * The follow code won't compile
    *
    * Function<String, String>[] collectStrings = new Function<String, String>[] {};
    * */


    /*
    * Return statements, for which the target type is the return type of the method
    * */
    Runnable returnDatePrinter(){
        return () -> System.out.println(new Date());
    }

    /*
    * Lamdba expression bodies, for which the target type is the type expected for the body,
    * which is derived in turn from the outer target type
    * */

    Callable<Runnable> c = ()-> () -> System.out.println("Hi");

    /*
    * currying, same as above
    * */

    Supplier<Supplier<String>> curry = () -> () -> "Hi";


    /*
    * Ternary (Same as variable declaration)
    * */
    Callable<Integer> c1 = true ? (() -> 23) : (() -> 24);

    /*
    * Cast expression
    * */

    Object o = (Supplier) ()-> "hi";

    /*
    * If reuse the lambda (cast), can compile, but throw runtime java.lang.ClassCastException
    *  Callable c2 = (Callable) o;
    * */



    /*
    * Overloading with Lambda expressions
    *
    * */

    static <T> void overloading( Function<T, T> fn){
        System.out.println("Function");
    }

    static <T> void overloading ( UnaryOperator<T> fn){
        System.out.println("UnaryOperator");
    }




    /*
    * Overloading with method references
    */


    <T> void overloading2 (Supplier<T> Supplier) {
        System.out.println("Supplier");
    }

    <T, U> void overloading2 (Function<T, U> fn) {
        System.out.println("Function");
    }


    /*
    *  Method and constructor Reference
    * */

    static class User{
        final private String userName;
        public User(String username) {
            this.userName = username;
        }

        public String getUserName(){
            return this.userName;
        }

        @Override
        public String toString(){
            return  "My name is " + userName;
        }

        static public String transform(String name){
            return "My name is " + name;
        }
    }

    // Method reference
    public void tryMethodReference(){
        List<User> users = Arrays.asList(new User("Xiao Ming"), new User("Xiao Hong"));

        users.stream().map(User::getUserName).forEach(System.out::println);
    }


    // Method constructor reference
    public void tryMethodConstructorReference(){
        List<User> users = Arrays.asList(new User("Xiao Ming"), new User("Xiao Hong"));
        users.stream().map(User::getUserName).map(User::new).forEach(System.out::println);
    }


    // Static method reference
    public void tryStaticMethodReference(){
        List<User> users = Arrays.asList(new User("Xiao Ming"), new User("Xiao Hong"));

        users.stream().map(User::getUserName).map(User::transform).forEach(System.out::println);
    }


    //method reference with two parameters
    public void tryMethodWithTwoParams(){
        //create a map
        Map<String, String> map = new HashMap<>();
        map.put("Xiaohong", "1");
        map.put("Xiaoming", "2");
        map.put("asan", "3");

        String str = "Xiaohong-Xiaoming-asan";
        map.replaceAll(str::replace);

        // same as above
        // map.replaceAll((k, v) -> str.replace(k, v));
        map.forEach((k , v) -> System.out.println(k + " " + v));


        map = new HashMap<>();
        map.put("Xiaohong", "1");
        map.put("Xiaoming", "2");
        map.put("asan", "3");

        map.replaceAll(String::concat);

        // same as above
        //  map.replaceAll((k, v) -> k.concat(v));
        map.forEach((k , v) -> System.out.println(k + " " + v));



    }





}
