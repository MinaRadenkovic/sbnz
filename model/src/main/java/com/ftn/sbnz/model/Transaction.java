package com.ftn.sbnz.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transaction {

    private String id;
    private String type;
    private BigDecimal amount;
    private Date date;
    private String paymentMethod;
    private String description;
    private boolean pdv;
    private String category;
    private boolean largeTransaction;
    private boolean potentialTaxProblem;
    private boolean taxReview;
    private String journalDebit;
    private String journalCredit;
    private boolean valid;
    private String error;
    private List<String> warnings = new ArrayList<>();

    public Transaction() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPdv() {
        return pdv;
    }

    public void setPdv(boolean pdv) {
        this.pdv = pdv;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isLargeTransaction() {
        return largeTransaction;
    }

    public void setLargeTransaction(boolean largeTransaction) {
        this.largeTransaction = largeTransaction;
    }

    public boolean isPotentialTaxProblem() {
        return potentialTaxProblem;
    }

    public void setPotentialTaxProblem(boolean potentialTaxProblem) {
        this.potentialTaxProblem = potentialTaxProblem;
    }

    public boolean isTaxReview() {
        return taxReview;
    }

    public void setTaxReview(boolean taxReview) {
        this.taxReview = taxReview;
    }

    public String getJournalDebit() {
        return journalDebit;
    }

    public void setJournalDebit(String journalDebit) {
        this.journalDebit = journalDebit;
    }

    public String getJournalCredit() {
        return journalCredit;
    }

    public void setJournalCredit(String journalCredit) {
        this.journalCredit = journalCredit;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void addWarning(String warning) {
        if (warning != null && !warning.isBlank() && !warnings.contains(warning)) {
            warnings.add(warning);
        }
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", description='" + description + '\'' +
                ", pdv=" + pdv +
                ", category='" + category + '\'' +
                ", largeTransaction=" + largeTransaction +
                ", journalDebit='" + journalDebit + '\'' +
                ", journalCredit='" + journalCredit + '\'' +
                ", valid=" + valid +
                ", error='" + error + '\'' +
                ", warnings=" + warnings +
                '}';
    }
}
