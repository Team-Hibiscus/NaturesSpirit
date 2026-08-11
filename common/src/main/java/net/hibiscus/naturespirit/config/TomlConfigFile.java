package net.hibiscus.naturespirit.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.toml.TomlFormat;
import net.hibiscus.naturespirit.NaturesSpirit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class TomlConfigFile {

    private final CommentedFileConfig config;

    private TomlConfigFile(CommentedFileConfig config) {
        this.config = config;
    }

    public static TomlConfigFile openOrCreate(Path path) {
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            throw new IllegalStateException("Could not create config directory " + path.getParent(), e);
        }
        CommentedFileConfig config = CommentedFileConfig.builder(path, TomlFormat.instance())
                .sync()
                .preserveInsertionOrder()
                .build();
        config.load();
        return new TomlConfigFile(config);
    }

    public boolean define(String key, boolean defaultValue) {
        return define(key, defaultValue, null);
    }

    public boolean define(String key, boolean defaultValue, String comment) {
        Object stored = config.get(key);
        boolean value = defaultValue;
        if (stored instanceof Boolean bool) {
            value = bool;
        } else if (stored != null) {
            warnInvalid(key, stored, defaultValue);
        }
        write(key, value, comment);
        return value;
    }

    public int defineInRange(String key, int defaultValue, int min, int max) {
        return defineInRange(key, defaultValue, min, max, null);
    }

    public int defineInRange(String key, int defaultValue, int min, int max, String comment) {
        Object stored = config.get(key);
        int value = defaultValue;
        if (stored instanceof Number number) {
            value = number.intValue();
            if (value < min || value > max) {
                warnInvalid(key, stored, defaultValue);
                value = defaultValue;
            }
        } else if (stored != null) {
            warnInvalid(key, stored, defaultValue);
        }
        write(key, value, comment);
        return value;
    }

    public void save() {
        config.save();
        config.close();
    }

    private void write(String key, Object value, String comment) {
        config.set(key, value);
        if (comment != null) {
            config.setComment(key, comment);
        }
    }

    private static void warnInvalid(String key, Object stored, Object defaultValue) {
        NaturesSpirit.LOG.warn("Invalid value {} for config key {}, falling back to {}", stored, key, defaultValue);
    }
}
