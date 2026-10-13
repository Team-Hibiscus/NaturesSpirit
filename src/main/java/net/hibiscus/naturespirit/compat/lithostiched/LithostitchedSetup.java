package net.hibiscus.naturespirit.compat.lithostiched;

public class LithostitchedSetup {
    public static void registerLithostitchedBiomes() {
        LithostitchedEventHandlers.handleAddRegionsEvent();
        LithostitchedEventHandlers.handleAddBiomeInjectorsEvent();
        LithostitchedEventHandlers.handleAddWorldgenModifiersEvent();
    }
}
