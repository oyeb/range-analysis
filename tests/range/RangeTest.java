package range;

import soot.*;
import soot.jimple.AssignStmt;
import soot.jimple.Expr;
import soot.jimple.Stmt;
import soot.jimple.internal.JimpleLocal;

import org.junit.Test;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.graph.UnitGraph;

import static org.junit.Assert.*;

public class RangeTest {
    private JimpleLocal foo, bar, baz;
    private Range a, b, c;
    private SootMethod range_tests, main;

    public RangeTest(){
        foo = new JimpleLocal("foo", new IntType(null));
        bar = new JimpleLocal("bar", new IntType(null));
        baz = new JimpleLocal("baz", new IntType(null));

        a = new Range(foo, 0, 8);
        b = new Range(foo, -8, 3);

        SootClass sClass = Scene.v().loadClassAndSupport("test");
        sClass.setApplicationClass();

        main = sClass.getMethodByName("main");
        range_tests = sClass.getMethodByName("range_tests");
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

    @Test
    public void flow() {
        Body b = main.retrieveActiveBody();
        UnitGraph graph = new ExceptionalUnitGraph(b);
        for (Unit u : graph) {
            if (u instanceof AssignStmt){
                System.out.println(u);
                for (ValueBox vb : u.getUseBoxes()){
                    if (vb.getValue() instanceof Expr) System.out.print("expr! ");
                    System.out.println(vb.getValue());
                }
            }
            System.out.println("--");
        }
    }
}