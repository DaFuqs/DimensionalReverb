package de.dafuqs.reverb.mixin;

import de.dafuqs.reverb.*;
import de.dafuqs.reverb.sound.*;
import net.minecraft.client.*;
import net.minecraft.client.network.*;
import net.minecraft.client.sound.*;
import net.minecraft.client.world.*;
import net.minecraft.sound.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.*;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
	
	@Shadow
	public ClientPlayerEntity player;
	
	@Shadow
	public ClientWorld world;
	
	@Inject(method = "getMusicInstance()Lnet/minecraft/client/sound/MusicInstance;", at = @At("HEAD"), cancellable = true)
	private void reverb$getMusicType(CallbackInfoReturnable<MusicInstance> ci) {
		if (this.player != null) {
			Optional<SoundEffects> soundEffects = Reverb.SOUND_EFFECTS.getOptionalValue(world.getRegistryKey().getValue());
			if (soundEffects.isPresent()) {
				Optional<MusicSound> musicSound = soundEffects.get().getMusic();
				musicSound.ifPresent(sound -> ci.setReturnValue(new MusicInstance(sound, 1.0F)));
			}
		}
	}
	
}
