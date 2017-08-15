exercise_040_we_are_here

## Exercise 040 > We Are Here!

### What if I want to let others know about guests who want to be greeted in a special way? 

* There could be other people interested in knowing that a "picky" or "particular" person
has entered the lagom greeting lounge. We need to be able to make sure that people can consume
that information without 
    1) depending on our service to be alive to get that info
    2) being knowledgeable about other services and depending on them to
    be alive to receive that info. 
* We achieve this by using an intermediary device to propagate messages to interested
parties. You may recognize this as "pub-sub"
* Lagom uses Kafka for this mechanism by default. We will publish these "GreetingEvents" to 
a Kafka topic
* Tasks
    1) Update the API to show we will be producing a stream of events to a topic
    2) Update the Impl to publish those events
    
*Note: pay attention to the ACL that's used at this point*

