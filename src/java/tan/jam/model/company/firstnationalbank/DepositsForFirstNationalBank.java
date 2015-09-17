/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.firstnationalbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForFirstNationalBank {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForFirstNationalBank onlineTransferFrom;
    private OnlineReversalFromForFirstNationalBank  onlineReversalFrom;
    public DepositsForFirstNationalBank(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForFirstNationalBank(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForFirstNationalBank(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
         int StartIndex = FileUtils.findIndexBySingleValue(lines,"A C C O U N T  C R E D I T  T R A N S A C T I O N");
         int EndIndex = FileUtils.findIndexBySingleValue(lines,"O T H E R  D E B I T  T R A N S A C T I O N");
         return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex);
    }
     public OnlineTransferFromForFirstNationalBank getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForFirstNationalBank onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForFirstNationalBank getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForFirstNationalBank onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
