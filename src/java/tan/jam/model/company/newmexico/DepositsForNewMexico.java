/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.newmexico;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForNewMexico {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForNewMexico onlineTransferFrom;
    private OnlineReversalFromForNewMexico  onlineReversalFrom;
    public DepositsForNewMexico(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForNewMexico(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForNewMexico(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
          
          int StartIndex = FileUtils.findStartIndex(lines, "DEPOSITS\\s+AND\\s+OTHER\\s+CREDITS", "DATE\\s+DESCRIPTION\\s+AMOUNT");
          int EndIndex = FileUtils.findIndexBySingleValue(lines,"TOTAL\\s+CREDITS");
          
          return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex);
    }
     public OnlineTransferFromForNewMexico getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForNewMexico onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForNewMexico getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForNewMexico onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
