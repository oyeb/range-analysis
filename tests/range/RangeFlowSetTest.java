package range;

import org.junit.Test;
import soot.IntType;
import soot.jimple.internal.JimpleLocal;

import static org.junit.Assert.*;

public class RangeFlowSetTest {
    Range foo1, foo2, foo_merged, bar1;
    RangeFlowSet s1, s2, s12;
    JimpleLocal foo, bar, baz;

    public RangeFlowSetTest(){
        foo = new JimpleLocal("foo", new IntType(null));
        bar = new JimpleLocal("bar", new IntType(null));
        baz = new JimpleLocal("baz", new IntType(null));

        foo1 = new Range(foo, 0, 8);
        foo2 = new Range(foo, -8, 3);
        foo_merged = foo1.union(foo2);
        bar1 = new Range(bar, -18, -13);

        s1 = new RangeFlowSet();
        s1.add(foo1);
        s1.add(bar1);

        s2 = new RangeFlowSet();
        s2.add(foo2);


        s12 = new RangeFlowSet();
        s1.union(s2, s12);
    }

    @Test
    public void unionProper() throws Exception {
        Range r = s12.get(foo);
        assertTrue(foo_merged.equals(r));
    }

    @Test
    public void unionNull() throws Exception {
        Range r = s12.get(bar);
        assertTrue(bar1.equals(r));
    }

    @Test
    public void copy_constructor() throws Exception {
        RangeFlowSet mirror = new RangeFlowSet(s1);
        assertTrue(mirror.equals(s1));
        assertFalse(mirror.getElementsMap() == s1.getElementsMap());
        assertFalse(mirror.get(foo) == s1.get(foo));
    }

    @Test
    public void copy() throws Exception {
        RangeFlowSet mirror = new RangeFlowSet();
        s1.copy(mirror);
        assertTrue(mirror.equals(s1));
        assertFalse(mirror.getElementsMap() == s1.getElementsMap());
        assertFalse(mirror.get(foo) == s1.get(foo));
    }
}