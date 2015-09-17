/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.flagstar;

import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class FlagStar extends Company {
    
    private String[] pages;
    private List<String> lines;
  /*  private CredentialsForWellsOld credentials;
    private DailyLedgerBalanceForWellsOld dailyLedgerBalance;
    private DepositsForWellsOld deposits;
    private DebitsForWellsOld credits;
    private KeywordsForWellsOld keywordsInput;
   */
    
    public FlagStar(String[] pages, List<String> words) {
        this.pages = pages;
        this.lines = FileUtils.fillLines(pages);
       /* this.credentials = new CredentialsForWellsOld(pages, lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForWellsOld(lines);
        this.deposits = new DepositsForWellsOld(lines);
        this.credits = new DebitsForWellsOld(lines);
        this.keywordsInput = new KeywordsForWellsOld(lines, words);
        //this.dlb = new DLBForWellsOld(lines);
        startExecution();*/
    }
    
    private void startExecution() {
        
        executeCredentialsSection();
        executeDebitsSection();
        executeDepositsSection();
        executeDailyLedgerBalanceSection();
        executeKeywordsSection();
    }
    
    private void executeKeywordsSection() {
      /*  setKeywordSum(this.keywordsInput.getSumForKeywords());
        setKeywordAmount(this.keywordsInput.getKeywordAmount());
        setKeywordDate(this.keywordsInput.getKeywordDate());
        setKeywordDescription(this.keywordsInput.getKeywordDescription());*/
    }
    
    private void executeDailyLedgerBalanceSection() {
      /*  setMinValue(this.dailyLedgerBalance.getMinValue());
        setNoOfNegativeValues(this.dailyLedgerBalance.getNoOfNegativeValues());*/
        
    }
    
    private void executeDepositsSection() {
      /*  setSumOfOnlineTransferFrom(this.deposits.getOnlineTransferFromSum());
        setSumOfReversalFrom(this.deposits.getOnlineReversalFromSum());*/
    }
    
    private void executeDebitsSection() {
       /* setOnlineTransferToDate(this.credits.getDate());
        setOnlineTransferToAmount(this.credits.getAmount());
        setOnlineTransferToCheckNo(this.credits.getCheckNo());
        setOnlineTransferToDescription(this.credits.getDescription());
        setOnlineTransferToCheckNoDuplicateRemoved(this.credits.getCheckNoDuplicatesRemoved());*/
        
    }
    
    private void executeCredentialsSection() {
       /* setCompanyName(this.credentials.getCompanyName());
        setAccountNumber(this.credentials.getAccountNumber());
        setDate(this.credentials.getDate());
        setTotalDeposits(this.credentials.getTotalDeposits());
        setAverageLedgerBalance(this.credentials.getAverageLedgerBalance());*/
    }
    
    public String[] getPages() {
        return pages;
    }
    
    public void setPages(String[] pages) {
        this.pages = pages;
    }
    
    public List<String> getLines() {
        return lines;
    }
    
    public void setLines(List<String> lines) {
        this.lines = lines;
    }
    
   /* public CredentialsForFlagStar getCredentials() {
        return credentials;
    }
    
    public void setCredentials(CredentialsForFlagStar credentials) {
        this.credentials = credentials;
    }
    
    public DailyLedgerBalanceForFlagStar getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForFlagStar dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public DepositsForFlagStar getDeposits() {
        return deposits;
    }
    
    public void setDeposits(DepositsForFlagStar deposits) {
        this.deposits = deposits;
    }
    
    public DebitsForFlagStar getCredits() {
        return credits;
    }
    
    public void setCredits(DebitsForFlagStar credits) {
        this.credits = credits;
    }
    
    public KeywordsForFlagStar getKeywordsInput() {
        return keywordsInput;
    }
    
    public void setKeywordsInput(KeywordsForFlagStar keywordsInput) {
        this.keywordsInput = keywordsInput;
    }*/
    
    
}
