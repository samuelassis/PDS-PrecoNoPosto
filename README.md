# Preço No Posto
Repositório para projeto prático da disciplina de Praticas de Desenvolvimento de Software -  UFMG
## Integrantes
 - Cecília Roberta Pereira do Nascimento - 2016000567
 - Emerson Gouveia de Faria - 2018111900
 - Fábio Silva Brum - 2018123674
 - Samuel Assis Vieira - 2018109736
# Sprint 1
 ### Funções
  - Desenvolvedores: Emerson e Fábio
  - Design: Cecilia
  - Banco de Dados: Samuel
## Descrição
Preço no Posto é um aplicativo que funciona como um radar de preços de combustiveis, para que o cliente possa encontrar os melhores preços de combustiveis e cadastrar boas ofertas quando as encontrar. Além de poder avaliar o posto e seus serviços. Tudo em um só lugar.
### MVP
Como o tipo de negócio já é consolidado, Google Maps e ComOferta trazem funcionalidades parecidas. Como MVP uma possibilidade seria fazer o aplicativo com um escopo mais limitado, uma região da cidade, por exemplo. A ideia seria fazer uma espécie de mágico de Oz, onde podemos manualmente pegar alguns preços atualizados e levantar os serviços de alguns postos da região, esse levantamento pode ser feito numa planilha simples que seria usada para alimentar o app. O levantamento do preço pode ser feito mais facilmente pelo site Mercado Mineiro. O aplicativo teria funcionlidades limitadas de avaliação para que pudessemos entender a demanda e avaliar a viabilidade.
## Tecnologias
 - Kotlin
 - MySQL
## Protótipo (Telas)
https://www.figma.com/file/gTJ67Z6kA0It9GrKbPdkqQ/Pre%C3%A7o-no-Posto---PROD?node-id=0%3A1
# Sprint 2

## Arquitetura Hexagonal

### Por que estamos adotando essa arquitetura?

### Portas e adaptadores

#### Portas
O sistema conta com 7 portas, sendo 4 de entrada e 3 de saída. 
As portas de entrada são:
- UserAccess: Interface para atividades relacionadas a controle de acesso do usuário (Login/SignUp).
- GasStationRating: Interface para salvar avaliações dos postos feitas pelos usuários.
- GasStationFilters: Interface para serviços de filtragem dos postos disponíveis.
- GasStationDetails: Interface para buscar e calcular as avaliações dos usuários.

As portas de saída são:
- UserRepository: Interface responsável por comunicar com a tabela de usuários.
- RatingRepository: Interface responsável por comunicar com a tabela de avaliações.
- GasStationRepository: Interface responsável por comunicar com a tabela de postos.

#### Adaptadores
O sistema, tabém conta também com 7 adaptadores.
Os adaptadores de entrada são:
- UsarAccesImpl: Implementa a interface de UserAcess utilizando uma implementação da porta de saída UserRepository.
- GasStationRatingImpl: Implementa a interface de GasStationRating utilizando uma implementação da porta de saída RatingRepository.
- GasStationFiltersImpl: Implementa a interface de GasStationFilters utilizando uma implementação da porta de saída GasStationRepository e uma implementação para a porta de saída UserRepository.
- GasStationDetailsImpl: Implementa a interface de GasStationDetails utilizando uma implementação da porta de saída GasStationRepository.

Os adaptadores de saída são:
- UserDao: Implementa a porta de saída UserRepository, comunicando-se com o banco de dados através da biblioteca Room.
- RatingDao: Implementa a porta de saída RatingRepository, comunicando-se com o banco de dados através da biblioteca Room.
- GasStationDao: Implementa a porta de saída GasStationRepository, comunicando-se com o banco de dados através da biblioteca Room.
