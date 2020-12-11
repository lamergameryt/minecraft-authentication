# Server Authenticator
#### Authenticate your players with ease
![GitHub](https://img.shields.io/github/license/lamergameryt/minecraft-authentication) ![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/lamergameryt/minecraft-authentication) [![Versions Supported](https://img.shields.io/badge/Spigot%20Version-1.8+-blue.svg)](https://spigot.org/) ![GitHub followers](https://img.shields.io/github/followers/lamergameryt?style=social)
[![Made With Java](https://img.shields.io/badge/Made%20With-Java-blueviolet)](https://java.com)

Server Authenticator is a **Spigot plugin** with you can require users to authenticate themselves upon joining the server. 

This plugin was made by me in the **year 2017** ago. This is the reason the code qualitiy is poor.
If I find this repository get semi-popular, I'll update the code to improve its quality.  

## How to use this?
To compile this plugin, you will need the required dependencies. This project has no external dependencies other than **Spigot** itself.

The project was made with Spigot version `spigot-1.8-R0.1-SNAPSHOT-latest` and this is the recommended version too. To export this plugin as a jar file, build this plugin with Spigot as a dependency.

Once this plugin is built as a jar file, you can drag and drop it into your `plugins` folder.

## Permissions
This plugin has only two permissions:
* `serverauth.resetcommand`: Required to execute the command `/serverauth reset` which is used to reset the player's password.
* `serverauth.reloadcommand`: Required to execute the command `/serverauth reloadconfig` which is used to reload the configuration file. 

## Is this secure?
The players passwords are hashed with the SHA-512 hashing algorithm.
According to [this post](https://stackoverflow.com/questions/6776050/how-long-to-brute-force-a-salted-sha-512-hash-salt-provided), a String hashed with SHA-512 will take approximately **3.17 * 10^64 years**.

Hence it is safe to say that the neither the admins, nor anyone trying to bruteforce the password is going to get the original password anytime soon.

## License
This plugin is licensed under the MIT License. See the LICENSE file in the top distribution directory for the full license text.

## Liked it?
Thank you for reading through this!

If you liked it, consider giving a star to this project and following me on GitHub as I have put my ❤ and a lot of effort into this.