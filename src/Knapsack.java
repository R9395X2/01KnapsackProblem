import java.util.ArrayList;
import java.util.List;

public class Knapsack {
    static int capacity = 13; //背包容量
    public static void main(String[] args) {
        List<goods> g=new ArrayList<goods>();
        g.add(new goods("牛奶",4,9));
        g.add(new goods("汽水",3,2));
        g.add(new goods("饼干",4,9));
        g.add(new goods("面包",5,10));
        g.add(new goods("啤酒",10,24));
        System.out.println("容量: 1  2  3  4  5  6  7  8  9  10 11 12 13");
        System.out.print("最大价值为"+solution(g,capacity));
    }
    static int solution(List<goods> g, int capacity){
        int[][] dp=new int[g.size()+1][capacity+1]; //i行j列
        for(int i=1;i<=g.size();i++){  //物品数量
            System.out.print(g.get(i-1).name+": ");
            for(int j=1;j<=capacity;j++){  //容量
                //如果当前物品重量大于容量，不装入背包,价值等于之前
                if(g.get(i-1).weight>j){
                    dp[i][j]=dp[i-1][j];
                }else{ //如果当前物品可以装入背包 则 存当前物品和上一个最优解取最大值
                    dp[i][j]=Math.max(dp[i-1][j],g.get(i-1).value+dp[i-1][j-g.get(i-1).weight]);
                }
                if(dp[i][j]<10)
                    System.out.print(dp[i][j]+"  ");
                else
                    System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        int maxvalue=dp[g.size()][capacity];
        System.out.print("选择货物为:"); //输出最优解货物
        for(int i=g.size();maxvalue>0;) {
                if(dp[i-1][capacity]==maxvalue){
                    i--;
                }
                System.out.print(" "+g.get(i-1).name);
                i--;
                maxvalue-=g.get(i).value;
        }
        System.out.println();
        return dp[g.size()][capacity];
    }
}
class goods {
    String name;
    int weight;
    int value;
    public goods(String name, int weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }
}
