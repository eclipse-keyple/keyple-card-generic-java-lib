# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]
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
  (CardSelector<IsoCardSelector> cardSeletor, GenericCardSelectionExtension genericCardSelectionExtension)`
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
- "Keyple Util Library" to version `2.3.1` by removing the use of deprecated methods.
- "Keyple Service Resource Library" to version `3.0.0`

## [2.0.2] - 2021-12-17
### Fixed
- Logging of APDU request command (issue [#9]).

## [2.0.1] - 2021-11-22
### Added
- "CHANGELOG.md" file (issue [eclipse/keyple#6]).
- CI: Forbid the publication of a version already released (issue [#4]).
### Fixed
- Returned value of `addSuccessfulStatusWord` method (issue [#7]).
### Upgraded
- "Keyple Service Resource Library" to version `2.0.1`

## [2.0.0] - 2021-10-06
This is the initial release.
It follows the extraction of Keyple 1.0 components contained in the `eclipse/keyple-java` repository to dedicated repositories.
It also brings many major API changes.

[unreleased]: https://github.com/eclipse/keyple-card-generic-java-lib/compare/2.0.2...HEAD
[2.0.2]: https://github.com/eclipse/keyple-card-generic-java-lib/compare/2.0.1...2.0.2
[2.0.1]: https://github.com/eclipse/keyple-card-generic-java-lib/compare/2.0.0...2.0.1
[2.0.0]: https://github.com/eclipse/keyple-card-generic-java-lib/releases/tag/2.0.0

[#9]: https://github.com/eclipse/keyple-card-generic-java-lib/issues/9
[#7]: https://github.com/eclipse/keyple-card-generic-java-lib/issues/7
[#4]: https://github.com/eclipse/keyple-card-generic-java-lib/issues/4

[eclipse/keyple#6]: https://github.com/eclipse/keyple/issues/6