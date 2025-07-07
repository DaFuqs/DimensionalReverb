package de.dafuqs.reverb;

import de.dafuqs.reverb.sound.*;
import de.dafuqs.reverb.sound.distortion.*;
import de.dafuqs.reverb.sound.reverb.*;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.event.registry.*;
import net.minecraft.core.Registry;
import net.minecraft.registry.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.*;

public class Reverb implements ModInitializer {
	
	public static final String MOD_ID = "reverb";
	
	public static final ResourceKey<Registry<SoundEffects>> SOUND_EFFECTS_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(MOD_ID, "sound_effects"));
	public static final Registry<SoundEffects> SOUND_EFFECTS = FabricRegistryBuilder.createSimple(SOUND_EFFECTS_KEY).attribute(RegistryAttribute.SYNCED).buildAndRegister();
	
	@Override
	public void onInitialize() {
		Registry.register(ReverbEffect.REVERB_EFFECT_CODEC, ResourceLocation.fromNamespaceAndPath(MOD_ID, "static"), StaticReverbEffect.CODEC);
		Registry.register(DistortionEffect.DISTORTION_EFFECT_CODEC, ResourceLocation.fromNamespaceAndPath(MOD_ID, "static"), StaticDistortionEffect.CODEC);
	}
	
}
