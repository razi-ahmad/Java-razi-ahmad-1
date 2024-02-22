package io.sytac.dataharvester.dto;

import java.util.List;

public record UserData(
        Integer userId,
        String userName,
        Integer userAge,
        List<UserShow> events) {
    public UserData(Integer userId, String userName, Integer userAge, List<UserShow> events) {
        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
        this.events = events;
    }

    @Override
    public String toString() {
        return "UserEvents{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", events=" + events +
                '}';
    }
}
