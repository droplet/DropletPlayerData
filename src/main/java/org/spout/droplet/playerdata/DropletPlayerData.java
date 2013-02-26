package org.spout.droplet.playerdata;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.h2.jdbcx.JdbcConnectionPool;
import org.spout.droplet.playerdata.configuration.DatabaseConfiguration;
import org.spout.api.plugin.CommonPlugin;

public class DropletPlayerData extends CommonPlugin {
	private DatabaseConfiguration config;
	private JdbcConnectionPool pool;

	@Override
	public void onLoad() {
		getLogger().info("loaded.");
	}

	@Override
	public void onEnable() {
		config = new DatabaseConfiguration(this.getDataFolder());
		config.load();

		String path;
		try {
			path = getDataFolder().getCanonicalPath();
		} catch (IOException e) {
			path = getDataFolder().getPath();
		}
		final String connUrl = DatabaseConfiguration.CONNECTION_STRING.getString().replaceAll(Pattern.quote("${plugin_data_dir}"), Matcher.quoteReplacement(path));
		final String user = DatabaseConfiguration.USERNAME.getString();
		getLogger().info("Attempting to connect to " + connUrl + ", with user [" + user + "]");

		pool = JdbcConnectionPool.create(connUrl, user, DatabaseConfiguration.PASSWORD.getString());
		pool.setMaxConnections(DatabaseConfiguration.POOL_SIZE.getInt());
		getEngine().getEventManager().registerEvents(new DataEventListener(this), this);

		getLogger().info("enabled.");
	}

	@Override
	public void onDisable() {
		pool.dispose();
		getLogger().info("disabled.");
	}

	public DatabaseConfiguration getConfig() {
		return config;
	}

	public JdbcConnectionPool getConnectionPool() {
		return pool;
	}
}
