package de.dafuqs.reverb.sound.distortion;

import com.mojang.serialization.*;
import de.dafuqs.reverb.*;
import net.fabricmc.fabric.api.event.registry.*;
import net.minecraft.client.*;
import net.minecraft.client.sound.*;
import net.minecraft.registry.*;
import net.minecraft.util.*;

import java.util.function.*;

/**
 * A Distortion effect controls
 */
public abstract class DistortionEffect {
	
	public static final RegistryKey<Registry<MapCodec<? extends DistortionEffect>>> DISTORTION_EFFECT_CODEC_KEY = RegistryKey.ofRegistry(Identifier.of(Reverb.MOD_ID, "distortion_effect"));
	public static final Registry<MapCodec<? extends DistortionEffect>> DISTORTION_EFFECT_CODEC = FabricRegistryBuilder.createSimple(DISTORTION_EFFECT_CODEC_KEY).attribute(RegistryAttribute.SYNCED).buildAndRegister();
	public static final Codec<DistortionEffect> CODEC = DISTORTION_EFFECT_CODEC.getCodec().dispatchStable(DistortionEffect::getCodec, Function.identity());
	
	public abstract MapCodec<? extends DistortionEffect> getCodec();
	
	/**
	 * Whether a Sound Event should be ignored
	 *
	 * @param identifier the Identifier of the Sound Event
	 */
	public abstract boolean shouldIgnore(Identifier identifier);
	
	public abstract boolean isEnabled(MinecraftClient client, SoundInstance soundInstance);
	
	public abstract float getEdge(MinecraftClient client, SoundInstance soundInstance);
	
	public abstract float getGain(MinecraftClient client, SoundInstance soundInstance);
	
	public abstract float getLowpassCutoff(MinecraftClient client, SoundInstance soundInstance);
	
	public abstract float getEQCenter(MinecraftClient client, SoundInstance soundInstance);
	
	public abstract float getEQBandWidth(MinecraftClient client, SoundInstance soundInstance);
	
}
