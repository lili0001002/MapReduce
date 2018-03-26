package Sort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


import java.io.IOException;

public class Sort {
    public static class Map extends Mapper<LongWritable,Text,InfoBean,NullWritable> {
        public InfoBean v = new InfoBean();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String [] fields = line.split("\t");
            String account = fields[0];
            double in = Double.parseDouble(fields[1]);
            double out = Double.parseDouble(fields[2]);
            v.set(account,in,out);
            context.write(v,NullWritable.get());
        }
    }
    public static class reducer extends Reducer<InfoBean,NullWritable,Text,InfoBean>{
        public Text k = new Text();
        @Override
        protected void reduce(InfoBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            String account = key.getAccount();
            k.set(account);
            context.write(k,key);
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(Sort.class);
        job.setMapperClass(Map.class);
        job.setMapOutputKeyClass(InfoBean.class);
        job.setMapOutputValueClass(NullWritable.class);
        FileInputFormat.setInputPaths(job,new Path(args[0]));

        job.setReducerClass(reducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(InfoBean.class);
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);
    }
}
