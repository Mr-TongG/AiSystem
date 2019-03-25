package example.company;

public class Rule {
    private int factNum;
    private int facts[];
    private boolean used;//是否使用
    private boolean possible;//是否可能
    private boolean endResult;
    private int resultID;
    private int nextFactPos;//记录下一次需验证的特征位置

    public Rule(int factNum,int[] facts,boolean endResult,int resultID){
        this.used=false;
        this.possible=true;
        this.factNum = factNum;
        this.facts=facts;
        this.endResult=endResult;
        this.resultID=resultID;
        this.nextFactPos=0;
    }


    public int getNextFactPos() {
        return nextFactPos;
    }


    public void setNextFactPos(int nextFactPos) {
        this.nextFactPos = nextFactPos;
    }


    public int getResultID() {
        return resultID;
    }
    public void setResultID(int resultID) {
        this.resultID = resultID;
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

    //初始化规则库
    public static Rule[] IntRules(){
        Rule[] rules= new Rule[15];
        //以下为结果集
        //rule0
        int fact0[]={3,12,22,23};
        rules[0]=new Rule(4,fact0,true,7);
        //rule1
        int fact1[]={3,12,21,23};
        rules[1]=new Rule(4,fact1,true,6);
        //rule2
        int fact2[]={11,19,20,22};
        rules[2]=new Rule(4,fact2,true,5);
        //rule3
        int fact3[]={11,21};
        rules[3]=new Rule(2,fact3,true,4);
        //rule4
        int fact4[]={17,19,20,13,-9};
        rules[4]=new Rule(5,fact4,true,3);
        //rule5
        int fact5[]={17,18,13,-9};
        rules[5]=new Rule(4,fact5,true,2);
        //rule6
        int fact6[]={16,13};
        rules[6]=new Rule(2,fact6,true,1);

        //以下为非结果集
        int fact7[]={15};
        rules[7]=new Rule(1,fact7,false,3);

        int fact8[]={14};
        rules[8]=new Rule(1,fact8,false,3);

        int fact9[]={10};
        rules[9]=new Rule(1,fact9,false,13);

        int fact10[]={8,9};
        rules[10]=new Rule(2,fact10,false,13);

        int fact11[]={1,7};
        rules[11]=new Rule(2,fact11,false,12);

        int fact12[]={4,5,6};
        rules[12]=new Rule(3,fact12,false,12);

        int fact13[]={2,3};
        rules[13]=new Rule(2,fact13,false,11);

        int fact14[]={1,3};
        rules[14]=new Rule(2,fact14,false,11);

        return rules;
    }
}