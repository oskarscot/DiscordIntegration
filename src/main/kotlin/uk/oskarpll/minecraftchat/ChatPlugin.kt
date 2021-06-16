package uk.oskarpll.minecraftchat

import club.minnced.discord.webhook.WebhookClient
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class ChatPlugin : JavaPlugin() {

    lateinit var config: PluginConfig
    lateinit var webhookClient: WebhookClient

    override fun onEnable() {
        config = ConfigLoader.load(dataFolder, PluginConfig::class, "config")
        webhookClient = WebhookClient.withUrl(config.webhookUrl)
        Bukkit.getPluginManager().registerEvents(ChatListener(this), this)
    }

    override fun onDisable() {
        webhookClient.close()
    }
}