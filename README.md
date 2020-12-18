# AutoConfig-TOML

[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)
![Build](https://github.com/TheRandomLabs/AutoConfig-TOML/workflows/Build/badge.svg?branch=3.x.x-fabric)
[![Average time to resolve an issue](http://isitmaintained.com/badge/resolution/TheRandomLabs/AutoConfig-TOML.svg)](http://isitmaintained.com/project/TheRandomLabs/AutoConfig-TOML "Average time to resolve an issue")

A custom TOML [ConfigSerializer](https://github.com/shedaniel/AutoConfig/blob/1.16/src/main/java/me/sargunvohra/mcmods/autoconfig1u/serializer/ConfigSerializer.java)
for [AutoConfig](https://github.com/shedaniel/AutoConfig/tree/1.16) that uses
[NightConfig](https://github.com/TheElectronWill/night-config).

## Sponsors

I've partnered with BisectHosting! In my experience, their servers are lag-free, easy to manage and
of high quality. Check them out here:

<a href="https://bisecthosting.com/TheRandomLabs">
	<img src="https://www.bisecthosting.com/images/logos/dark_text@1538x500.png" width="385" height="125" border="0">
</a>

Use the code "**TheRandomLabs**" to get 25% off your first month!

I've also partnered with Apex Hosting, and in my experience, their servers are *also* lag-free,
easy to manage, and of high quality. Check them out here:

<a href="https://billing.apexminecrafthosting.com/aff.php?aff=3907">
	<img src="https://cdn.apexminecrafthosting.com/img/theme/apex-hosting-mobile.png" width="594" height="100" border="0">
</a>

## Aims

AutoConfig-TOML aims to provide a `ConfigSerializer` for TOML that is slightly better than
[Toml4jConfigSerializer](https://github.com/shedaniel/AutoConfig/blob/1.16/src/main/java/me/sargunvohra/mcmods/autoconfig1u/serializer/Toml4jConfigSerializer.java),
the TOML `ConfigSerializer` that comes with AutoConfig.

## Features

### Better validation

* `ConfigData#validatePostLoad()` is always called and any updated values are written to disk on
both serialization and deserialization.
* As a result, it can be ensured that valid values are always present both in the configuration
written to disk and in the configuration object.
* Additionally, `ConfigData#validatePostLoad()` is also called on categories, and not just the main
configuration object.
* In addition, NightConfig's [Spec*](https://github.com/TheElectronWill/night-config/tree/master/core/src/main/java/com/electronwill/nightconfig/core/conversion)
annotations are supported, and invalid values are automatically reset to the defaults.

### Property keys

* Property field names are automatically converted to lower_snake_case TOML keys.
* [Path](https://github.com/TheElectronWill/night-config/blob/master/core/src/main/java/com/electronwill/nightconfig/core/conversion/Path.java)
annotations may be utilised to manually specify TOML property keys, which are not subject to this conversion.

### Configuration paths

* A `Path` annotation may also be used to specify the path of a configuration file relative to the
configuration directory.

### Comments

* Comments for properties, categories and configuration files may be specified through the use of
`TOMLConfigSerializer.Comment`.
* Default, minimum and maximum values are automatically appended to the comments.

### Utility methods

If a reference to the `TOMLConfigSerializer` is stored, the following utility methods are
available:

* `TOMLConfigSerializer#getConfig()` returns the configuration object.
* `TOMLConfigSerializer#reloadFromDisk()` reloads the configuration from disk.

## Usage

`TOMLConfigSerializer` can be used just like any other `ConfigSerializer` for AutoConfig.
Example usage can be found in the [test mod](https://github.com/TheRandomLabs/AutoConfig-TOML/tree/autoconfig-3.x.x-fabric/src/testmod).

### Using with Gradle

```groovy
repositories {
	//...

	maven {
		url "https://dl.bintray.com/shedaniel/cloth-config-2"
	}

	maven {
		url "https://dl.bintray.com/shedaniel/autoconfig1u"
	}

	maven {
		url "https://jitpack.io"
	}
}

dependencies {
	//...

	modImplementation("me.shedaniel.cloth:config-2:${project.clothConfigVersion}") {
		exclude(module: "fabric-api")
	}
	include "me.shedaniel.cloth:config-2:${project.clothConfigVersion}"

	modImplementation("me.sargunvohra.mcmods:autoconfig1u:${project.autoConfigVersion}") {
		exclude(module: "fabric-api")
	}

	include "me.sargunvohra.mcmods:autoconfig1u:${project.autoConfigVersion}"

	modImplementation "com.github.TheRandomLabs:AutoConfig-TOML:autoconfig-3.x.x-fabric-SNAPSHOT"
	include "com.github.TheRandomLabs.AutoConfig-TOML:autoconfig-3.x.x-fabric-SNAPSHOT"
}
```

### Why not just write another configuration library?

* AutoConfig lets me easily create a configuration GUI on both Fabric and Forge.
* I'm not sure the world needs yet another configuration library.
* This way, I don't have to maintain my own configuration library.
