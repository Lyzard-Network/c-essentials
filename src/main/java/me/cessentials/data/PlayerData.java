package me.cessentials.data;

import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerData {
    private UUID uuid;
    private int kills;
    private int deaths;
    private boolean flight;

    public PlayerData(UUID uuid) {
        kills = 0;
        deaths = 0;
        flight = false;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public boolean isFlight() {
        return flight;
    }

    public void setFlight(boolean flight) {
        this.flight = flight;
    }
}
