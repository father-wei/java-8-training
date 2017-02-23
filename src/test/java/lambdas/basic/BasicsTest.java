package lambdas.basic;

import org.junit.Test;
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

    


}
