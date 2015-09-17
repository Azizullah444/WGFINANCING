/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.regionsbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Aziz Ullah
 */
public class DepositsForRegionsBank {
    private List<String> lines;
    private List<String> initialBlock;
    private List<String> selectedBlock;
    private List<String> selectedBlockForReversal;
    private double onlineTransferFromSum;
    private double onlineReversalFromSum;

    public DepositsForRegionsBank(List<String> lines) {
        this.lines = lines;
        startExecution();
    }
    private void show(){
        int count=0;
        for(String s: initialBlock){
            System.out.println(++count +" "+s);
        }
    }
    private void startExecution() {
       this.initialBlock = captureInitialBlock();
       this.selectedBlock = captureSelectedOnlineTransferFromBlock();
       this.selectedBlockForReversal=captureSelectedOnlineReversalFromBlock();
       this.onlineTransferFromSum = findOnlineTransferFromSum();
       this.onlineReversalFromSum = findOnlineReversalFromSum();
       this.selectedBlockForReversal=captureSelectedOnlineTransferFromBlock();
       //show();
    }
    private double findOnlineReversalFromSum(){
        double sum=0;
        for(String s: this.selectedBlockForReversal){
            String r = FileUtils.getLastElementFromArray(s);
           // System.out.println(r);
            double d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(r));
            sum += d;
        }
        return sum;
    }
    private List<String> captureSelectedOnlineReversalFromBlock(){
        List<String> main = new ArrayList<>();
        List<String> list = this.initialBlock;
        for(String s: list){
            String res = FileUtils.getResultFromPattern("^\\d{1,2}/\\d{1,2}", s);
         //   System.out.println();
            if(!res.startsWith("NoMatch")){
                String v = FileUtils.getResultFromPattern("Reversal|Return|Rtrn|Bounce|Refund|REFUND|REVERSAL|RETURN|BOUNCE", s);
                if(!v.startsWith("NoMatch")){
                main.add(s);
               // System.out.println(s);
                }//inner if
            }
        }
        return main;
    }
    private double findOnlineTransferFromSum(){
        double sum=0;
        for(String s: this.selectedBlock){
            String r = FileUtils.getLastElementFromArray(s);
          //  System.out.println(r);
            double d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(r));
            sum += d;
        }
        return sum;
    }
    private List<String> captureSelectedOnlineTransferFromBlock(){
        List<String> main = new ArrayList<>();
        List<String> list = this.initialBlock;
        for(String s: list){
            String res = FileUtils.getResultFromPattern("^\\d{1,2}/\\d{1,2}", s);
          //  System.out.println();
            if(!res.startsWith("NoMatch")){
                
                String v = FileUtils.getResultFromPattern("EB\\s+From|EB\\s+from", s);
                if(!v.startsWith("NoMatch")){
                main.add(s);
             //   System.out.println(s);
                }//inner if
            }
        }
        return main;
    }
    private List<String> captureInitialBlock(){
        String start="\\s*DEPOSITS\\s+&\\s+CREDITS";
        String end="^\\s*Total\\s+Deposits\\s+&\\s+Credits";
        
        return FileUtils.getSelectedBlock(lines, start, end);
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public List<String> getInitialBlock() {
        return initialBlock;
    }

    public void setInitialBlock(List<String> initialBlock) {
        this.initialBlock = initialBlock;
    }

    public List<String> getSelectedBlock() {
        return selectedBlock;
    }

    public void setSelectedBlock(List<String> selectedBlock) {
        this.selectedBlock = selectedBlock;
    }

    public double getOnlineTransferFromSum() {
        return onlineTransferFromSum;
    }

    public void setOnlineTransferFromSum(double onlineTransferFromSum) {
        this.onlineTransferFromSum = onlineTransferFromSum;
    }

    public double getOnlineReversalFromSum() {
        return onlineReversalFromSum;
    }

    public void setOnlineReversalFromSum(double onlineReversalFromSum) {
        this.onlineReversalFromSum = onlineReversalFromSum;
    }
    public List<String> getSelectedBlockForReversal() {
        return selectedBlockForReversal;
    }

    public void setSelectedBlockForReversal(List<String> selectedBlockForReversal) {
        this.selectedBlockForReversal = selectedBlockForReversal;
    }
}
