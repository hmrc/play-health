#Play Health Plugin

[![Apache-2.0 license](http://img.shields.io/badge/license-Apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

[![Build Status](https://travis-ci.org/hmrc/play-health.svg)](https://travis-ci.org/hmrc/play-health) [ ![Download](https://api.bintray.com/packages/hmrc/releases/play-health/images/download.svg) ](https://bintray.com/hmrc/releases/play-health/_latestVersion)


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
    "uk.gov.hmrc" %% "play-health" % "[INSERT VERSION]"
```
For Java 7 use versions <= 0.8.0

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

## License ##
 
This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").