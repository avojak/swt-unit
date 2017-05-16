# swt-unit

[![Build Status](https://travis-ci.org/avojak/swt-unit.svg?branch=master)](https://travis-ci.org/avojak/swt-unit) [![Coverage Status](https://coveralls.io/repos/github/avojak/swt-unit/badge.svg?branch=master)](https://coveralls.io/github/avojak/swt-unit?branch=master) [![License: GPL v3](https://img.shields.io/badge/license-GPL%20v3-blue.svg)](http://www.gnu.org/licenses/gpl-3.0) [![Version](https://img.shields.io/badge/version-1.0-blue.svg)](http://repo.thedesertmonk.com/artifactory/webapp/#/artifacts/browse/tree/General/maven-release/com/thedesertmonk/util/swt-unit/1.0)

Generally I try to avoid directly testing [SWT](https://www.eclipse.org/swt/) controls, however there are times where it is useful to verify their state. The aim of this project is to provide utilities which facilitate unit tests around SWT widgets and controls.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

It is expected that the appropriate SWT .jar be present at runtime.

### Installing

The artifact may be pulled into a Maven project via the `pom.xml` file. 

*Note: swt-unit is a testing utility and therefore should be specified with the `test` scope*.

#### Release Versions (Recommended)

```xml
<dependency>
  <groupId>com.thedesertmonk.util</groupId>
  <artifactId>swt-unit</artifactId>
  <version>1.0</version>
  <scope>test</scope>
</dependency>
```
```xml
<repository>
  <id>thedesertmonk-release</id>
  <name>TheDesertMonk Maven Release Repository</name>
  <url>http://repo.thedesertmonk.com/maven-release</url>
</repository>
```

#### Snapshot Versions

```xml
<dependency>
  <groupId>com.thedesertmonk.util</groupId>
  <artifactId>swt-unit</artifactId>
  <version>1.0-SNAPSHOT</version>
  <scope>test</scope>
</dependency>
```
```xml
<repository>
  <id>thedesertmonk-snapshot</id>
  <name>TheDesertMonk Maven Snapshot Repository</name>
  <url>http://repo.thedesertmonk.com/maven-snapshot</url>
</repository>
```

## Example Usage

### `WidgetHierarchyInspector`

The `WidgetHierarchyInspector` will enumerate the child `Control` objects of a given a parent `Composite`. 

Suppose you have the following hierarchy:

```java
Shell s = new Shell(display);
Composite c = new Composite(s, SWT.NONE);
ScrolledComposite sc = new ScrolledComposite(s, SWT.V_SCROLL);
Button b = new Button(s, SWT.PUSH);
```

The `getChildren(Composite, Class)` method will behave as follows:

```java
// A List containing a single element: b
List<Button> children = WidgetHierarchyInspector.getChildren(s, Button.class);
```
```java
// A List containing a single element: sc
List<ScrolledComposite> children = WidgetHierarchyInspector.getChildren(s, ScrolledComposite.class);
```
```java
// A List containing two elements: s and sc (ScrolledComposite is a subclass of Composite)
List<Composite> children = WidgetHierarchyInspector.getChildren(s, Composite.class);
```
```java
// An empty List
List<Label> children = WidgetHierarchyInspector.getChildren(s, Label.class);
```

## Running the tests

The [JUnit](http://junit.org/junit4/) tests can be run via the following Maven command:

```
man clean verify
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Travis CI](https://travis-ci.org) - Continuous Integration and Deployment
* [JaCoCo](http://www.eclemma.org/jacoco/) - Code Coverage

## Versioning

I use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/avojak/swt-unit/tags).

## License

This project is licensed under the GNU General Public License v3.0  - see the [LICENSE.md](LICENSE.md) file for details.