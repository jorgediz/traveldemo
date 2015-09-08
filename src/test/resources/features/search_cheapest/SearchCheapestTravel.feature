Feature: Lookup a definition
    In order to plan my travel
    As a prospective traveler
    I want to see a list of travel options sorted by price


    Scenario: Need both origin and destination to search
        Given i am on the home page
        When i submit the search
        Then i should see errors


    Scenario: Travel by train from Berlin to Prague
        Given i am on the home page
        And i choose 'en' as language
        And i choose 'EUR' as currency
        And i choose from 'Berlin, Germany' to 'Prague, Czech Republic' 
        When i submit the search
        Then i should be on the results page
#       And i see the travel options by 'train'
#        And travel options should be sorted by price

