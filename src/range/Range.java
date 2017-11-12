package range;
import soot.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Range {
    private Local entity; // this could be a Local instead.
    private int leftBound, rightBound;
    private boolean undef = false;

    public Range(Local entity){
        this.entity = entity;
        undef = true;
    }

    public Range(Local entity, int left, int right){
        this.entity = entity;
        leftBound = left;
        rightBound = right;
    }

    public Local getEntity() {
        return entity;
    }

    public Range clone(){
        return new Range(this.entity, this.leftBound, this.rightBound);
    }

    public void setBounds(int left, int right){
        leftBound = left;
        rightBound = right;
    }

    public String toString(){
        return String.format("%s <%d, %d>", entity.toString(), leftBound, rightBound);
    }

    public Range union(Range other){
        if ((this.entity.getType() != other.entity.getType()) || this.entity.getName().equals(other.entity.getName())){
            throw new UnsupportedOperationException("type mismatch");
        }
        else if (this.undef || other.undef){
            return new Range((Local) entity.clone());
        }
        else{
            return new Range((Local) entity.clone(),
                    min(leftBound, other.leftBound),
                    max(rightBound, other.rightBound));
        }
    }
}
