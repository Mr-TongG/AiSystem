package com.MyProject;

import java.util.Scanner;

public class Main {
    //建立特征库
    static String Features[]={"含二氧化碳","含果汁","含蔬菜汁","含茶叶浓缩液","有保健作用",
            "能补充维生素","能补充电解质","能产生大量气泡","碳酸类饮料","有甜味","颜色为褐色",
            "果蔬类饮料","茶饮料类","功能饮料类","运动饮料类","颜色为无色","主要原料是苹果",
            "主要原料是番茄","味道微酸","颜色为红色","含益生菌","味道微甜","颜色为绿色"};
    //建立结果库
    static String Results[]={"可乐","苏打水","苹果醋","番茄汁","脉动","养乐多","绿茶"};
    //建立事实库
    static Facts factsDB = new Facts();
    //建立规则库并初始化
    static Rule rules[]=Rule.InitRules();

    public static void main(String args[]){
        System.out.println("特征集如下：");
        for(int n=0;n<Features.length;n++){
            System.out.println("特征"+(n+1)+":"+Features[n]);
        }
        Identify();
        while(true){
            System.out.println("还要再进行匹配吗？（Y or N）");
            Scanner select=new Scanner(System.in);//输入初始化
            String letter=select.nextLine();//输入一个字母
            if(letter.equals("Y")){
                Identify();
            }
            else if(letter.equals("N")){
                System.exit(0);//退出
            }
            else{
                System.out.println("你输入的字符有误，请重新输入！");
            }
        }
    }

    public static int compare(Rule r,Facts f){
        int F_Rule[] = r.getFacts();
        int F_Fact[]= f.getFacts();
        int factNum =f.getFactNum();

        for(int i=r.getNextFactPos();i<r.getFactNum();i++){
            boolean isMatch=false;
            for(int j=0;j<factNum;j++){
                if(F_Rule[i] == F_Fact[j]){//匹配成功
                    if(i+1 == r.getFactNum()){//全部规则特征已匹配完毕
                        r.setUsed(true);
                        if(r.isEndResult()){//是最终答案
                            return 2;
                        }
                        else{//中间特征
                            return 1;
                        }
                    }
                    //部分特征匹配成功，开始匹配下一个特征
                    isMatch=true;
                    break;
                }
                else{//不匹配，继续循环
                    continue;
                }
            }
            if(isMatch){//匹配成功
                continue;
            }else{//匹配失败
                r.setNextFactPos(i);
                return 0;
            }
        }
        //无匹配项
        return 0;
    }

    public static void Identify(){
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
        factsDB.setFacts(f);
        boolean isEnd = false;
        boolean findAns = false;
        while(!isEnd){
            isEnd= true;
            for(int i=0;i<rules.length;i++){
                if(rules[i].isUsed() || !rules[i].isPossible()) continue;//该规则失效
                int res = compare(rules[i],factsDB);
                if(res == 0){//不匹配
                    continue;
                }
                else if(res == 1){//匹配，但是为中间值
                    int[] facts=factsDB.getFacts();
                    int n = factsDB.getFactNum();
                    facts[n]=rules[i].getResultID();
                    factsDB.setFacts(facts);
                    factsDB.setFactNum(++n);
                    isEnd= false;
                    break;
                }
                else if(res == 2){//匹配，且为最终答案
                    System.out.println("结果是:"+Results[rules[i].getResultID()-1]);
                    findAns=true;
                    break;
                }
            }
        }
        if(!findAns){
            System.out.println("无匹配答案");
            System.out.println();
        }
    }
}
