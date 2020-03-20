package io.github.thebusybiscuit.slimefun4.core.services.github;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import me.mrCookieSlime.Slimefun.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.api.Slimefun;

abstract class GitHubConnector {

    protected File file;
    protected final GitHubService github;

    public GitHubConnector(GitHubService github) {
        this.github = github;
    }

    public abstract String getFileName();

    public abstract String getRepository();

    public abstract String getURLSuffix();

    public abstract void onSuccess(JsonElement element);

    public void onFailure() {
        // Don't do anything by default
    }

    public void pullFile() {
        file = new File("plugins/Slimefun/cache/github/" + getFileName() + ".json");

        if (github.isLoggingEnabled()) {
            Slimefun.getLogger().log(Level.INFO, "Retrieving {0}.json from GitHub...", this.getFileName());
        }

        try {
            URL website = new URL("https://api.github.com/repos/" + this.getRepository() + this.getURLSuffix());

            URLConnection connection = website.openConnection();
            connection.setConnectTimeout(3000);
            connection.addRequestProperty("User-Agent", "Slimefun 4 GitHub Agent (by TheBusyBiscuit)");
            connection.setDoOutput(true);

            try (ReadableByteChannel channel = Channels.newChannel(connection.getInputStream())) {
                try (FileOutputStream stream = new FileOutputStream(file)) {
                    stream.getChannel().transferFrom(channel, 0, Long.MAX_VALUE);
                    parseData();
                }
            }
        }
        catch (IOException e) {
            if (github.isLoggingEnabled()) {
                Slimefun.getLogger().log(Level.WARNING, "Could not connect to GitHub in time.");
            }

            if (hasData()) {
                parseData();
            }
            else {
                onFailure();
            }
        }
    }

    public boolean hasData() {
        return getFile().exists();
    }

    public File getFile() {
        return file;
    }

    public void parseData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(getFile()))) {
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            JsonElement element = new JsonParser().parse(builder.toString());
            onSuccess(element);
        }
        catch (IOException x) {
            Slimefun.getLogger().log(Level.SEVERE, x, () -> "An Error occured while parsing GitHub-Data for Slimefun " + SlimefunPlugin.getVersion());
            onFailure();
        }
    }
}
