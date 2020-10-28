# tiny-preconditions

A really-really small library to check method arguments.

## External tools

[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/fxrobin/functional-switch)
[![Build Status](https://travis-ci.org/fxrobin/tiny-preconditions.svg?branch=master)](https://travis-ci.org/fxrobin/tiny-preconditions)

## Usage

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
