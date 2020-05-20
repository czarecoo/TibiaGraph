package com.czareg.model;

import java.util.Objects;

public class World {
    String name;
    int online;
    String location;
    String worldtype;
    String additional;

    public World() {
    }

    public World(String name, int online, String location, String worldtype, String additional) {
        this.name = name;
        this.online = online;
        this.location = location;
        this.worldtype = worldtype;
        this.additional = additional;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWorldtype() {
        return worldtype;
    }

    public void setWorldtype(String worldtype) {
        this.worldtype = worldtype;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        World world = (World) o;
        return online == world.online &&
                Objects.equals(name, world.name) &&
                Objects.equals(location, world.location) &&
                Objects.equals(worldtype, world.worldtype) &&
                Objects.equals(additional, world.additional);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, online, location, worldtype, additional);
    }

    @Override
    public String toString() {
        return "World{" +
                "name='" + name + '\'' +
                '}';
    }
}
