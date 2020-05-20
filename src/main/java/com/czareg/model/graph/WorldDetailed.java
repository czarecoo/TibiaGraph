package com.czareg.model.graph;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties
public class WorldDetailed {
    WorldInformation world_information;
    List<Player> players_online;

    public WorldDetailed() {
    }

    public WorldDetailed(WorldInformation world_information, List<Player> players_online) {
        this.world_information = world_information;
        this.players_online = players_online;
    }

    public WorldInformation getWorld_information() {
        return world_information;
    }

    public void setWorld_information(WorldInformation world_information) {
        this.world_information = world_information;
    }

    public List<Player> getPlayers_online() {
        return players_online;
    }

    public void setPlayers_online(List<Player> players_online) {
        this.players_online = players_online;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorldDetailed that = (WorldDetailed) o;
        return Objects.equals(world_information, that.world_information) &&
                Objects.equals(players_online, that.players_online);
    }

    @Override
    public int hashCode() {
        return Objects.hash(world_information, players_online);
    }
}