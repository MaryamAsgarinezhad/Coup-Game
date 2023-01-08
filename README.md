# Coup-Game
In this exercise, we have implemented a the coup game program with a graphical user interface in which the user plays against 3 other players who are robots.
We have also added a new action to make the game more interesting:

Exchange:

Each player in his turn, in addition to the actions in the main game, must be able to exchange one of his cards by paying a coin to the treasury.
This card swap does not need to affect any cards and cannot be challenged or countered. When the player decides to take this action and pays a coin, he adds one of his cards of his choice to the pile of cards on the floor without showing it to the other players, and then one card from the pile of cards on the floor to The accident is selected and placed in this player's hand.


Robots:
We have designed 4 robots from which three can be selected to participate in the game as the opponent player. Three of the bots should behave as described below:

Coup player:
In his turn, if he has less than seven coins, he claims that he has a big card and takes three coins, if he has seven coins or more, he coups against one of the other players who still has a card in his hand.

Paranoid:
In situations where it is possible to challenge one of the opponent's players, he challenges one of the players who claimed that position.

Cautious Assassin:
This player looks to attack but refuses to bluff. If he does not have a killer card, his game strategy is as follows: if he has an ambassador, he decides to change the card in each turn, and if he finds a killer in the cards that come from the deck of cards, he replaces it with an ambassador and in Otherwise, it will not change. If the ambassador doesn't have one, he will ask for a change if he has one coin, and if he doesn't have one, he will receive two coins.

We have arbitrarily determined the strategy of the fourth robot and the same strategy of the three robots mentioned in the situations that are not mentioned.

In the graphical user interface, the following information must be visible:

1. Game guide card

2. The user's cards and the number of his coins

3. The number of cards and the number of coins of other players

4. Other players' face-up cards

5. General game event logger (in which we record actions and countermeasures and challenges of the game)


EXAMPLE:
A->B: Coup
B->A: Assassinate
A->B: Blocks Assassination
C->A: Challenge 
A->KILL: Duke
C->DECK: Ambassador
D->BANK: Foreign Aid
...

In this example, player A has attempted a coup against player B. Later, player B attempted to assassinate player A. But player A has countermeasured and claimed to have a king. But player C feels that A is bluffing and insists on scandalizing him and challenges him, and because A bluffed and lost the challenge, he breaks his high card. Now it is the turn of player C. He claims to have an ambassador and makes the exchange.

As a final note about the user interface, note that the user may or may not react (for example, challenge, bluff) to each opponent's move, and this non-reaction has a button.

Settings file:
The following properties can be changed by modifying a json file in the application:

-Which bots to play as players 2, 3 and 4 respectively
-Cards in players' hands at the beginning of the game
