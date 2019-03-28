package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.Main.Identify;

public class Main {
    static String Features[]={"含二氧化碳","含果汁","含蔬菜汁","含茶叶浓缩液","有保健作用",
            "能补充维生素","能补充电解质","能产生大量气泡","碳酸类饮料","有甜味","颜色为褐色",
            "果蔬类饮料","茶饮料类","功能饮料类","运动饮料类","颜色为无色","主要原料是苹果",
            "主要原料是番茄","味道微酸","颜色为红色","含益生菌","味道微甜","颜色为绿色"};
    //建立结果库
    static String Results[]={"可乐","苏打水","苹果醋","番茄汁","养乐多","脉动","绿茶"};
    //建立事实库
    static Facts factsDB = new Facts();
    static int factNum=0;
    //建立规则库并初始化
    static Rule rules[]= Rule.InitRules();

    //显示框和复选按钮是全局的
    static JTextField f1=new JTextField();
    static JRadioButton[] feather=new JRadioButton[23];

    static int[] fact1 = new int[25];
    static int[] fact2 = new int[25];
    public static void main(String[] args){
        JFrame f=new JFrame("正向产生式系统");
        f.setPreferredSize(new Dimension(1500,800));
        f.setLocation(100,100);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        Font font=new Font("微软雅黑",Font.PLAIN,20);

        JPanel panel=new JPanel();
        panel.setBounds(100,100,1200,300);
        panel.setLayout(new FlowLayout());
        panel.setFont(font);
        JLabel label1=new JLabel("特征集如下：");
        label1.setBounds(10,10,100,30);
        label1.setFont(font);
        panel.add(label1);
        //两个面板
        JPanel panel2=new JPanel();
        panel2.setBounds(100,400,1200,300);
        panel2.setLayout(null);
        panel2.setFont(font);
        JLabel label2=new JLabel("结果为：");
        label2.setBounds(600,10,100,30);
        label2.setFont(font);
        panel2.add(label2);

        for(int i=0;i<Features.length;i++) {//对可选项进行初始化
            feather[i]=new JRadioButton(Features[i]);
            feather[i].setSelected(false);
            feather[i].setBounds(50, 50+50*i, 180, 50);
            feather[i].setFont(font);
            panel.add(feather[i]);
        }

        JButton button=new JButton("开始");
        Listen l=new Listen();
        button.addActionListener(l);
        button.setBounds(380,0,100,50);
        button.setFont(font);

        //显示结果的文本框
        f1.setBounds(700,0,200,50);
        f1.setFont(font);

        panel2.add(button);
        panel2.add(f1);
        f.add(panel);
        f.add(panel2);
        f.pack();
    }

    public static int compare(Rule r,Facts f){
        int F_Rule[] = r.getFacts();
        int F_Fact[]= f.getFacts();
        int factNum =f.getFactNum();

        for(int i=r.getNextFactPos();i<r.getFactNum();i++){//从该规则的第i项开始匹配
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
        for(int i=0;i<feather.length;i++){
            if(feather[i].isSelected())
                fact1[i]=i+1;
        }
        for(int i=0;i<fact2.length;i++){
            for(int j=0;j<fact1.length;j++)
                if(fact1[j]!=0){
                    fact2[i]=fact1[j];
                    fact1[j]=0;
                    break;
                }
            if(fact2[i]!=0){
                factNum++;
                factsDB.setFactNum(factNum);
            }
        }
        factsDB.setFacts(fact2);
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
                else if(res == 1){//匹配，但是为中间值，把根据事实推出的中间结果加入到事实库中
                    int[] facts=factsDB.getFacts();
                    int n = factsDB.getFactNum();
                    facts[n]=rules[i].getResultID();
                    factsDB.setFacts(facts);
                    factsDB.setFactNum(++n);
                    isEnd= false;
                    break;
                }
                else if(res == 2){//匹配，且为最终答案
                    f1.setText(Results[rules[i].getResultID()-1]);
                    //System.out.println("结果是:"+Results[rules[i].getResultID()-1]);
                    findAns=true;
                    break;
                }
            }
        }
        if(!findAns){
            f1.setText("无匹配答案");
        }
    }
}
class Listen implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        Main.fact1 = new int[25];
        Main.fact2 = new int[25];
        Identify();
    }
}