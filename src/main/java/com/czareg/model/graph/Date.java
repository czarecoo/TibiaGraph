package com.czareg.model.graph;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties
public class Date {
    String date;
    String timezone_type;
    String timezone;

    public Date() {
    }

    public Date(String date, String timezone_type, String timezone) {
        this.date = date;
        this.timezone_type = timezone_type;
        this.timezone = timezone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date1 = (Date) o;
        return Objects.equals(date, date1.date) &&
                Objects.equals(timezone_type, date1.timezone_type) &&
                Objects.equals(timezone, date1.timezone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, timezone_type, timezone);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimezone_type() {
        return timezone_type;
    }

    public void setTimezone_type(String timezone_type) {
        this.timezone_type = timezone_type;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
