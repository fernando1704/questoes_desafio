Resposta às questões teóricas

4) O que é Deadlock? Detalhe um pouco sobre o caso e como você poderia resolver isso. 

Deadlock ocorre quando processos ou threads aguardam dados de outros processos ou threads. Exemplo: O processo 1 aguarda dados do processo 2. Ao mesmo tempo, o processo 2 aguarda dados do 1. Isso gera a situação de deadlock, que significa que os processos ficariam 'travados' aguardando dados um do outro.
Isso pode ocorrer entre 2 ou mais processos, ou entre as threads de um mesmo processo. É uma situação que deve ser evitada ao máximo.
Existem algumas formas de evitar os problemas com Deadlocks, uma delas é simplesmente ignorar o problema, como é feito as vezes em alguns sistemas operacionais. Existe também a possibilidade de localizar um deadlock e tentar se recuperar dele, por dois meios

- Preempção: Retira temporariamente o recurso de um determinado processo e entrega a outro
- Revisão de estado: Os processos voltam a um determinado estado, antes do Deadlock.

5) Uma das grandes inclusões no Java 8 foi a API Stream. Com ela podemos fazer diversas operações de loop, filtros, maps, etc. Porém, existe uma variação bem interessante do Stream que é ParallelStreams. Descreva com suas palavras quando qual é a diferença entre os dois e quando devemos utilizar cada um deles

A principal diferença entre a API Stream e ParallelStream é que a segunda utiliza de paralelismo para realizar as operações, dividindo o problema em múltiplos pequenos problemas, possibilitando que sejam executados paralelamente em diferentes cores do processador. 
Apesar das vantagens da ParallelStream, nem sempre é melhor utilizá-la, visto que o custo com a coordenação das diferentes execuções podem comprometer o desempenho da aplicação.
Ela poderia ser utilizada em casos onde existem realmente muitas informações e que são possíveis de se paralelizar, obtendo vantagem sobre o modelo sequencial.
