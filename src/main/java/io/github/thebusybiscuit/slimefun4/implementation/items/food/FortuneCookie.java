package io.github.thebusybiscuit.slimefun4.implementation.items.food;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.cscorelib2.chat.ChatColors;
import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SimpleSlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemConsumptionHandler;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class FortuneCookie extends SimpleSlimefunItem<ItemConsumptionHandler> {

    public FortuneCookie(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public ItemConsumptionHandler getItemHandler() {
        return (e, p, item) -> {
            List<String> messages = SlimefunPlugin.getLocal().getMessages(p, "messages.fortune-cookie");
            String message = messages.get(ThreadLocalRandom.current().nextInt(messages.size()));

            p.sendMessage(ChatColors.color(message));
        };
    }

}
