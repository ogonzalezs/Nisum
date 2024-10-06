package com.nisum.crudapi.h2.Prueba.dto;

import java.util.List;

public class UserRequest {
    private String name;
    private String email;
    private String password;
    private List<PhoneRequest> phones;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PhoneRequest> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneRequest> phones) {
        this.phones = phones;
    }

    public static class PhoneRequest {
        private String number;
        private String citycode;
        private String contrycode;

        // Getters y Setters

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getCitycode() {
            return citycode;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        public String getContrycode() {
            return contrycode;
        }

        public void setContrycode(String contrycode) {
            this.contrycode = contrycode;
        }
    }
}