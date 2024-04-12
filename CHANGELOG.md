# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [3.0.1] - 2024-04-12
### Changed
- Java source and target levels `1.6` -> `1.8`
### Upgraded
- Keypop Reader API `2.0.0` -> `2.0.1`
- Keypop Card API `2.0.0` -> `2.0.1`
- Keyple Common API `2.0.0` -> `2.0.1`
- Keyple Service Resource Lib `3.0.0` -> `3.0.1`
- Keyple Util Lib `2.3.1` -> `2.4.0`
- Gradle `6.8.3` -> `7.6.4`

## [3.0.0] - 2023-11-28
:warning: Major version! Following the migration of the "Calypsonet Terminal" APIs to the
[Eclipse Keypop project](https://keypop.org), this library now implements Keypop interfaces.
### Added
- Added project status badges on `README.md` file.
### Changed
- Class renamed:
  - `GenericCardSelection` -> `GenericCardSelectionExtension`
- Methods renamed or signatures refactored:
  - `GenericCardSelection createCardSelection ()` -> `GenericCardSelectionExtension createGenericCardSelectionExtension()`
  - `CardResourceProfileExtension createCardResourceProfileExtension(GenericCardSelectionExtension 
  genericCardSelectionExtension)`  -> `CardResourceProfileExtension createCardResourceProfileExtension
  (IsoCardSelector cardSeletor, GenericCardSelectionExtension genericCardSelectionExtension)`
  - `processApdusToByteArrays ()` -> `processApdusToByteArrays (ChannelControl channelControl)`
    and `processApdusToHexStrings ()` -> `processApdusToHexStrings (ChannelControl channelControl)
    The enum `ChannelControl` has been created for this purpose.
### Removed
- Removed methods from `GenericCardSelectionExtension` (now available from Keyple core service):
  - `filterByCardProtocol(...)`
  - `filterByPowerOnData(...)`
  - `filterByDfName(...)`
  - `setFileOccurrence(...)`
  - `setFileControlInformation(...)`
- Removed enumerations:
  - `FileOccurrence`
  - `FileControlInformation`
- Removed method from `GenericExtensionService`:
  - `prepareReleaseChannel()`
### Fixed
- CI: code coverage report when releasing.
### Upgraded
- Calypsonet Terminal Reader API `1.0.0` -> Keypop Reader API `2.0.0`
- Calypsonet Terminal Card API `1.0.0` -> Keypop Card API `2.0.0`
- Keyple Util Library `2.1.0` -> `2.3.1`
- Keyple Service Resource Library `2.0.1` -> `3.0.0`

## [2.0.2] - 2021-12-17
### Fixed
- Logging of APDU request command (issue [#9]).

## [2.0.1] - 2021-11-22
### Added
- "CHANGELOG.md" file (issue [eclipse-keyple/keyple#6]).
- CI: Forbid the publication of a version already released (issue [#4]).
### Fixed
- Returned value of `addSuccessfulStatusWord` method (issue [#7]).
### Upgraded
- "Keyple Service Resource Library" to version `2.0.1`

## [2.0.0] - 2021-10-06
This is the initial release.
It follows the extraction of Keyple 1.0 components contained in the `eclipse-keyple/keyple-java` repository to dedicated repositories.
It also brings many major API changes.

[unreleased]: https://github.com/eclipse-keyple/keyple-card-generic-java-lib/compare/3.0.1...HEAD
[3.0.1]: https://github.com/eclipse-keyple/keyple-card-generic-java-lib/compare/3.0.0...3.0.1
[3.0.0]: https://github.com/eclipse-keyple/keyple-card-generic-java-lib/compare/2.0.2...3.0.0
[2.0.2]: https://github.com/eclipse-keyple/keyple-card-generic-java-lib/compare/2.0.1...2.0.2
[2.0.1]: https://github.com/eclipse-keyple/keyple-card-generic-java-lib/compare/2.0.0...2.0.1
[2.0.0]: https://github.com/eclipse-keyple/keyple-card-generic-java-lib/releases/tag/2.0.0

[#9]: https://github.com/eclipse-keyple/keyple-card-generic-java-lib/issues/9
[#7]: https://github.com/eclipse-keyple/keyple-card-generic-java-lib/issues/7
[#4]: https://github.com/eclipse-keyple/keyple-card-generic-java-lib/issues/4

[eclipse-keyple/keyple#6]: https://github.com/eclipse-keyple/keyple/issues/6