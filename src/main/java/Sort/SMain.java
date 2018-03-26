package Sort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import javax.xml.soap.Text;

public class SMain {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(SMain.class);
        job.setMapperClass(SMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(InfoBean.class);
        FileInputFormat.setInputPaths(job, new Path(args[0]));

        job.setReducerClass(SReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(InfoBean.class);
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);

    }
}
