package io.sytac.dataharvester.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record User(@NotNull Integer id,
                   @NotNull String dateOfBirth,
                   @NotNull String email,
                   @Nullable String firstName,
                   @Nullable String gender,
                   @Nullable String ipAddress,
                   @NotNull String country,
                   @NotNull String lastName) {
    public User(Integer id, @Nonnull String dateOfBirth, @Nonnull String email, @Nullable String firstName, @Nullable String gender, @Nullable String ipAddress, @Nonnull String country, @Nonnull String lastName) {
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.firstName = firstName;
        this.gender = gender;
        this.ipAddress = ipAddress;
        this.country = country;
        this.lastName = lastName;
    }

    public String toString() {
        return "User(id=" + this.id + ", dateOfBirth=" + this.dateOfBirth + ", email=" + this.email + ", firstName=" + this.firstName + ", gender=" + this.gender + ", ipAddress=" + this.ipAddress + ", country=" + this.country + ", lastName=" + this.lastName + ")";
    }

}