package sr.grpc.server;

public class PrimeLikeClass {
    int number;
    long value;
    float percent;

    public PrimeLikeClass(int number, long value, float percent){
        this.number = number;
        this.value = value;
        this.percent = percent;
    }

    public float getPercent() {
        return percent;
    }

    public int getNumber() {
        return number;
    }

    public long getValue() {
        return value;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }
}
