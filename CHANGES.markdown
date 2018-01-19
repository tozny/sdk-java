# Change Log

## [2.2.1] - 2018-1-19

- Fix broken documentation

## [2.2.0] - 2018-01-19

- Add dynamic authentication method calls

## [2.1.10] - 2016-10-04

- Add realm.user_push calls

## [2.1.9] - 2016-08-29

- Fix a Java compilation error with non-final variables passed to a Map

## [2.1.8] - 2016-08-29

- Add realm.link_challenge calls
- Add user.* calls for link/otp challenges, results, and challenge exchange

## [2.1.7] - 2016-06-27

- Fix a Java 7 incompatibility
- Resolve a null pointer exception in the new `realm.users_get` call

## [2.1.6] - 2016-06-03

- Fix a JSON parsing exception when no results are returned from `realm.users_get` calls

## [2.1.5] - 2016-06-02

- Update documentation

## [2.1.4] - 2016-06-02

- Add basic support for `realm.users_get` API calls

## [2.1.3] - 2016-04-28

- Fix JSON interpretation in `checkValidLogin` requests

## [2.1.2] - 2016-03-30

- Fix incorrect key name in JSON deserialization

## [2.1.1] - 2016-03-28

- Fix a number of JSON serialization issues

## [2.1.0] - 2016-03-28

- Add `userDeviceAdd` method to `RealmAPI`
