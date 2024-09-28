package model;

public class Plane {

    private int planeID;
    private String planeName;
    private String planeManufacturer;
    private boolean hasFirstClass;
    private boolean hasBusinessClass;
    private boolean hasEconomyClass;

    public int getPlaneID() {
        return planeID;
    }

    public void setPlaneID(int planeID) {
        this.planeID = planeID;
    }

    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

    public String getPlaneManufacturer() {
        return planeManufacturer;
    }

    public void setPlaneManufacturer(String planeManufacturer) {
        this.planeManufacturer = planeManufacturer;
    }

    public boolean isHasFirstClass() {
        return hasFirstClass;
    }

    public void setHasFirstClass(boolean hasFirstClass) {
        this.hasFirstClass = hasFirstClass;
    }

    public boolean isHasBusinessClass() {
        return hasBusinessClass;
    }

    public void setHasBusinessClass(boolean hasBusinessClass) {
        this.hasBusinessClass = hasBusinessClass;
    }

    public boolean isHasEconomyClass() {
        return hasEconomyClass;
    }

    public void setHasEconomyClass(boolean hasEconomyClass) {
        this.hasEconomyClass = hasEconomyClass;
    }
}