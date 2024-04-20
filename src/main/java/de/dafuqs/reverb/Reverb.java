package de.dafuqs.reverb;

import de.dafuqs.reverb.sound.SoundEffects;
import de.dafuqs.reverb.sound.distortion.DistortionEffect;
import de.dafuqs.reverb.sound.distortion.DistortionFilter;
import de.dafuqs.reverb.sound.distortion.StaticDistortionEffect;
import de.dafuqs.reverb.sound.reverb.ReverbEffect;
import de.dafuqs.reverb.sound.reverb.ReverbFilter;
import de.dafuqs.reverb.sound.reverb.StaticReverbEffect;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.sound.SoundEngineLoadEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

@Mod(Reverb.MOD_ID)
public class Reverb {
	public Reverb(IEventBus modBus) {
		modBus.register(this);
	}

	public static final String MOD_ID = "reverb";

	public static final ResourceKey<Registry<SoundEffects>> SOUND_EFFECTS_KEY = ResourceKey.createRegistryKey(new ResourceLocation(MOD_ID, "sound_effects"));

	public static final Registry<SoundEffects> SOUND_EFFECTS = new RegistryBuilder<>(SOUND_EFFECTS_KEY).sync(true).create();;

	@SubscribeEvent
	public void register(RegisterEvent e) {
		e.register(ReverbEffect.REVERB_EFFECT_CODEC_KEY, helper -> helper.register(new ResourceLocation(MOD_ID, "static"), StaticReverbEffect.CODEC));
		e.register(DistortionEffect.DISTORTION_EFFECT_CODEC_KEY, helper -> helper.register(new ResourceLocation(MOD_ID, "static"), StaticDistortionEffect.CODEC));
	}

	@SubscribeEvent
	public void createNewRegistries(NewRegistryEvent e) {
		e.register(ReverbEffect.REVERB_EFFECT_CODEC);
		e.register(DistortionEffect.DISTORTION_EFFECT_CODEC);
		e.register(SOUND_EFFECTS);
	}
}
