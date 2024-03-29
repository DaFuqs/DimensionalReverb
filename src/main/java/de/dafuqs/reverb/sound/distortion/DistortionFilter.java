package de.dafuqs.reverb.sound.distortion;

import de.dafuqs.reverb.*;
import de.dafuqs.reverb.sound.*;
import net.fabricmc.api.*;
import net.minecraft.client.*;
import net.minecraft.client.sound.*;
import net.minecraft.util.math.*;
import org.apache.logging.log4j.*;
import org.lwjgl.openal.*;

import java.util.*;

@Environment(EnvType.CLIENT)
public class DistortionFilter {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	public static int id = -1;
	public static int slot = -1;
	
	public static void update() {
		try {
			id = EXTEfx.alGenEffects();
			slot = EXTEfx.alGenAuxiliaryEffectSlots();
		} catch (Throwable t) {
			LOGGER.log(Level.WARN, "Error updating distortion filter. No audio devices present?");
		}
	}
	
	public static boolean update(SoundInstance soundInstance, DistortionEffect data) {
		if (id == -1 || slot == -1) {
			update();
		}
		
		MinecraftClient client = MinecraftClient.getInstance();
		
		if (data.isEnabled(client, soundInstance)) {
			EXTEfx.alAuxiliaryEffectSlotf(slot, EXTEfx.AL_EFFECTSLOT_GAIN, 0);
			EXTEfx.alEffecti(id, EXTEfx.AL_EFFECT_TYPE, EXTEfx.AL_EFFECT_DISTORTION);
			EXTEfx.alEffectf(id, EXTEfx.AL_DISTORTION_EDGE, MathHelper.clamp(data.getEdge(client, soundInstance), EXTEfx.AL_DISTORTION_MIN_EDGE, EXTEfx.AL_DISTORTION_MAX_EDGE));
			EXTEfx.alEffectf(id, EXTEfx.AL_DISTORTION_GAIN, MathHelper.clamp(data.getGain(client, soundInstance), EXTEfx.AL_DISTORTION_MIN_GAIN, EXTEfx.AL_DISTORTION_MAX_GAIN));
			EXTEfx.alEffectf(id, EXTEfx.AL_DISTORTION_LOWPASS_CUTOFF, MathHelper.clamp(data.getLowpassCutoff(client, soundInstance), EXTEfx.AL_DISTORTION_MIN_LOWPASS_CUTOFF, EXTEfx.AL_DISTORTION_MAX_LOWPASS_CUTOFF));
			EXTEfx.alEffectf(id, EXTEfx.AL_DISTORTION_EQCENTER, MathHelper.clamp(data.getEQCenter(client, soundInstance), EXTEfx.AL_DISTORTION_MIN_EQCENTER, EXTEfx.AL_DISTORTION_MAX_EQCENTER));
			EXTEfx.alEffectf(id, EXTEfx.AL_DISTORTION_EQBANDWIDTH, MathHelper.clamp(data.getEQBandWidth(client, soundInstance), EXTEfx.AL_DISTORTION_MIN_EQBANDWIDTH, EXTEfx.AL_DISTORTION_MAX_EQBANDWIDTH));
			EXTEfx.alAuxiliaryEffectSloti(slot, EXTEfx.AL_EFFECTSLOT_EFFECT, id);
			EXTEfx.alAuxiliaryEffectSlotf(slot, EXTEfx.AL_EFFECTSLOT_GAIN, 1);
			
			return true;
		}
		return false;
	}
	
	public static void update(SoundInstance soundInstance, int sourceID) {
		MinecraftClient client = MinecraftClient.getInstance();
		
		if (!(client == null || client.world == null)) {
			Optional<SoundEffects> soundEffects = Reverb.SOUND_EFFECTS.getOrEmpty(client.world.getRegistryKey().getValue());
			if (soundEffects.isPresent()) {
				Optional<DistortionEffect> distortion = soundEffects.get().getDistortion();
				if (distortion.isPresent()) {
					if (!distortion.get().shouldIgnore(soundInstance.getId())) {
						for (int i = 0; i < 2; i++) {
							AL11.alSourcei(sourceID, EXTEfx.AL_DIRECT_FILTER, 0);
							AL11.alSource3i(sourceID, EXTEfx.AL_AUXILIARY_SEND_FILTER, update(soundInstance, distortion.get()) ? slot : 0, 0, 0);
							int error = AL11.alGetError();
							if (error == AL11.AL_NO_ERROR) {
								break;
							} else {
								LOGGER.warn("OpenAl Error {}", error);
							}
						}
					}
				}
			}
		}
	}
	
}
