package range;

import soot.Local;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.scalar.ArraySparseSet;
import soot.toolkits.scalar.BackwardFlowAnalysis;
import soot.toolkits.scalar.FlowSet;
import soot.util.Chain;


public class RangeAnalysis extends BackwardFlowAnalysis {

    private FlowSet emptySet;

    public RangeAnalysis(UnitGraph g) {
        super(g);
        emptySet = new RangeFlowSet();
        doAnalysis();
    }

    @Override
    protected Object newInitialFlow() {
        return emptySet.clone();
    }

    @Override
    protected Object entryInitialFlow() {
        return emptySet.clone();
    }

    @Override
    protected void merge(Object in1, Object in2, Object out) {
        FlowSet inSet1 = (FlowSet) in1,
                inSet2 = (FlowSet) in2,
                outSet = (FlowSet) out;
        inSet1.union(inSet2, outSet);
    }

    @Override
    protected void copy(Object src, Object dest) {
        FlowSet destSet = (FlowSet) dest,
                srcSet = (FlowSet) src;
        srcSet.copy(destSet);
    }

    @Override
    protected void flowThrough(Object in, Object node, Object out) {

    }

}