package net.hibiscus.naturespirit.blocks.block_entities;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.hibiscus.naturespirit.NatureSpirit;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;

public record PizzaToppingVariant(
        int hunger, float saturation, ResourceLocation itemId, ResourceLocation texturePath, String translationKey
) {

    public static final Codec<PizzaToppingVariant> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
                    ExtraCodecs.intRange(0, 20).fieldOf("hunger").forGetter(PizzaToppingVariant::hunger),
                    ExtraCodecs.POSITIVE_FLOAT.fieldOf("saturation").forGetter(PizzaToppingVariant::saturation),
                    ResourceLocation.CODEC.fieldOf("item").forGetter(PizzaToppingVariant::itemId),
                    ResourceLocation.CODEC.fieldOf("texture_path").forGetter(PizzaToppingVariant::texturePath),
                    ExtraCodecs.NON_EMPTY_STRING.fieldOf("translation_key").forGetter(PizzaToppingVariant::translationKey)
            ).apply(instance, PizzaToppingVariant::new)
    );
    public static final StreamCodec<ByteBuf, PizzaToppingVariant> PACKET_CODEC;
    public static final Codec<Holder<PizzaToppingVariant>> ENTRY_CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<PizzaToppingVariant>> ENTRY_PACKET_CODEC;

    public PizzaToppingVariant(int hunger, float saturation, ResourceLocation itemId, ResourceLocation texturePath, String translationKey) {
        this.hunger = hunger;
        this.saturation = saturation;
        this.itemId = itemId;
        this.texturePath = texturePath;
        this.translationKey = translationKey;
    }

    public int hunger() {
        return this.hunger;
    }

    public float saturation() {
        return this.saturation;
    }

    public ResourceLocation texturePath() {
        return this.texturePath;
    }

    public ResourceLocation itemId() {
        return this.itemId;
    }

    static {
        PACKET_CODEC = StreamCodec.composite(ByteBufCodecs.VAR_INT, PizzaToppingVariant::hunger, ByteBufCodecs.FLOAT, PizzaToppingVariant::saturation, ResourceLocation.STREAM_CODEC,
                PizzaToppingVariant::itemId, ResourceLocation.STREAM_CODEC, PizzaToppingVariant::texturePath, ByteBufCodecs.STRING_UTF8, PizzaToppingVariant::translationKey, PizzaToppingVariant::new);
        ENTRY_CODEC = RegistryFileCodec.create(NatureSpirit.PIZZA_TOPPING_VARIANT, CODEC);
        ENTRY_PACKET_CODEC = ByteBufCodecs.holder(NatureSpirit.PIZZA_TOPPING_VARIANT, PACKET_CODEC);
    }
}
