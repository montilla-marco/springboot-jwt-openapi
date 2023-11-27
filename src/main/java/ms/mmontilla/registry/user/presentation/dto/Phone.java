package ms.mmontilla.registry.user.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Phone {
    @JsonProperty("number")
    @Min(6)
    private Integer number = null;

    @JsonProperty("citycode")
    @NotNull
    @Min(1)
    private Integer cityCode = null;

    @JsonProperty("contrycode")
    @NotNull
    @Min(1)
    private Integer countryCode = null;

    public Phone() {
    }

    public Phone(Integer number, Integer cityCode, Integer countryCode) {
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }

    @Schema(description = "")
    @NotNull
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }


    @Schema(description = "")
    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    @Schema(description = "")
    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

}

