package com.ishland.slimefun.core.cargonet.data;

import org.bukkit.Location;

import javax.annotation.Nonnull;
import java.util.Objects;

public class CargoNetRoute {
    @Nonnull
    public final Location from;
    @Nonnull
    public final Location to;
    @Nonnull
    public final CargoNetFilter filter;

    public CargoNetRoute(@Nonnull Location from, @Nonnull Location to, @Nonnull CargoNetFilter filter) {
        this.from = from;
        this.to = to;
        this.filter = filter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CargoNetRoute that = (CargoNetRoute) o;
        return from.equals(that.from) &&
                to.equals(that.to) &&
                filter.equals(that.filter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, filter);
    }
}
