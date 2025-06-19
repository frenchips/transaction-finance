package com.example.test.service;

import com.example.test.dto.FetchUserDto;
import com.example.test.entity.User;
import com.example.test.repository.UserRepository;
import com.example.test.request.AddUserRequest;
import com.example.test.request.UpdateUserRequest;
import com.example.test.response.Response;
import com.example.test.response.SaveUserResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public ResponseEntity<Response> saveUser(AddUserRequest request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setCreateAt(new Timestamp(System.currentTimeMillis()));

        userRepository.save(user);

        SaveUserResponse saveResponse = new SaveUserResponse();
        saveResponse.setId(user.getId());
        saveResponse.setName(user.getName());
        saveResponse.setEmail(user.getEmail());
        saveResponse.setCreateAt(user.getCreateAt());

        Response response = new Response();
        response.setMessage("Data berhasil disimpan");
        response.setData(saveResponse);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @Override
    public ResponseEntity<Response> findAllUser() {

        List<Object[]> listObj = userRepository.findAllUser();
        List<FetchUserDto> listData = new ArrayList<>();
        for(Object[] obj : listObj){
            FetchUserDto dto = new FetchUserDto();
            dto.setId(obj[0] != null ? (UUID) obj[0] : null);
            dto.setName(obj[1] != null ? (String) obj[1] : null);
            dto.setEmail(obj[2] != null ? (String) obj[2] : null);
            dto.setCreateAt(obj[3] != null ? (Timestamp) obj[3] : null);

            listData.add(dto);
        }

        Response response = new Response();
        response.setMessage("Data berhasil ditampilkan");
        response.setData(listData);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @Override
    public ResponseEntity<Response> getUserId(UUID id) {

        User user = userRepository.findById(id).orElse(null);


        FetchUserDto dto = new FetchUserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setCreateAt(user.getCreateAt());

        Response response = new Response();
        response.setMessage("Data berhasil ditampilkan");
        response.setData(dto);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @Override
    @Transactional
    public ResponseEntity<Response> updateUser(UUID id, UpdateUserRequest request) {
        User user = userRepository.findById(id).orElse(null);

        if(user != null){
            user.setEmail(request.getEmail());
            user.setCreateAt(new Timestamp(System.currentTimeMillis()));

            userRepository.save(user);
        }

        Response response = new Response();
        response.setMessage("Data berhasil diubah");
        response.setData(user);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
