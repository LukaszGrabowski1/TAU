Scenario:  User want to hack site, and access site avaiable only for logged user.
Given user is on myLogin page
When user change url to access LoggedPage
Then the fact that user can access this site is false

Scenario:  User enter wrong login and password.
Given user is on myLogin page
When user clicks the login input field and enter 14352523
When user clicks the password input field and enter 1234891234
Then user click login button and the fact that he is logged is false

Scenario:  User enter correct login and wrong password.
Given user is on myLogin page
When user clicks the login input field and enter 1234
When user clicks the password input field and enter 9126059712360
Then user click login button and the fact that he is logged is false

Scenario:  User enter wrong login and correct password.
Given user is on myLogin page
When user clicks the login input field and enter 1837645918234
When user clicks the password input field and enter 9126059712360
Then user click login button and the fact that he is logged is false

Scenario:  User enter correct login and password.
Given user is on myLogin page
When user clicks the login input field and enter 1234
When user clicks the password input field and enter 5678
Then user click login button and the fact that he is logged is true

Scenario: User is logged and want to logout.
Given user is on myLogin page
When user click Wyloguj link user will be logged out
Then User is logged out, and redirected to home page with loggin form - the fact is true