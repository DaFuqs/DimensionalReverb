package de.dafuqs.reverb;

import de.dafuqs.reverb.sound.*;
import de.dafuqs.reverb.sound.distortion.*;
import de.dafuqs.reverb.sound.reverb.*;
import net.neoforged.bus.api.*;
import net.neoforged.fml.common.*;
import net.neoforged.neoforge.registries.*;

@Mod(Reverb.MOD_ID)
public class Reverb {
	
	public static final String MOD_ID = "reverb";
	
	public Reverb(IEventBus modBus) {
		modBus.addListener(Reverb::registerRegistries);
	}
	
	public static void registerRegistries(NewRegistryEvent event) {
		event.register(DistortionEffect.DISTORTION_EFFECTS);
		event.register(ReverbEffect.REVERB_EFFECTS);
		event.register(SoundEffects.SOUND_EFFECTS);
	}
	
}
