/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.guarantybank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForGuarantyBank {

    private List<String> lines;
    private List<String> selectedBlock;
    private List<String> selectedBlockForReversal;
    private double onlineReversalFromSum;
    private OnlineTransferFromForGuarantyBank onlineTransferFrom;
    public DepositsForGuarantyBank(List<String> lines) {
        this.lines = lines;
        startExecution();
    }

    private void startExecution() {
        this.selectedBlock = captureSelectedBlock();
        this.selectedBlockForReversal=captureSelectedOnlineReversalFromBlock();
        this.onlineTransferFrom = new OnlineTransferFromForGuarantyBank(this.selectedBlock);
    }
   private double findOnlineReversalFromSum(){
        double sum=0;
        for(String s: this.selectedBlockForReversal){
            String[] r = s.trim().split("\\s+");
            double d = FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(r[1]));
            sum += d;
        }
        return sum;
    }
   private List<String> captureSelectedOnlineReversalFromBlock(){
        List<String> main = new ArrayList<>();
        List<String> list = this.selectedBlock;
        for(String s: list){
            String res = FileUtils.getResultFromPattern("^\\s*\\d{1,2}/\\d{1,2}", s);
            if(!res.startsWith("NoMatch")){
                String v = FileUtils.getResultFromPattern("REVERSAL|RETURN|Rtrn|Bounce|REFUND", s);
                if(!v.startsWith("NoMatch")){
                    main.add(s);
                }
            }
        }
        return main;
    }
    private List<String> captureSelectedBlock() {
        String start = "- - -\\s+CREDITS\\s+- -";
        String end = " - - -\\s+MISCELLANEOUS DEBITS\\s+- - -| - - -\\s+ATM/DEBIT CARD TRANSACTIONS\\s+- - -";
        List<String> list = FileUtils.getSelectedBlock(lines, start, end);
        
        return list;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public List<String> getSelectedBlock() {
        return selectedBlock;
    }

    public void setSelectedBlock(List<String> selectedBlock) {
        this.selectedBlock = selectedBlock;
    }

    public OnlineTransferFromForGuarantyBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForGuarantyBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
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
