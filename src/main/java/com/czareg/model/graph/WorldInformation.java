package com.czareg.model.graph;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties
public class WorldInformation {
    String name;
    int players_online;
    OnlineRecord online_record;
    String creation_date;
    String location;
    String pvp_type;
    String transfer_type;
    List<String> world_quest_titles;
    String battleye_status;
    @JsonProperty("Game World Type:")
    String gameWordType;
    String premium_type;
    @JsonProperty("Tournament World Type:")
    String tournamentWorldType;

    public WorldInformation() {
    }

    public WorldInformation(String name, int players_online, OnlineRecord online_record, String creation_date, String location, String pvp_type, String transfer_type, List<String> world_quest_titles, String battleye_status, String gameWordType, String premium_type, String tournamentWorldType) {
        this.name = name;
        this.players_online = players_online;
        this.online_record = online_record;
        this.creation_date = creation_date;
        this.location = location;
        this.pvp_type = pvp_type;
        this.transfer_type = transfer_type;
        this.world_quest_titles = world_quest_titles;
        this.battleye_status = battleye_status;
        this.gameWordType = gameWordType;
        this.premium_type = premium_type;
        this.tournamentWorldType = tournamentWorldType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorldInformation that = (WorldInformation) o;
        return players_online == that.players_online &&
                Objects.equals(name, that.name) &&
                Objects.equals(online_record, that.online_record) &&
                Objects.equals(creation_date, that.creation_date) &&
                Objects.equals(location, that.location) &&
                Objects.equals(pvp_type, that.pvp_type) &&
                Objects.equals(transfer_type, that.transfer_type) &&
                Objects.equals(world_quest_titles, that.world_quest_titles) &&
                Objects.equals(battleye_status, that.battleye_status) &&
                Objects.equals(gameWordType, that.gameWordType) &&
                Objects.equals(premium_type, that.premium_type) &&
                Objects.equals(tournamentWorldType, that.tournamentWorldType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, players_online, online_record, creation_date, location, pvp_type, transfer_type, world_quest_titles, battleye_status, gameWordType, premium_type, tournamentWorldType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayers_online() {
        return players_online;
    }

    public void setPlayers_online(int players_online) {
        this.players_online = players_online;
    }

    public OnlineRecord getOnline_record() {
        return online_record;
    }

    public void setOnline_record(OnlineRecord online_record) {
        this.online_record = online_record;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPvp_type() {
        return pvp_type;
    }

    public void setPvp_type(String pvp_type) {
        this.pvp_type = pvp_type;
    }

    public String getTransfer_type() {
        return transfer_type;
    }

    public void setTransfer_type(String transfer_type) {
        this.transfer_type = transfer_type;
    }

    public List<String> getWorld_quest_titles() {
        return world_quest_titles;
    }

    public void setWorld_quest_titles(List<String> world_quest_titles) {
        this.world_quest_titles = world_quest_titles;
    }

    public String getBattleye_status() {
        return battleye_status;
    }

    public void setBattleye_status(String battleye_status) {
        this.battleye_status = battleye_status;
    }

    public String getGameWordType() {
        return gameWordType;
    }

    public void setGameWordType(String gameWordType) {
        this.gameWordType = gameWordType;
    }

    public String getPremium_type() {
        return premium_type;
    }

    public void setPremium_type(String premium_type) {
        this.premium_type = premium_type;
    }

    public String getTournamentWorldType() {
        return tournamentWorldType;
    }

    public void setTournamentWorldType(String tournamentWorldType) {
        this.tournamentWorldType = tournamentWorldType;
    }
}