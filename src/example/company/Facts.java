package example.company;

public class Facts {

    private int factNum;
    private int facts[];

    //初始化
    Facts(){
        factNum=0;
        facts=new int[0];
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
}
