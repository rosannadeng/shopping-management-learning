package entity;

public class GSales {
    private int gId;
    private int sNum;
    private int sId;
    private int gNum;
    private int totalNum;
    private String gName;
    private double gPrice;

    public GSales(int gId, int sNum, int sId) {
        this.gId = gId;
        this.sNum = sNum;
        this.sId = sId;
    }

    public GSales(String gName, double gPrice, int gNum, int totalNum) {
        this.gNum = gNum;
        this.totalNum = totalNum;
        this.gName = gName;
        this.gPrice = gPrice;
    }

    public int getgId() {
        return gId;
    }

    public void setgId(int gId) {
        this.gId = gId;
    }

    public int getsNum() {
        return sNum;
    }

    public void setsNum(int sNum) {
        this.sNum = sNum;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public int getgNum() {
        return gNum;
    }

    public void setgNum(int gNum) {
        this.gNum = gNum;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public double getgPrice() {
        return gPrice;
    }

    public void setgPrice(double gPrice) {
        this.gPrice = gPrice;
    }
}
