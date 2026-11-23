package io.miguelsouza.hexagonal.application.core.domain;

public class Customer {

    public Customer(Address address, String cpf, String id, Boolean isValidCpf, String name) {
        this.address = address;
        this.cpf = cpf;
        this.id = id;
        this.isValidCpf = isValidCpf;
        this.name = name;
    }

    public Customer() {
        this.isValidCpf = false;
    }

    private String id;
    private String name;
    private Address address;
    private String cpf;
    private Boolean isValidCpf;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getValidCpf() {
        return isValidCpf;
    }

    public void setValidCpf(Boolean validCpf) {
        isValidCpf = validCpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
