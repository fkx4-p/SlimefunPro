package io.github.thebusybiscuit.slimefun4.core.services.github;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.bukkit.ChatColor;

import io.github.thebusybiscuit.cscorelib2.data.ComputedOptional;

/**
 * Represents a {@link Contributor} who contributed to a GitHub repository.
 *
 * @author TheBusyBiscuit
 * @author Walshy
 * 
 * @see GitHubService
 * 
 */
public class Contributor {

    private static final String PLACEHOLDER_HEAD = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDZiYTYzMzQ0ZjQ5ZGQxYzRmNTQ4OGU5MjZiZjNkOWUyYjI5OTE2YTZjNTBkNjEwYmI0MGE1MjczZGM4YzgyIn19fQ==";

    private final String ghName;
    private final String mcName;
    private final String profileLink;
    private final ConcurrentMap<String, Integer> contributions = new ConcurrentHashMap<>();
    private final ComputedOptional<String> headTexture = ComputedOptional.createNew();

    private boolean locked = false;

    public Contributor(String name, String profile) {
        ghName = profile.substring(profile.lastIndexOf('/') + 1);
        mcName = name;
        profileLink = profile;
    }

    public Contributor(String name) {
        ghName = name;
        mcName = name;
        profileLink = null;
    }

    public void setContribution(String role, int commits) {
        if (!locked || role.startsWith("translator,")) {
            contributions.put(role, commits);
        }
    }

    /**
     * Returns the name of this contributor.
     *
     * @return the name of this contributor
     */
    public String getName() {
        return ghName;
    }

    /**
     * Returns the MC name of the contributor.
     * This may be the same as {@link #getName()}.
     *
     * @return The MC username of this contributor.
     */
    public String getMinecraftName() {
        return mcName;
    }

    /**
     * Returns a link to the GitHub profile of this {@link Contributor}.
     *
     * @return The GitHub profile of this {@link Contributor}
     */
    public String getProfile() {
        return this.profileLink;
    }

    public List<Map.Entry<String, Integer>> getContributions() {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(contributions.entrySet());
        list.sort(Comparator.comparingInt(entry -> -entry.getValue()));
        return list;
    }

    /**
     * This method gives you the amount of contributions this {@link Contributor}
     * has submmited in the name of the given role.
     * 
     * @param role
     *            The role for which to count the contributions.
     * @return The amount of contributions this {@link Contributor} submitted as the given role
     */
    public int getContributions(String role) {
        return contributions.getOrDefault(role, 0);
    }

    /**
     * Returns this Creator's head texture.
     * If no texture could be found, or it hasn't been pulled yet,
     * then it will return a placeholder texture.
     * 
     * @return A Base64-Head Texture
     */
    public String getTexture() {
        if (!headTexture.isComputed() || !headTexture.isPresent()) {
            return PLACEHOLDER_HEAD;
        }
        else {
            return headTexture.get();
        }
    }

    /**
     * This method will return whether this instance of {@link Contributor} has
     * pulled a texture yet.
     * 
     * @return Whether this {@link Contributor} has been assigned a texture yet
     */
    public boolean hasTexture() {
        return headTexture.isComputed();
    }

    public void setTexture(String skin) {
        headTexture.compute(skin);
    }

    public int getTotalContributions() {
        return contributions.values().stream().mapToInt(Integer::intValue).sum();
    }

    public int index() {
        return -getTotalContributions();
    }

    public String getDisplayName() {
        return ChatColor.GRAY + ghName + (!ghName.equals(mcName) ? ChatColor.DARK_GRAY + " (MC: " + mcName + ")" : "");
    }

    public void lock() {
        this.locked = true;
    }
}
