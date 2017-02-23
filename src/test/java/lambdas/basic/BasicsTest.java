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



}
