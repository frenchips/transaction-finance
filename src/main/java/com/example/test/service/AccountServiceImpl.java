package com.example.test.service;

import com.example.test.dto.FetchAccountDto;
import com.example.test.entity.Account;
import com.example.test.entity.User;
import com.example.test.repository.AccountRepository;
import com.example.test.repository.UserRepository;
import com.example.test.request.AddAccountRequest;
import com.example.test.response.Response;
import com.example.test.response.SaveAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<Response> saveAccount(AddAccountRequest request) {

        User user = userRepository.findById(request.getUserId()).orElse(null);

        Account account = new Account();
        account.setUserId(user);
        account.setBalance(request.getInitialBalance());
        account.setCreateAt(new Timestamp(System.currentTimeMillis()));

        accountRepository.save(account);

        SaveAccountResponse saveAccountResponse = new SaveAccountResponse();
        saveAccountResponse.setAccountId(account.getId());
        saveAccountResponse.setUserId(user.getId());
        saveAccountResponse.setBalance(account.getBalance());
        saveAccountResponse.setCreateAt(account.getCreateAt());

        Response response = new Response();
        response.setMessage("Data Account berhasil disimpan");
        response.setData(saveAccountResponse);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @Override
    public ResponseEntity<Response> findAccount() {
        List<Object[]> listObj = accountRepository.findAccountAll();
        List<FetchAccountDto> listData = new ArrayList<>();

        for(Object[] obj : listObj){
            FetchAccountDto dto = new FetchAccountDto();

            dto.setId(obj[0] != null ? (UUID) obj[0] : null);
            dto.setUserId(obj[1] != null ? (UUID) obj[1] : null);
            dto.setBalance(obj[2] != null ? ((BigDecimal) obj[2]).doubleValue() : null );
            dto.setCreateAt(obj[3] != null ? (Timestamp) obj[3] : null);
            listData.add(dto);
        }

        Response response = new Response();
        response.setMessage("Data berhasil ditampilkan");
        response.setData(listData);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }
}
