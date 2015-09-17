/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.bankofamerica;

import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;


public class BankOfAmerica extends Company {

    private String[] pages;
    private List<String> lines;
    private List<String> words;
    private CredentialsForBankOfAmerica credentials;
    private DepositsBankOfAmerica deposits;
    private DebitsForBankOfAmerica debits;
    private DailyLedgerBalanceForBankOfAmerica dailyLedgerBalance;
    private KeywordsForBankOfAmerica keywordsInput;

    public BankOfAmerica(String[] pages, List<String> words) {
        this.pages = pages;
        this.words = words;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForBankOfAmerica(pages, lines);
        this.deposits = new DepositsBankOfAmerica(lines);
        this.debits = new DebitsForBankOfAmerica(lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForBankOfAmerica(lines);
        this.keywordsInput = new KeywordsForBankOfAmerica(lines, words);

        System.out.println("Bank Of America In Progess");
        startExecution();
    }

    private void startExecution() {
        executeCredentialsSection();
        executeDailyLedgerBalanceSection();
        executeDebitsSection();
        executeDepositsSection();
        executeKeywordsSection();
    }

    private void executeCredentialsSection() {
        setCompanyName(this.credentials.getCompanyName());
        setAccountNumber(this.credentials.getAccountNumber());
        setDate(this.credentials.getDate());
        setTotalDeposits(this.credentials.getTotalDeposits());
        setAverageLedgerBalance(this.credentials.getAverageLedgerBalance());
    }
    private void executeKeywordsSection(){
        setKeywordSum(this.keywordsInput.getSumForKeywords());
        setKeywordAmount(this.keywordsInput.getKeywordAmount());
        setKeywordDate(this.keywordsInput.getKeywordDate());
        setKeywordDescription(this.keywordsInput.getKeywordDescription());
        setDailypaymentKeywordDate(this.keywordsInput.getDailypaymentKeywordDate());
        setDailypaymentKeywordAmount(this.keywordsInput.getDailypaymentKeywordAmount());
        setDailypaymentKeywordDescription(this.keywordsInput.getDailypaymentKeywordDescription());
    }
    private void executeDebitsSection() {
           
        setOnlineTransferToDate(this.debits.getOnlineTransferTo().getDate());
        setOnlineTransferToAmount(this.debits.getOnlineTransferTo().getAmount());
        setOnlineTransferToCheckNo(this.debits.getOnlineTransferTo().getCheckNo());
        setOnlineTransferToDescription(this.debits.getOnlineTransferTo().getDescription());
        setOnlineTransferToCheckNoDuplicateRemoved(this.debits.getOnlineTransferTo().getCheckNoDuplicateRemoved());
    }

    private void executeDepositsSection() {
        setSumOfOnlineTransferFrom(this.deposits.getOnlineTransferFrom().getSum());
        setSumOfReversalFrom(this.deposits.getOnlineTLRFrom().getSum());

    }

    private void executeDailyLedgerBalanceSection() {
        setMinValue(this.dailyLedgerBalance.getMinValue());
        setNoOfNegativeValues(this.dailyLedgerBalance.getNoOfNegativeValues());
   
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

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public CredentialsForBankOfAmerica getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsForBankOfAmerica credentials) {
        this.credentials = credentials;
    }

    public DailyLedgerBalanceForBankOfAmerica getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }

    public void setDailyLedgerBalance(DailyLedgerBalanceForBankOfAmerica dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }

    public DepositsBankOfAmerica getDeposits() {
        return deposits;
    }

    public void setDeposits(DepositsBankOfAmerica deposits) {
        this.deposits = deposits;
    }

    public DebitsForBankOfAmerica getDebits() {
        return debits;
    }

    public void setDebits(DebitsForBankOfAmerica debits) {
        this.debits = debits;
    }

    public KeywordsForBankOfAmerica getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(KeywordsForBankOfAmerica keywordsInput) {
        this.keywordsInput = keywordsInput;
    }

}
