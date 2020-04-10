package com.ishland.slimefun.core.cargonet.data;

import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CargoNetFilter {
    private final boolean isWhitelist;
    @Nonnull
    private final List<ItemStack> content;

    public CargoNetFilter(boolean isWhitelist, List<ItemStack> content) {
        this.isWhitelist = isWhitelist;
        this.content = Collections.unmodifiableList(content);
    }

    public boolean isWhitelist() {
        return isWhitelist;
    }

    @Nonnull
    public List<ItemStack> getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CargoNetFilter that = (CargoNetFilter) o;
        return isWhitelist == that.isWhitelist &&
                content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isWhitelist, content);
    }

    public CargoNetFilter intersection(CargoNetFilter that) {
        if (this.isWhitelist == that.isWhitelist) {
            List<ItemStack> thisFilter = new ArrayList<>(this.content);
            if (this.isWhitelist)
                thisFilter.retainAll(that.content);
            else
                thisFilter.addAll(that.content);
            return new CargoNetFilter(this.isWhitelist, thisFilter);
        } else if (!this.isWhitelist) {
            return that.intersection(this);
        } else {
            List<ItemStack> thisFilter = new ArrayList<>(this.content);
            thisFilter.removeAll(that.content);
            return new CargoNetFilter(true, thisFilter);
        }
    }
}
