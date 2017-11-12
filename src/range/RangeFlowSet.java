package range;

import soot.Local;
import soot.toolkits.scalar.AbstractFlowSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RangeFlowSet extends AbstractFlowSet{
    public Map<Local, Range> elements;

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

    @Override
    public boolean contains(Object o) {
        if (o instanceof Local)
            return elements.containsKey(o);
        else
            throw new UnsupportedOperationException("wrong key type");
    }

    @Override
    public List toList() {
        return null;
    }
}
