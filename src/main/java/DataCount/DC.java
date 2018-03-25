package DataCount;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class DC {
    //在hadoop上运行的时候参考以下命令
    //hadoop jar /root/...(包名) /data.doc(这个要传入的第一个参数) /dataout(输出)
    public static void main (String [] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(DC.class);
        job.setMapperClass(DCMapper.class);
        job.setReducerClass(DCReduce.class);

        //当满足Mapper的第一个key，value和第二个key，value一致的时候可以省略以下语句
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(DCJavaBean.class);

        job.setReducerClass(DCReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setMapOutputValueClass(DCJavaBean.class);
        FileOutputFormat.setOutputPath(job , new Path(args[1]));

        job.waitForCompletion(true);
    }
}
