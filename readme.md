### Input

Data is hardcoded

### Algorithm
- randomly creates new population with `POPULATION_SIZE (9)`
- selects the population with less conflicts
- while fitness isn't 1.0 evolving the population
  - crossover the population
    - creates new random population
      - leaves first `NUMB_OF_ELITE_SCHEDULES (1)` schedules from initial one
      - for `90% (CROSSOVER_RATE)` of other items 
        - creates 2 tournament populations
          - creates new population with `TOURNAMENT_SELECTION_SIZE (3)` schedules
          - set schedules randomly from initial population
        - selects 2 best schedules from each created population
        - crossover them
          - 50% of schedules from each tournament population
      - for other 10% leaves schedules from initial one
  - mutates crossovered population
    - creates new random population with crossovered population size
    - leaves first `NUMB_OF_ELITE_SCHEDULES (1)` schedules from initial one
    - mutates other schedules
      - creates new random schedule
      - for `10% (MUTATION_RATE)` set classes from initial schedule

### Output
At the end logs schedule with 1.0 fitness'


### TODO
- remove dept
- multiple groups schedule
- optimize writes at instance creation which will be overwritten
- sort output by meeting time/group