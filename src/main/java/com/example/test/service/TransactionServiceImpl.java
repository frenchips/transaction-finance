package com.example.test.service;

import com.example.test.dto.FetchTransactionAllDto;
import com.example.test.entity.Account;
import com.example.test.entity.Transaction;
import com.example.test.repository.AccountRepository;
import com.example.test.repository.TransactionRepository;
import com.example.test.request.AddTransactionRequest;
import com.example.test.response.Response;
import com.example.test.response.SaveTransactionResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    private final String DEBIT = "DEBIT";
    private final String CREDIT = "CREDIT";

    @Override
    @Transactional
    public ResponseEntity<Response> addTransaction(AddTransactionRequest request) {

        Account account = accountRepository.findById(request.getAccountId()).orElse(null);

        Transaction transaction = new Transaction();

        if(account != null){
            if(DEBIT.equalsIgnoreCase(request.getType())){
                if(account.getBalance() >= request.getAmount()) {
                    transaction.setAccountId(account);
                    transaction.setType(request.getType());
                    transaction.setAmount(account.getBalance() - request.getAmount());
                    transaction.setCreateAt(new Timestamp(System.currentTimeMillis()));
                } else {
                    throw new IllegalArgumentException("Saldo tidak mencukupi untuk melakukan Debit");
                }
            } else if (CREDIT.equalsIgnoreCase(request.getType())) {
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

    @Override
    public ResponseEntity<Response> searchData(Pageable pageable) {

        Page<Object[]> pageList = transactionRepository.search(pageable);
        List<Object[]> listObj = pageList.getContent();

        List<FetchTransactionAllDto> listData = new ArrayList<>();

        for(Object[] obj : listObj){
            FetchTransactionAllDto dto = new FetchTransactionAllDto();
            dto.setName(obj[0] != null ? (String) obj[0] : null);
            dto.setSaldo(obj[1] != null ? ((BigDecimal) obj[1]).doubleValue() : null);
            dto.setType(obj[2] != null ? (String) obj[2] : null);
            dto.setAmount(obj[3] != null ? (Double) obj[3]: null);

            listData.add(dto);
        }

        Response response = new Response();
        response.setMessage("Data berhasil ditampilkan");
        response.setData(listData);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

}
