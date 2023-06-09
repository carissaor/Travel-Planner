# TRIP PLANNER

> What will the application do?

The application will enable users to add destinations they wish to visit in the future.
Under each destination, there will be a budget, a wishlist and an itinerary. 
The wishlist will include information researched by the user including cost and information for 
food, activities and accommodation etc. User will be able to add that information to 
the itinerary as long as the total cost stays within the budget. 

> Who will use it?

Anyone who loves to travel can use the application.
With the cost of plane ticket, hotels, and all other expenses associated with international travel, 
it would be helpful for users to keep track of their estimated expense and plan their trips accordingly. 

> Why is this project of interest to you?   

As we enter the post-pandemic era, many countries lift their travel restrictions,
it is more possible for us to travel compared to 2-3 years ago. As someone who loves to travel,
I often find it challenging to plan my itinerary according to my budget and duration of stay because
I am often overwhelmed by the amount of information about a place found on the internet. 
Therefore, a trip planner sounds like an interesting and useful tool to me. 

## User Stories

- As a user, I want to be able to add and remove destination to destination list.
- As a user, I want to be able to categorize my findings in wishlist. 
- As a user, I want to be able to keep track of my budget.
- As a user, I want to be able to add and remove the itinerary of each day.
- As a user, I want to be able to save my destination list to file. (if I so choose)
- As a user, I want to be able to load my destination list from file. (if I so choose)

## Instructions for Grader
- You can generate the first required action related to adding Xs to a Y by clicking the button with text "Add" to add
new destinations to the existing list of destinations
- You can generate the second required action related to adding Xs to a Y by clicking the button with text "Remove" to 
remove the chosen destination from the existing list of destinations
- You can locate my visual component in the splash screen when the application starts.
- You can save the state of my application by clicking yes in a popup window that shows before quitting the application.
- You can reload the state of my application by clicking yes in a popup window when starting the application.

## Phase 4: Task 2
Sun Apr 09 14:21:54 PDT 2023

add place1 to destination list.

Sun Apr 09 14:21:58 PDT 2023

1 day is added to duration

Sun Apr 09 14:21:58 PDT 2023

$ 1 is added to budget

Sun Apr 09 14:21:59 PDT 2023

1 day is removed from duration

Sun Apr 09 14:22:01 PDT 2023

remove place1 from destination list.

## Phase 4: Task 3
Singleton pattern could be used for the DestinationList class. 
This is because it makes more sense if there is only 1 destination list to save all the destinations and
since several classes has association with the DestinationList class, it is important to 
ensure all classes are referring to the same DestinationList.

Secondly, instead of setting type of DestinationList as an array list,
hashMap could be used.
This enables faster and easier retrieval of destination,
the order of destinations does not matter and
it makes more sense if no duplicate of destination is allowed.
So the key could be the name of the place and value could be the Destination type.

