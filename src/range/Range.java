package range;
import soot.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Range {
    private Local entity; // this could be a Local instead.
    private int leftBound, rightBound;

    public Range(Local entity){
        this.entity = entity;
    }

    public Range(Local entity, int left, int right){
        if (left > right) throw new UnsupportedOperationException("left bound should be less or equal to right bound.");
        this.entity = entity;
        leftBound = left;
        rightBound = right;
    }

    public Local getEntity() {
        return entity;
    }

    public int getLeftBound() {
        return leftBound;
    }

    public int getRightBound() {
        return rightBound;
    }

    public Range clone(){
        return new Range(this.entity, this.leftBound, this.rightBound);
    }

    public String toString(){
        return String.format("%s <%d, %d>", entity.toString(), leftBound, rightBound);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Range)) return false;
        Range other = (Range) obj;
        return (entity == other.getEntity() || entity.equivTo(other.getEntity())) &&
                leftBound == other.getLeftBound() &&
                rightBound == other.getRightBound();
    }

    @Override
    public int hashCode() {
        return entity.hashCode() - leftBound + rightBound * 3;
    }

    // TODO: 13/11/17
    // the condition needs improvement, can i simply call equivTo() or equals() rather than comparing the strings?
    public Range union(Range other){
        if (other == null) {
            return this.clone();
        }
        if ((this.entity.getType() != other.entity.getType()) ||
                !this.entity.getName().equals(other.entity.getName())){
            throw new UnsupportedOperationException("type mismatch");
        }
        else{
            return new Range(entity,
                    min(leftBound, other.leftBound),
                    max(rightBound, other.rightBound));
        }
    }
}
