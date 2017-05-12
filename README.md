# TP_Lanches

Técnicas de Programação
Exercício sobre padrões arquiteturais para camada de negócios
Enunciado:


Um sistema de vendas de lanches de uma famosa cadeia de lancherias precisa registrar a venda de lanches e emitir
uma nota fiscal simplificada.
Para registrar a venda de um produto é necessário conhecer o código e a quantidade do produto sendo vendido.
A nota fiscal simplificada possui uma listagem contendo cada produto (com seu nome, quantidade, preço unitário
e subtotal), bem como o valor total da venda efetuada.
O cálculo do valor total de venda pode sofrer uma aplicação de desconto caso o comprador seja estudante ou
idoso. Nesses casos o desconto é de 10% para estudantes e 15% para idosos. Tal informação deve aparecer na
nota fiscal, com o valor total e o valor final com o desconto aplicado.
Importante: utilize a classe FonteDeDados que implementa uma coleção em memória (código-fonte auto
documentado disponível no Moodle) como fachada de acesso a camada de persistência de dados.
Exercícios:


1) Apresente um diagrama de classes que modele a camada de negócio como um padrão arquitetural
   “Transaction Script”.
2) Após, implemente o código da camada em Java.
3) Implemente casos de teste básicos em JUnit para a camada de negócio.
4) Apresente um diagrama de classes que modele a camada de negócio como um padrão “Domain Model”.
5) Após, implemente o código da camada em Java.
6) Implemente casos de teste básicos em JUnit para a camada de negócio.
