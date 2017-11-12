import soot.*;

import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.graph.UnitGraph;

public class RangeAnalysisLauncher {
    public static void main(String[] args) {
        SootClass sClass = Scene.v().loadClassAndSupport(args[0]);
        sClass.setApplicationClass();

        for (SootMethod m : sClass.getMethods()) {
            Body b = m.retrieveActiveBody();
            System.out.println("METHOD START !!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("In method: \"" + m.toString() + "\"");

            UnitGraph graph = new ExceptionalUnitGraph(b);
            for (Unit u : graph) {
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