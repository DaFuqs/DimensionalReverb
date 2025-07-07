package de.dafuqs.reverb.mixin;

import com.mojang.blaze3d.audio.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin(Channel.class)
public interface SourceAccessor {
	
	@Accessor
	int getPointer();
	
}
