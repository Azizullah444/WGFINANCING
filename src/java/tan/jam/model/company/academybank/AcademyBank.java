/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.academybank;

import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class AcademyBank extends Company {
    
    private String[] pages;
    private List<String> lines;
    private List<String> keywords;
    private CredentialsForAcademyBank credentials;
    private DepositsForAcademyBank deposits;
    private DebitsForAcademyBank debits;
    private DailyLedgerBalanceForAcademyBank dailyLedgerBalance;
    private KeywordsForAcademyBank keywordsInput;
    
    public AcademyBank(String[] pages, List<String> keywords) {
        this.pages = pages;
        this.keywords = keywords;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForAcademyBank(pages,lines);
        this.deposits = new DepositsForAcademyBank(lines, keywords);
        this.debits = new DebitsForAcademyBank(lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForAcademyBank(lines);
        this.keywordsInput = new KeywordsForAcademyBank(lines, keywords);
        System.out.println("Academy Bank In Progress");
        startExecution();
        
    }
    
    private void startExecution() {
        executeCredentialsSection();
        executeDailyLedgerBalanceSection();
        executeDebitsSection();
        executeDepositsSection();
        executeKeywordsSection();
    }

    private void executeKeywordsSection() {
        
        setKeywordSum(this.keywordsInput.getSumForKeywords());
        setKeywordAmount(this.keywordsInput.getKeywordAmount());
        setKeywordDate(this.keywordsInput.getKeywordDate());
        setKeywordDescription(this.keywordsInput.getKeywordDescription());
        setDailypaymentKeywordDate(this.keywordsInput.getDailypaymentKeywordDate());
        setDailypaymentKeywordAmount(this.keywordsInput.getDailypaymentKeywordAmount());
        setDailypaymentKeywordDescription(this.keywordsInput.getDailypaymentKeywordDescription());
    }

    private void executeCredentialsSection() {
        setCompanyName(this.credentials.getCompanyName());
        setAccountNumber(this.credentials.getAccountNumber());
        setDate(this.credentials.getDate());
        setTotalDeposits(this.credentials.getTotalDeposits());
    }
    
    private void executeDepositsSection() {
        setSumOfOnlineTransferFrom(this.deposits.getOnlineTransferFromSum());
        setSumOfReversalFrom(this.deposits.getOnlineReversalFromSum());
    }
    
    private void executeDebitsSection() {
        setOnlineTransferToDate(this.debits.getDate());
        setOnlineTransferToAmount(this.debits.getAmount());
        setOnlineTransferToCheckNo(this.debits.getCheckNo());
        setOnlineTransferToDescription(this.debits.getDescription());
        setOnlineTransferToCheckNoDuplicateRemoved(this.debits.getCheckNoDuplicatesRemoved());
    }
    
    private void executeDailyLedgerBalanceSection() {
        setMinValue(this.dailyLedgerBalance.getMinValue());
        setNoOfNegativeValues(this.dailyLedgerBalance.getNoOfNegativeValues());
        setAverageLedgerBalance(this.dailyLedgerBalance.getAverageLedgerBalance());
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
    
    public List<String> getKeywords() {
        return keywords;
    }
    
    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
    
    public CredentialsForAcademyBank getCredentials() {
        return credentials;
    }
    
    public void setCredentials(CredentialsForAcademyBank credentials) {
        this.credentials = credentials;
    }
    
    public DepositsForAcademyBank getDeposits() {
        return deposits;
    }
    
    public void setDeposits(DepositsForAcademyBank deposits) {
        this.deposits = deposits;
    }
    
    public DebitsForAcademyBank getDebits() {
        return debits;
    }
    
    public void setDebits(DebitsForAcademyBank credits) {
        this.debits = credits;
    }
    
    public DailyLedgerBalanceForAcademyBank getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForAcademyBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
    
    public KeywordsForAcademyBank getKeywordsInput() {
        return keywordsInput;
    }
    
    public void setKeywordsInput(KeywordsForAcademyBank keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
    
}
