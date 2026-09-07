package net.minecraft.core.cauldron;

import net.minecraft.world.item.Item;

/**
 * Package-local bridge for NeoForge 26.1.0.x, which lacks RegisterCauldronInteractionEvent
 * (added in 26.1.1.8-beta) and keeps {@link CauldronInteraction.Dispatcher#put(Item, CauldronInteraction)}
 * package-private. Lives in the vanilla package so registration can call put without waiting on AT cache.
 */
public final class NSCauldronRegistration {

    private NSCauldronRegistration() {
    }

    public static void put(CauldronInteraction.Dispatcher dispatcher, Item item, CauldronInteraction interaction) {
        dispatcher.put(item, interaction);
    }
}
