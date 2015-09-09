Feature: Search travel options
    In order to plan my travel
    As a prospective traveler
    I want to see a list of travel options sorted by price


    Scenario: Travel from Berlin to Prague. default options
        Given i am on the home page
        And i prefer 'en' as language
        And i want to go from 'Berlin, Germany' to 'Prague, Czech Republic'
        When i search
        Then i am on the results page
        And i see travel options sorted by price


    Scenario Outline: Travel from and to different cities
        Given i am on the home page
        And i prefer 'en' as language
        And i want to go from '<fromcity>' to '<tocity>' 
        When i search
        Then i am on the results page
        And i see travel options sorted by price
    Examples:
        | fromcity              | tocity                    |
        | Berlin, Germany       | Prague, Czech Republic    |
        | Madrid, Spain         | Paris, France             |


    Scenario Outline: Search in different languages
        Given i am on the home page
        And i prefer '<lang>' as language
        And i want to go from '<fromcity>' to '<tocity>' 
        When i search
        Then i am on the results page
        And i see travel options sorted by price
    Examples:
        | lang  | fromcity              | tocity                    |
        | es    | Berlín, Alemania      | Praga, República Checa    |
        | de    | Berlin, Deutschland   | Prag, Tschechien          |


    Scenario Outline: Travel by different transports
        Given i am on the home page'Berlin, Germany' to 'Prague, Czech Republic'
        And i prefer 'en' as language
        And i want to go from 'Berlin, Germany' to 'Prague, Czech Republic' 
        When i search
        Then i am on the results page
        And i travel by '<transport>'
        And i see travel options sorted by price
    Examples:
        | transport |
        | train     |
        | flight    |
        | bus       |
 

    Scenario Outline: Using different currencies
        Given i am on the home page
        And i prefer 'en' as language
        And i prefer '<currency>' as currency
        And i want to go from 'Berlin, Germany' to 'Prague, Czech Republic'
        When i search
        Then i am on the results page
        And i see travel options sorted by price
    Examples:
        | currency  |
        | EUR       |
        | CHF       |


    Scenario: Round trip
        Given i am on the home page
        And i prefer 'en' as language
        And i go round trip
        And i want to go from 'Berlin, Germany' to 'Prague, Czech Republic'
        When i search
        Then i am on the results page
        And i see travel options sorted by price

