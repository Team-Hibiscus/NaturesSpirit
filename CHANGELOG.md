# Changelog

## 2.3.0+26.1

### Multiloader / NeoForge
- Fixed NeoForge compile on NeoForge `26.1.0.19-beta` (MC 26.1): cauldron interactions register in common setup instead of `RegisterCauldronInteractionEvent` (that event exists only from NeoForge `26.1.1.8-beta` onward).
- Added package-local `NSCauldronRegistration` bridge + access transformer entry for `CauldronInteraction.Dispatcher#put`.

### Worldgen / Terralith
- Fixed meadow vegetal feature order in builtin `modified_mountain_biomes` so Lithostitched + Terralith no longer hit a Feature order cycle (`trees_meadow` before `patch_grass_plain`).
- Softened snowy biome borders (Lithostitched region noise / density, Terralith `scarlet_mountains` + `gravel_desert` temps, NS snow biome temps).
- Optional Terralith plant sprinkle via Lithostitched modifiers (unique feature IDs, only when Terralith is loaded).

### Content / polish
- Pizza model `#missing` texture fix; azolla/helvola blockstate cleanup; Iris marigold mapping.
- Tundra `has_precipitation` enabled.
- Config comments / Explorer-friendly region weight defaults (90/90/100/100/90).
