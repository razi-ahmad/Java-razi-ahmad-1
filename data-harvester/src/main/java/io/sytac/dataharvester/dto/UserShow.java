package io.sytac.dataharvester.dto;

public record UserShow(
        String showId,
        String title,
        String platform,
        String firstPersonOfCast,
        String eventTime) {
    public UserShow(String showId, String title, String platform, String firstPersonOfCast, String eventTime) {
        this.showId = showId;
        this.title = title;
        this.platform = platform;
        this.firstPersonOfCast = firstPersonOfCast;
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "UserEvent{" +
                "showId='" + showId + '\'' +
                ", title='" + title + '\'' +
                ", platform='" + platform + '\'' +
                ", firstPersonOfCast='" + firstPersonOfCast + '\'' +
                ", eventTime=" + eventTime +
                '}';
    }
}
