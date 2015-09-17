/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.citibank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DepositsForCitiBank {
    List<String> lines;
    List<String> selectedBlock;
    OnlineTransferFromForCitiBank onlineTransferFrom;
    OnlineReversalFromForCitiBank onlineReversalFrom;

    public DepositsForCitiBank(List<String> lines,List<String> keywords) {
        this.lines = lines;
        startExecution();
    }

    private void startExecution() {
        this.selectedBlock = captureSelectedBlock();
        this.onlineTransferFrom = new OnlineTransferFromForCitiBank(this.selectedBlock);
        this.onlineReversalFrom = new OnlineReversalFromForCitiBank(this.selectedBlock);
    }
    private List<String> captureSelectedBlock(){
         String start = "^\\s*CHECKING\\s+ACTIVITY";
        String end = "^\\s*Total\\s+Debits/Credits";
        List<String> main = new ArrayList<>();
        List<String> list = FileUtils.getSelectedBlock(lines, start, end);
        for (String s : list) {
            String result = FileUtils.getResultFromPattern("^\\d{1,2}/\\d{1,2}", s);
            if (!result.startsWith("NoMatch")) {
                main.add(s);
            }
        }
        return main;
    }
}
