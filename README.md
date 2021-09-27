
## Package Structure ##

- common -> this will contain all stuff the other tree layers have in common

- data -> this will contain the classes corresponding to the data
  - repository - this will only contain the implementation, because, the actually implementation also contain the direct access to our data 
  - remote
    - dto - this is Data Transfer Object, is basically the object the api return

- di -> this will contain the dependency injection code. It maket easy replace any depency, for example when you test somenthing

- presentation -> this will contain all the ui classes
    - coin_detail -> this will contain code related to a details of a coin
      - components -> this will contain all the composables 
    - coin_list -> this will contain code responsible to list all the coins in the API
      - components -> this will contain all the composables
    - ui

- domain -> this will contain 
  - models - this will only contain the data shown in our UI
  - repository - this package in the domain layer will only define our repositories. The reason we have this here at all, is, that is very helpful for the test cases
  - use_cases - the use cases should only have one public function, and that is the function to execute that use case
    - get_coins -> this will be the use case to get coins
    - get_coin -> this will be the use case to get single coin, coin details

### ViewModels
  * viewModels are reasonable for maintenance our state, we use then because they keep the state when the configuration change happn, for example a screen rotation. 