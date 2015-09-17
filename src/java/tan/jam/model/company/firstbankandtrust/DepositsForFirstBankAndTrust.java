/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.firstbankandtrust;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForFirstBankAndTrust {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForFirstBankAndTrust onlineTransferFrom;
    private OnlineReversalFromForFirstBankAndTrust  onlineReversalFrom;
    public DepositsForFirstBankAndTrust(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForFirstBankAndTrust(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForFirstBankAndTrust(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
         int StartIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*DEPOSITS");
         int EndIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*WITHDRAWALS|^\\s*CHECKS\\s+IN\\s+NUMBER\\s+ORDER|^\\s*DAILY\\s+BALANCES");
         return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex); 
    }
     public OnlineTransferFromForFirstBankAndTrust getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForFirstBankAndTrust onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForFirstBankAndTrust getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForFirstBankAndTrust onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
