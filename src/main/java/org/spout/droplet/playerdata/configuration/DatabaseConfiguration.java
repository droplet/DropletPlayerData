package org.spout.droplet.playerdata.configuration;

import java.io.File;
import java.util.logging.Level;

import org.spout.api.Spout;
import org.spout.api.exception.ConfigurationException;
import org.spout.api.util.config.ConfigurationHolderConfiguration;
import org.spout.api.util.config.yaml.YamlConfiguration;
import org.spout.api.util.config.ConfigurationHolder;

public class DatabaseConfiguration extends ConfigurationHolderConfiguration {
	public static final ConfigurationHolder CONNECTION_STRING = new ConfigurationHolder("jdbc:h2:${plugin_data_dir}/player_data", "database", "connection-url");
	public static final ConfigurationHolder USERNAME = new ConfigurationHolder("sa", "database", "username");
	public static final ConfigurationHolder PASSWORD = new ConfigurationHolder("sa", "database", "password");
	public static final ConfigurationHolder POOL_SIZE = new ConfigurationHolder("10", "database", "pool-size");

	public DatabaseConfiguration(File dataFolder) {
		super(new YamlConfiguration(new File(dataFolder, "config.yml")));
	}

	@Override
	public void load() {
		try {
			super.load();
			super.save();
		} catch (ConfigurationException e) {
			Spout.getLogger().log(Level.WARNING, "Error loading player-data configuration: ", e);
		}
	}

	@Override
	public void save() {
		try {
			super.save();
		} catch (ConfigurationException e) {
			Spout.getLogger().log(Level.WARNING, "Error saving player-data configuration: ", e);
		}
	}
}
