package com.ftn.sbnz.service;

import com.ftn.sbnz.model.FinancialSummary;
import com.ftn.sbnz.model.Transaction;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionProcessor {

    private final KieContainer kieContainer;

    public TransactionProcessor(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public FinancialSummary process(List<Transaction> transactionHistory) {
        KieSession kieSession = null;
        try {
            kieSession = kieContainer.newKieSession("ksession-rules");
            if (kieSession == null) {
                throw new IllegalStateException("KieSession 'ksession-rules' not found. Available kbases: " + kieContainer.getKieBaseNames());
            }
            FinancialSummary summary = new FinancialSummary();
            kieSession.insert(summary);
            EntryPoint stream = kieSession.getEntryPoint("TransactionStream");
            for (Transaction transaction : transactionHistory) {
                kieSession.insert(transaction);
                stream.insert(transaction);
            }
            kieSession.fireAllRules();
            return summary;
        } finally {
            if (kieSession != null) {
                kieSession.dispose();
            }
        }
    }
}
