# Naming Conventions #

### Classes ###

- **Activity Classes**
  - append with "Activity" (ex: HotSeatGameActivity)
- **Nested Handler Classes**
  - append with "Handler" (ex: StartGameButtonHandler)


### Variables ###

- **Variable Names (default)**
  - lower camel case
  - noun preceeded by adjectives
  - *example*: playerNames
- **Boolean Variables**
  - preceeded with "is" (ex: isLegalLimit)
- **Constant Variables**
  - underscored, upper case (ex: MISSLE_LAUNCH_SEQUENCE)


### Methods ###

- **Method Names (default)**
  - lower camel case
  - action phrase
  - *example*: formulateAntiderivative
- **Get/Set Methods**
  - prepend with "get" and "set", respectively (ex: getNumberOfPlayers)


### Layout ID Values ###
- prepend with general usage location
- append with tag type
- *examples*: hotSeatMoveResetButton, settingsSoundFxSeekBar


#### Other Notes ####
- treat acronyms as words (ex: xmlHttpRequest)
- use full words (bad: "num", "btn"; good: "number", "button")
