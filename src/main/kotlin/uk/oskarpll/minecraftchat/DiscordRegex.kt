package uk.oskarpll.minecraftchat

import java.util.regex.Pattern

/**
 * @author oskarscot
 * @project MinecraftChat
 */
object DiscordRegex {

    /*
    Taken from Javacord https://github.com/Javacord/Javacord/blob/master/javacord-api/src/main/java/org/javacord/api/util/DiscordRegexPattern.java
     */

    val USER_MENTION = Pattern.compile(
        """(?x)                  # enable comment mode 
(?<!                # negative lookbehind 
                    # (do not have uneven amount of backslashes before) 
    (?<!\\)       # negative lookbehind (do not have one backslash before) 
    (?:\\{2}+)    # exactly two backslashes 
    {0,1000000000}+ # 0 to 1_000_000_000 times 
                    # (basically *, but a lookbehind has to have a maximum length) 
    \\            # the one escaping backslash 
)                   # 
<@!?+               # '<@' or '<@!' 
(?<id>[0-9]++)      # the user id as named group 
>                   # '>'"""
    )

    /**
     * A pattern which checks for mentioned roles (e.g. `<@&1234567890>`).
     */
    val ROLE_MENTION = Pattern.compile(
        """(?x)                  # enable comment mode 
(?<!                # negative lookbehind 
                    # (do not have uneven amount of backslashes before) 
    (?<!\\)       # negative lookbehind (do not have one backslash before) 
    (?:\\{2}+)    # exactly two backslashes 
    {0,1000000000}+ # 0 to 1_000_000_000 times 
                    # (basically *, but a lookbehind has to have a maximum length) 
    \\            # the one escaping backslash 
)                   # 
<@&                 # '<@&' 
(?<id>[0-9]++)      # the role id as named group 
>                   # '>'"""
    )

    /**
     * A pattern which checks for mentioned channels (e.g. `<#1234567890>`).
     */
    val CHANNEL_MENTION = Pattern.compile(
        """(?x)                  # enable comment mode 
(?<!                # negative lookbehind 
                    # (do not have uneven amount of backslashes before) 
    (?<!\\)       # negative lookbehind (do not have one backslash before) 
    (?:\\{2}+)    # exactly two backslashes 
    {0,1000000000}+ # 0 to 1_000_000_000 times 
                    # (basically *, but a lookbehind has to have a maximum length) 
    \\            # the one escaping backslash 
)                   # 
(?-x:<#)            # '<#' with disabled comment mode due to the # 
(?<id>[0-9]++)      # the channel id as named group 
>                   # '>'"""
    )

    /**
     * A pattern which checks for custom emojis (e.g. `<:my_emoji:1234567890>`).
     */
    val CUSTOM_EMOJI = Pattern.compile(
        """(?x)                  # enable comment mode 
(?<!                # negative lookbehind 
                    # (do not have uneven amount of backslashes before) 
    (?<!\\)       # negative lookbehind (do not have one backslash before) 
    (?:\\{2}+)    # exactly two backslashes 
    {0,1000000000}+ # 0 to 1_000_000_000 times 
                    # (basically *, but a lookbehind has to have a maximum length) 
    \\            # the one escaping backslash 
)                   # 
<a?+:               # '<:' or '<a:' 
(?<name>\w++)      # the custom emoji name as named group 
:                   # ':' 
(?<id>[0-9]++)      # the custom emoji id as named group 
>                   # '>' 
"""
    )

    /**
     * A pattern which checks for message links (e.g. `https://discord.com/channels/@me/1234/5678`)
     */
    val MESSAGE_LINK = Pattern.compile(
        """
    (?x)                               # enable comment mode 
    (?i)                             # ignore case 
    (?:https?+://)?+                 # 'https://' or 'http://' or '' 
    (?:(?:canary|ptb)\.)?+          # 'canary.' or 'ptb.'
    discord(?:app)?+\.com/channels/ # 'discord(app).com/channels/' 
    (?:(?<server>[0-9]++)|@me)       # '@me' or the server id as named group 
    /                                # '/' 
    (?<channel>[0-9]++)              # the textchannel id as named group 
    /                                # '/' 
    (?<message>[0-9]++)              # the message id as named group 
    
    """.trimIndent()
    )

    /**
     * A pattern which checks for webhook urls (e.g. `https://discord.com/api/webhooks/1234/abcd`)
     */
    val WEBHOOK_URL = Pattern.compile(
        """
    (?x)                                   # enable comment mode 
    (?i)                                 # ignore case 
    (?:https?+://)?+                     # 'https://' or 'http://' or '' 
    (?:(?:canary|ptb)\.)?+              # 'canary.' or 'ptb.'
    discord(?:app)?+\.com/api/webhooks/ # 'discord(app).com/api/webhooks' 
    (?<id>[0-9]++)                       # the webhook id as named group 
    /                                    # '/' 
    (?<token>[^/\s]++)                  # the webhook token as named group 
    
    """.trimIndent()
    )

    /**
     * A pattern to match snowflakes.
     */
    val SNOWFLAKE = Pattern.compile("(?<id>[0-9]{15,25})")
}