# tiny-preconditions

A really-really small library to check method arguments.

[![Build Status](https://travis-ci.org/fxrobin/tiny-preconditions.svg?branch=master)](https://travis-ci.org/fxrobin/tiny-preconditions)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/1e5c0243120047eb93d299de2ec5c566)](https://www.codacy.com/manual/fxrobin/tiny-preconditions/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=fxrobin/tiny-preconditions&amp;utm_campaign=Badge_Grade)
[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/fxrobin/functional-switch)

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
