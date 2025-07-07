package de.dafuqs.reverb.mixin;

import com.llamalad7.mixinextras.sugar.*;
import de.dafuqs.reverb.sound.distortion.*;
import de.dafuqs.reverb.sound.reverb.*;
import net.minecraft.client.resources.sounds.*;
import net.minecraft.client.sounds.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.At.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(SoundEngine.class)
public abstract class SoundSystemMixin {
	
	@Inject(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/GameOptions;getSoundVolume(Lnet/minecraft/sound/SoundCategory;)F"))
	public void reverb$tick(CallbackInfo ci, @Local ChannelAccess.ChannelHandle sourceManager, @Local SoundInstance soundInstance) {
		sourceManager.execute(source -> ReverbFilter.update(soundInstance, ((SourceAccessor) source).getPointer()));
		sourceManager.execute(source -> DistortionFilter.update(soundInstance, ((SourceAccessor) source).getPointer()));
	}
	
	@Inject(method = "play(Lnet/minecraft/client/resources/sounds/SoundInstance;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/sound/Channel$SourceManager;run(Ljava/util/function/Consumer;)V", ordinal = 0, shift = Shift.AFTER))
	public void reverb$play(SoundInstance soundInstance, CallbackInfo ci, @Local ChannelAccess.ChannelHandle sourceManager) {
		sourceManager.execute(source -> ReverbFilter.update(soundInstance, ((SourceAccessor) source).getPointer()));
		sourceManager.execute(source -> DistortionFilter.update(soundInstance, ((SourceAccessor) source).getPointer()));
	}
	
	@Inject(method = "reload()V", at = @At("TAIL"))
	public void reverb$reloadSounds(CallbackInfo ci) {
		ReverbFilter.update();
		DistortionFilter.update();
	}
	
}
