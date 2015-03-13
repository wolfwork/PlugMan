package com.rylinaux.plugman.messaging;

/*
 * #%L
 * PlugMan
 * %%
 * Copyright (C) 2010 - 2014 PlugMan
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import com.rylinaux.plugman.configuration.Configuration;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Manages custom messages.
 *
 * @author rylinaux
 */
public class MessageFormatter {

    /**
     * The configuration file.
     */
    private final Configuration messageConfiguration;

    /**
     * Construct our object.
     *
     * @param plugin the plugin instance
     */
    public MessageFormatter(JavaPlugin plugin) {
        this.messageConfiguration = new Configuration(plugin, "messages.yml");
    }

    /**
     * Returns the formatted version of the message.
     *
     * @param key  the key
     * @param args the args to replace
     * @return the formatted String
     */
    public String format(String key, Object... args) {
        return format(true, key, args);
    }

    /**
     * Returns the formatted version of the message.
     *
     * @param prefix whether to prepend with the plugin's prefix
     * @param key    the key
     * @param args   the args to replace
     * @return the formatted String
     */
    public String format(boolean prefix, String key, Object... args) {
        String message = prefix ? messageConfiguration.get("prefix") + messageConfiguration.get(key) : messageConfiguration.get(key);
        for (int i = 0; i < args.length; i++) {
            message = message.replace("{" + i + "}", String.valueOf(args[i]));
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    /**
     * Returns the message configuration.
     *
     * @return the message configuration.
     */
    public Configuration getMessageConfiguration() {
        return messageConfiguration;
    }

}
