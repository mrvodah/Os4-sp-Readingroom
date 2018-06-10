package together.readingroom;

public class Student {
    private int mID;
    private String mName;
    private String mPassword;
    private String mEmail;
    private String mPhoneNumber;
    private String midnum;

    public Student() {
    }

    public Student(String mName, String mEmail, String mPhoneNumber, String midnum) {
        this.mName = mName;
        this.mEmail = mEmail;
        this.mPhoneNumber = mPhoneNumber;
        this.midnum = midnum;
    }

    public Student(int mID, String mName, String mPassword, String mEmail, String mPhoneNumber, String midnum) {
        this.mID = mID;
        this.mName = mName;
        this.mPassword = mPassword;
        this.mEmail = mEmail;
        this.mPhoneNumber = mPhoneNumber;
        this.midnum = midnum;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getMidnum() {
        return midnum;
    }

    public void setMidnum(String midnum) {
        this.midnum = midnum;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    @Override
    public String toString() {
        return "Student{" +
                "mID=" + mID +
                ", mName='" + mName + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mPhoneNumber='" + mPhoneNumber + '\'' +
                ", midnum='" + midnum + '\'' +
                '}';
    }
}
