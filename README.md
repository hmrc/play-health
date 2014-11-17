#Play Health Plugin

Plugin to provide common health check functionality for Play +2.2.3 projects.

##Endpoints

This adds two endpoints:

```
     /ping/ping     - Returns 200 and an empty body
	 /admin/details - Returns information about the running service
```

##Setup

Add the jar to the projects dependencies:

```
    "uk.gov.hmrc" %% "play-health" % "0.5.0"
```

Add plugin to play.plugins:

```
    {priority}:uk.gov.hmrc.play.health.HealthPlugin
```

Additionally, add the following to the routes file:

```
    ->     /                                    health.Routes
```

## Configuration

The plugin expects to find the apps name in the `application.conf` under the key `appName`.
For the `/admin/details` endpoint to work as expected, the applications `manifest.mf` must include a key `Implementation-Title` with the same value as `appName`.
