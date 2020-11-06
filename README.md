# tiny-preconditions

A really-really small library to check method arguments.

[![Build Status](https://travis-ci.org/fxrobin/tiny-preconditions.svg?branch=master)](https://travis-ci.org/fxrobin/tiny-preconditions)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/1e5c0243120047eb93d299de2ec5c566)](https://www.codacy.com/manual/fxrobin/tiny-preconditions/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=fxrobin/tiny-preconditions&amp;utm_campaign=Badge_Grade)
[![CodeFactor](https://www.codefactor.io/repository/github/fxrobin/tiny-preconditions/badge)](https://www.codefactor.io/repository/github/fxrobin/tiny-preconditions)
[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/fxrobin/tiny-preconditions)
[![hackmd-github-sync-badge](https://hackmd.io/HsI9GxZyQCydR3lXJ1Xjqg/badge)](https://hackmd.io/HsI9GxZyQCydR3lXJ1Xjqg)

## How to use

Just add this dependency to you maven project : 

```xml
<dependency>
    <groupId>fr.fxjavadevblog</groupId>
    <artifactId>tiny-preconditions</artifactId>
    <version>1.0.8</version>
</dependency>
```

## Usage

The class `Checker` offers static methods to check various conditions : 

- `Checker.notNull` : checks nullity 
- `Checker.respect` : checks a fields against 
    - a REGEXP if a pattern is given
    - a Predicate which takes the parameter and returns true or false
- `Checker.inRange` : checks if an integer is between 2 integers (included)
- `Checker.notEmpty` : checks if a collection is empty

Here is an example used in the JUnit tests :

```java
public static void demoPreconditions(String name, Integer age, byte[] picture, Collection<String> skills)
{
    Checker.notNull("name",name);
    Checker.notNull("name",name, Predicate.not(String::isEmpty); // Java 11
    Checker.notNull("age", age, IllegalArgumentException::new);
    Checker.notNull(picture, "picture");
    Checker.inRange("age", age, 14, 18);
    Checker.inRange("age", age, 14, 18, AgeBusinessException::new);
    Checker.respects("name", name, "[A-Z]+");
    Checker.respects("picture", picture, ValidationUtils::isPngData);
    Checker.notEmpty("skills", skills);
}
```

## i18n

Messages are bundled into the file "tiny-preconditions.properties".
They are loaded and accessed by the enum class `PreconditionMessage`.

Here are the default messages : 

```
SHOULD_NOT_BE_NULL=The argument {0} should not be null
SHOULD_NOT_BE_EMPTY=The argument {0} should not be empty
SHOULD_BE_BETWEEN=The argument {0} should be between {1} and {2} inclusive
SHOULD_MATCH_REGEXP=The argument {0} should match this regexp {1}
SHOULD_RESPECT_BOOLEAN_CONDITION=The argument {0} should respect this boolean condition \: {1}
MAP_SHOULD_NOT_CONTAIN_ANY_NULL_REFERENCE=The map {0} should not contain any null references but key [{1}] refers to null

```

Localized and custom messages can be easily added.

## Code quality

This project aims to be rated A with Codacy and Codefactor.

Developments are and will be made in that goal.

Current quality status : [![Codacy Badge](https://app.codacy.com/project/badge/Grade/1e5c0243120047eb93d299de2ec5c566)](https://www.codacy.com/manual/fxrobin/tiny-preconditions/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=fxrobin/tiny-preconditions&amp;utm_campaign=Badge_Grade)
[![CodeFactor](https://www.codefactor.io/repository/github/fxrobin/tiny-preconditions/badge)](https://www.codefactor.io/repository/github/fxrobin/tiny-preconditions)

## Travis CI Building

This project is build with Travis-CI and tested with JDK 8 and JDK 11.

Current build status : [![Build Status](https://travis-ci.org/fxrobin/tiny-preconditions.svg?branch=master)](https://travis-ci.org/fxrobin/tiny-preconditions)

## I Need You!

Please feel free to collaborate to this tiny project to make it as tiny as possible but as powerfull as it could be.

This is very easy thanx to Gitpod. Click on this badge to open a fully configured IDE inside your browser [![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/fxrobin/tiny-preconditions)
