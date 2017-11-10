import soot.*;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.graph.UnitGraph;

import java.util.Iterator;

public class RangeAnalysisLauncher {
    public static void main(String[] args) {
        SootClass sClass = Scene.v().loadClassAndSupport(args[0]);
        sClass.setApplicationClass();

        Iterator methodIt = sClass.getMethods().iterator();
        while (methodIt.hasNext()) {
            SootMethod m = (SootMethod) methodIt.next();
            Body b = m.retrieveActiveBody();
            System.out.println("METHOD START !!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("In method: \"" + m.toString() + "\"");

            UnitGraph graph = new ExceptionalUnitGraph(b);
            Iterator gIt = graph.iterator();
            while (gIt.hasNext()) {
                Unit u = (Unit) gIt.next();
                UnitPrinter up = new NormalUnitPrinter(b);
                up.setIndent("  ");
                System.out.println("----------------------");
                u.toString(up);
                System.out.println(up.output());
            }
            System.out.println("METHOD END   =========================");
        }
    }
}