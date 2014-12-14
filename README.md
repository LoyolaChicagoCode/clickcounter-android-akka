[![Build Status](https://travis-ci.org/LoyolaChicagoCode/clickcounter-android-akka.svg?branch=master)](https://travis-ci.org/LoyolaChicagoCode/clickcounter-android-akka)
[![Coverage Status](https://img.shields.io/coveralls/LoyolaChicagoCode/clickcounter-android-akka.svg)](https://coveralls.io/r/LoyolaChicagoCode/clickcounter-android-akka)
[![Download](https://api.bintray.com/packages/loyolachicagocode/generic/clickcounter-android-akka/images/download.svg) ](https://bintray.com/loyolachicagocode/generic/clickcounter-android-akka/_latestVersion)

# Learning Objectives

This example is intended as a starting point for anyone planning develop
*actor-based* Android applications using Scala and
[Akka](http://doc.akka.io/docs/akka/snapshot/scala/actors.html).
Its learning objectives are:

- Android application development using Scala
    - using the Simple Build Tool (sbt) for Scala in conjunction with 
      [pfn's well-maintained plugin](https://github.com/pfn/android-sdk-plugin)
    - using IntelliJ IDEA
- Android application architecture for testability and maintainability
    - [Dependency Inversion Principle (DIP)](http://en.wikipedia.org/wiki/Dependency_inversion_principle)
    - [Model-View-Adapter](http://en.wikipedia.org/wiki/Model-view-adapter) architectural pattern
    - Separation of Android activity into event-handling and lifecycle management
    - Separation of concerns among components using
      [Akka actors](http://doc.akka.io/docs/akka/snapshot/scala/actors.html)
- Effective testing
    - Unit testing and [Behavior-Driven Development (BDD)](http://en.wikipedia.org/wiki/Behavior-driven_development) 
      with ScalaTest
    - [Mock objects](http://en.wikipedia.org/wiki/Mock_object) with [ScalaMock](http://scalamock.org/)
    - Functional testing (out-of-container) using [Robolectric](http://robolectric.org/)
- End-to-end example of continuous integration (CI) for Scala/Android (see status badges at the top of this file)

# Building and Running

Please refer to [these notes](http://lucoodevcourse.github.io/notes/scalaandroiddev.html) for details.

# References

- [Akka actors hello world example](http://doc.akka.io/docs/akka/snapshot/scala/hello-world.html)
- [James Earl Douglas's SBTB 2014 presentation](https://www.youtube.com/watch?v=sZYAFWTyOlE)
- [James Earl Douglas's Scala CI example](https://github.com/earldouglas/scala-ci): Travis and Coveralls
- [Matthew Fellows's Scala CI/CD example](http://www.onegeek.com.au/scala/setting-up-travis-ci-for-scala): Travis and Bintray
