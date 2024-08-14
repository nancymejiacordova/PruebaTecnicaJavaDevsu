Feature: Testing Clients REST API

Scenario: Testing valid GET endpoint
  Given url 'http://localhost:8095/clientes'
  When method GET
  Then status 200
