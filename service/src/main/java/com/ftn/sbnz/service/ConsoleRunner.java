package com.ftn.sbnz.service;

import com.ftn.sbnz.model.FinancialSummary;
import com.ftn.sbnz.model.Transaction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private final TransactionProcessor transactionProcessor;

    public ConsoleRunner(TransactionProcessor transactionProcessor) {
        this.transactionProcessor = transactionProcessor;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        List<Transaction> history = new ArrayList<>();

        System.out.println("=== Konzolni sistem za automatsko knjiženje i CEP ===");
        System.out.println("Unesite transakcije; prazan tip za kraj.");

        while (true) {
            String type = prompt(scanner, "Tip transakcije (kupovina/prodaja/plata): ");
            if (type.isBlank()) {
                break;
            }

            String amountText = prompt(scanner, "Iznos: ");
            String dateText = prompt(scanner, "Datum (yyyy-MM-dd HH:mm): ");
            String paymentMethod = prompt(scanner, "Način plaćanja (gotovina/bankovni_transfer/kartica): ");
            String pdvText = prompt(scanner, "PDV obračunat (true/false): ");
            String description = prompt(scanner, "Opis: ");
            String category = prompt(scanner, "Kategorija (ostavi prazno za automatsku klasifikaciju): ");

            Transaction transaction = new Transaction();
            transaction.setId(UUID.randomUUID().toString());
            transaction.setType(type);
            transaction.setAmount(parseAmount(amountText));
            transaction.setDate(parseDate(dateText));
            transaction.setPaymentMethod(paymentMethod);
            transaction.setPdv(parseBoolean(pdvText));
            transaction.setDescription(description);
            if (!category.isBlank()) {
                transaction.setCategory(category);
            }
            history.add(transaction);

            System.out.println("Obrada transakcije...\n");
            try {
                FinancialSummary summary = transactionProcessor.process(history);
                printTransactionResult(transaction, summary);
            } catch (Exception e) {
                System.out.println("Greška pri obradi transakcije: " + e.getMessage());
                e.printStackTrace(System.out);
            }
            System.out.println("Unesite sledeću transakciju ili pritisnite Enter za kraj.");
            System.out.println();
        }

        System.out.println("Kraj unosa. Konačni finansijski izveštaj:");
        if (!history.isEmpty()) {
            FinancialSummary summary = transactionProcessor.process(history);
            System.out.println(summary);
        } else {
            System.out.println("Nije uneta nijedna transakcija.");
        }
    }

    private String prompt(Scanner scanner, String message) {
        System.out.print(message);
        System.out.flush();
        return scanner.nextLine().trim();
    }

    private BigDecimal parseAmount(String amountText) {
        try {
            return new BigDecimal(amountText);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    private Date parseDate(String dateText) {
        try {
            return DATE_FORMAT.parse(dateText);
        } catch (ParseException e) {
            return null;
        }
    }

    private boolean parseBoolean(String value) {
        return Boolean.parseBoolean(value);
    }

    private void printTransactionResult(Transaction transaction, FinancialSummary summary) {
        System.out.println();
        System.out.println("=== Rezultat transakcije ===");
        System.out.println("ID: " + transaction.getId());
        System.out.println("Tip: " + transaction.getType());
        System.out.println("Iznos: " + transaction.getAmount());
        System.out.println("Datum: " + transaction.getDate());
        System.out.println("Način plaćanja: " + transaction.getPaymentMethod());
        System.out.println("Kategorija: " + transaction.getCategory());
        System.out.println("Duguje: " + transaction.getJournalDebit());
        System.out.println("Potražuje: " + transaction.getJournalCredit());
        System.out.println("Transakcija ispravna: " + transaction.isValid());
        System.out.println("Greška: " + transaction.getError());
        System.out.println("Upozorenja: " + transaction.getWarnings());
        System.out.println("=== Finansijski izveštaj ===");
        System.out.println(summary);
        System.out.println();
    }
}
