package com.nisum.crudapi.h2.Prueba.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "phones")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String number;
    private String citycode;
    private String countrycode;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


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

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public static Builder builder(){
        return new Builder();
    }

    public static final class Builder {

        private final Phone phone;

        public Builder() { phone = new Phone();}

        public Builder setId(Long id) {
            phone.setId(id);
            return this;
        }

        public Builder setNumber(String number) {
            phone.setNumber(number);
            return this;
        }

        public Builder setCitycode(String citycode) {
            phone.setCitycode(citycode);
            return this;
        }

        public Builder setCountrycode(String countrycode) {
            phone.setCitycode(countrycode);
            return this;
        }

        public Phone build() {
            return phone;
        }
    }


}
