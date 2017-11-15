package range;

import soot.IntType;
import soot.jimple.internal.JimpleLocal;

import org.junit.Test;
import static org.junit.Assert.*;

public class RangeTest {
    public JimpleLocal foo, bar, baz;
    private Range a, b, c;

    public RangeTest(){
        foo = new JimpleLocal("foo", new IntType(null));
        bar = new JimpleLocal("bar", new IntType(null));
        baz = new JimpleLocal("baz", new IntType(null));

        a = new Range(foo, 0, 8);
        b = new Range(foo, -8, 3);
    }

    @Test
    public void unionProper() throws Exception {
        c = a.union(b);
        assertEquals("leftBound", -8, c.getLeftBound());
        assertEquals("rightBound", 8, c.getRightBound());
    }

    @Test
    public void unionNull() throws Exception {
        c = a.union(null);
        assertEquals("leftBound", a.getLeftBound(), c.getLeftBound());
        assertEquals("rightBound", a.getRightBound(), c.getRightBound());
    }

    @Test
    public void equality() throws Exception {
        c = a.clone();
        assertTrue(c.equals(a));
    }
}