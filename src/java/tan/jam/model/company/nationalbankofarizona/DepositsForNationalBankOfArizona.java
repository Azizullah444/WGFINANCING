/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.nationalbankofarizona;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForNationalBankOfArizona {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForNationalBankOfArizona onlineTransferFrom;
    private OnlineReversalFromForNationalBankOfArizona  onlineReversalFrom;
    public DepositsForNationalBankOfArizona(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForNationalBankOfArizona(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForNationalBankOfArizona(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
         int StartIndex=FileUtils.findStartIndex(lines,"^\\s*\\d*\\s+DEPOSITS/CREDITS","Date\\s+Amount\\s+Description");
         int EndIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*\\d*\\s+CHARGES/DEBITS");
         return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex); 
    }
     public OnlineTransferFromForNationalBankOfArizona getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForNationalBankOfArizona onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForNationalBankOfArizona getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForNationalBankOfArizona onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
