
Feature: Blog Search

Scenario: Buscar artigo
  Given que acesso o blog do Agi
  When realizo a busca por "pix"
  Then devo visualizar resultados relevantes
