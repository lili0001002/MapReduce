package DataCount;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class DCReduce extends Reducer<Text,DCJavaBean,Text,DCJavaBean>{
    @Override
    protected void reduce(Text key, Iterable<DCJavaBean> values, Context context) throws IOException, InterruptedException {
        //如果是在父类方法的扩展那么就需要super，如果要完全重写则删除
        //super.reduce(key, values, context);
        long up_sum = 0;
        long down_sum = 0;
        for (DCJavaBean bean : values) {
            up_sum += bean.getUpPayLoad();
            down_sum += bean.getDownPayLoad();
        }
        DCJavaBean bean = new DCJavaBean("",up_sum,down_sum);
        context.write(key , bean);
    }
}
