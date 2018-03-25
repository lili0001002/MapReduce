package DataCount;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DCJavaBean implements Writable{
    private String telNo;

    private long upPayLoad;

    private long downPayLoad;

    private long totalPayLoad;

    public String getTelNo() {
        return telNo;
    }
    //添加一个有参的构造方法，防止反射构造时出现问题
    public DCJavaBean() {
    }

    public DCJavaBean(String telNo, long upPayLoad, long downPayLoad) {
        this.telNo = telNo;
        this.upPayLoad = upPayLoad;
        this.downPayLoad = downPayLoad;
        this.totalPayLoad = upPayLoad+downPayLoad;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public long getUpPayLoad() {
        return upPayLoad;
    }

    public void setUpPayLoad(long upPayLoad) {
        this.upPayLoad = upPayLoad;
    }

    public long getDownPayLoad() {
        return downPayLoad;
    }

    public void setDownPayLoad(long downPayLoad) {
        this.downPayLoad = downPayLoad;
    }

    public long getTotalPayLoad() {
        return totalPayLoad;
    }

    public void setTotalPayLoad(long totalPayLoad) {
        this.totalPayLoad = totalPayLoad;
    }
    //序列化
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(telNo);
        dataOutput.writeLong(upPayLoad);
        dataOutput.writeLong(downPayLoad);
        dataOutput.writeLong(totalPayLoad);
    }
    //反序列化 注意类型和数据
    public void readFields(DataInput dataInput) throws IOException {
        this.telNo = dataInput.readUTF();
        this.upPayLoad = dataInput.readLong();
        this.downPayLoad = dataInput.readLong();
        this.totalPayLoad = dataInput.readLong();
    }

    @Override
    public String toString() {
        return this.upPayLoad + "\t" +this.downPayLoad + "\t" + this.totalPayLoad ;
    }
}
