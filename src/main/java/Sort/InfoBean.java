package Sort;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class InfoBean implements WritableComparable<InfoBean>{
    private String account;
    private double income;
    private double expenses;
    private double surplus;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public double getSurplus() {
        return surplus;
    }

    public void setSurplus(double surplus) {
        this.surplus = surplus;
    }
    //打印的时候默认调用
    @Override
    public String toString() {
        return this.income + "\t" + this.expenses + "\t" +this.surplus;
    }

    public int compareTo(InfoBean o) {
        if (this.income == o.getIncome()) {
            return this.expenses > o.getExpenses() ? -1 :1 ;
        } else {
            return this.income > o.getIncome() ? 1 : -1 ;
        }
    }
    public void set(String account , double income ,double expenses) {
        this.account = account;
        this.income = income;
        this.expenses = expenses;
        this.surplus = income - expenses;
    }
    //序列化
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(account);
        dataOutput.writeDouble(income);
        dataOutput.writeDouble(expenses);
        dataOutput.writeDouble(surplus);
    }
    //反序列化
    public void readFields(DataInput dataInput) throws IOException {
        this.account = dataInput.readUTF();
        this.income = dataInput.readDouble();
        this.expenses = dataInput.readDouble();
        this.surplus = dataInput.readDouble();
    }
}
