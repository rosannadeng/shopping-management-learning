package entity;

public class SalesMan {
    private String SName;
    private String SPassword;
    private int SId;

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public String getSPassword() {
        return SPassword;
    }

    public void setSPassword(String SPassword) {
        this.SPassword = SPassword;
    }

    public int getSId() {
        return SId;
    }

    public void setSId(int SId) {
        this.SId = SId;
    }

//login check
    public SalesMan( int SId,String SName) {
        this.SName = SName;
        this.SId = SId;
    }
//add user
    public SalesMan(String SName, String SPassword) {
        this.SName = SName;
        this.SPassword = SPassword;
    }
//update user
    public SalesMan(Integer SId,String SName, String SPassword) {
        this.SName = SName;
        this.SPassword = SPassword;
        this.SId = SId;
    }
}
