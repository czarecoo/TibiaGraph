package com.czareg.model;

import java.util.Objects;

public class Information {
    String api_version;
    long execution_time;
    String last_updated;
    String timestamp;

    public Information() {
    }

    public Information(String api_version, long execution_time, String last_updated, String timestamp) {
        this.api_version = api_version;
        this.execution_time = execution_time;
        this.last_updated = last_updated;
        this.timestamp = timestamp;
    }

    public String getApi_version() {
        return api_version;
    }

    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }

    public long getExecution_time() {
        return execution_time;
    }

    public void setExecution_time(long execution_time) {
        this.execution_time = execution_time;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Information that = (Information) o;
        return execution_time == that.execution_time &&
                Objects.equals(api_version, that.api_version) &&
                Objects.equals(last_updated, that.last_updated) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(api_version, execution_time, last_updated, timestamp);
    }
}
