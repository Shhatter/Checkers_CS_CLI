package checkers;

import checkers.enums.PawnColor;

/**
 * Created by Praca on 2017-06-18.
 */
public class MoveTransfer
{
    int nStart,mStart,nDestination,mDestination;
    PawnColor color;
    String ownerID;
    String order;
    boolean systemEnabled;

    public MoveTransfer(int nStart, int mStart,int nDestination,int mDestination, PawnColor color, String ownerID,String order,boolean systemEnabled)

    {
        this.nDestination = nDestination;
        this.mDestination = mDestination;
        this.nStart = nStart;
        this.mStart = mStart;
        this.color = color;
        this.ownerID = ownerID;
        this.order = order;
        this.systemEnabled = systemEnabled;
    }

    public MoveTransfer() //9
    {
        this.nDestination = 0;
        this.mDestination = 0;
        this.nStart = 0;
        this.mStart = 0;
        this.color = PawnColor.NONE;
        this.ownerID = "NONE";
        this.order = "IDLE";
        this.systemEnabled = true;
    }

    public void setAllData(int n, int m, PawnColor color, String ownerID)
    {
        this.nStart = n;
        this.mStart = m;
        this.color = color;
        this.ownerID = ownerID;
        this.order = order;
        this.systemEnabled = systemEnabled;
    }
    public int getnStart() {
        return nStart;
    }

    public void setnStart(int nStart) {
        this.nStart = nStart;
    }

    public int getmStart() {
        return mStart;
    }

    public void setmStart(int mStart) {
        this.mStart = mStart;
    }

    public int getnDestination() {
        return nDestination;
    }

    public void setnDestination(int nDestination) {
        this.nDestination = nDestination;
    }

    public int getmDestination() {
        return mDestination;
    }

    public void setmDestination(int mDestination) {
        this.mDestination = mDestination;
    }

    public PawnColor getColor() {
        return color;
    }

    public void setColor(PawnColor color) {
        this.color = color;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public boolean isSystemEnabled() {
        return systemEnabled;
    }

    public void setSystemEnabled(boolean systemEnabled) {
        this.systemEnabled = systemEnabled;
    } //setters & getters


}
