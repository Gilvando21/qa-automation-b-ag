Feature: Blog Search

  @smoke @web @search @positive
  Scenario Outline: Deve retornar resultados para termos válidos
    Given que acesso o blog do Agi
    When realizo a busca por "<termo>"
    Then devo visualizar resultados relevantes

    Examples:
      | termo |
      | pix   |
      | p     |

  @web @search @negative
  Scenario Outline: Não deve retornar resultados para termos inexistentes
    Given que acesso o blog do Agi
    When realizo a busca por "<termo>"
    Then devo visualizar mensagem de nenhum resultado

    Examples:
      | termo             |
      | xyz123inexistente |

  @web @search @edge
  Scenario Outline: Deve tratar corretamente buscas inválidas ou vazias
    Given que acesso o blog do Agi
    When realizo a busca por "<termo>"
    Then devo permanecer na página inicial

    Examples:
      | termo |
      |       |