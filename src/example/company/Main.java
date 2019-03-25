package example.company;

import java.util.Scanner;

public class Main {

    //特征集与结果集初始化，用以输入输出
    static String Features[] = {"反刍","有蹄","哺乳类",
            "眼向前方","有爪","犬齿","吃肉","下蛋","能飞",
            "有羽毛","蹄类","食肉类","鸟类","有奶","毛发",
            "善飞","黑色白条纹","游泳","长腿","长脖子","黑条纹",
            "暗斑点","黄褐色"};

    static String Results[] = {"信天翁","企鹅","鸵鸟","斑马","长颈鹿","虎","豹"};


    public static void main(String args[]){

        System.out.println("特征集如下");
        for(int n=0;n<Features.length;n++){
            System.out.println("特征"+(n+1)+":"+Features[n]);
        }
        //Rule[] rules = Rule.IntRules();//规则库初始化
        Facts factsDB = new Facts();
        Rule rules[];
        rules=Rule.IntRules();
        //输入特征选项 5,{14,22,20,19,2}
        int[] f = new int[25];
        Scanner input=new Scanner(System.in);//输入初始化

        System.out.println("输入将要匹配的特征的数量（整数）");
        int FactNum=input.nextInt();//输入一个正整数
        factsDB.setFactNum(FactNum);
        System.out.println("对应上表，输入将要匹配的特征值（整数）");
        for(int k=0;k<FactNum;k++){
            int feature=input.nextInt();//输入一个正整数
            f[k] = feature;
        }
        /*f[0]=14;f[1]=22;f[2]=20;f[3]=19;f[4]=2;*/
        factsDB.setFacts(f);
        boolean isEnd = false;
        boolean findAns = false;
        while(!isEnd){
            isEnd= true;
            for(int i=0;i<rules.length;i++){
                if(rules[i].isUsed() || !rules[i].isPossible()) continue;//该规则失效
                int res = cmp(rules[i],factsDB);
                if(res == 0){
                    continue;//不匹配
                }else if(res == 1){
                    //匹配，但是为中间值
                    int[] facts=factsDB.getFacts();
                    int n = factsDB.getFactNum();
                    facts[n]=rules[i].getResultID();
                    factsDB.setFacts(facts);
                    factsDB.setFactNum(++n);
                    isEnd= false;
                    break;
                }else if(res == 2){
                    //匹配，且为最终答案
                    System.out.println("结果是:"+Results[rules[i].getResultID()-1]);
                    findAns=true;
                    break;
                }
            }
        }
        if(!findAns){
            System.out.println("无匹配答案");
        }



    }

    public static int cmp(Rule r,Facts f){
        int F_Rule[] = r.getFacts();
        int F_Fact[]= f.getFacts();
        int factNum =f.getFactNum();

        for(int i=r.getNextFactPos();i<r.getFactNum();i++){
            boolean isMatch=false;
            for(int j=0;j<factNum;j++){
                if(F_Rule[i] == F_Fact[j]){//匹配成功
                    if(i+1 == r.getFactNum()){//全部规则特征已匹配完毕
                        r.setUsed(true);
                        if(r.isEndResult()){
                            //是最终答案
                            return 2;
                        }else{
                            //中间特征
                            return 1;
                        }
                    }
                    //部分特征匹配成功，开始匹配下一个特征
                    isMatch=true;
                    break;
                }else{
                    //不匹配，继续循环
                    continue;
                }
            }
            if(isMatch){
                //匹配成功
                continue;
            }else{
                //匹配失败
                r.setNextFactPos(i);
                return 0;
            }
        }
        //无匹配项
        return 0;
    }
}
