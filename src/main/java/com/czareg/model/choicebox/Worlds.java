package com.czareg.model.choicebox;

import java.util.List;
import java.util.Objects;

public class Worlds {
    int online;
    List<World> allworlds;

    public Worlds() {
    }

    public Worlds(int online, List<World> allworlds) {
        this.online = online;
        this.allworlds = allworlds;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public List<World> getAllworlds() {
        return allworlds;
    }

    public void setAllworlds(List<World> allworlds) {
        this.allworlds = allworlds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worlds worlds = (Worlds) o;
        return online == worlds.online &&
                Objects.equals(allworlds, worlds.allworlds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(online, allworlds);
    }
}
