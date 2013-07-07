/*
 * This file is part of DropletPlayerData.
 *
 * Copyright (c) 2013 Spout LLC <http://www.spout.org/>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.spout.droplet.playerdata.configuration;

import java.io.File;
import java.util.logging.Level;

import org.spout.api.Spout;
import org.spout.cereal.config.ConfigurationException;
import org.spout.cereal.config.ConfigurationHolder;
import org.spout.cereal.config.ConfigurationHolderConfiguration;
import org.spout.cereal.config.yaml.YamlConfiguration;

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
