package com.czareg.model.choicebox;

import com.czareg.model.Information;

import java.util.Objects;

public class GetWorlds {
    Worlds worlds;
    Information information;

    public GetWorlds() {
    }

    public GetWorlds(Worlds worlds, Information information) {
        this.worlds = worlds;
        this.information = information;
    }

    public Worlds getWorlds() {
        return worlds;
    }

    public void setWorlds(Worlds worlds) {
        this.worlds = worlds;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetWorlds getWorlds = (GetWorlds) o;
        return Objects.equals(worlds, getWorlds.worlds) &&
                Objects.equals(information, getWorlds.information);
    }

    @Override
    public int hashCode() {
        return Objects.hash(worlds, information);
    }
}
