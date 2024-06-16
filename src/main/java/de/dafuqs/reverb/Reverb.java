package de.dafuqs.reverb;

import de.dafuqs.reverb.sound.*;
import de.dafuqs.reverb.sound.distortion.*;
import de.dafuqs.reverb.sound.reverb.*;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.event.registry.*;
import net.minecraft.registry.*;
import net.minecraft.util.*;

public class Reverb implements ModInitializer {
	
	public static final String MOD_ID = "reverb";
	
	public static final RegistryKey<Registry<SoundEffects>> SOUND_EFFECTS_KEY = RegistryKey.ofRegistry(Identifier.of(MOD_ID, "sound_effects"));
	public static final Registry<SoundEffects> SOUND_EFFECTS = FabricRegistryBuilder.createSimple(SOUND_EFFECTS_KEY).attribute(RegistryAttribute.SYNCED).buildAndRegister();
	
	@Override
	public void onInitialize() {
		Registry.register(ReverbEffect.REVERB_EFFECT_CODEC, Identifier.of(MOD_ID, "static"), StaticReverbEffect.CODEC);
		Registry.register(DistortionEffect.DISTORTION_EFFECT_CODEC, Identifier.of(MOD_ID, "static"), StaticDistortionEffect.CODEC);
	}
	
}
