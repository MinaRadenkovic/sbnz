package com.ftn.sbnz.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FinancialSummary {

    private BigDecimal totalIncome = BigDecimal.ZERO;
    private BigDecimal totalExpense = BigDecimal.ZERO;
    private String financialState;
    private List<String> warnings = new ArrayList<>();

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        if (totalIncome != null) {
            this.totalIncome = totalIncome;
        }
    }

    public BigDecimal getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(BigDecimal totalExpense) {
        if (totalExpense != null) {
            this.totalExpense = totalExpense;
        }
    }

    public String getFinancialState() {
        return financialState;
    }

    public void setFinancialState(String financialState) {
        this.financialState = financialState;
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
        return "FinancialSummary{" +
                "totalIncome=" + totalIncome +
                ", totalExpense=" + totalExpense +
                ", financialState='" + financialState + '\'' +
                ", warnings=" + warnings +
                '}';
    }
}
