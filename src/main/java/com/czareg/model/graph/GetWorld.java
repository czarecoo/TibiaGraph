package com.czareg.model.graph;

import com.czareg.model.Information;

import java.util.Objects;

public class GetWorld {
    WorldDetailed world;
    Information information;

    public GetWorld() {

    }

    public GetWorld(WorldDetailed world, Information information) {
        this.world = world;
        this.information = information;
    }

    public WorldDetailed getWorld() {
        return world;
    }

    public void setWorld(WorldDetailed world) {
        this.world = world;
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
        GetWorld getWorld = (GetWorld) o;
        return Objects.equals(world, getWorld.world) &&
                Objects.equals(information, getWorld.information);
    }

    @Override
    public int hashCode() {
        return Objects.hash(world, information);
    }
}
