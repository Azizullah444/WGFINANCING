/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.guarantybank;

import java.util.ArrayList;
import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;

/**
 *
 * @author Lenovo
 */
public class GuarantyBank extends Company {

    private String[] pages;
    private List<String> lines;
    private List<String> keywords;

    private CredentialsForGuarantyBank credentials;
    private DebitsForGuarantyBank debits;
    private DepositsForGuarantyBank deposits;
    private KeywordsForGuarantyBank keywordsInput;
    DailyLedgerBalanceForGuarantyBank dailyLedgerBalance;

    public GuarantyBank(String[] pages, List<String> keywords) {
        this.pages = pages;
        this.keywords = keywords;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForGuarantyBank(pages); 
        this.debits = new DebitsForGuarantyBank(lines);
        this.deposits = new DepositsForGuarantyBank(lines);
        this.keywordsInput = new KeywordsForGuarantyBank(lines, keywords);
        this.dailyLedgerBalance = new DailyLedgerBalanceForGuarantyBank(lines);
        System.out.println("Guaranty Bank In Progress");
        startExecution();
    }

    private void startExecution() {
        executeCredentialsSection(); 
        executeDepositsSection();
        executeDebitsSection();
        executeDailyLedgerBalanceSection();
        executeKeywordsSection();
    }

    private void executeCredentialsSection() {
        setCompanyName(this.credentials.getCompanyName());
        setAccountNumber(this.credentials.getAccountNumber());
        setDate(this.credentials.getDate());
        setTotalDeposits(this.credentials.getTotalDeposits());
    }

    private void executeDepositsSection() {
        setSumOfOnlineTransferFrom(this.deposits.getOnlineTransferFrom().getSum());
        setSumOfReversalFrom(this.deposits.getOnlineReversalFromSum());
    }

    private void executeDebitsSection() {
        setOnlineTransferToAmount(this.debits.getOnlineTransferTo().getAmount());
        setOnlineTransferToCheckNo(this.debits.getOnlineTransferTo().getCheckNo());
        setOnlineTransferToCheckNoDuplicateRemoved(this.debits.getOnlineTransferTo().getCheckNoDuplicate());
        setOnlineTransferToDate(this.debits.getOnlineTransferTo().getDate());
        setOnlineTransferToDescription(this.debits.getOnlineTransferTo().getDescription());
    }

    private void executeDailyLedgerBalanceSection() {
        setMinValue(this.dailyLedgerBalance.getMinValue());
        setNoOfNegativeValues(this.dailyLedgerBalance.getNoOfNegativeValues());
        setAverageLedgerBalance(this.dailyLedgerBalance.getAverageLedgerBalance());
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

    public CredentialsForGuarantyBank getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsForGuarantyBank credentials) {
        this.credentials = credentials;
    }

    public DebitsForGuarantyBank getDebits() {
        return debits;
    }

    public void setDebits(DebitsForGuarantyBank debits) {
        this.debits = debits;
    }

    public DepositsForGuarantyBank getDeposits() {
        return deposits;
    }

    public void setDeposits(DepositsForGuarantyBank deposits) {
        this.deposits = deposits;
    }

    public KeywordsForGuarantyBank getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(KeywordsForGuarantyBank keywordsInput) {
        this.keywordsInput = keywordsInput;
    }
    public DailyLedgerBalanceForGuarantyBank getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }
    
    public void setDailyLedgerBalance(DailyLedgerBalanceForGuarantyBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }
}
