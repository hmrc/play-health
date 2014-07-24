#Play Health Plugin

Plugin to provide common health check functionality for Play projects.

##Endpoints

This adds two endpoints:

```
     /ping/ping     - Returns 200 and an empty body
	 /admin/details - Returns information about the running service
```

##Setup

Add the jar to the projects dependencies:

```
    Project("myProject).settings(libraryDependencies += "uk.gov.hmrc" %% "play-health" % "0.2.0")
```

Currently, the plugin is only available for version 2.1 of Play, and 2.10 of Scala.

Additionally, add the following to the routes file:

```
    ->     /                                    health.Routes
```

## Configuration

The plugin expects to find the apps name in the `application.conf` under the key `appName`.
For the `/admin/details` endpoint to work as expected, the applications `manifest.mf` must include a key `Implementation-Title` with the same value as `appName`.
