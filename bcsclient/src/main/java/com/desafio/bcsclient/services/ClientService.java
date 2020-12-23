package com.desafio.bcsclient.services;


import com.desafio.bcsclient.dto.ClientDto;
import com.desafio.bcsclient.entities.Client;
import com.desafio.bcsclient.repositories.ClientRepository;
import com.desafio.bcsclient.services.exceptions.DatabaseException;
import com.desafio.bcsclient.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDto> findAllPaged(PageRequest pageRequest){
        Page<Client> list = clientRepository.findAll(pageRequest);
        return list.map(x -> new ClientDto(x));
    }

    @Transactional(readOnly = true)
    public ClientDto findById(Long id) {
        Optional<Client> obj = clientRepository.findById(id);
        Client entity = obj.orElseThrow(()-> new ResourceNotFoundException("Entity not found"));
        return new ClientDto(entity);
    }

    @Transactional
    public ClientDto insert(ClientDto clientDto) {
        Client entity = new Client();
        entity.setName(clientDto.getName());
        entity.setCpf(clientDto.getCpf());
        entity.setBirthDate(clientDto.getBirthDate());
        entity.setIncome(clientDto.getIncome());
        entity.setChildren(clientDto.getChildren());
        entity = clientRepository.save(entity);
        return  new ClientDto(entity);
    }

    @Transactional
    public ClientDto update(Long id,ClientDto clientDto) {
        try {
            Client entity = clientRepository.getOne(id);
            entity.setName(clientDto.getName());
            entity.setCpf(clientDto.getCpf());
            entity.setBirthDate(clientDto.getBirthDate());
            entity.setIncome(clientDto.getIncome());
            entity.setChildren(clientDto.getChildren());
            entity = clientRepository.save(entity);
            return new ClientDto(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found "+ id);
        }

    }

    public void delete(Long id) {
        try {
            clientRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id not found "+id);
        }catch (DataIntegrityViolationException e){
            throw  new DatabaseException("Integrity violation");
        }
    }
}
