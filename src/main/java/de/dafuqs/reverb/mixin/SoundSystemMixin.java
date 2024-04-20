package de.dafuqs.reverb.mixin;

import de.dafuqs.reverb.sound.distortion.DistortionFilter;
import de.dafuqs.reverb.sound.reverb.ReverbFilter;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.ChannelAccess;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.client.sounds.WeighedSoundEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Mixin(SoundEngine.class)
public abstract class SoundSystemMixin {
	
	@Inject(method = "tickNonPaused", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Options;getSoundSourceVolume(Lnet/minecraft/sounds/SoundSource;)F", shift = Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
	public void reverb$tick(CallbackInfo ci, Iterator<?> iterator, Map.Entry<?, ?> entry, ChannelAccess.ChannelHandle sourceManager, SoundInstance soundInstance) {
		sourceManager.execute(source -> ReverbFilter.update(soundInstance, ((SourceAccessor) source).getSource()));
		sourceManager.execute(source -> DistortionFilter.update(soundInstance, ((SourceAccessor) source).getSource()));
	}
	
	@Inject(method = "play", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/sounds/ChannelAccess$ChannelHandle;execute(Ljava/util/function/Consumer;)V", ordinal = 0, shift = Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
	public void reverb$play(SoundInstance soundInstance, CallbackInfo ci, WeighedSoundEvents weightedSoundSet, ResourceLocation identifier, Sound sound, float f, float g, SoundSource soundCategory, float h, float i, SoundInstance.Attenuation attenuationType, boolean bl, Vec3 vec3d, boolean bl3, boolean bl4, CompletableFuture<?> completableFuture, ChannelAccess.ChannelHandle sourceManager) {
		sourceManager.execute(source -> ReverbFilter.update(soundInstance, ((SourceAccessor) source).getSource()));
		sourceManager.execute(source -> DistortionFilter.update(soundInstance, ((SourceAccessor) source).getSource()));
	}

	@Inject(method = "reload", at = @At("TAIL"))
	public void reverb$reloadSounds(CallbackInfo ci) {
		ReverbFilter.update();
		DistortionFilter.update();
	}
	
}
