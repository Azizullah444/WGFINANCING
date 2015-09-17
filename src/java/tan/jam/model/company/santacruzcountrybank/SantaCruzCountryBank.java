/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tan.jam.model.company.santacruzcountrybank;

import java.util.List;
import tan.jam.company.Company;
import tan.jam.company.utils.FileUtils;


public class SantaCruzCountryBank extends Company {

    private String[] pages;
    private List<String> lines;
    private List<String> words;
    private CredentialsForSantraCruzCountryBank credentials;
    private DepositsForSantaCruzCountryBank deposits;
    private DebitsForSantaCruzCountryBank debits;
    private DailyLedgerBalanceForSantaCruzCountryBank dailyLedgerBalance;
    private KeywordsForSantaCruzCountryBank keywordsInput;

    public SantaCruzCountryBank(String[] pages, List<String> words) {
        this.pages = pages;
        this.words = words;
        this.lines = FileUtils.fillLines(pages);
        this.credentials = new CredentialsForSantraCruzCountryBank(pages, lines);
        this.deposits = new DepositsForSantaCruzCountryBank(lines);
        this.debits = new DebitsForSantaCruzCountryBank(lines);
        this.dailyLedgerBalance = new DailyLedgerBalanceForSantaCruzCountryBank(lines);
        this.keywordsInput = new KeywordsForSantaCruzCountryBank(lines, words);

        System.out.println("SantaCruzCountry In Progess");
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

    public CredentialsForSantraCruzCountryBank getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsForSantraCruzCountryBank credentials) {
        this.credentials = credentials;
    }

    public DailyLedgerBalanceForSantaCruzCountryBank getDailyLedgerBalance() {
        return dailyLedgerBalance;
    }

    public void setDailyLedgerBalance(DailyLedgerBalanceForSantaCruzCountryBank dailyLedgerBalance) {
        this.dailyLedgerBalance = dailyLedgerBalance;
    }

    public DepositsForSantaCruzCountryBank getDeposits() {
        return deposits;
    }

    public void setDeposits(DepositsForSantaCruzCountryBank deposits) {
        this.deposits = deposits;
    }

    public DebitsForSantaCruzCountryBank getDebits() {
        return debits;
    }

    public void setDebits(DebitsForSantaCruzCountryBank debits) {
        this.debits = debits;
    }

    public KeywordsForSantaCruzCountryBank getKeywordsInput() {
        return keywordsInput;
    }

    public void setKeywordsInput(KeywordsForSantaCruzCountryBank keywordsInput) {
        this.keywordsInput = keywordsInput;
    }

}
