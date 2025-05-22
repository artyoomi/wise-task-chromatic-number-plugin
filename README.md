## Description
Plugin for the [wise-task](https://github.com/Terross/wise-task-library) graph
system, created as part of the colloquium on the discipline "Combinatorics
and Graph Theory" at ETU "LETI". Adds an algorithm to the system that
finds the chromatic number of a graph. The implementation is based on the
algorithm described in the [book](https://docviewer.yandex.ru/view/1059915284/?*=r5gluBrjYoMDycxH5mq0GHfd1GB7InVybCI6InlhLWRpc2stcHVibGljOi8vWmFLL29IK08zQ0FJSWJyRFo0N21jaFpzN1V1VzJnMklmRHZYdGFSRVFENUVhSVp1SnVwYkZIc21RYU9uTUs3eXEvSjZicG1SeU9Kb25UM1ZvWG5EYWc9PSIsInRpdGxlIjoiQ29tcE1hdGgucGRmIiwibm9pZnJhbWUiOmZhbHNlLCJ1aWQiOiIxMDU5OTE1Mjg0IiwidHMiOjE3NDcyNTE0Njk4NDYsInl1IjoiNjEzNjQ0MTUwMTczMDMxMjU5NCJ9).

### Build locally
To build project locally you need to specify ```OWNER_USERNAME``` and ```OWNER_TOKEN```
Gradle properties. Two methods can be used:
- Set in ```gradle.properties``` file in project root directory
  ```file=gradle.properties
  OWNER_USERNAME=username
  OWNER_TOKEN=token
  ```
- Set by the environment variables
  ```sh  
  export ORG_GRADLE_PROJECT_OWNER_USERNAME=username
  export ORG_GRADLE_PROJECT_OWNER_TOKEN=token
  ```
