package ru.panyukovnn.multithreadingmentoring.theory.immutable;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Address {

    private String city;
    private String street;
    private String house;
}
