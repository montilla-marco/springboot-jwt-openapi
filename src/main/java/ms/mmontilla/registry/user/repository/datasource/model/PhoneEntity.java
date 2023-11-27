package ms.mmontilla.registry.user.repository.datasource.model;

import javax.persistence.*;

@Entity(name = "phones")
public class PhoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="person_id")
    private PersonEntity person;

    private Integer number;

    private Integer cityCode;

    private Integer countryCode;

    public PhoneEntity() {
    }

    public PhoneEntity(Integer number, Integer cityCode, Integer countryCode) {
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }
}
