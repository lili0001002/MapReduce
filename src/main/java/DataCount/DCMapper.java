package DataCount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DCMapper extends Mapper<LongWritable,Text,Text,DCJavaBean>{
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split("\t");
        String telNo = fields[1];
        long up = Long.parseLong(fields[8]);
        //自动生成返回值 ctrl+alt+v
        long down = Long.parseLong(fields[9]);
        //生成到下一行 ctrl + D
        DCJavaBean bean = new DCJavaBean(telNo,up,down);
        context.write(new Text(telNo) , bean);
    }
}
