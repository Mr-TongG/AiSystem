package com;

//规则库
public class Rule {
    private int factNum;
    private int facts[];
    private boolean used;//是否使用
    private boolean possible;//是否可能
    private boolean endResult;
    private int resultID;
    private int nextFactPos;//记录下一次需验证的特征位置

    public Rule(int factNum, int[] facts, boolean endResult, int resultID) {
        this.used = false;
        this.possible = true;
        this.factNum = factNum;
        this.facts = facts;
        this.endResult = endResult;
        this.resultID = resultID;
        this.nextFactPos = 0;
    }

    public int getFactNum() {
        return factNum;
    }

    public void setFactNum(int factNum) {
        this.factNum = factNum;
    }

    public int[] getFacts() {
        return facts;
    }

    public void setFacts(int[] facts) {
        this.facts = facts;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isPossible() {
        return possible;
    }

    public void setPossible(boolean possible) {
        this.possible = possible;
    }

    public boolean isEndResult() {
        return endResult;
    }

    public void setEndResult(boolean endResult) {
        this.endResult = endResult;
    }

    public int getResultID() {
        return resultID;
    }

    public void setResultID(int resultID) {
        this.resultID = resultID;
    }

    public int getNextFactPos() {
        return nextFactPos;
    }

    public void setNextFactPos(int nextFactPos) {
        this.nextFactPos = nextFactPos;
    }

    public static Rule[] InitRules(){
        Rule[] rules = new Rule[14];
    //结果库
        //可乐（碳酸类，甜味，褐色）
        int fact0[]={9,10,11};
        rules[0]=new Rule(3,fact0,true,1);

        //苏打水（碳酸类，微甜，无色）
        int fact1[]={9,16,22};
        rules[1]=new Rule(3,fact1,true,2);

        //苹果醋（含果汁，主要为苹果，微酸）
        int fact2[]={2,17,19};
        rules[2]=new Rule(3,fact2,true,3);

        //番茄汁（含蔬菜汁，主要为番茄，红色，微酸）
        int fact3[]={3,18,19,20};
        rules[3]=new Rule(4,fact3,true,4);

        //养乐多（含益生菌，甜味）
        int fact4[]={10,21};
        rules[4]=new Rule(2,fact4,true,5);

        //脉动（运动类饮料，甜味，无色）
        int fact5[]={15,10,16};
        rules[5]=new Rule(3,fact5,true,6);

        //绿茶（茶饮料，微甜，绿色）
        int fact6[]={13,22,23};
        rules[6]=new Rule(3,fact6,true,7);

    //非结果库
        //碳酸类饮料（含二氧化碳）
        int fact7[]={1};
        rules[7]=new Rule(1,fact7,false,10);

        //碳酸类饮料（能产生大量气泡）
        int fact8[]={8};
        rules[8]=new Rule(1,fact8,false,10);

        //果蔬类饮料（含果汁）
        int fact9[]={2};
        rules[9]=new Rule(1,fact9,false,10);

        //含蔬菜汁（含蔬菜汁）
        int fact10[]={3};
        rules[10]=new Rule(1,fact10,false,10);

        //茶饮料（含茶叶浓缩液）
        int fact11[]={4};
        rules[11]=new Rule(1,fact11,false,10);

        //功能饮料（有保健作用）
        int fact12[]={5};
        rules[12]=new Rule(1,fact12,false,10);

        //运动饮料类（补充维生素，补充电解质）
        int fact13[]={6,7};
        rules[13]=new Rule(2,fact13,false,10);

        return rules;
    }
}
