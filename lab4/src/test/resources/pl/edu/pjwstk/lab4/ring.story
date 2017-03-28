Scenario: Really sleepy student oversleep to school 3 times. The alarm clock can ring only once.

Given A sleepy student sets alarm at 07:30 to wake up to school
When It would be 07:30 when alarm start to ring
Then The sleepy student is awake is true

When It would be 07:30 when alarm start to ring
Then The sleepy student is awake is false

When It would be 07:20 when alarm start to ring
Then The sleepy student is awake is false

When It would be 07:30 when alarm start to ring
Then The sleepy student is awake is true