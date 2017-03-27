Scenario: Once upon the time really sleepy student oversleep to work 3 times. Now he need a great alarm clock to not oversleep again. But be careful the alarm clock rings only once.

Given A sleepy student sets alarm at 07:30 to wake up to school
When It would be 07:30 when alarm start to ring
Then The sleepy student is awake is true

When It would be 07:30 when alarm start to ring
Then The sleepy student is awake is false

When It would be 07:20 when alarm start to ring
Then The sleepy student is awake is false

When It would be 07:30 when alarm start to ring
Then The sleepy student is awake is true