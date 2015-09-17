/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.firstbank2;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForFirstBank2 {
    List<String> lines;
    List<String> selectedBlock;
    private OnlineTransferFromForFirstBank2 onlineTransferFrom;
    private OnlineReversalFromForFirstBank2  onlineReversalFrom;
    public DepositsForFirstBank2(List<String> lines) {
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
        this.onlineTransferFrom = new OnlineTransferFromForFirstBank2(this.selectedBlock);
        this.onlineReversalFrom=new OnlineReversalFromForFirstBank2(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
         int StartIndex = FileUtils.findIndexBySingleValue(lines,"Deposits\\s+and\\s+Other\\s+Credits");
         int EndIndex = FileUtils.findIndexBySingleValue(lines,"Numerical\\s+Check\\s+Listing|Other\\s+Debits\\s+and\\s+Converted\\s+Checks");
         return FileUtils.getSelectedBlockByIndex(lines, StartIndex, EndIndex); 
    }
     public OnlineTransferFromForFirstBank2 getOnlineTransferFrom() {
        return onlineTransferFrom;
    }

    public void setOnlineTransferFrom(OnlineTransferFromForFirstBank2 onlineTransferFrom) {
        this.onlineTransferFrom = onlineTransferFrom;
    }
    public OnlineReversalFromForFirstBank2 getOnlineReversalFrom() {
        return onlineReversalFrom;
    }

    public void setOnlineReversalFrom(OnlineReversalFromForFirstBank2 onlineReversalFrom) {
        this.onlineReversalFrom = onlineReversalFrom;
    }
}
