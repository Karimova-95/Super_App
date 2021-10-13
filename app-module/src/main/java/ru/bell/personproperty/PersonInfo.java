package ru.bell.personproperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@ConfigurationProperties(prefix = "person")
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonInfo {

    @NotBlank
//    @Value("${user.name}")
    private String name;

    @Min(value = 18, message = "Возраст должен быть старше 17")
    @Max(value = 50, message = "Возраст должен быть младше 51")
//    @Value("${user.age}")
    private int age;
}
