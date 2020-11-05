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
	Checker.notNull(name, MSG_NOT_NULL, "name");
	Checker.notNull(age, MSG_NOT_NULL, "age");
	Checker.notNull(picture, MSG_NOT_NULL, "picture");
	Checker.respects(name, pattern, MSG_UPPER_CASE, "name");
	Checker.inRange(age, AGE_MIN, AGE_MAX, AgeException::new, "age");
	Checker.respects(picture, ValidationUtils::isPngData, MSG_NOT_PNG_IMAGE, "picture");
	Checker.notEmpty(skills, MSG_NOT_EMPTY_COLLECTION, "skills");
}
```

## I Need You!

Please feel free to collaborate to this tiny project to make it as tiny as possible but as powerfull as it could be.

This is very easy thanx to Gitpod. Click on this badge to open a fully configured IDE inside your browser [![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/fxrobin/tiny-preconditions)

