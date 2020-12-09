package com.desafio.bcsclient.dto;

import com.desafio.bcsclient.entities.Client;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class ClientDto implements Serializable {

    private static final long serialVersionUID=1L;

    private Long id;
    private String name;
    private String cpf;
    private Double income;
    private Instant birthDay;
    private Integer children;

    public ClientDto() {
    }

    public ClientDto(Client entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.cpf = entity.getCpf();
        this.income = entity.getIncome();
        this.birthDay = entity.getBirthDay();
        this.children = entity.getChildren();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Instant getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Instant birthDay) {
        this.birthDay = birthDay;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }
}
