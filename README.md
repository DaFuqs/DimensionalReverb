A small fabric library able to modify sound reverb, distortion or set a fixed background music for dimensions.

# Adding Dimensional Sound Settings
Register your dimension identifier to the Reverb.SOUND_EFFECTS registry.

This would add some reverb to the vanilla overworld:
```java
Registry.register(Reverb.SOUND_EFFECTS, Identifier.ofVanilla("overworld"), 
        new SoundEffects(Optional.of(new StaticReverbEffect.Builder().setDecayTime(8.0F).setDensity(0.5F).build()),
        Optional.empty(),
        Optional.empty())
);
```

# Origin & Thanks
A good chunk of code originates from LudoCrypts' Liminal Library at: https://github.com/LudoCrypt/Liminal-Library/
Since that has recently moved to using the Quilt modloader, this lib serves as a minimalistic and non-intrusive way to make it's dimensional sounds features available on fabric.

Thanks a lot to LudoCrypt for creating such a sleek and easy to use system!