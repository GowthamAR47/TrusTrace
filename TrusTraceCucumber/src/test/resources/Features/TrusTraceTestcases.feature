Feature: TrusTrace QA coding assesment.
@UITC
  Scenario Outline: Add the product and checkout
    
    Given user launch the <url> in desired <browser>
    And user login with <username> and <password>
    When user add the product to cart
    And user provides the <firstname>,<lastname>,<zip> and checkout
    Then user should verify the success message

    Examples: 
      |url                      |browser|username     |password    |firstname|lastname |zip   |
      |https://www.saucedemo.com|chrome |standard_user|secret_sauce|Gowtham  |A R      |642001|
      |https://www.saucedemo.com|edge   |standard_user|secret_sauce|Trus     |Trace    |642500|
      |https://www.saucedemo.com|firefox|standard_user|secret_sauce|coding   |assesment|123456|
      
      
@APITC
  Scenario Outline: Check the API in Post method
  
  	Given post the API <url>
  	When provide the <title>,<body>,<userid>
  	Then check the status code <statuscode>
  	And contains the response with <title>,<body>,<userid>
  	
  	Examples:
  	|url                                       |title|body|userid|statuscode|
  	|https://jsonplaceholder.typicode.com/posts|foo  |boo |1     |201       |
  	|https://jsonplaceholder.typicode.com/posts|Rest |Api |2     |201       |
  	|https://jsonplaceholder.typicode.com/posts|demo |one |10    |201       |