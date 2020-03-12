# My Finance

LOGIN COMMAND<br/>
SIGN_IN, admin, 1234<br/>
SIGN_UP, MrRobot, 9999, 9999<br/>
<br/>
USER COMMAND<br/>
<br/>
MY_ACCOUNTS#ID=MrRobot<br/>
CREATE_ACCOUNT#ID=MrXXX&BALANCE=1204.21&NAME=BelCard<br/>
SELECT_ACCOUNT#ID=MrRobot@1583824237692<br/>
DELETE_ACCOUNT#ID=MrXXX@1583998860224<br/>
<br/>
MY_EXPENSES#ID=MrRobot@1583824237692<br/>
CREATE_EXPENSE#ID=MrXXX@1583996205058&AMOUNT=800&TYPE=INCOME<br/>
SELECT_EXPENSE#ID=MrXXX@1583996205058@1584000954826<br/>
DELETE_EXPENSE#ID=MrXXX@1583996205058@1583996205058<br/>

# EXAMPLE
Enter command: SIGN_IN<br/>
Enter your login: MrXXX<br/>
Enter your password: <br/>
You are identified<br/>
role=USER<br/>
Enter command with arguments: MY_EXPENSES#ID=MrRobot@1583824237692<br/>
|ID                                           |Amount         |Type      |<br/>
|MrRobot@1583824237692@1583924900106          |25.00          |COMMUNAL  |<br/>
|MrRobot@1583824237692@1583926330227          |600.00         |INCOME    |<br/>
|MrRobot@1583824237692@1583926358884          |30.00          |CAR       |<br/>
|MrRobot@1583824237692@1583926381225          |56.23          |FOOD      |<br/>
|MrRobot@1583824237692@1583926414516          |120.20         |PERSONAL  |<br/>
<br/>
Enter command with arguments: MY_ACCOUNTS#ID=MrXXX<br/>
|ID                            |Name                |Balance        |Records   |<br/>
|MrXXX@1583996205058           |MyFirstAccount      |655.00         |2         |<br/>
|MrXXX@1583998848244           |BelCard             |1204.21        |1         |<br/>
|MrXXX@1583999791975           |VISA                |204.56         |1         |<br/>
<br/>
