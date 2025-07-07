package de.dafuqs.reverb.mixin;

import de.dafuqs.reverb.*;
import de.dafuqs.reverb.sound.*;
import net.minecraft.client.*;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.network.*;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.world.*;
import net.minecraft.sound.*;
import net.minecraft.sounds.Music;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.*;

@Mixin(Minecraft.class)
public class MinecraftClientMixin {
	
	@Shadow
	public LocalPlayer player;
	
	@Shadow
	public ClientLevel world;
	
	@Inject(method = "getMusicType", at = @At("HEAD"), cancellable = true)
	private void reverb$getMusicType(CallbackInfoReturnable<Music> ci) {
		if (this.player != null) {
			Optional<SoundEffects> soundEffects = Reverb.SOUND_EFFECTS.getOptional(world.dimension().location());
			if (soundEffects.isPresent()) {
				Optional<Music> musicSound = soundEffects.get().getMusic();
				musicSound.ifPresent(ci::setReturnValue);
			}
		}
	}
	
}
