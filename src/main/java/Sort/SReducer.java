package Sort;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SReducer extends Reducer<Text,InfoBean,Text,InfoBean>{
    private InfoBean v = new InfoBean();
    @Override
    protected void reduce(Text key, Iterable<InfoBean> values, Context context) throws IOException, InterruptedException {
        double in_sum = 0;
        double out_sum = 0;
        for (InfoBean bean : values) {
            in_sum += bean.getIncome();
            out_sum += bean.getExpenses();
        }
        context.write(key,v);
    }
}
