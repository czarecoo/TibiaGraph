package com.czareg.model.graph;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties
public class Player {
    String name;
    int level;
    String vocation;

    public Player() {
    }

    public Player(String name, int level, String vocation) {
        this.name = name;
        this.level = level;
        this.vocation = vocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getVocation() {
        return vocation;
    }

    public void setVocation(String vocation) {
        this.vocation = vocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return level == player.level &&
                Objects.equals(name, player.name) &&
                Objects.equals(vocation, player.vocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level, vocation);
    }
}
