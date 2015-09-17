/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.banklandmark;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForBankLandmark {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForBankLandmark onlineTransferFrom;
    private OnlineReversalFromForBankLandmark  onlineReversalFrom;
    public DepositsForBankLandmark(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForBankLandmark(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForBankLandmark(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
         
          int StartIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*\\*+\\s*Other\\s+Credits");
          int EndIndex = FileUtils.findIndexBySingleValue(lines,"^\\s*Total\\s+Other\\s+Credits");
          
          return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex);
    }
     public OnlineTransferFromForBankLandmark getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForBankLandmark onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForBankLandmark getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForBankLandmark onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
