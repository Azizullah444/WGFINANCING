/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.tdbank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class OnlineTransferToForTdBank {
    
    private List<String> initialBlock;
    private List<String> selectedBlock;
    private List<String> date;
    private List<String> description;
    private List<String> checkNo;
    private List<Double> amount;
    private List<String> checkNoDuplicateRemoved;
    
    public OnlineTransferToForTdBank(List<String> initialBlock) {
        this.initialBlock = initialBlock;
        this.date = new ArrayList<>();
        this.description = new ArrayList<>();
        this.checkNo = new ArrayList<>();
        this.amount = new ArrayList<>();
        
        startExecution();
    }

    private void show() {
        for (int a = 0; a < date.size(); a++) {
//            System.out.println(date.get(a) + " " + amount.get(a) + " " + description.get(a) + " " + checkNo.get(a));
        }
    }

    private void startExecution() {
        List<String> SecondLine = new ArrayList<>();
        this.selectedBlock = captureSelectedRecordsBlock();
        captureDateDescriptionAmount();
         show();
    }
    
    private void captureDateDescriptionAmount() {
        List<String> list = this.selectedBlock;
        for (String s : list) {
            String[] temp = s.split(" ");
            date.add(temp[0]);
            amount.add(FileUtils.convertStringToDouble(FileUtils.getDollarSignRemovedValue(temp[temp.length - 1])));
           String desc = "";
            for (int a = 1; a < temp.length - 1; a++) {
                desc += temp[a] + " ";
            }
            description.add(desc); /*
            String res = FileUtils.getResultFromPattern("Transfer\\s+to\\s+CK\\s+\\d{4,10}", desc);
             if (!res.startsWith("NoMatch")) {
                checkNo.add(FileUtils.getFourDigitAccountNumber(res));
               // System.out.println(re);
            }
            else{
                checkNo.add("N/A");
            }*/
        }
        this.checkNoDuplicateRemoved = FileUtils.removeDuplicateValues(checkNo);
    }
    
    private List<String> captureSelectedRecordsBlock() {
        List<String> list = initialBlock;
        List<String> main = new ArrayList<String>();
        for (String s : list) {
            String result = FileUtils.getResultFromPattern("e\\s?+Transfer\\s+Debit", s);
            if (!result.startsWith("NoMatch")) {
                main.add(s);
              
            }
              String second = FileUtils.getResultFromPattern("Transfer\\s+to\\s+CK\\s+\\d{5,}|Transfer\\s+to\\s+SV\\s+\\d+", s);
              if (!second.startsWith("NoMatch")) {
             // description.add(s);
              checkNo.add(FileUtils.getFourDigitAccountNumber(s));
              }
            
        }
        return main;
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

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public List<String> getCheckNo() {
        return checkNo;
    }

    public void setCheckNo(List<String> checkNo) {
        this.checkNo = checkNo;
    }

    public List<Double> getAmount() {
        return amount;
    }

    public void setAmount(List<Double> amount) {
        this.amount = amount;
    }

    public List<String> getCheckNoDuplicateRemoved() {
        return checkNoDuplicateRemoved;
    }

    public void setCheckNoDuplicateRemoved(List<String> checkNoDuplicateRemoved) {
        this.checkNoDuplicateRemoved = checkNoDuplicateRemoved;
    }
}
