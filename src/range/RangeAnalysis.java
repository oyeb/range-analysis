package range;

import soot.Local;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.scalar.ArraySparseSet;
import soot.toolkits.scalar.BackwardFlowAnalysis;
import soot.toolkits.scalar.FlowSet;
import soot.util.Chain;


public class RangeAnalysis extends BackwardFlowAnalysis {

    private FlowSet emptySet, initialSet;

    public RangeAnalysis(UnitGraph g) {
        super(g);
        initialSet = new ArraySparseSet();
        emptySet = initialSet.clone();

        // generate list of locals
        Chain locals = g.getBody().getLocals();
        for (Object local : locals) {
            Local l = (Local) local;
            initialSet.add(new Range(l));
        }
        doAnalysis();
    }

    @Override
    protected Object newInitialFlow() {
        return emptySet.clone();
    }

    @Override
    protected Object entryInitialFlow() {
        return null;
    }

    @Override
    protected void merge(Object o, Object a1, Object a2) {

    }

    @Override
    protected void copy(Object o, Object a1) {

    }

    @Override
    protected void flowThrough(Object o, Object o2, Object a1) {

    }

}