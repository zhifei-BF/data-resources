package com.liquor.pattern.prototype;

/**
 * Project：design-pattern
 * Date：2021/10/29
 * Time：17:39
 * Description：形状
 *
 * @author Liquor
 * @version 1.0.0
 */
public abstract class Shape implements Cloneable {
    private String id;
    protected String type;

    abstract void draw();

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
