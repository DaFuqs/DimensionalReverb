package de.dafuqs.reverb.sound.reverb;

import com.mojang.serialization.*;
import de.dafuqs.reverb.*;
import net.minecraft.client.*;
import net.minecraft.client.resources.sounds.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.neoforged.neoforge.registries.*;

import java.util.function.*;

/**
 * A Reverb effect controls
 */
public abstract class ReverbEffect {
	
	public static final ResourceKey<Registry<MapCodec<? extends ReverbEffect>>> REVERB_EFFECTS_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(Reverb.MOD_ID, "reverb_effect"));
	public static final Registry<MapCodec<? extends ReverbEffect>> REVERB_EFFECTS = new RegistryBuilder<>(REVERB_EFFECTS_KEY).sync(true).create();
	public static final Codec<ReverbEffect> CODEC = REVERB_EFFECTS.byNameCodec().dispatchStable(ReverbEffect::getCodec, Function.identity());
	
	public abstract MapCodec<? extends ReverbEffect> getCodec();
	
	/**
	 * Whether a Sound Event should be ignored
	 *
	 * @param resourceLocation the Identifier of the Sound Event
	 */
	public abstract boolean shouldIgnore(ResourceLocation resourceLocation);
	
	public abstract boolean isEnabled(Minecraft client, SoundInstance soundInstance);
	
	public abstract float getAirAbsorptionGainHF(Minecraft client, SoundInstance soundInstance);
	
	public abstract float getDecayHFRatio(Minecraft client, SoundInstance soundInstance);
	
	public abstract float getDensity(Minecraft client, SoundInstance soundInstance);
	
	public abstract float getDiffusion(Minecraft client, SoundInstance soundInstance);
	
	public abstract float getGain(Minecraft client, SoundInstance soundInstance);
	
	public abstract float getGainHF(Minecraft client, SoundInstance soundInstance);
	
	public abstract float getLateReverbGainBase(Minecraft client, SoundInstance soundInstance);
	
	public abstract float getDecayTime(Minecraft client, SoundInstance soundInstance);
	
	public abstract float getReflectionsGainBase(Minecraft client, SoundInstance soundInstance);
	
	public abstract int getDecayHFLimit(Minecraft client, SoundInstance soundInstance);
	
	public abstract float getLateReverbDelay(Minecraft client, SoundInstance soundInstance);
	
	public abstract float getReflectionsDelay(Minecraft client, SoundInstance soundInstance);
	
}
