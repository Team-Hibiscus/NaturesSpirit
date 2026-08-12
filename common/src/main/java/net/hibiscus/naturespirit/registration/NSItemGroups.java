package net.hibiscus.naturespirit.registration;

import net.hibiscus.naturespirit.config.NSConfig;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;


public class NSItemGroups {

    public static final NSRegistrar<CreativeModeTab> CREATIVE_MODE_TABS = NSRegistrar.of(Registries.CREATIVE_MODE_TAB);

    public static final NSHolder<CreativeModeTab> NS_ITEM_GROUP = CREATIVE_MODE_TABS.register("ns_item_group",
            () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .icon(() -> new ItemStack(NSBlocks.REDWOOD.getSapling().get()))
                    .title(Component.translatable("itemGroup.natures_spirit.item_group"))
                    .displayItems((pParameters, pOutput) -> {
                        if (NSConfig.creativeTab) {
                            pOutput.acceptAll(NSRegistryHelper.ITEMS.entries().stream().map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).toList());
                        }
                    })
                    .build());

    public static void bootstrap() {
    }
}
