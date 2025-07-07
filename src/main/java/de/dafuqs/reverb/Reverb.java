package de.dafuqs.reverb;

import de.dafuqs.reverb.sound.*;
import de.dafuqs.reverb.sound.distortion.*;
import de.dafuqs.reverb.sound.reverb.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.neoforged.bus.api.*;
import net.neoforged.fml.common.*;
import net.neoforged.neoforge.registries.*;

@Mod(Reverb.MOD_ID)
public class Reverb {
	
	public static final String MOD_ID = "reverb";
	
	public static final ResourceKey<Registry<SoundEffects>> SOUND_EFFECTS_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(MOD_ID, "sound_effects"));
	public static final Registry<SoundEffects> SOUND_EFFECTS = new RegistryBuilder<>(SOUND_EFFECTS_KEY).sync(true).create();
	
	public Reverb(IEventBus modBus) {
		Registry.register(ReverbEffect.REVERB_EFFECT_CODEC, ResourceLocation.fromNamespaceAndPath(MOD_ID, "static"), StaticReverbEffect.CODEC);
		Registry.register(DistortionEffect.DISTORTION_EFFECT_CODEC, ResourceLocation.fromNamespaceAndPath(MOD_ID, "static"), StaticDistortionEffect.CODEC);
	}
	
}
