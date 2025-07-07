package de.dafuqs.reverb.mixin;

import de.dafuqs.reverb.sound.*;
import net.minecraft.client.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.client.player.*;
import net.minecraft.sounds.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.*;

@Mixin(Minecraft.class)
public class MinecraftClientMixin {
	
	@Shadow
	public LocalPlayer player;
	
	@Shadow
	public ClientLevel level;
	
	@Inject(method = "getSituationalMusic()Lnet/minecraft/sounds/Music;", at = @At("HEAD"), cancellable = true)
	private void reverb$getMusicType(CallbackInfoReturnable<Music> ci) {
		if (this.player != null) {
			Optional<SoundEffects> soundEffects = SoundEffects.SOUND_EFFECTS.getOptional(level.dimension().location());
			if (soundEffects.isPresent()) {
				Optional<Music> musicSound = soundEffects.get().getMusic();
				musicSound.ifPresent(ci::setReturnValue);
			}
		}
	}
	
}
