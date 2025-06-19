package com.example.test.service;

import com.example.test.constants.TransactionType;
import com.example.test.entity.Account;
import com.example.test.entity.Transaction;
import com.example.test.repository.AccountRepository;
import com.example.test.repository.TransactionRepository;
import com.example.test.request.AddTransactionRequest;
import com.example.test.response.Response;
import com.example.test.response.SaveTransactionResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Override
    @Transactional
    public ResponseEntity<Response> addTransaction(AddTransactionRequest request) {

        Account account = accountRepository.findById(request.getAccountId()).orElse(null);

        Transaction transaction = new Transaction();

        if(account != null){
            if(TransactionType.DEBIT.equals(request.getType())){
                if(account.getBalance() >= request.getAmount()) {
                    transaction.setAccountId(account);
                    transaction.setType(request.getType());
                    transaction.setAmount(account.getBalance() - request.getAmount());
                    transaction.setCreateAt(new Timestamp(System.currentTimeMillis()));
                } else {
                    throw new IllegalArgumentException("Saldo tidak mencukupi untuk melakukan Debit");
                }
            } else if (TransactionType.CREDIT.equals(request.getType())) {
                transaction.setAccountId(account);
                transaction.setType(request.getType());
                transaction.setAmount(account.getBalance() + request.getAmount());
                transaction.setCreateAt(new Timestamp(System.currentTimeMillis()));
            }
        }


        transactionRepository.save(transaction);

        SaveTransactionResponse saveResponse = new SaveTransactionResponse();
        saveResponse.setId(transaction.getId());
        saveResponse.setAccountId(account.getId());
        saveResponse.setType(transaction.getType());
        saveResponse.setAmount(transaction.getAmount());
        saveResponse.setCreateAt(transaction.getCreateAt());

        Response response = new Response();
        response.setMessage("Data berhasil disimpan");
        response.setData(saveResponse);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
