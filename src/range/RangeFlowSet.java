package range;

import soot.Local;
import soot.toolkits.scalar.AbstractFlowSet;

import java.util.*;

public class RangeFlowSet extends AbstractFlowSet{
    private Map<Local, Range> elements;

    public RangeFlowSet(){
        elements = new HashMap<>();
    }
    public RangeFlowSet(RangeFlowSet other){
        for (Map.Entry<Local, Range> entry : other.elements.entrySet()){
            elements.put(entry.getKey(), entry.getValue());
        }
    }

    private boolean sameType(Object flowSet) {
        return flowSet instanceof RangeFlowSet;
    }

    @Override
    public AbstractFlowSet clone() {
        return new RangeFlowSet(this);
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void add(Object o) {
        Range r = (Range) o;
        elements.put(r.getEntity(), r);
    }

    @Override
    public void remove(Object o) {
        Local l = (Local) o;
        elements.remove(l);
    }

    public Range get(Local key){
        return elements.get(key);
    }

    public Map<Local, Range> getElementsMap(){
        return elements;
    }

    @Override
    public boolean contains(Object o) {
        if (o instanceof Local)
            return elements.containsKey(o);
        else
            throw new UnsupportedOperationException("wrong key type");
    }

    @Override
    public List toList() {
        ArrayList<Range> result = new ArrayList<>();
        for (Map.Entry<Local, Range> entry : elements.entrySet()){
            result.add(entry.getValue());
        }
        return result;
    }

    @Override
    public String toString() {
        return elements.toString();
    }

    /*
        other = other this (dest == other)
        dest = other this
        this = other this (dest == this)
         */
    // There's no need to check all the crap above as the iteration is on "allKeys"
    public void union(RangeFlowSet otherFlow, RangeFlowSet destFlow) {
        if (this.sameType(otherFlow) && this.sameType(destFlow)) {
            // merge keySets, iterate
            HashSet<Local> allKeys = new HashSet<>(elements.keySet());
            allKeys.addAll(otherFlow.elements.keySet());

            // merge ranges
            for (Local local : allKeys){
                Range thisRangeValue = this.elements.get(local);
                Range otherRangeValue = otherFlow.elements.get(local);
                Range merged = (thisRangeValue == null)? otherRangeValue.clone() : thisRangeValue.union(otherRangeValue);
                destFlow.add(merged);
            }
        } else {
            super.union(otherFlow, destFlow);
        }
    }
}
