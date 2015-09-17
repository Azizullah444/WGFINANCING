/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.bmoharrisbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForBMOHarrisBank {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForBMOHarrisBank onlineTransferFrom;
    private OnlineReversalFromForBMOHarrisBank  onlineReversalFrom;
    public DepositsForBMOHarrisBank(List<String> lines) {
        this.lines = lines;
        startExecution();
    }
    private void show(){
        for(String s: this.selectedBlock){
            System.out.println(s);
        }
    }
    private void startExecution() {
       this.selectedBlock = captureSelectedBlock();
        this.onlineTransferFrom = new OnlineTransferFromForBMOHarrisBank(this.selectedBlock);
        this.onlineReversalFrom=  new OnlineReversalFromForBMOHarrisBank(this.selectedBlock);
        //show();
    }
    private List<String> captureSelectedBlock(){
        
        int startIndex = FileUtils.findIndexBySingleValue(lines, "^\\s*Deposits\\s+and\\s+Other\\s+Credits");
        int endIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*Withdrawals\\s+and\\s+Other\\s+Debits|^\\s*Checks\\s+by\\s+Serial\\s+Number");
        
        return FileUtils.getSelectedBlockByIndex(lines, startIndex, endIndex);
    }
     public OnlineTransferFromForBMOHarrisBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForBMOHarrisBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForBMOHarrisBank getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForBMOHarrisBank onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
