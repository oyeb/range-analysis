package range;

import soot.toolkits.graph.DirectedGraph;
import soot.toolkits.scalar.BackwardFlowAnalysis;

public class RangeAnalysis extends BackwardFlowAnalysis{

    public RangeAnalysis(DirectedGraph g){
        super(g);
        doAnalysis();
    }

    @Override
    protected Object newInitialFlow() {
        return null;
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