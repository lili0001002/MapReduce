package Sort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SMapper extends Mapper<LongWritable,Text,Text,InfoBean>{
    //这种方式只需要new一个对象，减少占用资源
    private Text k = new Text();
    private InfoBean v = new InfoBean();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String [] fields = line.split("\t");
        String account = fields[0];
        double in = Double.parseDouble(fields[1]);
        double out = Double.parseDouble(fields[2]);
        k.set(account);
        v.set(account,in,out);
        context.write(k,v);
    }
}
