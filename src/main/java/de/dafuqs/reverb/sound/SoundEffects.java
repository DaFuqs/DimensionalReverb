package de.dafuqs.reverb.sound;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import de.dafuqs.reverb.*;
import de.dafuqs.reverb.sound.distortion.*;
import de.dafuqs.reverb.sound.reverb.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.sounds.*;
import net.neoforged.neoforge.registries.*;

import java.util.*;

@SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "unused"})
public class SoundEffects {
	
	public static final ResourceKey<Registry<SoundEffects>> SOUND_EFFECTS_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(Reverb.MOD_ID, "sound_effects"));
	public static final Registry<SoundEffects> SOUND_EFFECTS = new RegistryBuilder<>(SOUND_EFFECTS_KEY).sync(true).create();
	
	public static final Codec<SoundEffects> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			ReverbEffect.CODEC.optionalFieldOf("reverb").stable().forGetter((soundEffects) -> soundEffects.reverb),
			DistortionEffect.CODEC.optionalFieldOf("distortion").stable().forGetter((soundEffects) -> soundEffects.distortion),
			Music.CODEC.optionalFieldOf("music").stable().forGetter((soundEffects) -> soundEffects.music)
	).apply(instance, instance.stable(SoundEffects::new)));
	
	private final Optional<ReverbEffect> reverb;
	private final Optional<DistortionEffect> distortion;
	private final Optional<Music> music;
	
	public SoundEffects() {
		this(Optional.empty(), Optional.empty(), Optional.empty());
	}
	
	public SoundEffects(Optional<ReverbEffect> reverb, Optional<DistortionEffect> distortion, Optional<Music> music) {
		this.reverb = reverb;
		this.distortion = distortion;
		this.music = music;
	}
	
	public Optional<ReverbEffect> getReverb() {
		return reverb;
	}
	
	public Optional<DistortionEffect> getDistortion() {
		return distortion;
	}
	
	public Optional<Music> getMusic() {
		return music;
	}
	
}
