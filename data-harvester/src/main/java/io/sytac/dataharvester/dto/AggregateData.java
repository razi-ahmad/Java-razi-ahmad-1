package io.sytac.dataharvester.dto;

import java.util.List;

public record AggregateData(
        Long totalTimeOfStreams,
        Long countOfReleases,
        List<UserData> usersData) {
    public AggregateData(Long totalTimeOfStreams, Long countOfReleases, List<UserData> usersData) {
        this.totalTimeOfStreams = totalTimeOfStreams;
        this.countOfReleases = countOfReleases;
        this.usersData = usersData;
    }

    @Override
    public String toString() {
        return "AggregateData{" +
                "totalTimeOfStreams=" + totalTimeOfStreams +
                ", countOfReleases=" + countOfReleases +
                ", usersData=" + usersData +
                '}';
    }
}
