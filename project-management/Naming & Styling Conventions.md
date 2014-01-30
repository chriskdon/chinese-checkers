# Naming & Styling Conventions #

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


### Comments ###
- create using Javadoc/Javadoc standards
- *crash course*: [Wikipedia article](http://en.wikipedia.org/wiki/Javadoc#Overview_of_Javadoc)
- *example*: [code](http://www.docjar.net/html/api/java/util/Collections.java.html) (focus on the styling of the comments)


### Layout ID Values ###
- prepend with general usage location
- append with tag type
- *examples*: hotSeatMoveResetButton, settingsSoundFxSeekBar

### Colours ###
- hex rgba values (android color resource must be hex)

#### Other Notes ####
- treat acronyms as words (ex: xmlHttpRequest)
- use full words (bad: "num", "btn"; good: "number", "button")
