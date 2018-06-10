package together.readingroom;

import java.io.Serializable;

/**
 * Created by VietVan on 10/06/2018.
 */

public class Phong implements Serializable{
    private int mID;
    private int tt;
    private String timebegin, timeend;
    private String midnum;

    public Phong(int mID, int tt, String timebegin, String timeend, String midnum) {
        this.mID = mID;
        this.tt = tt;
        this.timebegin = timebegin;
        this.timeend = timeend;
        this.midnum = midnum;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public int getTt() {
        return tt;
    }

    public void setTt(int tt) {
        this.tt = tt;
    }

    public String getTimebegin() {
        return timebegin;
    }

    public void setTimebegin(String timebegin) {
        this.timebegin = timebegin;
    }

    public String getTimeend() {
        return timeend;
    }

    public void setTimeend(String timeend) {
        this.timeend = timeend;
    }

    public String getMidnum() {
        return midnum;
    }

    public void setMidnum(String midnum) {
        this.midnum = midnum;
    }
}
