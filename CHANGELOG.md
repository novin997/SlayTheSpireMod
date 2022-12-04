## Changelog ##
#### v1.0.0 ####
* Initial release

#### v1.0.1 ####
* Scale console by Settings.scale
* Prevent game hotkeys from activating while console is visible

#### v1.1.0 ####
* Add mod badges
* Add initial support for mod settings screens
* Add `relic` console command
* Add option to change console keybind on BaseMod settings screen

#### v1.1.1 #####
* Scale mod badges by Settings.scale
* Scale mod settings screens by Settings.scale

#### v1.1.2 #####
* Fix bug with IDs which contain spaces (FlipskiZ)
* Add `card` console command (FlipskiZ)
* Add `kill all` console command

#### v1.1.3 #####
* Initial support for each mod badge being tied to its own settings panel

#### v1.1.4 #####
* Add `gold` command
* Add `energy` command
* Remove bundled font and use one from the base game instead

#### v1.1.5 ####
* Add `energy inf` command
* Add `PostEnergyRechargeSubscriber` interface and related code

#### v1.2.0 ####
* Add `PostDrawSubscriber` interface and related code
* Add `CustomRelic` extension of AbstractRelic
* Add support for loading custom RelicStrings
* Fix a bug that prevented the character `D` from being input into the console
* Rename `card` command to `hand`
* Add `hand r all` command
* Add `deck` command
* Add `draw` command
* Add `float BaseMod.pathDensityMultiplier` property which can be used to modify map generation

#### v1.2.1 ####
* Add `PreStartGameSubscriber` interface and related code

#### v1.2.2 ####
* Add `AbstractDungeon` cleanup to diff, nothing changed yet.
* Add `relic list` command
* Fix crash when attempting to `deck add` an invalid card id
* Add upgrade support to `deck add` and `hand add`
* Add `PreMonsterTurnSubscriber` interface and related code

#### v1.2.3 ####
* Add `PostCampfireSubscriber` interface and related code
* Add proper support for `IntangiblePower` to be applied to players

#### v1.3.0 ####
* Update to support `[EARLY_ACCESS_013]`

#### v1.3.1 ####
* Fix bug that prevented the `deck r` command from working

#### v1.3.1a ####
* Pull in changes from `[EARLY_ACCESS_013]` hotfix 2

#### v1.3.2 ####
* A lot of behind the scenes work on settings pages.
* MODDERS: You shouldn't try to build complex settings pages right now, its probably going to get significantly cleaned up and reworked to be more simple soon.

#### v1.3.3 ####
* Fix non-id parameters on commands being cases sensitive
* Change backspace behavior on console
* Fix scaling for new settings panel elements introduced in v1.3.2

#### v1.3.4 ####
* Add `StartActSubscriber` interface and related code
* Add `PostDungeonInitializeSubscriber` interface and related code
* Add `StartGameSubscriber` interface and related code
* Fix bug that would prevent `publishPreStartGame()` from occuring in some cases
* Cleanup part 1
* Add IDEA project files
* Remove `_build.bat` since this can be automated in IDEA

#### v1.3.5 ####
* Add support for CustomRelic outline images

#### v1.3.6 ####
* Prevent Girya from stacking above 3 if `receivePostCampfire()` returns false

#### v1.4.0 ####
* Update to support `[EARLY_ACCESS_014]`
* Mostly switch to using SpirePatch for inserting hooks
* Speed up `kill all` command

#### v1.4.1 ####
* Add `kill self` command

#### v1.4.2 ####
* Add support for adding custom localization strings of any type
* Code cleanup

#### v1.4.3 ####
* Add `potion` command
* Add `fight` command
* Speed up `kill self` command

#### v1.5.0 ####
* Update to support `[EARLY_ACCESS_015]`

#### v1.5.1 (daviscook477) ####
* Code cleanup + bugfixes
* Add hooks for modifying the player's initial deck and initial relics
* Add hooks for changing the relics and potions sold by the shop
* Add methods for abstracting the manipulation of RelicLibrary
* Initial support for custom cards

#### v1.6.3  ####
This is a fast forward to v1.6.3 of daviscook477's fork with a few additional changes
* Update to support weekly patch 12 (daviscook477)
* Add hooks for adding custom player characters and the corresponding hooks for adding a new colors for custom cards (daviscook477)
* More code cleanup (daviscook477)
* Even more code cleanup

#### v1.6.4 ####
* Move changelog out of README
* Fix bug that caused `PublishPostDraw` to fire multiple times
* Fix bug that caused exhausted pile hotkey to work while console was visible
* Switch `AbstractCampfireOption` to use an instrument patch rather than overwriting base file
* Switch `CancelButton` to use an instrument patch rather than overwriting base file
* Switch `DiscardPilePanel` to use an instrument patch rather than overwriting base file
* Switch `DrawPilePanel` to use an instrument patch rather than overwriting base file
* Switch `EndTurnButton` to use an instrument patch rather than overwriting base file
* Switch `MapGenerator` to use an instrument patch rather than overwriting base file
* Switch `MonsterGroup` to use an instrument patch rather than overwriting base file
* Switch `TopPanel` to use an instrument patch rather than overwriting base file

#### v1.6.5 ####
* Switch `IntangiblePower` to use an instrument patch rather than overwriting base file
* Rewrite `IntangiblePower` changes to make it not mess up intents
* Remove diff, _diff.bat, _patch.bat since they are no longer needed

#### v1.6.6 ####
* Switch versioning system over to the one used in https://github.com/daviscook477/BaseMod/releases since he was using a different one
* Hook for exhausting a card
* Hook for finishing a battle
* Add support for Card Unlocks for custom characters
* Hook for using a card

#### v1.7.0 ####
* Add support for Saves for custom characters
* Fix some bugs with card unlock support

#### v1.7.1 ####
* Update to support weekly patch (week 13)

#### v1.7.2 ####
* Support viewing custom cards in the card library (kiooeht)

#### v1.7.3 ####
* Fix slowdown when viewing upgrades in the card library

#### v1.7.4 ####
* Add support for inspect view or custom cards. This requires including a larger texture next to every card texture where if the original card was `my_card.png` you now need `my_card_p.png` too in order to support inspect view
* NOTE: this introduced a breaking change to the API in `addColor` that will require you to update your mods

#### v1.7.5 ####
* Add support to the dev console for checking events with `event [ID]`

#### v1.8.0 ####
* Custom potion support (Haashii)
* All new wiki

#### v1.9.0 ####
* Custom animations for characters (monster support to come soon!)
* Add support for custom energy orbs (Blank The Evil)
* Add support to the dev console for applying powers with `power [ID] [amount]`

#### v1.9.1 ####
* Custom animations no longer run after their associated model is destroyed
* Hook for modifying base damage on cards like Perfected Strike or Heavy Blade

#### v1.9.2 ####
* Fix campfire UI for weekly patch (week 14)

#### v1.9.3 ####
* Reorganize the image locations for the test mod

#### v1.9.4 ####
* Add support for Custom Keywords to BaseMod with the editKeywords callback that ensures adding keywords is timed properly

#### v1.9.5 ####
* Add a hook for when powers are modified

#### v1.9.6 ####
* Can now set up character specific relics for characters other than the ironclad and silent

#### v2.0.0 ####
* Dev console has been completely revamped
* Holding down keys triggers multiple keypresses
* The console remembers previous commands so use the up and down arrows to select between previously typed commands
* The console is multiline now in order to display the last few commands and their outputs
* Help text is displayed for commands if they are mistyped
* More commands! `hp` and `maxhp` allow you to change your current HP and current max HP

#### v2.0.1 ####
* Hotfix reverting multiple keypresses on holding down keys because it broke a bunch of things

#### v2.1.0 ####
* New UI features for Modders

#### v2.1.1 ####
* Hotfix to prevent wasting GPU cycles when there are no custom characters to render

#### v2.1.2 ####
* Update to week 15 patch

#### v2.1.3 ####
* Hotfix to fix the console on the week 15 patch

#### v2.2.0 ####
* Add text box support for mod settings with `BaseMod.openTextPanel`
* Merge pull request by BlankTheEvil to fix `CustomCardWithRender` appearing incorrectly in the CardLibrary screen

#### v2.3.0 ####
* Support ModTheSpire v2.5.0 and now save the console hotkey
* Indicate that BaseMod is built specifically for the week 15 patch
* The ModPanel UI backend has been made better and more extensible. A few methods have been deprecated but there are no breakign changes.
* Added ToggleButton - idea and some code courtesy of twanvl (https://github.com/twanvl)
* Added a LabeledToggleButton which is a ToggleButton with a text label

#### v2.3.1 ####
* Cleanup README
* Fix character select screen memory leak (kiooeht)
* Revamp subscription system
* Deprecate old subscription system
* Fix issue #24
* Fix issue #29
* Support animations done in spriter - these have a much smaller performance impact (kiooeht)
* Prep for MTS v2.6.0 when it releases
* Add a new badge icon for BaseMod
* Support week 17 patch

#### v2.4.0 ####
* **Requires** ModTheSpire v2.6.0 now
* Support **week 18** patch
* Convert CardLibraryScreen patches to Locator patches
* Convert CardCrawlGame patches to Locator patches
* Modal choice cards! (kiooeht) (https://github.com/daviscook477/BaseMod/wiki/Modal-Choice-Cards)
* Fix issue #40
* Fix issue #41
* Remove issue #38
* Add feature #31
* Add feature #39

#### v2.4.1 ####
* Fix multiple bugs with start act, pre start game, and start game subscribers
* Add support for per-card energy orb graphics (BlankTheEvil)

#### v2.5.0 ####
* Support **week 20** patch (kiooeht)
* Add support for custom dynamic variables (kiooeht)
* Merge CustomCardWithRender features into CustomCard - breaking change - should only affect BlackMageMod
* Add feature CardBasic (1st functional version) (DemoXinMC)
* Add support for per-card banners (BlankTheEvil)

#### v2.6.0 ####
* Support **week 21** patch

#### v2.7.0 ####
* Support **week 22** patch (kiooeht)
* Allow mods to use the same card and relic IDs without conflict (kiooeht)
* Modal option improvements (kiooeht)

#### v2.8.0 ####
* Support **week 23** patch (kiooeht)
* Fix bugs in last week's ID conflict resolver (robojumper)
* Support energy symbols using [E] instead of color specific symbols (kiooeht)

#### v2.9.0 ####
* Support week 24 patch (kiooeht)
* Disable mod ID on cards and relics as it has problems

#### v2.9.1 ####
* Fix duplicate rendering of custom characters (again)

#### v2.10.0 ####
* Support week 25 patch (kiooeht)
* Actually set hybridColor and spotsColor for custom potions (twanvl)

#### v2.11.0 ####
* Support week 27 (kiooeht)
* Fix power command (kiooeht)
* Fix abandon run to abandon modded character runs (kiooeht)
* Bug fix: Use skill background for skill cards in portrait view (twanvl)
* Fix modal choice cards to not counts towards cards played count (kiooeht)
* Death hook (alexdriedger)


#### v2.12.0 ####
* Support week 28 (kiooeht)
* Add OrbStrings to localization support (kiooeht)

#### v2.13.0 ####
* Support for week 29 (kiooeht)
* Add AutoComplete to DevConsole (Skrelpoid)

#### v2.14.0 ####
* Support for week 30 (kiooeht)
* Deprecate starting deck and relic hooks (kiooeht)

#### v2.15.0 ####
* Support for week 31 (kobting)
* Add modded characters to the Custom mode screen (kiooeht)

#### v2.16.0 ####
* Add debug command (Blank The Evil)
* Fix Run History screen (kiooeht)
* StartBattleHook (kobting)
* Diverse includes modded character's cards (Moocowsgomoo)
* Patch events and relics that care about Strikes and Defends (twanvl)
* Fix portrait image in right-click card previews (twanvl)
* Custom event support (Blank The Evil)
* Character select screen pages with many characters (kobting)
* Allow custom energy symbols on cards (twanvl)
* Fix [E] rendering in portait mode (twanvl)
* Dev console: Discard command (twanvl)
* Dev console: Set card numbers command (twanvl)

#### v2.17.0 ####
* Support for week 34 (kiooeht)
* Fix RelicViewScreen not scrolling far enough with mods that add relics (kiooeht)
* Potions can be specific to a character (twanvl)

#### v2.18.0 ####
* Identify what mod relics, cards, and powers are from (kiooeht)
* Allowing adding relics to blue pool (kiooeht)
* Make relic and power onEvokeOrb actually usable (kiooeht)
* Fix ascension for modded characters (kiooeht)
* Fix crash if Note For Yourself event (kiooeht)
* Fix card rendering to not draw curse texture (Blank The Evil)

#### v2.19.0 ####
* Support for week 37 (kiooeht)
* Fix Ascension 14 for modded characters (kiooeht)
* Fix Shiny modifier for modded characters (kiooeht)
* Fix Insanity modifier for modded characters (kiooeht)
* Anti-alias modded art assets automatically (kiooeht)

#### v3.0.0 ####
* Custom monsters (kiooeht)
* Custom monster fights (kiooeht)
* Custom bosses (kiooeht)
* Card tags (kiooeht)
* Fix and improve custom events API (kiooeht)
* Custom bottle-style relics (kiooeht)
* Make StartBattleHook work when loading a save (gogo81745)
* Automatically give player an orb slot when channeling on a character without orb slots (kiooeht)
* Render colored outline for custom character relics (kiooeht)

#### v3.0.1 ####
* Fix OnStartBattle hook (kiooeht)

#### v3.0.2 ####
* Fix Ascension mode for modded characters after v3.0 (kiooeht)
* Fix Gremlin Match tag for modded characters (kiooeht)

#### v3.1.0 ####
* Let basic Strike/Defend events work with not CustomCards (kiooeht)
* Make "deck add" command mark cards as seen (kiooeht)
* Fix small orb icons followed by a period in tooltips (kiooeht)
* Refactor CustomBottleRelic to be an interface (kiooeht)
* Render custom card tooltips in SingleCardViewPopup (kiooeht)
* Fix Red/Green/Blue/Colorless Cards modifiers to work with modded characters (kiooeht)
* Add custom mode modifiers hook (alexdriedger)
* Automatically add custom mode modifiers for modded character cards (kiooeht)
* Max HP change hook (kiooeht)

#### v3.1.1 ####
* Hotfix crash when starting modded character runs (kiooeht)

#### v3.2.0 ####
* Support for week 41 (kiooeht)
* Add custom monsters to console suggestions (kiooeht)
* Make card compendium only use added colors (kiooeht)
  * Fixes FruityMod breaking compendium with incomplete character
* Use character name for tabs in card compendium (kiooeht)
* Use character name on run history screen (kiooeht)
* Support nice monster names on run history screen (kiooeht)

#### v3.2.1 ####
* Fix ascension level select for modded characters (kiooeht)
* Update README

#### v3.2.2 ####
* Fix crash when unlocking ascension as base game character (kiooeht)

#### v3.3.0 ####
* Fix crash if any mod patches AbstactPower (kiooeht)
* Fix crash when adding custom monster that uses setHp (kiooeht)
* Fix fight command after a monster room (twanvl)
* Mod items in top panel (kobting)

#### v3.4.0 ####
* Support for week 43 (kiooeht)
* Fix TopPanel positions on not 16:9 aspect ratios (kiooeht)
* Fix modified card costs not rendering when not in hand (Moocowsgomoo)
* Fix ModLabeledToggleButton privacy (Blank The Evil)

#### v3.5.0 ####
* Support for week 44 (kiooeht)
* Fix Start Game hook triggering when looping in Endless mode (kiooeht)
* Fix Ascension 20 double boss when custom bosses have been added (kiooeht)
* Allow custom characters to add relic unlocks (kiooeht)

#### v4.0.0 ####
* Support for week 45 (kiooeht)
* Fix PostPotionUse hook (kiooeht)
* Fix drawn cards entering modal choice options (kiooeht)
* Sort modded characters alphabetically by name (kiooeht)
* Shrink card description font for cards with lots of text (kiooeht)
* Allow max hand size to be changed (kiooeht)
* Fix ModSlider not allowing x position to be set (kiooeht)
* Fix custom character card and seen count (kiooeht)

#### v4.0.1 ####
* Fix Modal Choice Cards when double-played (kiooeht)
* Don't shorten name if adding "..." makes it longer than it was (kiooeht)
* Fix custom character card modifiers (kiooeht)
* Allow passing EnergyOrbInterface to CustomPlayer (kiooeht)
* Slow energy orb spin when out of energy (kiooeht)

#### v4.0.2 ####
* Fix Discovery not obeying new max hand size (kiooeht)
* Fix ModSlider using y position as x position (kiooeht)

#### v5.0.0 ####
* Support for week 46 (kiooeht)
* Fix custom monsters being more weighted (kiooeht)
* Fix custom monsters not being used for the first strong enemy (kiooeht)
* More fixing ModSlider position (kiooeht)

#### v5.0.1 ####
* Fix NPE in base game GetAllInBattleInstances (kiooeht)

#### v5.1.0 ####
* Support for week 47 (kiooeht)
* Add PreRoomRender hook (Blank The Evil)
* Custom save fields for cards and relics (twanvl)

#### v5.2.0 ####
* Make Spriter animation public (kiooeht)
* Spriter animations for monsters (kiooeht)
* Multi-word keywords (kiooeht)
* What mod is this from? for events (kiooeht)

#### v5.2.1 ####
* Fix crash from multi-word keywords on some relics (kiooeht)

#### v5.2.2 ####
* Easier SpineAnimation option for custom characters (kiooeht)
* Fix Diverse adding base character cards twice (kiooeht)

#### v5.3.0 ####
* DevConsole AutoComplete: The active suggestion is now highlighted. Suggestions are now filled up to ":" (Skrelpoid)
* Customizable Colors for CustomDynamicVariable (Skrelpoid)
* Fix softlock with Modal cards (kiooeht)
* Many bug fixes for the console (Skrelpoid)
* Blight command (kiooeht)

#### v5.3.1 ####
* Fix for week 50 drawing colorless icon over custom character cards in single card view (kiooeht)

#### v5.3.2 ####
* Fix custom character name color in stats screen (kiooeht)
* Allow Spriter animations to flip (support Spear & Shield fight) (kiooeht)
* Fix dialog position being reset on custom characters when entering a room (kiooeht)
* Fix PrePotionUse hook being called without targeting a monster (kiooeht)
* Fix crash if custom character has no character specific relics (kiooeht)
* Fix hand rendering craziness when the hand size gets out of control (kiooeht)
* Fix custom card tooltips going off the bottom of the screen (kiooeht)
* Fix max HP change hook triggering on ANY creature changing max HP (kiooeht)

#### v5.3.3 ####
* Fix Whatmod appearing on heart screen and in event combats (kiooeht)
* Maybe fix unlock crash on victory with custom characters (kiooeht)

#### v5.3.4 ####
* Fix startup crash if base character is fully (level 6) unlocked (kiooeht)

#### v5.3.5 ####
* Fix PostEnergyRecharge hook (kiooeht)
* Fix crash when toggling beta art on modded cards (kiooeht)
* Fix crash if modded card is missing _p texture (kiooeht)

#### v5.4.0 ####
* Fix unlock crash on victory with custom characters (kiooeht)
* Fix PostDeath hook not triggering on killing Heart (kiooeht)
* Tag the basegame basic cards with BASIC_STRIKE, BASIC_DEFEND, and FORM (kiooeht)
* Custom victory cutscenes for custom characters (kiooeht)
* Custom victory effects for custom characters (kiooeht)

#### v5.5.0 ####
* Add OnPlayerLoseBlockSubscriber hook (JohnnyDevo)
* Added function to remove relics from custom characters' pools (Moocowsgomoo)

#### dev ####
