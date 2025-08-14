Feature: Customer API tests

  Background:
    # Base URL
    * url 'http://localhost:5002/clientes'

  Scenario: Crear un cliente exitosamente
    Given request
      """
      {
        "personName": "Karate Test",
        "genderId": 1,
        "age": 25,
        "identificationTypeId": 1,
        "identificationNumber": "1234567896",
        "address": "Calle Test 123",
        "phone": "0999000000",
        "passwordHash": "hashTest123",
        "customerStatusId": 1
      }
      """
    When method post
    Then status 201
    And match response.personName == 'Karate Test'
    And match response.identificationNumber == '1234567896'

