package lambdas.basic;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.function.Supplier;

import static org.junit.Assert.*;

public class BasicsTest {
    private Basics basics = new Basics();

    @Test
    public void testExpressionLambda(){
        assertEquals(basics.expressionLambda.get(), "Hello World");
    }

    @Test
    public void testStatementLambda(){
        assertEquals(basics.statementLambda.get(), "Hello World");
    }

    @Test
    public void testBiConsummer(){
        basics.biConsumerTest1.accept("Hello", "World");
        basics.biConsumerTest2.accept("Hello", "World");
    }


    @Test
    public void testAnonymousClassIdentity(){
        assertEquals(basics.plusOne(10), 11);
    }

    @Test
    public void testAnonymousClassScope(){
        assertEquals(basics.anonymousScope(), "Anonymous Inner Class");
    }

    @Test
    public void testLambdaScope(){
        assertEquals(basics.getScopeToString.get(), "Outer Current Scope");
    }

    @Test
    public void testLambdaScopeThroughThread(){
        basics.runThread();
    }

    @Test
    public void testLambdaReferenceSelf(){
        assertEquals(basics.fact1(5), 120);
        assertEquals(basics.fact2.applyAsInt(5), 120);
        assertEquals(basics.fact3.applyAsInt(5), 120);
        assertEquals(Basics.fact3.applyAsInt(5), 120);
    }

    @Test
    public void interfaceWithOneMethodTest(){
        assertEquals(basics.interfaceWithOneMethod.apply("Hello") , "Hello World");
    }


    @Test
    public void testFunctionalInterface(){
        assertEquals(basics.si.compare(), true);
    }

    @Test
    public void testCompareTarget(){
        assertEquals(basics.cc.compare("a", "z") < 0, true);
    }

    @Test
    public void testReturnTarget(){
        basics.returnDatePrinter().run();
    }

    @Test
    public void testReturnTargetLambdaBody(){
        try {
            basics.c.call().run();
        }catch (Exception ex){

        }
    }


    @Test
    public void testCurry(){
        assertEquals(basics.curry.get().get(), "Hi");
    }

    @Test
    public void testCastExpressionTarget(){
        assertEquals(((Supplier)basics.o).get(), "hi") ;

    }


    @Test
    public void testOverloading(){
        basics.<Integer>overloading(  s -> s + 1);
    }

    @Test
    public void testOverloadingWithMethodReference(){
        basics.<Exception>overloading2( Exception::new );
        basics.<String, Exception>overloading2(Exception::new);
    }
}
