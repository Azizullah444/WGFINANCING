/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.suntrust;

import java.util.List;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class DebitsForSunTrustBank {
    private List<String> lines;
    private List<String> initialBlock;
    private OnlineTransferToForSunTrust onlineTransferTo;

    public DebitsForSunTrustBank(List<String> lines) {
        this.lines = lines;
        startExecution();
    }

    private void startExecution() {
        this.initialBlock = captureInitialBlock();
        this.onlineTransferTo = new OnlineTransferToForSunTrust(initialBlock);
    }
    private List<String> captureInitialBlock(){
        String start="^\\s*Withdrawals/\\s+Date\\s+Amount\\s+Serial\\s+#\\s+Description";
        String end="^\\s*Withdrawals/Debits:";
        List<String> list = FileUtils.getSelectedBlock(lines, start, end);
       
        return list;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public List<String> getInitialBlock() {
        return initialBlock;
    }

    public void setInitialBlock(List<String> initialBlock) {
        this.initialBlock = initialBlock;
    }

    public OnlineTransferToForSunTrust getOnlineTransferTo() {
        return onlineTransferTo;
    }

    public void setOnlineTransferTo(OnlineTransferToForSunTrust onlineTransferTo) {
        this.onlineTransferTo = onlineTransferTo;
    }
    
}
