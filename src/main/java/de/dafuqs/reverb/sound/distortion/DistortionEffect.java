package de.dafuqs.reverb.sound.distortion;

import com.mojang.serialization.*;
import de.dafuqs.reverb.*;
import net.minecraft.client.*;
import net.minecraft.client.resources.sounds.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.neoforged.neoforge.registries.*;

import java.util.function.*;

/**
 * A Distortion effect controls
 */
public abstract class DistortionEffect {
	
	public static final ResourceKey<Registry<MapCodec<? extends DistortionEffect>>> DISTORTION_EFFECTS_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(Reverb.MOD_ID, "distortion_effect"));
	public static final Registry<MapCodec<? extends DistortionEffect>> DISTORTION_EFFECTS = new RegistryBuilder<>(DISTORTION_EFFECTS_KEY).sync(true).create();
	public static final Codec<DistortionEffect> CODEC = DISTORTION_EFFECTS.byNameCodec().dispatchStable(DistortionEffect::getCodec, Function.identity());
	
	public abstract MapCodec<? extends DistortionEffect> getCodec();
	
	/**
	 * Whether a Sound Event should be ignored
	 *
	 * @param identifier the Identifier of the Sound Event
	 */
	public abstract boolean shouldIgnore(ResourceLocation identifier);
	
	public abstract boolean isEnabled(Minecraft client, SoundInstance soundInstance);
	
	public abstract float getEdge(Minecraft client, SoundInstance soundInstance);
	
	public abstract float getGain(Minecraft client, SoundInstance soundInstance);
	
	public abstract float getLowpassCutoff(Minecraft client, SoundInstance soundInstance);
	
	public abstract float getEQCenter(Minecraft client, SoundInstance soundInstance);
	
	public abstract float getEQBandWidth(Minecraft client, SoundInstance soundInstance);
	
}
