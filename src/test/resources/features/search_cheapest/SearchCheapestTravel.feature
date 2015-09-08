Feature: Search travel options
    In order to plan my travel
    As a prospective traveler
    I want to see a list of travel options sorted by price


    Scenario: Need both origin and destination to search
        Given i am on the home page
        When i search
        Then i see errors


    Scenario: Travel by train from Berlin to Prague
        Given i am on the home page
#       And i prefer 'en' as language
#       And i prefer 'EUR' as currency
        And i want to go from 'Berlin, Germany' to 'Prague, Czech Republic' 
        When i search
        Then i am on the results page
        And i see travel options sorted by price

