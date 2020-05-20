package com.czareg.model;

import java.util.Objects;

public class AllWords {
    Worlds worlds;
    Information information;

    public AllWords() {
    }

    public AllWords(Worlds worlds, Information information) {
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
        AllWords allWords = (AllWords) o;
        return Objects.equals(worlds, allWords.worlds) &&
                Objects.equals(information, allWords.information);
    }

    @Override
    public int hashCode() {
        return Objects.hash(worlds, information);
    }
}
