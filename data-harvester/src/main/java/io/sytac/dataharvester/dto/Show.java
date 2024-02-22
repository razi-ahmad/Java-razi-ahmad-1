package io.sytac.dataharvester.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record Show(
        @NotNull String showId,
        @Nullable String cast,
        @Nullable String country,
        @Nullable String dateAdded,
        @NotNull String description,
        @Nullable String director,
        @Nullable String duration,
        @NotNull String listedIn,
        @Nullable String rating,
        @NotNull Integer releaseYear,
        @NotNull String title,
        @NotNull String type,
        @NotNull String platform) {
    public Show(@Nonnull String showId, @Nullable String cast, @Nullable String country, @Nullable String dateAdded, @Nonnull String description, @Nullable String director, @Nullable String duration, @Nonnull String listedIn, @Nullable String rating, Integer releaseYear, @Nonnull String title, @Nonnull String type, @Nonnull String platform) {
        this.showId = showId;
        this.cast = cast;
        this.country = country;
        this.dateAdded = dateAdded;
        this.description = description;
        this.director = director;
        this.duration = duration;
        this.listedIn = listedIn;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.title = title;
        this.type = type;
        this.platform = platform;
    }

    @Nonnull
    public String toString() {
        return "Show(showId=" + this.showId + ", cast=" + this.cast + ", country=" + this.country + ", dateAdded=" + this.dateAdded + ", description=" + this.description + ", director=" + this.director + ", duration=" + this.duration + ", listedIn=" + this.listedIn + ", rating=" + this.rating + ", releaseYear=" + this.releaseYear + ", title=" + this.title + ", type=" + this.type + ", platform=" + this.platform + ")";
    }
}