package DataCount;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.HashMap;
import java.util.Map;

//Partitioner是在Reducer执行之后执行，因此需要以Reducer的输出作为Partitioner的输入泛型s
public class ProviderPartitioner extends Partitioner<Text,DCJavaBean>{

    private static Map<String,Integer> providerMap = new HashMap<String, Integer>();
    //静态的是从上往下执行，静态的先执行
    //静态块是先执行，想在重写的方法运行之前添加，需要用以下的方法
    static {
        providerMap.put("135",1);
        providerMap.put("136",2);
        providerMap.put("131",3);
        providerMap.put("132",3);
        providerMap.put("133",2);
        providerMap.put("134",1);
    }

    //重写的方法其中有几个Reducer就会有几个Partitioner  其中下面这个参数i就是数量
    public int getPartition(Text text, DCJavaBean dcJavaBean, int i) {
        String account = text.toString();
        String sub_acc = account.substring(0, 3);//从零开始取取三位
        Integer code = providerMap.get(sub_acc);
        if (code == null) {
            code = 0;
        }
        return code;
    }
}
